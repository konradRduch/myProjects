#pragma once
#include <fstream>
#include <iostream>
#include <vector>
#include "Lista.h"

class ZapisPliku
{
	std::string sciezka;
	Lista dane;
	void zapisz();
public:
	ZapisPliku(std::string s, Lista d) :sciezka(s),dane(d) {
		zapisz(); 
	}
	void pokazZapisywaneDane();


};
