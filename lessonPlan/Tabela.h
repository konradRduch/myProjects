#include <iostream>
#include <vector>
#include "struct.h"
#pragma once
class Tabela
{
	int lineSize;
	int columnSize;
	std::wstring **pola;

	void wypelnilStarymiDanymi(int, int, std::wstring** stare);
	void powiekszTablice(int, int);

public:
	Tabela();
	Tabela(int , int);
	Tabela(int,  int, std::wstring**);
	void pokazWyruwnanaTablica();
	void wstaw(int ,int,std::wstring);
	void wstaw(int, int, std::vector < std::wstring>);
	void wstaw(Dane, std::vector<std::wstring>);
	int getLineSize();
	~Tabela();

};
