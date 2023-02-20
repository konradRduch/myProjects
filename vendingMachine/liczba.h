#pragma once
#include <iostream>
class liczba
{
protected:
    std::string dziesiatki;
    std::string jednosci;
public:
    liczba() {};
    liczba(std::string l) :dziesiatki(l), jednosci(l)
    {
        if (l.size() == 1) {
            dziesiatki = "0";

        }
        else {
            dziesiatki.pop_back();
            dziesiatki += "0";
            jednosci.erase(jednosci.begin());
        }

    }
};

