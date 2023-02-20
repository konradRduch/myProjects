#pragma once
class Screen
{
protected:
	int number;
public: 
	Screen(int n) :number(n) {}
	virtual void show() {};

};

