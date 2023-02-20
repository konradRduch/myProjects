#include <iostream>
#include "Tabela.h"
#include <string>
#include <sstream>
#include "Lista.h"
#include "NapojWAutomacie.h"
#include "OdczytPliku.h"
#include "ZapisPliku.h"
#include <Windows.h>
#include "Reszta.h"
//--------------------------------------
#include "Screen.h"
#include "Owner.h"
#include "Customer.h"
#include <limits>

#undef max;
//--------------------------------------


#include "vendingMachineLayout.h"
std::string format(std::string str) {

    int x = str.find(".");
    if (x > 0) {
        x += 3;
        str.erase(x, str.size() - 1);
        return str;
    }
    else {
        return str;
    }
}
bool isNumber(std::string x) {
    bool c = true;
    for (int i = 0;i < x.size();i++) {
        if(x[i]!='.')c *= isdigit(x[i]);
    }
    return c;
}
bool isgood(std::string liczba) {
    if (liczba == "50pln")return 1;
    else if (liczba == "20pln")return 1;
    else if (liczba == "10pln")return 1;
    else if (liczba == "5pln")return 1;
    else if (liczba == "2pln")return 1;
    else if (liczba == "1pln")return 1;
    else if (liczba == "50gr")return 1;
    else if (liczba == "20gr")return 1;
    else if (liczba == "10gr")return 1;
    else if (liczba == "5gr")return 1;
    else if (liczba == "2gr")return 1;
    else if (liczba == "1gr")return 1;
    else return false;
}
double nominaly(std::string liczba) {
    if (liczba == "50pln")return 50;
    else if (liczba == "20pln")return 20;
    else if (liczba == "10pln")return 10;
    else if (liczba == "5pln")return 5;
    else if (liczba == "2pln")return 2;
    else if (liczba == "1pln")return 1;
    else if (liczba == "50gr")return 0.5;
    else if (liczba == "20gr")return 0.2;
    else if (liczba == "10gr")return 0.10;
    else if (liczba == "5gr")return 0.05;
    else if (liczba == "2gr")return 0.02;
    else if (liczba == "1gr")return 0.01;
    else return 0;
}

void middle(std::string text,std::string tab) {
  
    //std::string tab = "                                                                                               \n";
    if (text.size() > tab.size() - 1) { 
        middle("ERROR, NAPIS SIE NIE MIESCI",tab);
        return; 
    }
    int position = tab.size() / 2 - text.size() / 2;
    tab.replace(position, text.size(),text);
    std::cout << tab;
}
void middle(std::string text,std::string tab,char x) {

   // std::string tab = "                                                                                               \n";
    tab[tab.size()-2] = x;
    if (text.size() > tab.size() - 2) {
        middle("ERROR, NAPIS SIE NIE MIESCI",tab,x);
        return;
    }
    int position = (tab.size()-1) / 2 - text.size() / 2;
    tab.replace(position, text.size(), text);
    std::cout << tab;
}

std::string generuj(int rozmiar,std::string l) {
    std::string x = "";
    for (int i = 0;i < rozmiar;i++)x += l;
    return x;
}
std::string generuj(int rozmiar,std::string k, char l) {
    std::string x = "";
    for (int i = 0;i < rozmiar;i++)x += k;
    x[x.size() - 1] = l;
    return x;
}

std::string zawijanieTextu(std::string text, int line_length)
{
    std::istringstream words(text);
    std::ostringstream wrapped;
    std::string word;
    if (words >> word) {
        wrapped << word;
        int space_left = line_length - word.length();
        while (words >> word) {
            if (space_left < word.length() + 1) {
                wrapped << '\n' << word;
                space_left = line_length - word.length();
            }
            else {
                wrapped << ' ' << word;
                space_left -= word.length() + 1;
            }
        }
    }
    return wrapped.str();
}
Lista<std::string>string_to_list(std::string text) {

    Lista<std::string>wrappedList;
    std::string temp;
    for (int i = 0;i < text.size();i++) {
        temp += text[i];
        if (text[i] == '\n') {
            temp.pop_back();
            wrappedList.dodajElement(temp);
            temp.clear();
        }
    }
    wrappedList.dodajElement(temp);
    return wrappedList;
}


