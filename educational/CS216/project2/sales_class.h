#ifndef SALES_CLASS_H			// Check if we have already defined this or not.
#define SALES_CLASS_H

#include <string>			// Included for string datatype.
#include <vector>			// Included for vector datatype.

using namespace std;

					// This is our sale class, which contains everything.
class salesClass {
	private:
					// This is the struct for sales slips.
		struct salesStruct {
			int seller;
			int product;
			float revenue;
			int date;
		};
					// These are our dynamic arrays which store our
					// sellers, products, slips, and our 2D summary array.
		vector<string> sellers;
		vector<string> products;
		vector<salesStruct> slips;
		vector<vector<float> > summary;
	public:
					// These are our prototype functions we use in sales_definition.cpp
		void inputSellers();
		void inputProducts();
		void inputSlips();
		void buildSummary();
		void outputSummary();
};

#endif
