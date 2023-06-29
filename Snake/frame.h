#pragma once
#include <SFML/Graphics.hpp>
class frame
{
    sf::RectangleShape a;
    sf::RectangleShape b;
    int x;
    int y;
public:
    frame();
    void draw(sf::RenderWindow& window);
};

