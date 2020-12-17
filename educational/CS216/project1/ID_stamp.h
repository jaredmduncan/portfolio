// ID_stamp.h
// This header file contains the class definition with
// only prototype functions for the ID_stamp class.

// Check if we've already included the header.
#ifndef __ID_STAMP_H__
#define __ID_STAMP_H__

// string provides a string data type.
#include <string>

using namespace std;

// Class definition for ID_stamp.
class ID_stamp {
	// Storing ID.
	int stamp;
	// Storing description.
	string desc;
public:
	// Receiving description and generating an ID.
	void input();
	// Outputting description and ID.
	void output();
};

#endif
