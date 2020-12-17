#include "sales_definition.cpp"

using namespace std;

int main()
{
	salesClass salesObject;		// Initialize our sales object.
	salesObject.inputSellers();	// Get our sellers, products, and make some slips.
	salesObject.inputProducts();
	salesObject.inputSlips();
	salesObject.buildSummary();	// Generate our summary 2D array and display it.
	salesObject.outputSummary();
	return 0;			// Exit cleanly.
}

