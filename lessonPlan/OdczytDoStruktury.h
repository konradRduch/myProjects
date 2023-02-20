#pragma once
#include <iostream>
#include "ListaZStruktura.h"
#include <fstream>
#include <vector>
#include <string>
class OdczytDoStruktury
{
	std::string sciezkaPlikuNauczyciela;
	ListaZStruktura daneNauczycieli;

	void odczytaj();
public:
	OdczytDoStruktury(std::string spn):sciezkaPlikuNauczyciela(spn) { odczytaj(); }
	void pokaz();
	ListaZStruktura pobierzListe();



};

