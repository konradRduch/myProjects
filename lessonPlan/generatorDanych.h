#pragma once
#include <string>
#include <iostream>


class generatorDanych
{
public:
	std::wstring generujSale();
	std::wstring generujGrupe();
	std::wstring generujPrzedmiot();
	std::wstring generujDzienTygodnia(std::wstring);
	std::wstring generujTydzien();
	std::wstring generujGodzine();
	std::wstring generujNauczyciela(int );
	std::wstring generujTypPrzedmiotu();
	
	void generuj();
	
	
	std::wstring generujPlanDlaNauczyciela(int);
	
	
	
	

	

};

