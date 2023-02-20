#pragma once
#include <iostream>
#include "WezelZStruktura.h"
#include "struct.h"

class ListaZStruktura
{

	WezelZStruktura* glowa;

public:
	ListaZStruktura() :glowa(NULL) {}

	void dodajElement(Dane);
	int  zwrocIndex(Dane);
	void pokazListe();
	std::wstring pokazElementListy(int);
	void usunElement(int);
	void usunElement(Dane);
	void wyczyscListe();
	int rozmiarListy();
	ListaZStruktura tablicaZPodobneImie(std::wstring);
	Dane pokazElementListy2(int liczba);
	ListaZStruktura tablicaZPodobneKierunek(std::wstring);
	ListaZStruktura tablicaZPodobneSala(std::wstring);

	

};