void ekranWiadomosciAutomatu(std::string text,int size) {

    Lista<std::string>lista = string_to_list(zawijanieTextu(text, size - 1));
   
    std::cout << generuj(size, " ", '|');std::cout << std::endl;
    for (int i = 1;i <= lista.rozmiarListy();i++)
    middle(lista.pokazElementListy(i), generuj(size + 1 , " ", '\n'), '|');
    std::cout << generuj(size, "_", '|');std::cout << std::endl;
}
void wypelij(Tabela& automat, Lista<NapojWAutomacie>&listaNapojow) {
    int om = 1;
    for (int j = 0;j < 20;j += 5)
    {
        for (int i = 0;i < 5;i++)automat.wstaw(j, i, listaNapojow.pokazElementListy(om++));
    }
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

}
void show(Tabela& automat, std::string wyswietlacz="", std::string reszta = "", std::string produkt = "") {
   

    OdczytPliku odczytaneDane("C:\\Users\\HP\\Desktop\\mikroprogram\\napoje.txt");
    Lista<NapojWAutomacie> listaNapojow;
    listaNapojow = odczytaneDane.odczytaneDane();
    
    wypelij(automat, listaNapojow);
    

    int szerokoscTabeli = (automat.maxDlugiWyrazWTabeli() * automat.getColumnSize()) + automat.getColumnSize();
    
    middle("AUTOMAT Z NAPOJAMI", generuj(szerokoscTabeli, " ", '\n'));
    std::cout << generuj(szerokoscTabeli, "_",'\n');
    
    automat.pokazWyruwnanaTablica2();
    
    ekranWiadomosciAutomatu(wyswietlacz, szerokoscTabeli);
    ekranWiadomosciAutomatu(reszta, szerokoscTabeli);
    ekranWiadomosciAutomatu(produkt, szerokoscTabeli);
}
    
void napojMniej(int numer, NapojWAutomacie nowy) {
    OdczytPliku odczytaneDane("C:\\Users\\HP\\Desktop\\mikroprogram\\napoje.txt");
    Lista<NapojWAutomacie> listaNapojow;
    listaNapojow = odczytaneDane.odczytaneDane();
    listaNapojow.zmienElementListy(numer, nowy);
    ZapisPliku zapis("C:\\Users\\HP\\Desktop\\mikroprogram\\napoje.txt", listaNapojow);


}
void uzupelnijAutomat() {
    OdczytPliku odczyt("C:\\Users\\HP\\Desktop\\mikroprogram\\uzupelnienie.txt");
    Lista<NapojWAutomacie> listaNapojow;
    listaNapojow = odczyt.odczytaneDane();
    ZapisPliku zapis("C:\\Users\\HP\\Desktop\\mikroprogram\\napoje.txt", listaNapojow);
}

double lm;
//2494.75
double drobneAutomatu = 2600;
//------------------------------------------------------------------------------------
int setChoice(int n) {
    if (n >= 1 and n <= 20)return 1;
    else if (n == 21)return 2;
    else return 3;
}
enum choiceResult {
   customer=1, owner, outOfRange 
};
void enter(int &number) {
    
    std::cout << "wybierz numer ";
    if (std::cin >> number)return;
    else number = 0;
    
    std::cin.clear(); // clears the error flags
    // this line discards all the input waiting in the stream
    std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
   
}

