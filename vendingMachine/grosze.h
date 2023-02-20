#pragma once
#include "liczba.h"
#include <iostream>
#include <string>
class grosze: public liczba
{
    std::string rozmienjednosci(int reszta) {
        int iloscMonet = 3;
        int monety[3] = { 5 , 2, 1 };
        int historia[20];
        int licznik = 0;

        while (reszta > 0)
        {
            int nominal = 0;
            for (int i = 0; i < iloscMonet; ++i)
            {
                if ((monety[i] <= reszta) && (monety[i] > nominal))
                {
                    nominal = monety[i];
                }
            }
            reszta = reszta - nominal;
            historia[licznik] = nominal;
            licznik++;
        }

        std::string h = "";
        for (int i = 0; i < licznik; ++i)
        {
            h += std::to_string(historia[i]) + "groszy ";
        }
        return h;
    }
    std::string rozmiendziesiatki(int reszta) {
        int iloscMonet = 3;
        int monety[3] = { 50 , 20, 10 };
        int historia[20];
        int licznik = 0;
        while (reszta > 0)
        {
            int nominal = 0;
            for (int i = 0; i < iloscMonet; ++i)
            {
                if ((monety[i] <= reszta) && (monety[i] > nominal))
                {
                    nominal = monety[i];
                }
            }
            reszta = reszta - nominal;
            historia[licznik] = nominal;
            licznik++;
        }

        std::string h = "";
        for (int i = 0; i < licznik; ++i)
        {
            h += std::to_string(historia[i]) + "groszy ";
        }
        return h;

    }
public:
    grosze(std::string k) :liczba(k) {}
    std::string text() {
        return rozmiendziesiatki(std::stoi(dziesiatki)) + rozmienjednosci(std::stoi(jednosci));
    }

};

