#include "ZapisPliku.h"


void ZapisPliku::zapiszgra() {
	std::ofstream zapis;
	zapis.open(sciezka1);

		zapis << dane.difficulty << std::endl;
		zapis << dane.theme << std::endl;
	
	zapis.close();
}

void ZapisPliku::zapiszsnake() {
	std::ofstream zapis;
	zapis.open(sciezka2);
		zapis << dane.controls << std::endl;
		zapis << dane.sound << std::endl;
	
	zapis.close();
}