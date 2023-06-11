All: Libs Compile Execute
Libs:
	g++ -I ./include/ -o ./lib/Error.o -c ./src/Error.cpp
	g++ -I ./include/ -o ./lib/ArrayList.o -c ./src/ArrayList.cpp
	g++ -I ./include/ -o ./lib/FileOperation.o -c ./src/FileOperation.cpp
	g++ -I ./include/ -o ./lib/CircularDoublyLinkedList.o -c \
	./src/CircularDoublyLinkedList.cpp
Compile:	
	g++ -I ./include/ -o ./bin/Main ./src/Main.cpp \
	./lib/Error.o ./lib/ArrayList.o ./lib/FileOperation.o \
	./lib/CircularDoublyLinkedList.o
Execute:
	./bin/Main
