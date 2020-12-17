// File: ID_stamp.cpp
// This program receives a description and generates an ID.
// It also outputs the description and ID.

// iostream provides user input and output functionality.
#include <iostream>
// iomanip provides output formatting.
#include <iomanip>
// chrono provides time for randomization seed.
#include <chrono>
// string provides string data type and getline.
#include <string>
// cstdlib provides randomization method.
#include <cstdlib>
// limits provides a value used in conjunction with cin.ignore().
#include <limits>
// ID_stamp.h provides class definition for ID_stamp.
#include "ID_stamp.h"

using namespace std;

// Receiving description and generating ID.
void ID_stamp::input() {
	// Clear cin.
	cin.ignore(numeric_limits<streamsize>::max(), '\n');
	// Prompt the user for description string and receive it.
	cout << "Input a description as a string: ";
	string input;
	getline(cin,input);
	// Generate a random ID seeded from current time.
	srand(chrono::steady_clock::now().time_since_epoch().count());
	// Store our random id we generated.
	this->stamp = rand() % 1000000 + 1;
	// Store our description we retrieved from the user.
	this->desc = input;
}

// Outputting description and ID.
void ID_stamp::output() {
	// Display the ID we previously generated and the description.
	cout << "ID: " << setw(10) << this->stamp << ", DESC: " << setw(40) << this->desc << endl;
}
