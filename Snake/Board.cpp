#include "Board.h"

Board::Board() :x(10), y(10),c(sf::Color::Blue) {
    OdczytPliku o1("D:\\c++ graphic\\Project1\\Project1\\ustawienia\\ustawieniagry.txt", "D:\\c++ graphic\\Project1\\Project1\\ustawienia\\ustawieniasnake.txt");

    
    if (o1.odczytaneDane().theme == "black") {
        c = sf::Color::Black;
    }
    else if (o1.odczytaneDane().theme == "blue") {
        c = sf::Color::Blue;
    }
    else if (o1.odczytaneDane().theme == "cyan") {
        c = sf::Color::Cyan;
    }
    else if (o1.odczytaneDane().theme == "yellow") {
        c = sf::Color::Yellow;
    }
    else if (o1.odczytaneDane().theme == "orange") {
        c = sf::Color(222,120,31);
    }


    a.setFillColor(sf::Color::White);
    a.setSize(sf::Vector2f(10, 900));

    b.setFillColor(sf::Color::White);
    b.setSize(sf::Vector2f(800, 10));
}

void Board::draw(sf::RenderWindow& window) {
    window.clear(c);

    a.setPosition(x, y);
    window.draw(a);

    a.setPosition(x + 810, y);
    window.draw(a);

    b.setPosition(x + 10, y);
    window.draw(b);

    b.setPosition(x + 10, x + 80);
    window.draw(b);

    b.setPosition(x + 10, y + 890);
    window.draw(b);
}

void Board::drawEnd(sf::RenderWindow& window) {
    window.clear(c);

    a.setPosition(x, y);
    window.draw(a);

    a.setPosition(x + 810, y);
    window.draw(a);

    b.setPosition(x + 10, y);
    window.draw(b);

  

    b.setPosition(x + 10, y + 890);
    window.draw(b);
}