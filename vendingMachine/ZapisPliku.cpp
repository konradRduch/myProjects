#include "ZapisPliku.h"

void ZapisPliku::zapisz() {
	std::ofstream zapis;
	zapis.open(sciezka);
	for (int i = 1;i < dane.rozmiarListy() + 1;i++) {
		zapis << dane.pokazElementListy(i).nazwa << std::endl;
		zapis << dane.pokazElementListy(i).liczbaSztuk << std::endl;
		zapis << dane.pokazElementListy(i).cena << std::endl;
	}
	zapis.close();
}
void ZapisPliku::pokazZapisywaneDane(){
	//for (int i = 1;i < dane.rozmiarListy()+1;i++)
		//std::wcout << dane.pokazElementListy(i) << std::endl;
}
