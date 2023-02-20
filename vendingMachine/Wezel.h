#pragma once
#include <iostream>
template<typename T>
class Wezel
{
public:
	T dane;
	Wezel<T>* nastepny;

	Wezel(T d)
	{
		this->dane = d;
		this->nastepny = NULL;
	}

};

