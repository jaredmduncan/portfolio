// header.h

#include <string>

using namespace std;

#ifndef HEADER_H
#define HEADER_H

//////////////////////////////////////////////////
// CLASSES                                      //
//////////////////////////////////////////////////

template<typename T> class GenericRecord
{
	private:
		T var;
	public:
		void setVar(T var);
		T getVar();
};

// Linker errors if not included with header.
#include "implementation.h"

//////////////////////////////////////////////////
// STRUCTS                                      //
//////////////////////////////////////////////////

struct Furniture
{
	int Identifier;
	string Description;
	float Value;
};

struct Computer
{
	long int Identifier;
	string Description;
	float Value;
};

struct Building
{
	string Identifier;
	string Description;
	double Value;
};

#endif