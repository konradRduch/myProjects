#include "Lista.h"

int  Lista::rozmiarListy() {
	Wezel* index = glowa;
	int i = 0;
	if (glowa == NULL)return 0;

	while (index != NULL) {
		index = index->nastepny;
		i++;
	}
	return i;
}
void Lista::usunElement(int index)
{
	Wezel* temp1 = glowa;
	Wezel *temp2 = NULL;
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
void Lista::usunElement(std::wstring tekst) {
	usunElement(zwrocIndex(tekst));
}
int  Lista::zwrocIndex(std::wstring d) {
	int i = 0;
	Wezel* temp = glowa;
	if (glowa == NULL)return 0;

	while (i < rozmiarListy()) {
		i++;
		if (temp->dane == d)return i;
		temp = temp->nastepny;
	}
	return 0;

}
void Lista::dodajElement(std::wstring d)
{
	// Create the new Node.
	Wezel* newWezel = new Wezel(d);

	// Assign to head
	if (glowa == NULL) {
		glowa = newWezel;
		return;
	}

	// Traverse till end of list
	Wezel* temp = glowa;
	while (temp->nastepny != NULL) {

		// Update temp
		temp = temp->nastepny;
	}

	// Insert at the last.
	temp->nastepny = newWezel;
}
void Lista::pokazListe()
{
	Wezel* temp = glowa;

	// Check for empty list.
	if (temp == NULL) {
		std::cout << "List empty" << std::endl;
		return;
	}

	// Traverse the list.
	while (temp != NULL) {
		std::wcout << std::endl << temp->dane;
		temp = temp->nastepny;
	}
}
void Lista::wyczyscListe() {
	while (glowa != NULL) {
		usunElement(1);
	}
	glowa == NULL;
}
std::wstring Lista::pokazElementListy(int liczba) {
	Wezel* temp = glowa;
	if (liczba > rozmiarListy())return L"";
	// Check for empty list.
	if (temp == NULL) {
		return L"";
	}
	int licznik = 1;
	// Traverse the list.
	while (temp != NULL) {
		if(licznik==liczba)return temp->dane;
		temp = temp->nastepny;
		licznik++;
	}
}

