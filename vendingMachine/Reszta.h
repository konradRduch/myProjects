#pragma once
#include <iostream>
#include "grosze.h"
#include "zlote.h"

class Reszta
{
    std::string zl = "";
    std::string gr = "";
public:
    Reszta() {};
    Reszta(std::string k) {
        reszta(k);

    }
    std::string rozmienNaDrobne() {
        if (zl == "0" and gr == "00")return "RESZTA: 0.00";

        zlote pln(zl);
        grosze grosze(gr);
        return "RESZTA: " + pln.text() + grosze.text();
    }
    void reszta(std::string k) {
        zl = "";
        gr = "";
        bool flag = 0;
        for (int i = 0;i < k.size();i++)
        {
            if (!flag)zl += k[i];
            else gr += k[i];

            if (k[i] == '.')flag = 1;
        }
        zl.pop_back();

    }
};

