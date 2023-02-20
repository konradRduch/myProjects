#pragma once
#include <iostream>

struct Dane {

	std::wstring imie;
	std::wstring przedmiot;
	std::wstring kierunek;
	std::wstring sala;
	std::wstring dzienTygodnia;
	std::wstring godzinaRozpoczecia;
	std::wstring tydzien;
	std::wstring typZajec;

	Dane() :imie(L""), przedmiot(L""), kierunek(L""), sala(L""), dzienTygodnia(L""), godzinaRozpoczecia(L""), tydzien(L""), typZajec(L"") {}
	
	Dane(std::wstring i, std::wstring p, std::wstring k , std::wstring s, std::wstring dt, std::wstring gr, std::wstring t, std::wstring tz)
		:imie(i),przedmiot(p),kierunek(k),sala(s),dzienTygodnia(dt),godzinaRozpoczecia(gr),tydzien(t),typZajec(tz) {}

	friend std::wostream& operator<<(std::wostream& output, Dane& obj) {
		output << obj.imie << L"\n" << obj.przedmiot << L"\n" << obj.kierunek << L"\n" << obj.sala << L"\n" << obj.dzienTygodnia << L"\n" << obj.godzinaRozpoczecia << L"\n" << obj.tydzien << L"\n" << obj.typZajec ;
		return output;
	}
};
