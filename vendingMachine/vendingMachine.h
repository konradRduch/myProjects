#pragma once
#include <iostream>
#include "Lista.h"
#include "OdczytPliku.h"
class vendingMachine
{
	int chosenNumber;
	double currrentMoneyProfit;
	Lista<NapojWAutomacie>productsInMachine;
	NapojWAutomacie chosenProduct;
	//zrobic klase ktora wyswietla ekran automatu 
	std::string messageScreen;
	std::string changeScreen;
	std::string pickUpScreen;
	
	bool isgood(std::string liczba) {
		if (liczba == "50pln")return 1;
		else if (liczba == "20pln")return 1;
		else if (liczba == "10pln")return 1;
		else if (liczba == "5pln")return 1;
		else if (liczba == "2pln")return 1;
		else if (liczba == "1pln")return 1;
		else if (liczba == "50gr")return 1;
		else if (liczba == "20gr")return 1;
		else if (liczba == "10gr")return 1;
		else if (liczba == "5gr")return 1;
		else if (liczba == "2gr")return 1;
		else if (liczba == "1gr")return 1;
		else return false;
	}
	double nominaly(std::string liczba) {
		if (liczba == "50pln")return 50;
		else if (liczba == "20pln")return 20;
		else if (liczba == "10pln")return 10;
		else if (liczba == "5pln")return 5;
		else if (liczba == "2pln")return 2;
		else if (liczba == "1pln")return 1;
		else if (liczba == "50gr")return 0.5;
		else if (liczba == "20gr")return 0.2;
		else if (liczba == "10gr")return 0.10;
		else if (liczba == "5gr")return 0.05;
		else if (liczba == "2gr")return 0.02;
		else if (liczba == "1gr")return 0.01;
		else return 0;
	}

public:
	vendingMachine() : chosenNumber(0), currrentMoneyProfit(0), messageScreen(""), changeScreen("Reszta: "), pickUpScreen("Odbierz: ") {
		OdczytPliku load("C:\\Users\\HP\\Desktop\\mikroprogram\\napoje.txt");
		productsInMachine = load.odczytaneDane();
	}
	vendingMachine(int number) : chosenNumber(number), currrentMoneyProfit(0), messageScreen(""), changeScreen("Reszta: "), pickUpScreen("Odbierz: ") {
		OdczytPliku load("C:\\Users\\HP\\Desktop\\mikroprogram\\napoje.txt");
		productsInMachine = load.odczytaneDane();
		chosenProduct = productsInMachine.pokazElementListy(chosenNumber);
	}
	void setchosenNumber(int n) {
		chosenNumber = n;
	}
	void show() {
		std::cout << chosenProduct <<std::endl;
	}
	void throwCoins() {
	
	  double suma = 0;
	
	  std::string money;
	  do {
	      std::cin >> money;
	      if (isgood(money))suma += nominaly(money);
	      system("cls");
	      messageScreen = "WYBRANO " + chosenProduct.nazwa + " DO ZAPLACENIA " + chosenProduct.cena + "PLN, wrzuc monety i napisz OK \n(dostepne nominaly to 50pln, 20pln, 10pln, 5pln, 2pln, 1pln, 50gr, 20gr, 10gr, 5gr, 2gr, 1gr), obecnie wrzucono " + std::to_string(suma) +" PLN";
		  std::cout << messageScreen;
	  }while (money != "ok");
	  
	}






};

