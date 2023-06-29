#pragma once
#include <SFML/Graphics.hpp>
#include "header.h"

class Apple {
    sf::Texture appleTexture;
    sf::Sprite apple;

public:
    Apple();
    void generateApplePosition(std::vector<sf::Vector2i>& snake);

    void draw(sf::RenderWindow& window);

    sf::Vector2i get_position();


};
