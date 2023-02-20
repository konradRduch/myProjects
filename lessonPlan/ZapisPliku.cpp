#include "ZapisPliku.h"

void ZapisPliku::zapisz() {
	std::wofstream zapis;
	zapis.open(sciezka);
	for (int i = 1;i < dane.rozmiarListy()+1;i++)
		zapis << dane.pokazElementListy(i) <<std::endl;
	zapis.close();
}
void ZapisPliku::pokazZapisywaneDane(){
	for (int i = 1;i < dane.rozmiarListy()+1;i++)
		std::wcout << dane.pokazElementListy(i) << std::endl;
}
