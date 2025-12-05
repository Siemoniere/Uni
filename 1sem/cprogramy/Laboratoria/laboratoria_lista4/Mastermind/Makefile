#Makefile

all: main

main: main.o odpowiedz.o odrzuc.o porownaj.o pytanie.o rozbicie.o sortuj.o
	clang -o main main.o odpowiedz.o odrzuc.o porownaj.o pytanie.o rozbicie.o sortuj.o

main.o: main.c
	clang -c main.c

odpowiedz.o: odpowiedz.c
	clang -c odpowiedz.c

odrzuc.o: odrzuc.c
	clang -c odrzuc.c

porownaj.o: porownaj.c
	clang -c porownaj.c

pytanie.o: pytanie.c
	clang -c pytanie.c

rozbicie.o: rozbicie.c
	clang -c rozbicie.c

sortuj.o: sortuj.c
	clang -c sortuj.c

clean:
	rm -f main *.o*~
