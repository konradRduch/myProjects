#include "OdczytDoStruktury.h"

void OdczytDoStruktury::odczytaj(){
	Dane tekst;
	std::wifstream odczyt;
	odczyt.open(sciezkaPlikuNauczyciela);
	while (odczyt.good()) {
		getline(odczyt, tekst.imie);
		getline(odczyt, tekst.przedmiot);
		getline(odczyt, tekst.kierunek);
		getline(odczyt, tekst.sala);
		getline(odczyt, tekst.dzienTygodnia);
		getline(odczyt, tekst.godzinaRozpoczecia);
		getline(odczyt, tekst.tydzien);
		getline(odczyt, tekst.typZajec);
		daneNauczycieli.dodajElement(tekst);
	}
	odczyt.close();

}
void OdczytDoStruktury::pokaz() {
	daneNauczycieli.pokazListe();
}
ListaZStruktura OdczytDoStruktury::pobierzListe(){
	return daneNauczycieli;
}
