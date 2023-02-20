#pragma once
#include <fstream>
#include <iostream>
#include <string>
#include "Lista.h"
#include "NapojWAutomacie.h"


class OdczytPliku 
{
	std::string sciezka;
	Lista<NapojWAutomacie>dane;
	int rozmiar;	

	
public:
	void odczytaj() {
		
		std::ifstream odczyt;
		std::string nazwa;
		std::string cena;
		std::string iloscSztuk;
		int licznik = 0;
		odczyt.open(sciezka);
		while (odczyt.good()) {
			getline(odczyt,nazwa);
			getline(odczyt, cena);
			getline(odczyt, iloscSztuk);
			if (licznik < 20)dane.dodajElement(NapojWAutomacie(nazwa,iloscSztuk, cena));
			licznik++;
		}
		odczyt.close();
		rozmiar = dane.rozmiarListy();
	}
	OdczytPliku(std::string s) :sciezka(s) { odczytaj(); }

	Lista<NapojWAutomacie>odczytaneDane() {
		return dane;
	}
	void pokazOdczytaneDane() {
		dane.pokazListe();
	}
	int getRozmiar() {
		return rozmiar;
	}
	
};


