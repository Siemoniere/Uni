#!/bin/bash

convert() {
    size=$1
    if [ "$size" -lt 1024 ]; then
	echo "$size B"
    elif [ "$size" -lt 1048576 ]; then
	echo "$((size/1024)) kB"
    else
	echo "$((size/1048576)) MB"
    fi
}

chart_simplify() {
    size=$1
    if [ "$size" -lt 1024 ]; then
	echo "0"
    elif [ "$size" -lt 10485 ]; then
	echo "1"
    elif [ "$size" -lt 1048576 ]; then
	echo "2"
    else
	echo "3"
    fi
}

previous_receive=$(awk '/wlp0s20f3/ {print $2}' /proc/net/dev)
previous_transmit=$(awk '/wlp0s20f3/ {print $10}' /proc/net/dev)
declare -a transmit_array=(0 0 0 0 0 0 0 0 0 0) receive_array=(0 0 0 0 0 0 0 0 0 0 0)
elements=10

while true; do
    tput reset
    clear
    #tput setab 4
    tput setaf 1
    battery=$(cat /sys/class/power_supply/BAT0/uevent | grep "POWER_SUPPLY_CAPACITY=" | cut -d'=' -f2) 
    echo "Battery: $battery%"

    loadavg=$(awk '{print $1, $2, $3}' /proc/loadavg)
    echo "System load: $loadavg"

    mem_total=$(awk '/MemTotal/ {print $2}' /proc/meminfo)
    mem_available=$(awk '/MemAvailable/ {print $2}' /proc/meminfo)
    mem_used=$((mem_total-mem_available))
    echo "Memory used: $(convert $((mem_used*1024))) / $(convert $((mem_total*1024)))"

    uptime=$(awk '{print int($1)}' /proc/uptime)
    days=$((uptime/86400))
    hours=$(((uptime%86400)/3600))
    minutes=$(((uptime%3600)/60))
    seconds=$((uptime%60))
    echo "Time: ${days}d ${hours}h ${minutes}m ${seconds}s "

    echo "CPU usage per core:"
    core_number=$(nproc)
    for ((i=0; i<core_number; i++)); do
	act_time=$(awk -v i=$i '/cpu'"$i"'/ {sum = $2 + $3 + $4+ $7 + $8 + $9} END {print sum}' /proc/stat)
	total_time=$(awk -v i=$i '/cpu'"$i"'/ {sum = $2+$3+$4+$5+$6+$7+$8+$9+$10+$11} END {print sum}' /proc/stat)
	usage=$(echo "scale=2; 100*$act_time/$total_time" | bc -l)
	freq=$(cat /sys/devices/system/cpu/cpu$i/cpufreq/scaling_cur_freq)
	echo "Core $i: $((freq/1000)) MHz, $usage%"
    done

    current_receive=$(awk '/wlp0s20f3/ {print $2}' /proc/net/dev)
    current_transmit=$(awk '/wlp0s20f3/ {print $10}' /proc/net/dev)
    receive=$((current_receive-previous_receive))
    transmit=$((current_transmit-previous_transmit))
    echo "Actual receive: $(convert "$receive") / Actual transmit: $(convert "$transmit")"


    for ((i=1; i<$elements; i++)); do 
	receive_array[$((i-1))]=${receive_array[$i]}
    done

    for ((i=1; i<$elements; i++)); do
	transmit_array[$((i-1))]=${transmit_array[$i]}
    done
    receive_array[$((elements-1))]=$receive
    transmit_array[$((elements-1))]=$transmit

    echo "Receive chart: "
    for ((height=3; height>0; height--)); do
	for value in "${receive_array[@]}"; do
	    value=$(chart_simplify "$value")
	    if [ "$value" -ge "$height" ]; then 
		printf "█ "
	    else
		printf "  "
	    fi
	done
	echo ""
    done

    average_receive=0
    for ((i=0; i<elements; i++)); do
	average_receive=$((average_receive+receive_array[i]))
    done
    average_receive=$((average_receive/elements))
    echo "Average receive: $(convert "$average_receive")"


    echo "Transmit chart: "
    for ((height=3; height>0; height--)); do
	for value in "${transmit_array[@]}"; do
    	    value=$(chart_simplify "$value")
    	    if [ "$value" -ge "$height" ]; then 
		printf "█ "
    	    else
		printf "  "
    	    fi
	done
    echo ""
    done

    average_transmit=0
    for ((i=0; i<elements; i++)); do
	average_transmit=$((average_transmit+transmit_array[i]))
    done
    average_transmit=$((average_transmit/elements))

    echo "Average transmit: $(convert "$average_transmit")"

    previous_receive=$current_receive
    previous_transmit=$current_transmit
    sleep 1
done