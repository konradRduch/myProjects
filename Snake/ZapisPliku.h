#pragma once
#include <fstream>
#include <iostream>
#include <vector>
#include "sOptions.h"


class ZapisPliku
{
	std::string sciezka1;
	std::string sciezka2;
	sOptions dane;

	void zapiszgra();
	void zapiszsnake();
public:
	ZapisPliku(std::string s1,std::string s2, sOptions d) : sciezka1(s1) ,sciezka2(s2), dane(d) {
		zapiszgra();
		zapiszsnake();
	}



};