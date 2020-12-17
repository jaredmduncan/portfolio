// ID_stamp_driver.cpp
// This program asks the user for how many ids to generate
// and then executes code from ID_stamp.cpp to generate them.

// iostream provides user input and output functionality.
#include <iostream>

// ID_stamp.h contains class definition for ID_stamp.
#include "ID_stamp.h"

using namespace std;

int main() {
	// Create an ID_stamp type object.
	ID_stamp idStampObject;

	// Ask our user for an int.
	cout << "Input amount of times to loop as an integer: ";
	int count;
	cin >> count;

	// Loop as many times as the user told us to.
	for (int i = 0; i < count; i++) {
		// Get description from user.
		idStampObject.input();
		// Display ID number and description.
		idStampObject.output();
	}

	// We are done, exit gracefully.
	cout << "Finished!" << endl;
	return 0;
}
