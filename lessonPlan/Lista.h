#pragma once
#include <iostream>
#include "Wezel.h"
#include <string>

class Lista
{
	Wezel* glowa;

public:
	Lista() :glowa(NULL) {}
	
	void dodajElement(std::wstring);
	int  zwrocIndex(std::wstring);
	void pokazListe();
	std::wstring pokazElementListy(int);
	void usunElement(int);
	void usunElement(std::wstring);
	void wyczyscListe();
	int rozmiarListy();

	Lista& operator=(const Lista& l) {
		if (this == &l)return *this;
		wyczyscListe();
		glowa = l.glowa;

		return *this;
	}
	Lista& operator+=(std::wstring d) {
		dodajElement(d);
		return *this;
	}
	


};

