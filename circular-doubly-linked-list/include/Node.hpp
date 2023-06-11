#pragma once

struct Node {
	int data;
	Node* next;
	Node* prev;

	Node(const int& data, Node* next = nullptr, Node* prev = nullptr) {
		this->data = data;
		this->next = next;
		this->prev = prev;
	}
};


