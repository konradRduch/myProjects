#pragma once
#include <iostream>
#include "Screen.h"
class Owner : public Screen
{
public:
	Owner() :Screen(0) {
		//std::cout << number;
	}
	Owner(int n):Screen(n) {
		//std::cout << number;
	}

	void show() {
		std::cout << "Owner of the vending machine\n";
	}

};

