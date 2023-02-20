#include "ListaZStruktura.h"


bool isEqual(Dane d1,Dane d2) {
	if ((d1.dzienTygodnia == d2.dzienTygodnia) and
		(d1.godzinaRozpoczecia == d2.godzinaRozpoczecia) and
		(d1.imie == d2.imie) and
		(d1.kierunek == d2.kierunek) and
		(d1.przedmiot == d2.przedmiot) and
		(d1.sala == d2.sala) and
		(d1.tydzien == d2.tydzien) and
		(d1.typZajec == d2.typZajec))return true;
	return false;
}
int  ListaZStruktura::rozmiarListy() {
	WezelZStruktura* index = glowa;
	int i = 0;
	if (glowa == NULL)return 0;

	while (index != NULL) {
		index = index->nastepny;
		i++;
	}
	return i;
}
void ListaZStruktura::usunElement(int index)
{
	WezelZStruktura* temp1 = glowa;
	WezelZStruktura* temp2 = NULL;
	int ListLen = 0;

	if (glowa == NULL) {
		std::cout << "List empty." << std::endl;
		return;
	}

	// Find length of the linked-list.
	while (temp1 != NULL) {
		temp1 = temp1->nastepny;
		ListLen++;
	}

	// Check if the position to be
	// deleted is less than the length
	// of the linked list.
	if (ListLen < index || index < 1) {
		std::cout << "Index out of range"
			<< std::endl;
		return;
	}

	// Declare temp1
	temp1 = glowa;

	// Deleting the head.
	if (index == 1) {

		// Update head
		glowa = glowa->nastepny;
		delete temp1;
		return;
	}

	// Traverse the list to
	// find the node to be deleted.
	while ((index--) > 1) {

		// Update temp2
		temp2 = temp1;

		// Update temp1
		temp1 = temp1->nastepny;
	}

	// Change the next pointer
	// of the previous node.
	temp2->nastepny = temp1->nastepny;

	// Delete the node
	delete temp1;
}
void ListaZStruktura::usunElement(Dane tekst) {
	usunElement(zwrocIndex(tekst));
}
int  ListaZStruktura::zwrocIndex(Dane d) {
	int i = 0;
	WezelZStruktura* temp = glowa;
	if (glowa == NULL)return 0;

	while (i < rozmiarListy()) {
		i++;
		if (isEqual(temp->dane,d))return i;
		temp = temp->nastepny;
	}
	return 0;

}
void ListaZStruktura::dodajElement(Dane d)
{
	// Create the new Node.
	WezelZStruktura* newWezel = new WezelZStruktura(d);

	// Assign to head
	if (glowa == NULL) {
		glowa = newWezel;
		return;
	}

	// Traverse till end of list
	WezelZStruktura* temp = glowa;
	while (temp->nastepny != NULL) {

		// Update temp
		temp = temp->nastepny;
	}

	// Insert at the last.
	temp->nastepny = newWezel;
}
void ListaZStruktura::pokazListe()
{
	WezelZStruktura* temp = glowa;

	// Check for empty list.
	if (temp == NULL) {
		std::cout << "List empty" << std::endl;
		return;
	}

	// Traverse the list.
	while (temp != NULL) {
		std::wcout << temp->dane<<std::endl;
		temp = temp->nastepny;
	}
}
void ListaZStruktura::wyczyscListe() {
	while (glowa != NULL) {
		usunElement(1);
	}
	glowa == NULL;
}
std::wstring ListaZStruktura::pokazElementListy(int liczba) {
	WezelZStruktura* temp = glowa;
	if (liczba > rozmiarListy())return L"";
	// Check for empty list.
	if (temp == NULL) {
		return L"";
	}
	int licznik = 1;
	// Traverse the list.
	while (temp != NULL) {
		if (licznik == liczba)return temp->dane.imie;
		temp = temp->nastepny;
		licznik++;
	}
}
ListaZStruktura ListaZStruktura::tablicaZPodobneImie(std::wstring imie){
	WezelZStruktura* temp = glowa;
	ListaZStruktura nowa;
	while (temp != NULL) {
		if (temp->dane.imie == imie)nowa.dodajElement(temp->dane);
		temp = temp->nastepny;	
	}
	return nowa;
}

Dane ListaZStruktura::pokazElementListy2(int liczba) {
	WezelZStruktura* temp = glowa;
	Dane o;
	if (liczba > rozmiarListy())return o;
	// Check for empty list.
	if (temp == NULL) {
		return o ;
	}
	int licznik = 1;
	// Traverse the list.
	while (temp != NULL) {
		if (licznik == liczba)return temp->dane;
		temp = temp->nastepny;
		licznik++;
	}
}
ListaZStruktura ListaZStruktura::tablicaZPodobneKierunek(std::wstring kierunek) {
	WezelZStruktura* temp = glowa;
	ListaZStruktura nowa;
	while (temp != NULL) {
		if (temp->dane.kierunek == kierunek)nowa.dodajElement(temp->dane);
		temp = temp->nastepny;
	}
	return nowa;
}
ListaZStruktura ListaZStruktura::tablicaZPodobneSala(std::wstring sala) {
	WezelZStruktura* temp = glowa;
	ListaZStruktura nowa;
	while (temp != NULL) {
		if (temp->dane.sala == sala)nowa.dodajElement(temp->dane);
		temp = temp->nastepny;
	}
	return nowa;
}


