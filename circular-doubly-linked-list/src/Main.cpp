#include <iomanip>
#include "FileOperation.hpp"
#include "CircularDoublyLinkedList.hpp"

int main() {
	clock_t tStart = clock();
	FileOperation* file = new FileOperation("Numbers.txt");
	ArrayList* list_of_cdll_numbers[file->GetNumberOfLines()];
	file->SetArrayListSizeFromFile(list_of_cdll_numbers);
	file->AssignNumbersToArrayList(list_of_cdll_numbers);

	CircularDoublyLinkedList* cdll[file->GetNumberOfLines()];
	std::cout << "\n\nCircular Doubly Linked Lists:\n";
	for(int i = 0; i < file->GetNumberOfLines(); ++i) {
		cdll[i] = new CircularDoublyLinkedList(list_of_cdll_numbers[i], list_of_cdll_numbers[i]->Length());
		cdll[i]->MakeCDLLFromArrayList();
		cdll[i]->Print();
		std::cout << '\n';
	}
	std::cout << "\n\n";

	std::cout << std::setfill('-') << std::setw(42) << '-' << std::endl;
    std::cout << std::setfill(' ') << std::setw(27) << "Before swap\n";
	std::cout << std::setfill('-') << std::setw(42) << '-' << std::endl;
	std::cout << "The Largest Middle Node Address: "
			  << cdll[file->GetTheBiggestNumberIndex()]->GetMidItemAddress()
			  << std::endl
			  << "The Largest List Values:\n";
	cdll[file->GetTheBiggestNumberIndex()]->Print();
	
	std::cout << "\n\nThe Smallest Middle Node Address: "
			  << cdll[file->GetTheSmallestNumberIndex()]->GetMidItemAddress()
			  << std::endl
			  << "The Smallest List Values:\n";
	cdll[file->GetTheSmallestNumberIndex()]->Print();
	std::cout << std::endl << std::setfill('-') << std::setw(42) << '-' << std::endl;
	std::cout << '\n';

	// biggest<->smallest
	cdll[0]->SwapFrontAndBackOfTwoCDLL( cdll[file->GetTheBiggestNumberIndex()], 
										cdll[file->GetTheSmallestNumberIndex()] );
	std::cout << '\n';
	std::cout << std::setfill('-') << std::setw(42) << '-' << std::endl;
	std::cout << std::setfill(' ') << std::setw(27) << "After swap\n";
	std::cout << std::setfill('-') << std::setw(42) << '-' << std::endl;
	std::cout << "The Largest Middle Node Address: "
			  << cdll[file->GetTheBiggestNumberIndex()]->GetMidItemAddress()
			  << std::endl
			  << "The Largest List Values:\n";
	cdll[file->GetTheBiggestNumberIndex()]->Print();

	std::cout << "\n\nThe Smallest Middle Node Address: "
			  << cdll[file->GetTheSmallestNumberIndex()]->GetMidItemAddress()
			  << std::endl
			  << "The Smallest List Values:\n";
	cdll[file->GetTheSmallestNumberIndex()]->Print();
	std::cout << std::endl << std::setfill('-') << std::setw(42) << '-' << std::endl;

	delete file;
	std::cout << "\n\nTime taken: " << std::setprecision(10)
              << ( (double)(clock() - tStart) / CLOCKS_PER_SEC )
              << "s\n";
}
