#pragma once
#include <iostream>
#include "struct.h"
class WezelZStruktura
{
public:
	Dane dane;
	WezelZStruktura* nastepny;

	WezelZStruktura()
	{
		nastepny = NULL;
	}

	WezelZStruktura(Dane d)
	{
		this->dane = d;
		this->nastepny = NULL;
		
	}

};

