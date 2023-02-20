#include <iostream>
#include <Windows.h>
#include "generatorDanych.h"
#include "Lista.h"
#include "ZapisPliku.h"
#include "OdczytPliku.h"
#include <vector>
#include "ListaZStruktura.h"
#include "struct.h"
#include "OdczytDoStruktury.h"
#include "Ekran.h"
#include "Menu.h"
#include "Kierunek.h"
#include "Sala.h"
#include "Nauczyciela.h"


int main()
{
    std::locale loc("polish");
    std::locale::global(loc);
    system("chcp 1250");
    system("cls");
 
    generatorDanych g;
    g.generuj();
   
    std::wcout << L"Przykładowe dane, wygenerowano\n";
    Sleep(1000);
    std::wcout << L"Naciśnij enter żeby kontynułować\n";
    getchar();
    system("cls");

    std::wstring wybor;
    Ekran* e;
    for (;;) {
        e = new Menu;
        e->wyswietl();
        std::wcin >> wybor;
        if (wybor == L"kierunek") {
            e = new Kierunek;
            e->wyswietl();
        }
        else if (wybor == L"sala") {
            e = new Sala;
            e->wyswietl();
        }
        else if (wybor == L"nauczyciel") {
            e = new Nauczyciela;
            e->wyswietl();
        }
        else if (wybor == L"wyjscie")return 0;
    }
    return 0;
}






