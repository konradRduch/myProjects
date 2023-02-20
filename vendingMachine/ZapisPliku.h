#pragma once
#include <fstream>
#include <iostream>
#include "Lista.h"
#include "NapojWAutomacie.h"


class ZapisPliku
{
	std::string sciezka;
	Lista<NapojWAutomacie> dane;

	void zapisz();
public:
	ZapisPliku(std::string s, Lista<NapojWAutomacie> d) :sciezka(s),dane(d) {
		zapisz(); 
	}
	void pokazZapisywaneDane();


};