int main()
{
    
    Tabela automat(20, 5);
    for (;;) {
        system("cls");
        show(automat, "WYBIERZ PRODUKT", "RESZTA: ","ODBIERZ: ");
        int numerNapoju;
        std::cout << ">";std::cin >> numerNapoju;
        if (numerNapoju >= 1 and numerNapoju <= 20) {
            //opcje kupujacego
            OdczytPliku odczytaneDane("C:\\Users\\HP\\Desktop\\mikroprogram\\napoje.txt");
            Lista<NapojWAutomacie> listaNapojow;
            listaNapojow = odczytaneDane.odczytaneDane();
            NapojWAutomacie temp = listaNapojow.pokazElementListy(numerNapoju);
            std::string w = "WYBRANO " + temp.nazwa + " DO ZAPLACENIA " + temp.cena + "PLN, wrzuc monety i napisz OK  (dostepne nominaly to 50pln, 20pln, 10pln, 5pln, 2pln, 1pln, 50gr, 20gr, 10gr, 5gr, 2gr, 1gr), obecnie wrzucono 0.00 PLN ";
            std::string r = "RESZTA: ";
            std::string o = "ODBIERZ:";
            if (stoi(temp.liczbaSztuk) > 0){
                system("cls");
                show(automat, w, r, o);
                double kwota;
                //---------------------wrzucanie monet------------------
                double suma = 0;
                std::string money;
                do {
                    std::cin >> money;
                    if (isgood(money))suma += nominaly(money);
                    system("cls");
                    w = "WYBRANO " + temp.nazwa + " DO ZAPLACENIA " + temp.cena + "PLN, wrzuc monety i napisz OK (dostepne nominaly to 50pln, 20pln, 10pln, 5pln, 2pln, 1pln, 50gr, 20gr, 10gr, 5gr, 2gr, 1gr), obecnie wrzucono " + format(std::to_string(suma)) +" PLN";
                    show(automat, w, r, o);
                }while (money != "ok");
                kwota = suma;
                lm = kwota;
                //------------------------------------------------------
                
                if ((drobneAutomatu + kwota) <=2500) {
                        system("cls");
                        if (kwota >= stod(temp.cena)) {
                            w = "tranzakcja zaakceptowana, wpisz odbierz zeby odebrac swoj zakup i kontynulowac";
                            std::string reszta = std::to_string(kwota - stod(temp.cena));
                            Reszta re(format(reszta));
                            r = re.rozmienNaDrobne();
                            o = "ODBIERZ: " + temp.nazwa;
                            std::string nowaIlosc = std::to_string(stoi(temp.liczbaSztuk) - 1);
                            NapojWAutomacie nowy(temp.nazwa, temp.cena, nowaIlosc);
                            napojMniej(numerNapoju, nowy);
                            drobneAutomatu += stod(temp.cena);
                        }
                        else {
                            w = "tranzakcja odrzucona, brak wystarczajacych srodkow, wpisz odbierz zeby odebrac wrzucone pieniadze i kontynulowac";
                            r = format(std::to_string(kwota));
                            o = "odbierz ";
                        }
                        show(automat, w, r, o);
                        std::string odbior;
                        std::cout << ">";std::cin >> odbior;
                        while (odbior != "odbierz") {
                            system("cls");
                            show(automat, w, r, o);
                            std::cout << ">";std::cin >> odbior;
                        }
                        system("cls");
                }
                else{
                    system("cls");
                    w = "Automat chwilowo nie czynny, poczekaj az administrator oprozni pojemnik na drobne, wpisz odbierz zeby odebrac wrzucone pieniadze";
                    r = "reszta " + format(std::to_string(lm)) +"PLN";
                    std::string odbior;
                    show(automat, w, r, o);
                    std::cout << ">";std::cin >> odbior;
                    while (odbior != "odbierz") {
                        system("cls");
                        show(automat, w, r, o);
                        std::cout << ">";std::cin >> odbior;
                    }
                    system("cls");
                    
                }  
            
            }
            //zbiornik z napojem jest pusty
            else 
            {
                system("cls");
                w = "Zbiornik z "+ temp.nazwa +" pusty, nalezy poczekac az administrator wypelni zbiornik! Nacisnij enter zeby kontynulowac i wybrac inny produkt";
                show(automat, w, r, o);
                std::cout << ">";
                getchar();getchar();
            }
           
        }
        else if (numerNapoju == 21) {
            system("cls");
            show(automat, "Podaj haslo", "RESZTA: ", "ODBIERZ: ");
            std::string haslo; std::cin >> haslo;
            if (haslo == "1234"){
                OdczytPliku odczytaneDane("C:\\Users\\HP\\Desktop\\mikroprogram\\napoje.txt");
                Lista<NapojWAutomacie> listaNapojow;
                listaNapojow = odczytaneDane.odczytaneDane();
                
                system("cls");
                show(automat, "1)Uzupelnianie calego automatu, 2)Odebranie pieniedzy ", "RESZTA: ", "ODBIERZ: ");
                int wybor;
                std::cin >> wybor;
                try {
                    if (wybor == 1 or wybor == 2) {
                        switch (wybor) {
                        case 1:
                            uzupelnijAutomat();
                            system("cls");
                            show(automat, "Uzupelniono  automat, nacisnij enter zeby kontynulowac", "RESZTA: ", "ODBIERZ: ");
                            getchar();getchar();
                            break;
                        case 2:
                            system("cls");
                            double kwota1 = drobneAutomatu;
                            drobneAutomatu = 0;
                            show(automat, "Odebrano " + format(std::to_string(kwota1)) + "PLN, aktulany stan pojemnika to " + format(std::to_string(drobneAutomatu)) + "PLN, nacisnij enter zeby kontynulowac", "RESZTA: ", "ODBIERZ: ");
                            getchar();getchar();
                            break;
                       
                        }
                        
                    }
                    else throw(wybor);
                }
                catch (int i) {
                    system("cls");
                    show(automat, "nie ma takiej opcji, nacisnij enter zeby kontynulowac", "RESZTA: ", "ODBIERZ: ");
                    getchar();getchar();
                }
                
            }else{
                system("cls");
                show(automat, "zle haslo, nacisnij enter zeby kontynulowac", "RESZTA: ", "ODBIERZ: ");
                getchar();getchar();
            }
        }
        else {
            system("cls");
            show(automat, "wybrano numer spoza zakresu, nacisnij enter  zeby kontynulowac", "RESZTA: ", "ODBIERZ: ");
            std::cout << ">";
            getchar();getchar();
        }
    }

   // Screen *s;
   // int number;
   // for (;;) {
   //     enter(number);
   //     system("cls");
   //     switch (setChoice(number)) {
   //     case customer:
   //         s = new Customer(number);
   //         s->show();
   //         break;
   //     case owner:
   //         s = new Owner(number);
   //         s->show();
   //         delete s;
   //         break;
   //     case outOfRange:
   //         std::cout << "Bad number\n";
   //         break;
   //     }
   //
   // }
   // std::cout << std::endl;
    

  



    return 0;
}



  



