#include <iostream>
#include <limits>
#include <iomanip>
#include <string>
#include <vector>

#include "header.h"

using namespace std;

// Prepares cin for new input after failed input.
void resetCin() {
	cin.clear();
	cin.ignore(numeric_limits<streamsize>::max(),'\n');
}

int main()
{
	//////////////////////////////////////////////
	// CREATING RECORDS                         //
	//////////////////////////////////////////////

	// Reused helper variable.
	int quantity;

	//////////////////////////////////////////////
	// CREATING RECORDS: FURNITURE              //
	//////////////////////////////////////////////

	// We store furniture records here.
	vector< GenericRecord<Furniture> > recordsFurniture;

	// Loop until user supplies valid amount of records to make.
	bool firstLoop = true;
	do {
		if (firstLoop)
			firstLoop = false;
		else
			// Only call resetCin() after first loop.
			resetCin();

		cout << "Enter quantity (int) of furniture: ";
		int input;
		cin >> input;

		if (!cin)
			cout << "Invalid input." << endl;
		else
			quantity = input;
	} while (!cin);

	cout << endl;

	for (int i = 0; i < quantity; i++) {
		GenericRecord<Furniture> record;
		Furniture var;

		// Loop until user supplies valid "identifier".
		do {
			resetCin();

			cout << "Enter identifier (int): ";
			int input;
			cin >> input;

			if (!cin)
				cout << "Invalid input." << endl;
			else
				var.Identifier = input;
		} while (!cin);

		// Loop until user supplies valid "description".
		do {
			resetCin();

			cout << "Enter description (string): ";
			string input;
			cin >> input;

			if (!cin)
				cout << "Invalid input." << endl;
			else
				var.Description = input;
		} while (!cin);

		// Loop until user supplies valid "value".
		do {
			resetCin();

			cout << "Enter value (float): ";
			float input;
			cin >> input;

			if (!cin)
				cout << "Invalid input." << endl;
			else
				var.Value = input;
		} while (!cin);

		// Save the new record with the rest of the records.
		record.setVar(var);
		recordsFurniture.push_back(record);

		cout << endl;
	}

	//////////////////////////////////////////////
	// CREATING RECORDS: COMPUTER               //
	//////////////////////////////////////////////

	// We store computer records here.
	vector< GenericRecord<Computer> > recordsComputer;

	// Loop until user supplies valid amount of records to make.
	do {
		resetCin();

		cout << "Enter quantity (int) of computer: ";
		int input;
		cin >> input;

		if (!cin)
			cout << "Invalid input." << endl;
		else
			quantity = input;
	} while (!cin);

	cout << endl;

	for (int i = 0; i < quantity; i++) {
		GenericRecord<Computer> record;
		Computer var;

		// Loop until user supplies valid "identifier".
		do {
			resetCin();

			cout << "Enter identifier (long int): ";
			long int input;
			cin >> input;

			if (!cin)
				cout << "Invalid input." << endl;
			else
				var.Identifier = input;
		} while (!cin);

		// Loop until user supplies valid "description".
		do {
			resetCin();

			cout << "Enter description (string): ";
			string input;
			cin >> input;

			if (!cin)
				cout << "Invalid input." << endl;
			else
				var.Description = input;
		} while (!cin);

		// Loop until user supplies valid "value".
		do {
			resetCin();

			cout << "Enter value (float): ";
			float input;
			cin >> input;

			if (!cin)
				cout << "Invalid input." << endl;
			else
				var.Value = input;
		} while (!cin);

		// Save the new record with the rest of the records.
		record.setVar(var);
		recordsComputer.push_back(record);

		cout << endl;
	}

	//////////////////////////////////////////////
	// CREATING RECORDS: BUILDING               //
	//////////////////////////////////////////////

	// We store building records here.
	vector< GenericRecord<Building> > recordsBuilding;

	// Loop until user supplies valid amount of records to make.
	do {
		resetCin();

		cout << "Enter quantity (int) of building: ";
		int input;
		cin >> input;

		if (!cin)
			cout << "Invalid input." << endl;
		else
			quantity = input;
	} while (!cin);

	cout << endl;

	for (int i = 0; i < quantity; i++) {
		GenericRecord<Building> record;
		Building var;

		// Loop until user supplies valid "identifier".
		do {
			resetCin();

			cout << "Enter identifier (string): ";
			string input;
			cin >> input;

			if (!cin)
				cout << "Invalid input." << endl;
			else
				var.Identifier = input;
		} while (!cin);

		// Loop until user supplies valid "description".
		do {
			resetCin();

			cout << "Enter description (string): ";
			string input;
			cin >> input;

			if (!cin)
				cout << "Invalid input." << endl;
			else
				var.Description = input;
		} while (!cin);

		// Loop until user supplies valid "value".
		do {
			resetCin();

			cout << "Enter value (double): ";
			double input;
			cin >> input;

			if (!cin)
				cout << "Invalid input." << endl;
			else
				var.Value = input;
		} while (!cin);

		// Save the new record with the rest of the records.
		record.setVar(var);
		recordsBuilding.push_back(record);

		cout << endl;
	}

	//////////////////////////////////////////////
	// DISPLAYING RECORDS                       //
	//////////////////////////////////////////////

	//////////////////////////////////////////////
	// DISPLAYING RECORDS: FURNITURE            //
	//////////////////////////////////////////////

	cout << "FURNITURE" << endl;

	cout << left << setw(10) << "IDENTIFIER" << " "
		<< left << setw(30) << "DESCRIPTION" << " "
		<< right << setw(10) << "VALUE" << endl;

	for (int i = 0; i < recordsFurniture.size(); i++) {
		Furniture record = recordsFurniture[i].getVar();

		cout << left << setw(10) << record.Identifier << " "
			<< left << setw(30) << record.Description << " "
			<< right << setw(10) << record.Value << endl;
	}

	cout << endl;

	//////////////////////////////////////////////
	// DISPLAYING RECORDS: COMPUTER             //
	//////////////////////////////////////////////

	cout << "COMPUTER" << endl;

	cout << left << setw(10) << "IDENTIFIER" << " "
		<< left << setw(30) << "DESCRIPTION" << " "
		<< right << setw(10) << "VALUE" << endl;

	for (int i = 0; i < recordsComputer.size(); i++) {
		Computer record = recordsComputer[i].getVar();

		cout << left << setw(10) << record.Identifier << " "
			<< left << setw(30) << record.Description << " "
			<< right << setw(10) << record.Value << endl;
	}

	cout << endl;

	//////////////////////////////////////////////
	// DISPLAYING RECORDS: BUILDING             //
	//////////////////////////////////////////////

	cout << "BUILDING" << endl;

	cout << left << setw(10) << "IDENTIFIER" << " "
		<< left << setw(30) << "DESCRIPTION" << " "
		<< right << setw(10) << "VALUE" << endl;

	for (int i = 0; i < recordsBuilding.size(); i++) {
		Building record = recordsBuilding[i].getVar();

		cout << left << setw(10) << record.Identifier << " "
			<< left << setw(30) << record.Description << " "
			<< right << setw(10) << record.Value << endl;
	}

	cout << endl;

	//////////////////////////////////////////////
	// FINISHED                                 //
	//////////////////////////////////////////////

	return 0;
}
