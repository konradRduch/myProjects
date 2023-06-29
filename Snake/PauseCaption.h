#pragma once
#include "header.h"
#include "TextFont.h"
class PauseCaption :public TextFont {
    sf::Text pauseText;
public:
    PauseCaption();
    void draw(sf::RenderWindow& window);
};
