#include <iostream>
#include <vector>
#include "Lista.h"
#include "NapojWAutomacie.h"
#pragma once

class Tabela
{
	int lineSize;
	int columnSize;
	std::string **pola;

	void wypelnilStarymiDanymi(int, int, std::string** stare);
	void powiekszTablice(int, int);

public:
	Tabela();
	Tabela(int , int);
	Tabela(int,  int, std::string**);
	void pokazWyruwnanaTablica();
	void pokazWyruwnanaTablica2();
	void wstaw(int ,int,std::string);
	void wstaw(int, int, std::vector < std::string>);
	void wstaw(int, int, NapojWAutomacie);
	
	int maxDlugiWyrazWTabeli();

	int getLineSize();
	int getColumnSize();

	~Tabela();

};
