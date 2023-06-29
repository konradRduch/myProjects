#include "frame.h"


frame::frame() :x(10), y(10) {
    a.setFillColor(sf::Color::White);
    a.setSize(sf::Vector2f(10, 900));

    b.setFillColor(sf::Color::White);
    b.setSize(sf::Vector2f(800, 10));
}

void frame::draw(sf::RenderWindow& window) {
    window.clear(sf::Color::Black);

    a.setPosition(x, y);
    window.draw(a);

    a.setPosition(x + 810, y);
    window.draw(a);

    b.setPosition(x + 10, y);
    window.draw(b);

    b.setPosition(x + 10, y + 890);
    window.draw(b);
}