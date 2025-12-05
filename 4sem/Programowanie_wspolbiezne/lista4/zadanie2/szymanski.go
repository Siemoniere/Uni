package main

import (
	"fmt"
	"math/rand"
	"sync"
	"sync/atomic"
	"time"
)

const (
	NrOfProcesses = 10
	MinSteps      = 10
	MaxSteps      = 15
)

var (
	MinDelay = 10 * time.Millisecond
	MaxDelay = 50 * time.Millisecond

	flags [NrOfProcesses]int32
)

type ProcessState int

const (
	LocalSection ProcessState = iota
	EntryProtocol1
	EntryProtocol2
	EntryProtocol3
	EntryProtocol4
	CriticalSection
	ExitProtocol
)

type Position struct {
	X, Y int
}

type Trace struct {
	TimeStamp time.Duration
	Id        int
	Pos       Position
	Symbol    rune
}

func PrintFlags(sym rune) {
	fmt.Printf("Flags %c: ", sym)
	for i := 0; i < NrOfProcesses; i++ {
		val := atomic.LoadInt32(&flags[i])
		fmt.Printf("%d ", val)
	}
	fmt.Println()
}

func AwaitAll(lo, hi int, allowed []int32) {
	if hi < lo {
		return
	}
	for {
		ok := true
		for j := lo; j <= hi; j++ {
			f := atomic.LoadInt32(&flags[j])
			match := false
			for _, a := range allowed {
				if f == a {
					match = true
					break
				}
			}
			if !match {
				ok = false
				break
			}
		}
		if ok {
			return
		}
	}
}

func AwaitAny(lo, hi int, allowed []int32) {
	for {
		for j := lo; j <= hi; j++ {
			f := atomic.LoadInt32(&flags[j])
			for _, a := range allowed {
				if f == a {
					return
				}
			}
		}
	}
}

func process(id int, symbol rune, seed int64, report chan<- []Trace, wg *sync.WaitGroup) {
	defer wg.Done()

	r := rand.New(rand.NewSource(seed))
	startTime := time.Now()

	nSteps := MinSteps + r.Intn(MaxSteps-MinSteps+1)

	traces := make([]Trace, 0, nSteps+1)

	store := func(state ProcessState) {
		timeStamp := time.Since(startTime)
		traces = append(traces, Trace{
			TimeStamp: timeStamp,
			Id:        id,
			Pos:       Position{X: id, Y: int(state)},
			Symbol:    symbol,
		})
	}

	store(LocalSection)

	for step := 0; step < nSteps; step++ {
		time.Sleep(MinDelay + time.Duration(r.Float64()*float64(MaxDelay-MinDelay)))

		// ENTRY_PROTOCOL start
		atomic.StoreInt32(&flags[id], 1)
		time.Sleep(5 * time.Millisecond) 
		store(EntryProtocol1)
		AwaitAll(0, NrOfProcesses-1, []int32{0, 1, 2})

		atomic.StoreInt32(&flags[id], 3)
		time.Sleep(5 * time.Millisecond)
		store(EntryProtocol2)

		found1 := false
		for j := 0; j < NrOfProcesses; j++ {
			if atomic.LoadInt32(&flags[j]) == 1 {
				found1 = true
				break
			}
		}
		if found1 {
			atomic.StoreInt32(&flags[id], 2)
		time.Sleep(5 * time.Millisecond) 
			store(EntryProtocol3)
			AwaitAny(0, NrOfProcesses-1, []int32{1, 4})
		}

		atomic.StoreInt32(&flags[id], 4)
		time.Sleep(5 * time.Millisecond)
		store(EntryProtocol4)
		AwaitAll(0, id-1, []int32{0, 1})

		// CRITICAL_SECTION
		time.Sleep(5 * time.Millisecond) 
		store(CriticalSection)
		time.Sleep(MinDelay + time.Duration(r.Float64()*float64(MaxDelay-MinDelay)))

		// EXIT_PROTOCOL
		AwaitAll(id+1, NrOfProcesses-1, []int32{0, 1, 4})
		time.Sleep(5 * time.Millisecond) 
		store(ExitProtocol)
		atomic.StoreInt32(&flags[id], 0)
		time.Sleep(5 * time.Millisecond)
		store(LocalSection)
	}

	// Send traces to printer
	report <- traces
}

func main() {
	var wg sync.WaitGroup

	reportChan := make(chan []Trace, NrOfProcesses)

	// Start processes
	for i := 0; i < NrOfProcesses; i++ {
		wg.Add(1)
		go process(i, rune('A'+i), time.Now().UnixNano()+int64(i), reportChan, &wg)
	}

	// Wait for all processes to finish
	wg.Wait()
	close(reportChan)

	// Printer: collect and print traces
	for traces := range reportChan {
		for _, tr := range traces {
			fmt.Printf("%.6f %d %d %d %c\n",
				tr.TimeStamp.Seconds(),
				tr.Id,
				tr.Pos.X,
				tr.Pos.Y,
				tr.Symbol)
		}
	}

	// Print parameters line for display script
	fmt.Printf("-1 %d %d %d ", NrOfProcesses, NrOfProcesses, ExitProtocol+1)
		fmt.Printf("Local_Section;Entry_Protocol_1;Entry_Protocol_2;Entry_Protocol_3;Entry_Protocol_4;Critical_Section;Exit_Protocol")
	fmt.Println("EXTRA_LABEL;")
}

