#pragma once

#include <iostream>

class ArrayList {
public:
	ArrayList(int capacity);
	~ArrayList();
	void InsertAtTail(const int& data);
	int Length() const;
	const int& ReadAt(int index);
	void Print() const;
	
private:
	int* items_;
	int number_of_item_;
	int capacity_;
};
