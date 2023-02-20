#pragma once
#include <iostream>
#include  "Ekran.h"
    class Menu :public Ekran {
    public:
        void wyswietl() {
            std::cout << ">kierunek \n";
            std::cout << ">nauczyciel \n";
            std::cout << ">sala \n";
            std::cout << ">wyjscie \n";
        }
    };



