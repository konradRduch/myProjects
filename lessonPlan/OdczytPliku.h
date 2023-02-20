#pragma once
#include <fstream>
#include <iostream>
#include <vector>
#include <string>
#include "Lista.h"
class OdczytPliku
{
	std::string sciezka;
	Lista dane;
	int rozmiar;

	void odczytaj();
public:
	OdczytPliku(std::string s) :sciezka(s) { odczytaj(); }
	Lista odczytaneDane();
	void pokazOdczytaneDane();
	int getRozmiar();
	
};

