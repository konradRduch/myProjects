#pragma once
#include "header.h"
#include "OdczytPliku.h"

class Board {
    sf::Color c;
    sf::RectangleShape a;
    sf::RectangleShape b;
    int x;
    int y;
public:
    Board();
    void draw(sf::RenderWindow& window);
    void drawEnd(sf::RenderWindow& window);

};
