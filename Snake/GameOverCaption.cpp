#include "GameOverCaption.h"


GameOverCaption::GameOverCaption() {
    int i = 0;
    std::string f1;
    std::string f2;

    gameOverText.setFont(font);
    gameOverText.setCharacterSize(80);
    gameOverText.setFillColor(sf::Color::White);
    gameOverText.setPosition((WIDTH / 2 - 200), HEIGHT / 4);
    gameOverText.setString("GAME OVER");

    currentScore.setFont(font);
    currentScore.setCharacterSize(40);
    currentScore.setFillColor(sf::Color::White);
    currentScore.setPosition((WIDTH / 2 - 200), HEIGHT / 2 - 45);
    

    bestScore.setFont(font);
    bestScore.setCharacterSize(40);
    bestScore.setFillColor(sf::Color::White);
    bestScore.setPosition((WIDTH / 2 - 200), HEIGHT / 2);                     
    

    restartText.setFont(font);
    restartText.setCharacterSize(30);
    restartText.setFillColor(sf::Color::White);
    restartText.setPosition((WIDTH / 2 - 200) , HEIGHT / 2 + 100);
    restartText.setString("PRESS ENTER TO RESTART");


}
void GameOverCaption::draw(sf::RenderWindow& window, int &score, int &bestscore) {
    bestScore.setString("BEST SCORE     " + std::to_string(bestscore));
    currentScore.setString("SCORE                    " + std::to_string(score));
    window.draw(gameOverText);
    window.draw(bestScore);
    window.draw(currentScore);
    window.draw(restartText);
}