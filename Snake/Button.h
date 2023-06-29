#pragma once
#include <SFML/Graphics.hpp>

class Button {

    sf::Vector2f position;
    sf::Vector2f size;
    std::string text;
    sf::Text textObj;
    sf::Font font;
    sf::RectangleShape buttonShape;
public:
    Button() {}
    Button(sf::Vector2f position, sf::Vector2f size, std::string text)
        : position(position), size(size), text(text)
    {
        // Inicjalizacja tekstu
        font.loadFromFile("BAHNSCHRIFT 1.ttf");
        textObj.setFont(font);
        textObj.setString(text);
        textObj.setCharacterSize(30);
        textObj.setFillColor(sf::Color::Black);

        // Ustawienie pozycji tekstu na œrodku przycisku
        sf::FloatRect textBounds = textObj.getLocalBounds();
        textObj.setOrigin(textBounds.left + textBounds.width / 2,
            textBounds.top + textBounds.height / 2);
        textObj.setPosition(position.x + size.x / 2, position.y + size.y / 2);

        // Inicjalizacja prostok¹ta przycisku
        buttonShape.setPosition(position);
        buttonShape.setSize(size);
        buttonShape.setFillColor(sf::Color::Green);
    }

    void setbutton(sf::Vector2f position, sf::Vector2f size, std::string text) {
        // Inicjalizacja tekstu
        font.loadFromFile("BAHNSCHRIFT 1.ttf");
        textObj.setFont(font);
        textObj.setString(text);
        textObj.setCharacterSize(30);
        textObj.setFillColor(sf::Color::Black);

        // Ustawienie pozycji tekstu na œrodku przycisku
        sf::FloatRect textBounds = textObj.getLocalBounds();
        textObj.setOrigin(textBounds.left + textBounds.width / 2,
            textBounds.top + textBounds.height / 2);
        textObj.setPosition(position.x + size.x / 2, position.y + size.y / 2);

        // Inicjalizacja prostok¹ta przycisku
        buttonShape.setPosition(position);
        buttonShape.setSize(size);
        buttonShape.setFillColor(sf::Color::Green);
    }
    void draw(sf::RenderWindow& window) {
        window.draw(buttonShape);
        window.draw(textObj);
    }
    void changeColor(sf::Color type) {
        buttonShape.setFillColor(type);
    }

};

