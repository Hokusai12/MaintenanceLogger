#include <iostream>
#include <string>
#include <fstream>
#include <chrono>
#include <sstream>

enum class InputAction
{
	ADD, EDIT, VIEW, DELETE
};

static InputAction getAction(std::string& userInput)
{
	if (userInput == "Add Log")
	{
		return InputAction::ADD;
	}
	else if (userInput == "Edit Log")
	{
		return InputAction::EDIT;
	}
	else if (userInput.compare("View Log") == 0)
	{
		return InputAction::VIEW;
	}
	else if (userInput.compare("Delete Log") == 0)
	{
		return InputAction::DELETE;
	}
}

static void createLog(std::string& item, std::string& maintenance, std::string& notes)
{
	std::fstream logFile(item+".txt", std::ios_base::app);
	if (!logFile.is_open())
	{
		std::cerr << "Failed to open file" << std::endl;
		return;
	}

	//God I hate datetime in c++
	const std::chrono::time_point<std::chrono::system_clock> chronoNow = std::chrono::system_clock::now();
	const std::time_t stdNow = std::chrono::system_clock::to_time_t(chronoNow);
	std::tm timeInfo;
	localtime_s(&timeInfo, &stdNow);
	std::stringstream timeOutput;
	timeOutput << timeInfo.tm_mon + 1 << "/" << timeInfo.tm_mday << "/" << 1900 + timeInfo.tm_year;

	logFile << timeOutput.str() << std::endl;
	logFile << maintenance << std::endl;
	logFile << notes << "\n\n\n";
}

static void addLog()
{
	std::string itemName = "";
	std::string maintenancePerformed = "";
	std::string notes = "";

	std::cout << "What item is this for?" << std::endl;
	std::getline(std::cin, itemName);
	std::cout << "What maintenance was performed?" << std::endl;
	std::getline(std::cin, maintenancePerformed);
	std::cout << "Any Notes?" << std::endl;
	std::getline(std::cin, notes);

	createLog(itemName, maintenancePerformed, notes);
}

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
	while (true)
	{
		std::cout << "What would you like to do?\n[Add Log, Edit Log, View Log, Delete Log]: ";
		std::getline(std::cin, input);
		currentAction = getAction(input);
		if (currentAction == InputAction::ADD)
		{
			addLog();
		}
	}
}