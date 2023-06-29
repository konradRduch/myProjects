
#pragma once
#include <fstream>
#include <iostream>
#include <string>
#include "sOptions.h"


class OdczytPliku
{
	std::string sciezka1;
	std::string sciezka2;
	sOptions dane;

public:
	void odczytaj1() {
		std::ifstream odczyt;
		std::string d;
		std::string t;
		
		odczyt.open(sciezka1);
		while (odczyt.good()) {
			odczyt >> d;
			odczyt >> t;
			//std::cout << t;
		}
		odczyt.close();
		dane.difficulty = d;
		dane.theme = t;


	}
	void odczytaj2() {
		std::ifstream odczyt;
		std::string c;
		std::string s;

		odczyt.open(sciezka2);
		while (odczyt.good()) {
			odczyt >> c;
			odczyt >> s;
		}
		odczyt.close();
		dane.controls = c;
		dane.sound = s;
	}
	
	OdczytPliku(std::string s1,std::string s2) :sciezka1(s1), sciezka2(s2) { 
		odczytaj1();
		odczytaj2();
	}

	sOptions odczytaneDane() {
		return dane;
	}
	void pokazOdczytaneDane() {
		std::cout << dane;
	}
	
};