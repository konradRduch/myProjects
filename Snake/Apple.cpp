#include "Apple.h"

Apple::Apple() {
    if (!appleTexture.loadFromFile("jablko.png")) {
        // Handle texture loading error
    }
    apple.setTexture(appleTexture);
    apple.setScale(static_cast<float>(SIZE) / apple.getLocalBounds().width,
        static_cast<float>(SIZE) / apple.getLocalBounds().height);

}
void Apple::generateApplePosition(std::vector<sf::Vector2i>& snake) {
    //generowanie jablka 
    //jesli jabko bedzie sie pokrywalo z cialem snake'a wygeneruje inne wspolrzede 

    bool onSnake;
    do {
        onSnake = false;
        int appleX = (rand() % ((WIDTH / SIZE) - 2)) + 1;
        int appleY = (rand() % ((HEIGHT / SIZE) - 6)) + 5;

        for (const auto& segment : snake) {
            if (segment.x == appleX and segment.y == appleY) {
                onSnake = true;
                break;
            }
        }

        if (!onSnake) {
            apple.setPosition(appleX * SIZE, appleY * SIZE);
        }
    } while (onSnake);
}

void Apple::draw(sf::RenderWindow& window) {
    apple.setPosition(apple.getPosition());
    window.draw(apple);
}

sf::Vector2i Apple::get_position() {
    sf::Vector2i position = sf::Vector2i(apple.getPosition()) / SIZE;
    return position;
}