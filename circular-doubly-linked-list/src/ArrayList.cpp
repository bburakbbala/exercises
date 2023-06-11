#include "ArrayList.hpp"
#include "Error.hpp"

ArrayList::ArrayList(int capacity) {
	capacity_ = capacity;
	items_ = new int[capacity_];
	number_of_item_ = 0;
}
    
ArrayList::~ArrayList() {
	delete[] items_;
}

void ArrayList::InsertAtTail(const int& data) {
	// check capacity
	if (number_of_item_ == capacity_) {
		throw Error("Error: capacity is full");
		std::abort();
	}
	items_[number_of_item_] = data;
	++number_of_item_;
}

int ArrayList::Length() const {
	return number_of_item_;
}


// indexing works like array indexing
// after ReadAt you cannot assign to it
const int& ArrayList::ReadAt(int index) {
	if (index < 0 || index >= number_of_item_) {
		throw Error("Error: invalid index");
	}
	return items_[index];
}
    
void ArrayList::Print() const {
	std::cout << "\nList items\n";
	for (int i = 0; i < number_of_item_; ++i) {
		std::cout << items_[i] << " ";
	}
	std::cout << '\n';
}
