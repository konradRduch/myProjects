#pragma once
#include "Screen.h"
#include <iostream>
#include "vendingMachine.h"
class Customer : public Screen
{
public:
	Customer():Screen(0) {
		//std::cout << number;
	}
	Customer(int n) :Screen(n) {
		//std::cout << number;
	}



	void show() {
		std::cout << "Customer of the vending machine\n";	

		vendingMachine vm(number);
		vm.show();
		vm.throwCoins();
		





	}

};

