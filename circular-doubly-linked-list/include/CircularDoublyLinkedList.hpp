#pragma once

#include "Node.hpp"
#include "ArrayList.hpp"

class CircularDoublyLinkedList {
public:
	CircularDoublyLinkedList(ArrayList* cdll_numbers, int);
	~CircularDoublyLinkedList();
	const Node* GetMidItemAddress() const;
	int GetCDLLLength() const;
	void MakeCDLLFromArrayList();
	static void SwapFrontAndBackOfTwoCDLL( CircularDoublyLinkedList* biggest_cdll,
										   CircularDoublyLinkedList* smallest_cdll );
	void Print();
	
private:
	int cdll_length_;
	Node* middle_;
	ArrayList* content_of_list_;
};
