#include "OdczytPliku.h"

void OdczytPliku::odczytaj(){
	std::wstring tekst;
	std::wifstream odczyt;
	odczyt.open(sciezka);
	while (odczyt.good()) {
		getline(odczyt, tekst);
		if(tekst.size()!=0)dane.dodajElement(tekst);
	}
	odczyt.close();
	rozmiar = dane.rozmiarListy();
}
void OdczytPliku::pokazOdczytaneDane(){
	for (int i = 1;i <= dane.rozmiarListy();i++)
		std::wcout << dane.pokazElementListy(i) << std::endl;

}
Lista OdczytPliku::odczytaneDane(){
	return dane;
}
int OdczytPliku::getRozmiar(){
	return rozmiar;
}
