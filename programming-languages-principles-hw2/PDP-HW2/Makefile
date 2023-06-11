# -*- MakeFile -*-

INCLUDE = -I ./include/
CC = gcc

all: libs compile run

libs:
	$(CC) $(INCLUDE) -o ./lib/cJSON.o -c ./src/cJSON.c
	$(CC) $(INCLUDE) -o ./lib/Error.o -c ./src/Error.c
	$(CC) $(INCLUDE) -o ./lib/CharOperation.o -c ./src/CharOperation.c
	$(CC) $(INCLUDE) -o ./lib/DosyaOku.o -c ./src/DosyaOku.c
	$(CC) $(INCLUDE) -o ./lib/Musteri.o -c ./src/Musteri.c
	$(CC) $(INCLUDE) -o ./lib/Banka.o -c ./src/Banka.c
	$(CC) $(INCLUDE) -o ./lib/Hisse.o -c ./src/Hisse.c
	$(CC) $(INCLUDE) -o ./lib/HisseBul.o -c ./src/HisseBul.c
	$(CC) $(INCLUDE) -o ./lib/PortfoyHisse.o -c ./src/PortfoyHisse.c
	$(CC) $(INCLUDE) -o ./lib/Portfoy.o -c ./src/Portfoy.c
	$(CC) $(INCLUDE) -o ./lib/PortfoyLinkedList.o -c ./src/PortfoyLinkedList.c
	$(CC) $(INCLUDE) -o ./lib/Emir.o -c ./src/Emir.c
	$(CC) $(INCLUDE) -o ./lib/EmirIsle.o -c ./src/EmirIsle.c	
	
compile:
	$(CC) $(INCLUDE) -o ./bin/Test ./src/Test.c ./lib/Error.o ./lib/DosyaOku.o \
	 ./lib/cJSON.o ./lib/Musteri.o ./lib/Banka.o ./lib/Hisse.o ./lib/HisseBul.o \
	 ./lib/PortfoyHisse.o ./lib/Portfoy.o ./lib/PortfoyLinkedList.o \
	 ./lib/Emir.o ./lib/EmirIsle.o ./lib/CharOperation.o

run:
	./bin/Test
