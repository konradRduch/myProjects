#include "ScoreCaption.h"


ScoreCaption::ScoreCaption() {
    scoreText.setFont(font);
    scoreText.setCharacterSize(50);
    scoreText.setFillColor(sf::Color::White);
    scoreText.setPosition(35, 20);
}
void ScoreCaption::draw(sf::RenderWindow& window, int& score) {
    scoreText.setString("Score: " + std::to_string(score));
    window.draw(scoreText);
}