#pragma once
#include "Wezel.h"
#include "NapojWAutomacie.h"

template <typename T>
class Lista
{
	Wezel<T>* glowa;

public:
	Lista() :glowa(NULL) {}

	void dodajElement(T d) {
		
			// Create the new Node.
			Wezel<T>* newWezel = new Wezel<T>(d);

			// Assign to head
			if (glowa == NULL) {
				glowa = newWezel;
				return;
			}

			// Traverse till end of list
			Wezel<T>* temp = glowa;
			while (temp->nastepny != NULL) {

				// Update temp
				temp = temp->nastepny;
			}

			// Insert at the last.
			temp->nastepny = newWezel;
		
	}
	int  zwrocIndex(T d) {
		int i = 0;
		Wezel<T>* temp = glowa;
		if (glowa == NULL)return 0;
	
		while (i < rozmiarListy()) {
			i++;
			if (temp->dane == d)return i;
			temp = temp->nastepny;
		}
		return 0;
	
	}
	void pokazListe() {
		Wezel<T>* temp = glowa;
		
			// Check for empty list.
			if (temp == NULL) {
				std::cout << "List empty" << std::endl;
				return;
			}

			// Traverse the list.
			while (temp != NULL) {
				std::cout << temp->dane << std::endl;
				temp = temp->nastepny;
			}

	}
	void usunElement(int index)
	{
	Wezel<T>* temp1 = glowa;
	Wezel<T>*temp2 = NULL;
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
	void usun(T tekst) {
		usunElement(zwrocIndex(tekst));
	}
	void wyczyscListe() {
		while (glowa != NULL) {
					usunElement(1);
				}
				glowa == NULL;
	}
	int  rozmiarListy() {
		Wezel<T>* index = glowa;
		int i = 0;
		if (glowa == NULL)return 0;

		while (index != NULL) {
			index = index->nastepny;
			i++;
		}
		return i;
	}

	T pokazElementListy(int liczba) {
		Wezel<T>* temp = glowa;
		if (liczba > rozmiarListy())return T();
		// Check for empty list.
		if (temp == NULL) {
			return T();
		}
		int licznik = 1;
		// Traverse the list.
		while (temp != NULL) {
			if (licznik == liczba) {
				//std::cout << temp->dane;
				return temp->dane;
			}
			temp = temp->nastepny;
			licznik++;
		}
	}

};

template <>
class Lista<NapojWAutomacie>
{
	Wezel<NapojWAutomacie>* glowa;

public:
	Lista() :glowa(NULL) {}

	

	void dodajElement(NapojWAutomacie d) {

		// Create the new Node.
		Wezel<NapojWAutomacie>* newWezel = new Wezel<NapojWAutomacie>(d);

		// Assign to head
		if (glowa == NULL) {
			glowa = newWezel;
			return;
		}

		// Traverse till end of list
		Wezel<NapojWAutomacie>* temp = glowa;
		while (temp->nastepny != NULL) {

			// Update temp
			temp = temp->nastepny;
		}

		// Insert at the last.
		temp->nastepny = newWezel;

	}
	int  zwrocIndex(NapojWAutomacie d) {
		int i = 0;
		Wezel<NapojWAutomacie>* temp = glowa;
		if (glowa == NULL)return 0;

		while (i < rozmiarListy()) {
			i++;
			if (temp->dane.nazwa == d.nazwa)return i;
			temp = temp->nastepny;
		}
		return 0;

	}
	void pokazListe() {
		Wezel<NapojWAutomacie>* temp = glowa;

		// Check for empty list.
		if (temp == NULL) {
			std::cout << "List empty" << std::endl;
			return;
		}

		// Traverse the list.
		while (temp != NULL) {
			std::cout << temp->dane << std::endl;
			temp = temp->nastepny;
		}

	}
	void usunElement(int index)
	{
		Wezel<NapojWAutomacie>* temp1 = glowa;
		Wezel<NapojWAutomacie>* temp2 = NULL;
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
	void usun(NapojWAutomacie tekst) {
		usunElement(zwrocIndex(tekst));
	}
	void wyczyscListe() {
		while (glowa != NULL) {
			usunElement(1);
		}
		glowa == NULL;
	}
	int  rozmiarListy() {
		Wezel<NapojWAutomacie>* index = glowa;
		int i = 0;
		if (glowa == NULL)return 0;

		while (index != NULL) {
			index = index->nastepny;
			i++;
		}
		return i;
	}

	NapojWAutomacie pokazElementListy(int liczba) {
		Wezel<NapojWAutomacie>* temp = glowa;
		if (liczba > rozmiarListy())return NapojWAutomacie();
		// Check for empty list.
		if (temp == NULL) {
			return NapojWAutomacie();
		}
		int licznik = 1;
		// Traverse the list.
		while (temp != NULL) {
			if (licznik == liczba) { 
				return temp->dane; 
			}
			temp = temp->nastepny;
			licznik++;
		}
	}

	void zmienElementListy(int liczba, NapojWAutomacie copy ) {
		Wezel<NapojWAutomacie>* temp = glowa;
		if (liczba > rozmiarListy())return ;
		// Check for empty list.
		if (temp == NULL) {
			return ;
		}
		int licznik = 1;
		// Traverse the list.
		while (temp != NULL) {
			if (licznik == liczba) {
				temp->dane = copy;
				return;
			}
			temp = temp->nastepny;
			licznik++;
		}
	
}

};



