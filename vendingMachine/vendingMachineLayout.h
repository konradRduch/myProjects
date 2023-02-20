#pragma once
#include <iostream>
#include "Tabela.h"

class vendingMachineLayout
{
	std::string messageScreen;
	std::string changeScreen;
	std::string pickUpScreen;
	Tabela automat;

    std::string generuj(int rozmiar, std::string l) {
        std::string x = "";
        for (int i = 0;i < rozmiar;i++)x += l;
        return x;
    }
    std::string generuj(int rozmiar, std::string k, char l) {
        std::string x = "";
        for (int i = 0;i < rozmiar;i++)x += k;
        x[x.size() - 1] = l;
        return x;
    }


public:
	vendingMachineLayout(): automat(20,5), messageScreen(""), changeScreen("Reszta: "), pickUpScreen("Odbierz: ") {
		

        int m = 3;


        int licznik = 0;
        for (int i = 0;i < automat.getLineSize() / 5;i++)
        {
            for (int j = 0;j < automat.getColumnSize();j++)
            {
                automat.wstaw(m, j, std::to_string(licznik + 1));
                automat.wstaw(m + 1, j, generuj(automat.maxDlugiWyrazWTabeli(), "_"));
                licznik++;
            }
            m += 5;
        }
        automat.pokazWyruwnanaTablica();
	}



};

