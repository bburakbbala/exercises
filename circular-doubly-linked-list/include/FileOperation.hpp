#pragma once

#include <string>
#include <stdexcept>
#include <fstream>
#include <sstream>
#include "ArrayList.hpp"

class FileOperation {
public:
	FileOperation(std::string);
	~FileOperation();
	int GetNumberOfLines() const;
	int GetTheBiggestNumberIndex() const;
	int GetTheSmallestNumberIndex() const;
	void SetArrayListSizeFromFile(ArrayList**);
	void AssignNumbersToArrayList(ArrayList**);
   
private:
	size_t lines_;
	size_t number_of_numbers_in_line_;
	size_t biggest_of_the_first_number_in_lines_index_;
	size_t smallest_of_the_first_number_in_lines_index_;
	std::string line_;
	std::ifstream file_stream_;
	const std::string file_name_;
	ArrayList** number_of_cdll_;
};
