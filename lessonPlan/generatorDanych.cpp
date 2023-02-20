#include "generatorDanych.h"
#include <fstream>
#include <vector>
#include "Lista.h"
#include "OdczytPliku.h"
#include "ZapisPliku.h"

class nauczyciel
{
    std::wstring nazwa;
    std::wstring przedmiot;
public:
    nauczyciel(std::wstring n, std::wstring p) : nazwa(n), przedmiot(p) {}
    std::wstring getImiePrzedmiot() {
        return nazwa + L"\n" + przedmiot + L"\n";
    }


};


bool imieDamskie(std::wstring imie) {
    if (imie[imie.size() - 1] == L'a')return true;
    return false;
}
void pobierzDane(std::string sciezka, Lista &x) {
//std::vector<std::wstring>f{ };
//std::wofstream zapis;
//zapis.open("nazwiskaD.txt");
//for (int i = 0;i < f.size();i++) {
//   zapis << f[i]<<std::endl;
//}
//zapis.close();
   std::wstring imie;
   std::wifstream odczyt;
   odczyt.open(sciezka);
   while (odczyt.good()) {
       odczyt >> imie;
       x.dodajElement(imie);
   }
   odczyt.close();
    
}
std::wstring generujimieNazwisko(){
    Lista imie;
    Lista nazwiskoM;
    Lista nazwiskoD;
    pobierzDane("C:\\Users\\HP\\Desktop\\Plan Lekcji\\PlanLekcji\\PlanLekcji\\imiona.txt",imie);
    pobierzDane("C:\\Users\\HP\\Desktop\\Plan Lekcji\\PlanLekcji\\PlanLekcji\\nazwiskaM.txt", nazwiskoM);
    pobierzDane("C:\\Users\\HP\\Desktop\\Plan Lekcji\\PlanLekcji\\PlanLekcji\\nazwiskaD.txt", nazwiskoD);
    
    int k = rand() % imie.rozmiarListy()+1;
    int nD = rand() % nazwiskoD.rozmiarListy() + 1;
    int nM = rand() % nazwiskoM.rozmiarListy() + 1;
    
    if (imieDamskie( imie.pokazElementListy(k) )) {
         std::wcout << imie.pokazElementListy(k) << L" " << nazwiskoD.pokazElementListy(nD) << " \n";
        return imie.pokazElementListy(k) +L" "+ nazwiskoD.pokazElementListy(nD);
    }
    else {
         std::wcout << imie.pokazElementListy(k) << L" " << nazwiskoM.pokazElementListy(nM) << " \n";
        return imie.pokazElementListy(k) + L" " + nazwiskoM.pokazElementListy(nM);
    }

 
}

std::wstring generatorDanych::generujNauczyciela(int i) {
    Lista l;
    OdczytPliku o("C:\\Users\\HP\\Desktop\\dokument.txt");
    l = o.odczytaneDane();
    return l.pokazElementListy(i);
    
}
std::wstring generatorDanych::generujSale() {
    return L"sala " + std::to_wstring((rand() % 115) + 1)+ L"\n";
}
std::wstring generatorDanych::generujGrupe() {
    std::vector<std::wstring> kierunek{ L"Informatyka",L"Automatyka",L"Teleinformatyka" };
    std::vector<std::wstring> semestr {L"sem1",L"sem2",L"sem3",L"sem4",L"sem5",L"sem6",L"sem7"};
    return kierunek[rand() % kierunek.size()] + L" " + semestr[rand() % semestr.size()] + L" grupa " + std::to_wstring((rand() % 3) + 1) + L"\n";
}
std::wstring generatorDanych::generujPrzedmiot(){
    std::wstring przedmiot[6]{ L"wf",L"polski",L"angielski",L"matematyka",L"historia",L"plastyka" };
    return przedmiot[rand() % 6] ; 
}



std::wstring generatorDanych::generujDzienTygodnia(std::wstring dzientygodnia) {
    std::wstring tekst= generujGrupe() + generujSale() + dzientygodnia +L"\n" + generujGodzine() + generujTydzien() + generujTypPrzedmiotu();
    return tekst;
}
std::wstring generatorDanych::generujGodzine() {
    std::wstring godzina[11]{ L"8", L"9", L"10 ", L"11 ", L"12 ", L"13 ", L"14 ", L"15 ", L"16 ", L"17 ", L"18" };
    return godzina[rand() % 11]+L"\n";
}
std::wstring generatorDanych::generujTypPrzedmiotu() {
    std::wstring typPrzedmiotu[3]{L"cw", L"wyk", L"lab"};
    return typPrzedmiotu[rand() % 3] ;
}
std::wstring generatorDanych::generujTydzien() {
    std::wstring tydzien[3]{ L"P",L"N",L"NP"};
    return tydzien[rand() % 3] + L"\n";

}

void generatorDanych::generuj() {
    Lista d;
    for (int i = 1;i <= 150;i++)
        d.dodajElement(generujPlanDlaNauczyciela(i));

    ZapisPliku z("C:\\Users\\HP\\Desktop\\planNauczycieli.txt",d);
}



std::wstring generatorDanych::generujPlanDlaNauczyciela(int ix) {
    std::wstring tekst=L"";
   nauczyciel l(generujNauczyciela(ix), generujPrzedmiot());
 
   for (int i = 0;i < 7;i++) {
       tekst += l.getImiePrzedmiot() + generujDzienTygodnia(L"Poniedzia³ek") + L"\n";
   }
   for (int i = 0;i < 4;i++) {
       tekst += l.getImiePrzedmiot() + generujDzienTygodnia(L"Wtorek") + L"\n";
   }
   for (int i = 0;i < 5;i++) {
       tekst += l.getImiePrzedmiot() + generujDzienTygodnia(L"Œroda") + L"\n";
   }
   for (int i = 0;i < 5;i++) {
       tekst += l.getImiePrzedmiot() + generujDzienTygodnia(L"Czwartek") + L"\n";
   }
   for (int i = 0;i < 7;i++) {
       tekst += l.getImiePrzedmiot() + generujDzienTygodnia(L"Pi¹tek") + L"\n";
   }
   tekst += l.getImiePrzedmiot() + generujDzienTygodnia(L"Pi¹tek");
    return tekst;

}


//generuj plan dla sali
//generuj plan dla danej grupy zajeciowej






