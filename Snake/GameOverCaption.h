#pragma once
#include "header.h"
#include "TextFont.h"

class GameOverCaption :public TextFont {
    sf::Text gameOverText;
    sf::Text restartText;
    sf::Text bestScore;
    sf::Text currentScore;

public:
    GameOverCaption();
    void draw(sf::RenderWindow& window, int& score, int& bestscore);

};
