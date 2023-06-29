#include "PauseCaption.h"

PauseCaption::PauseCaption() {
    pauseText.setFont(font);
    pauseText.setCharacterSize(50);
    pauseText.setFillColor(sf::Color::White);
    pauseText.setPosition(WIDTH / 2 - 100, HEIGHT / 2 - 25);
    pauseText.setString("Paused");
}
void PauseCaption::draw(sf::RenderWindow& window) {
    window.draw(pauseText);
}