#include <iostream>
#include <string>

enum class InputAction
{
	ADD, EDIT, VIEW, DELETE
};

/*
	I want this to be completely functional, not OOP
*/

int main()
{
	std::string input;
	InputAction currentAction{};
	/*
		Functionality
		-Takes in Action Input (Add Log, Edit Log, View Log, Delete Log)
		-Outputs Response
	*/
	std::cout << "What would you like to do?\n[Add Log, Edit Log, View Log, Delete Log]: ";
	std::cin >> input;

}