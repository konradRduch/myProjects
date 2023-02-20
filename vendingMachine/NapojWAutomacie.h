#pragma once
#include <string>
#include <iostream>

struct NapojWAutomacie {
    std::string nazwa;
    std::string cena;
    std::string liczbaSztuk;

    NapojWAutomacie() :nazwa(""), cena(""), liczbaSztuk("") {}
    NapojWAutomacie(std::string n, std::string c, std::string ls) :nazwa(n), cena(c), liczbaSztuk(ls) {}


    NapojWAutomacie& operator=(const NapojWAutomacie& copy) {
        nazwa = copy.nazwa;
        cena = copy.cena;
        liczbaSztuk = copy.liczbaSztuk;
        return *this;
    }
    friend std::ostream& operator<<(std::ostream& output, NapojWAutomacie& obj) {
        output << obj.nazwa << "\n" << obj.cena << "\n" << obj.liczbaSztuk;
        return output;
    }
    void set(NapojWAutomacie o) {
        nazwa = o.nazwa;
        cena = o.cena;
        liczbaSztuk = o.liczbaSztuk;
    }


};