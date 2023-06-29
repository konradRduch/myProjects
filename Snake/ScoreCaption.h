#pragma once
#include "TextFont.h"
#include "header.h"
class ScoreCaption : public TextFont {
    sf::Text scoreText;
public:
    ScoreCaption();
    void draw(sf::RenderWindow& window, int& score);
};
