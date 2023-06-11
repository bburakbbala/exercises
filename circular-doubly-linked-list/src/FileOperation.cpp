#include "FileOperation.hpp"
#include "Error.hpp"

FileOperation::FileOperation(std::string file_name) : file_name_(file_name) {
	file_stream_.open(file_name_);
	try {
		if (!file_stream_.is_open()) {
			throw std::runtime_error("Error: file not found or could not be opened");
		}
	} catch(std::runtime_error& e) {
		std::cerr << e.what() << std::endl;
		std::abort();
	}
	// it sets how many lines in file (it doesn't count empty lines)
	for (lines_ = 0; std::getline(file_stream_, line_) && line_.length() != 0; ++lines_);
	file_stream_.close();
	number_of_numbers_in_line_ = 0;
}
   
FileOperation::~FileOperation() {
	file_stream_.close();
	// delete created ArrayList
	for (int i = 0; i < GetNumberOfLines(); ++i)
		delete number_of_cdll_[i];
}
   
int FileOperation::GetNumberOfLines() const {
	return lines_;
}
   
void FileOperation::SetArrayListSizeFromFile(ArrayList* number_of_cdll[]) {
	file_stream_.open(file_name_);
	int numbers;
	// input numbers in number_of_cdll as an array that contains numbers in line
	// while loop reads file to the  end of file
	// sets how many numbers there are in each
	// line then create ArrayList with that size
	size_t current_line{ };
	while (file_stream_.good()) {
		if (std::getline(file_stream_, line_) && line_.length() != 0) {
			std::istringstream iss(line_);
			while (iss >> numbers) {
				++number_of_numbers_in_line_;
			}
			// at least 3 number in line exception
			// and number_of_numbers_in_line should be odd number
			if (number_of_numbers_in_line_ < 3) {
				throw Error("Error: Input file must contain at least three numbers in each line.");
				std::abort();
			}
			if (number_of_numbers_in_line_ % 2 == 0) {
				throw Error("Error: In input file, number of numbers in each row must be odd in each line.");
				std::abort();
			}
					  
			// line size if not zero then set ArrayList size with number_of_numbers_in_line
			if (number_of_numbers_in_line_ != 0) {
				number_of_cdll[current_line] = new ArrayList(number_of_numbers_in_line_);
				++current_line;
			}
			number_of_numbers_in_line_ = 0;
		}
	}
	number_of_cdll_ = number_of_cdll;
	file_stream_.close();
}
    
void FileOperation::AssignNumbersToArrayList(ArrayList* numbers_of_cdll[]) {
	// assign each line numbers into ArrayList
	file_stream_.open(file_name_);
	size_t current_line{ };
	int numbers{ };
	int biggest_of_the_first_number_in_lines{ };
	int smallest_of_the_first_number_in_lines{ };
	number_of_numbers_in_line_ = 0;
	while (file_stream_.good()) {
		if (std::getline(file_stream_, line_) && line_.length() != 0) {
			std::istringstream iss(line_);
			while (iss >> numbers) {
				++number_of_numbers_in_line_;
				numbers_of_cdll[current_line]->InsertAtTail(numbers);
			}
			// if not zero then set array of numbers in line size
			if (number_of_numbers_in_line_ != 0) {
				// find biggest and smallest number index in first element in each lines
				if (numbers_of_cdll[current_line]->ReadAt(0) > biggest_of_the_first_number_in_lines) {
					biggest_of_the_first_number_in_lines = numbers_of_cdll[current_line]->ReadAt(0);
					biggest_of_the_first_number_in_lines_index_ = current_line;
				}
				if (current_line != 0) {
					if (numbers_of_cdll[current_line]->ReadAt(0) < smallest_of_the_first_number_in_lines) {
						smallest_of_the_first_number_in_lines = numbers_of_cdll[current_line]->ReadAt(0);
						smallest_of_the_first_number_in_lines_index_ = current_line;
					}
				} else {
				    smallest_of_the_first_number_in_lines = numbers_of_cdll[current_line]->ReadAt(0);
					smallest_of_the_first_number_in_lines_index_ = current_line;
				}
				++current_line;
			}
			number_of_numbers_in_line_ = 0;
		}
	}
	file_stream_.close();
}

int FileOperation::GetTheBiggestNumberIndex() const {
	return biggest_of_the_first_number_in_lines_index_;
}

int FileOperation::GetTheSmallestNumberIndex() const {
	return smallest_of_the_first_number_in_lines_index_;
}
