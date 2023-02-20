#include "Tabela.h"
#include <algorithm>

std::string formatNaSztuki(std::string x) {
    return "(" + x + ")";
}
std::string formatNaPLN(std::string x) {
    return x + " PLN";
}

Tabela::Tabela() : lineSize(0), columnSize(0), pola(nullptr){}
Tabela::Tabela(int ls, int cs) : lineSize(ls), columnSize(cs)
{
    pola = new std::string * [lineSize];
    for (int i = 0; i < lineSize; ++i)
    {
        pola[i] = new std::string[columnSize];
        for (int j = 0;j < columnSize;j++)
        {
            pola[i][j] = " ";
        }
    }
}
Tabela::Tabela(int ls, int cs, std::string** x) : lineSize(ls), columnSize(cs), pola(x) {}
void Tabela::pokazWyruwnanaTablica() {
    int* biggestNumberInColumn = new int[columnSize];
    for (int i = 0;i < columnSize;i++)
        biggestNumberInColumn[i] = pola[0][i].size();
    
    //najdluzsze slowo w kolumnie
    for (int i = 0;i < columnSize;i++)
    {
        for (int j = 0;j < lineSize;j++)
            if (biggestNumberInColumn[i] < pola[j][i].size())biggestNumberInColumn[i] = pola[j][i].size();
    }
    //wyrownanie kolumn do najdluzszego slowa w kolumnie i pokazanie tabeli
    int w;
    for (int i = 0;i < lineSize;i++)
    {
        for (int j = 0;j < columnSize;j++)
        {
            std::cout<< pola[i][j];
            w = pola[i][j].size() - biggestNumberInColumn[j];

            for (int k = 0;k < abs(w);k++)
                std::cout << " ";
            std::cout << "|";
        }
        std::cout << std::endl;
    }
    delete[]biggestNumberInColumn;
}

void Tabela::pokazWyruwnanaTablica2() {
    int w;
    for (int i = 0;i < lineSize;i++)
    {
        for (int j = 0;j < columnSize;j++)
        {
            std::cout << pola[i][j];
            w = pola[i][j].size() - maxDlugiWyrazWTabeli();

            for (int k = 0;k < abs(w);k++)
                std::cout << " ";
            std::cout << "|";
        }
        std::cout << std::endl;
    }
}

void Tabela::wstaw(int x, int y,std::string tekst) {
    if (x < 0 or y < 0)return;
    if (x >= lineSize or y >= columnSize) {
        int starelineSize =  lineSize;
        int starecolumnSize = columnSize;
        std::string** stare = pola;
       
        if(y<=columnSize)powiekszTablice(x+1 , columnSize+1);
        else if(x<=lineSize)powiekszTablice(lineSize +1 , y+1 );
        else powiekszTablice(x + 1, y + 1);
 
        wypelnilStarymiDanymi(starelineSize, starecolumnSize, stare);  
    } 
    pola[x][y] = tekst;
}
void Tabela::wstaw(int x, int y, std::vector<std::string>lista) {
    for (int i = 0;i < lista.size();i++)
        wstaw(x + i, y, lista[i]);
}
void Tabela::wstaw(int x, int y, NapojWAutomacie lista) {
        std::transform(lista.nazwa.begin(), lista.nazwa.end(), lista.nazwa.begin(), ::toupper);
        wstaw(x + 0, y, lista.nazwa);
        wstaw(x + 1, y, formatNaSztuki(lista.liczbaSztuk));
        wstaw(x + 2, y, formatNaPLN(lista.cena));
}
int Tabela::getLineSize() {
    return lineSize;
}
int Tabela::getColumnSize() {
    return columnSize;
}


void Tabela::wypelnilStarymiDanymi(int x, int y, std::string** stare) {
    for (int i = 0; i < x; ++i)
    {
        for (int j = 0;j < y;j++)
            pola[i][j] = stare[i][j];

    }
}
void Tabela::powiekszTablice(int nowyLinesize,int nowyColumnSize) {
    lineSize = nowyLinesize;
    columnSize = nowyColumnSize;
    pola = new std::string * [lineSize];
    for (int i = 0; i < lineSize; ++i)
       pola[i] = new std::string[columnSize];   
}
Tabela::~Tabela() {
    for (int i = 0; i < lineSize; i++)
        delete[] pola[i];
    delete[] pola;

    pola = nullptr;
}

int Tabela::maxDlugiWyrazWTabeli(){
    int* biggestNumberInColumn = new int[columnSize];
    for (int i = 0;i < columnSize;i++)
        biggestNumberInColumn[i] = pola[0][i].size();

    //najdluzsze slowo w kolumnie
    for (int i = 0;i < columnSize;i++)
    {
        for (int j = 0;j < lineSize;j++)
            if (biggestNumberInColumn[i] < pola[j][i].size())biggestNumberInColumn[i] = pola[j][i].size();
    }
    int min = -1;
    for (int i = 0;i < columnSize;i++)
    {
        for (int j = 0;j < lineSize;j++)
            if (biggestNumberInColumn[i]>=min)min = biggestNumberInColumn[i];
    }
    return min;
}

