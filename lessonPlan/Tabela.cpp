#include "Tabela.h"
#include <string>
int cos(std::wstring dzienTygodnia) {
    if (dzienTygodnia == L"Poniedzia³ek")return 2;
    else if (dzienTygodnia == L"Wtorek")return 4;
    else if (dzienTygodnia == L"Œroda")return 6;
    else if (dzienTygodnia == L"Czwartek")return 8;
    else return 10;
    
}
int cos2(std::wstring godzina) {
    return 6*stoi(godzina) - 46;
}
int ustalMiejsceKolumny(std::wstring dzienTygodnia, std::wstring tydzien) {
    if (tydzien == L"NP")return cos(dzienTygodnia);
    else if (tydzien == L"P")return cos(dzienTygodnia);
    else if (tydzien == L"N") return cos(dzienTygodnia) + 1;
}
int  ustalMiejsceLini(std::wstring godzina) {
    return cos2(godzina);
}


Tabela::Tabela() : lineSize(0), columnSize(0), pola(nullptr){}
Tabela::Tabela(int ls, int cs) : lineSize(ls), columnSize(cs)
{
    pola = new std::wstring * [lineSize];
    for (int i = 0; i < lineSize; ++i)
    {
        pola[i] = new std::wstring[columnSize];
        for (int j = 0;j < columnSize;j++)
        {
            pola[i][j] = L" ";
        }
    }
}
Tabela::Tabela(int ls, int cs, std::wstring** x) : lineSize(ls), columnSize(cs), pola(x) {}
void Tabela::pokazWyruwnanaTablica() {
    int* biggestNumberInColumn = new int[columnSize];
    for (int i = 0;i < columnSize;i++)
        biggestNumberInColumn[i] = pola[0][i].size();
    //najdluzsze slowo w kolumnie
    for (int i = 0;i < columnSize;i++)
    {
        for (int j = 0;j < lineSize;j++)if (biggestNumberInColumn[i] < pola[j][i].size())biggestNumberInColumn[i] = pola[j][i].size();
    }
    //wyrownanie kolumn do najdluzszego slowa w kolumnie i pokazanie tabeli
    int w;
    for (int i = 0;i < lineSize;i++)
    {
        for (int j = 0;j < columnSize;j++)
        {
            std::wcout << pola[i][j];
            w = pola[i][j].size() - biggestNumberInColumn[j];

            for (int k = 0;k < abs(w);k++)
                std::cout << " ";
            std::cout << "|";
        }
        std::cout << std::endl;
    }
    delete[]biggestNumberInColumn;
}
void Tabela::wstaw(int x, int y,std::wstring tekst) {
    if (x < 0 or y < 0)return;
    if (x >= lineSize or y >= columnSize) {
        int starelineSize =  lineSize;
        int starecolumnSize = columnSize;
        std::wstring** stare = pola;
       
        if(y<=columnSize)powiekszTablice(x+1 , columnSize+1);
        else if(x<=lineSize)powiekszTablice(lineSize +1 , y+1 );
        else powiekszTablice(x + 1, y + 1);
 
        wypelnilStarymiDanymi(starelineSize, starecolumnSize, stare);  
    } 
    pola[x][y] = tekst;
}
void Tabela::wstaw(int x, int y, std::vector<std::wstring>lista) {
    for (int i = 0;i < lista.size();i++)
        wstaw(x + i, y, lista[i]);
}
int Tabela::getLineSize() {
    return lineSize;
}
void Tabela::wypelnilStarymiDanymi(int x, int y, std::wstring** stare) {
    for (int i = 0; i < x; ++i)
    {
        for (int j = 0;j < y;j++)
            pola[i][j] = stare[i][j];

    }
}
void Tabela::powiekszTablice(int nowyLinesize,int nowyColumnSize) {
    lineSize = nowyLinesize;
    columnSize = nowyColumnSize;
    pola = new std::wstring * [lineSize];
    for (int i = 0; i < lineSize; ++i)
       pola[i] = new std::wstring[columnSize];   
}
Tabela::~Tabela() {
    for (int i = 0; i < lineSize; i++)
        delete[] pola[i];
    delete[] pola;

    pola = nullptr;
}

void Tabela::wstaw(Dane d, std::vector<std::wstring>sdfa){
    if (d.tydzien==L"NP") { 
       wstaw(ustalMiejsceLini(d.godzinaRozpoczecia), ustalMiejsceKolumny(d.dzienTygodnia, L"N"), sdfa);
       wstaw(ustalMiejsceLini(d.godzinaRozpoczecia), ustalMiejsceKolumny(d.dzienTygodnia, L"P"), sdfa);
    }else {
        wstaw(ustalMiejsceLini(d.godzinaRozpoczecia), ustalMiejsceKolumny(d.dzienTygodnia, d.tydzien), sdfa);
    }
        
}
