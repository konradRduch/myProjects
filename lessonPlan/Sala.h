#pragma once
#include <iostream>
#include <string>
#include  "Ekran.h"
#include "struct.h"
#include <Windows.h>
#include "OdczytDoStruktury.h"
#include "ListaZStruktura.h"
#include "Tabela.h"
class Sala : public Ekran
{
    std::wstring format(int x) {
        if (std::to_wstring(x).size() == 1)return L"0" + std::to_wstring(x);
        return std::to_wstring(x);
    }
public:
    void wyswietl() {
        std::wcin.ignore();
        std::wstring sala=L"";
        std::cout << ">Wybierz sale od 1 do 115, (wpisz sala (numer sali)) \n";       
        getline(std::wcin, sala);

        Sleep(1000);
        system("cls");

        OdczytDoStruktury o("C:\\Users\\HP\\Desktop\\sem2_projekt\\planNauczycieli.txt");
        ListaZStruktura l;
        l = o.pobierzListe();

        ListaZStruktura lm = l.tablicaZPodobneSala(sala);


        int lineSize = 65;
        int columnSize = 12;
        Tabela t1(lineSize, columnSize);

        t1.wstaw(0, 1, L"Dzien       ");
        t1.wstaw(1, 1, L"Tydzien     ");
        t1.wstaw(0, 2, L"Poniedzialek");
        t1.wstaw(0, 4, L"   Wtorek   ");
        t1.wstaw(0, 6, L"   Sroda    ");
        t1.wstaw(0, 8, L"  Czwartek  ");
        t1.wstaw(0, 10, L"   Piatek   ");

        for (int i = 0;i < 10;++i) {
            if (i % 2 == 0)t1.wstaw(1, i + 2, L"  parzysty");
            else t1.wstaw(1, i + 2, L"nie parzysty");

        }

        int k;
        int count = 1;
        int czas = 480;
        for (int i = 0;i < t1.getLineSize() - 2;i++) {
            k = i;
            if (i % 3 == 0) {
                ++count;
                k = i + 2;
                std::wstring l = std::wstring(L"(") + format(czas / 60) + L":" + format(czas % 60) + std::wstring(L"-") + format((czas + 30) / 60) + L":" + format((czas + 30) % 60) + std::wstring(L")");
                t1.wstaw(k, 1, l);
                czas += 30;
            }
        }

        for (int i = 1;i < lm.rozmiarListy();i++) {
            Dane lu = lm.pokazElementListy2(i);
            std::vector<std::wstring>li{ lu.przedmiot,lu.imie,lu.kierunek };
            t1.wstaw(lu, li);
        }


        t1.pokazWyruwnanaTablica();

        getchar();
        system("cls");


    }


};

