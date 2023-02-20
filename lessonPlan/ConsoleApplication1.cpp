#include <iostream>
#include <fstream>
#include "Tabela.h"
#include <string>

int main()
{
    std::cout << "Informatyka Sem I grp 5\nAktualna data 21.04.2020\nAktualna godzina 12:20                   Tydzien nieparzysty\n\n";
    int lineSize = 31;
    int columnSize = 7;
    std::ifstream odczyt;
    odczyt.open("C:\\Users\\HP\\Desktop\\c++\\26.06\\piatek\\Debug\\liczby.txt");

    std::string** polaa = new std::string * [lineSize];
    for (int i = 0; i < lineSize; ++i)
    {
        polaa[i] = new std::string[columnSize];
        for (int j = 0;j < columnSize;j++) polaa[i][j] = " ";//odczyt >> polaa[i][j];
    }
    polaa[0][0] = "  ";
    polaa[0][1] = "        ";
    polaa[0][2] = "Poniedzialek";
    polaa[0][3] = "   Wtorek   ";
    polaa[0][4] = "   Sroda    ";
    polaa[0][5] = "  Czwartek  ";
    polaa[0][6] = "   Piatek   ";
    //------------------------------------------//
    
    polaa[1][0] = "1.";
    polaa[1][1] = "(00:00-00:00)";
    int k;
    int count = 1;
    for (int i =0 ;i < lineSize-4;i++) {
        k = i;
        if (i % 3 == 0) {
            ++count;
            k = i + 4;
            polaa[k][0] = std::to_string(count) + ".";
            polaa[k][1] = "(00:00-00:00)";
        }
    }


    Tabela t1(lineSize, columnSize,polaa);
   t1.show();
    odczyt.close();
    
    return 0;
}

