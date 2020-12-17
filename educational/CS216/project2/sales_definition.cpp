#include <iostream>			// Included for input & output operations.
#include <limits>			// Included for clearing & ignoring cin appropriately.
#include <iomanip>			// Included for output formatting.
#include <string>			// Included for string datatype.
#include <vector>			// Included for vector datatype.
#include <ctime>			// Included for generating unix-epoch value.

#include "sales_class.h"

using namespace std;

					// In the event we need to clear & ignore cin, we call this function.
void resetCin() {
	cin.clear();
	cin.ignore(numeric_limits<streamsize>::max(),'\n');
}

					// Handles the user input logic for sellers.
void salesClass::inputSellers() {
					// Get amount of sellers.
	cout << "Please enter amount of sellers: ";
	int amount;
	cin >> amount;
	for (int i = 0; i < amount; i++) {
					// Get seller name and store it.
		resetCin();
		cout << "Please enter seller name: ";
		string name;
		getline(cin, name);
		this->sellers.insert(this->sellers.end(), name);
	}
}

					// Handles the user input logic for products.
void salesClass::inputProducts() {
					// Get amount of products.
	cout << "Please enter amount of products: ";
	int amount;
	cin >> amount;
	for (int i = 0; i < amount; i++) {
					// Get product name and store it.
		resetCin();
		cout << "Please enter product name: ";
		string name;
		getline(cin, name);
		this->products.insert(this->products.end(), name);
	}
}

					// Handles the user input logic for sales slips.
void salesClass::inputSlips() {
	for (int sellerCount = 0; sellerCount < this->sellers.size(); sellerCount++) {
		for (int productCount = 0; productCount < this->products.size(); productCount++) {
					// Collect revenue value for specific seller / product from user.
			cout << "Please enter " << this->sellers[sellerCount] << "'s " << this->products[productCount] << " revenue: ";
			float revenue;
			cin >> revenue;
					// Generate a sales slip and store it.
			salesClass::salesStruct slip;
			slip.seller = sellerCount;
			slip.product = productCount;
			slip.revenue = revenue;
			time_t epoch = time(nullptr);
			slip.date = int(epoch);
			this->slips.insert(this->slips.end(), slip);
		}
	}
}

					// Initializes our matrix, then populates it with data gathered from slips.
void salesClass::buildSummary() {
					// Initialize the two dimensional array to 0 for all values.
	for (int sellerCount = 0; sellerCount < this->sellers.size(); sellerCount++) {
		this->summary.push_back(vector<float>());
		for (int productCount = 0; productCount < this->products.size(); productCount++) {
			this->summary[sellerCount].push_back(0.00);
		}
	}
					// Populate the summary with data from slips.
	for (int slipCount = 0; slipCount < this->slips.size(); slipCount++) {
		this->summary[this->slips[slipCount].seller][this->slips[slipCount].product] = this->summary[this->slips[slipCount].seller][this->slips[slipCount].product] + this->slips[slipCount].revenue;
	}

}

					// Reads our summary data and outputs it for user to read, formatted.
void salesClass::outputSummary() {
					// Draw the header of our output.
	cout << endl << "ACME COMPANY MONTHLY SALES REPORT" << endl;
	cout << left << setw(25) << "REPRESENTATIVE" << " ";
	cout << left << setw(25) << "PRODUCT" << " ";
	cout << right << setw(15) << "REVENUE" << endl;
	cout << left << setw(25) << "-------------------------" << " ";
	cout << left << setw(25) << "-------------------------" << " ";
	cout << right << setw(15) << "---------------" << endl;
					// Begin displaying seller specific information,
					// also total up revenues simultaneously.
	float companyRevenue = 0.00;
	for (int sellerCount = 0; sellerCount < this->summary.size(); sellerCount++) {
		float sellerRevenue = 0.00;
		for (int productCount = 0; productCount < this->summary[sellerCount].size(); productCount++) {
					// Output seller / product specific information.
			float productRevenue = this->summary[sellerCount][productCount];
			cout << left << setw(25) << this->sellers[sellerCount] << " ";
			cout << left << setw(25) << this->products[productCount] << " ";
			cout << right << setw(15) << setprecision(2) << fixed << productRevenue << endl;
			sellerRevenue = sellerRevenue + productRevenue;
		}
					// Output seller specific total revenue.
		cout << endl << left << setw(25) << "SELLER'S TOTAL REVENUE:" << "  " << right << setw(40) << setprecision(2) << fixed << sellerRevenue << endl << endl;
		companyRevenue = companyRevenue + sellerRevenue;
	}
					// Output company wide revenue total.
	cout << endl << left << setw(25) << "COMPANY'S TOTAL REVENUE:" << "  " << right << setw(40) << setprecision(2) << fixed << companyRevenue << endl;
}
