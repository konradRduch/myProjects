#pragma once

#include <SFML/Graphics.hpp>
#include <iostream>
#include "sOptions.h"
#include "ZapisPliku.h"
#include "OdczytPliku.h"
#include "header.h"
#include "Board.h"

class Opcje
{
   

    bool fun1(std::string x) {
        if (x == "arrows")return false;
        else return true;
    }
    int fun2(std::string x) {
        if (x == "easy")return 0;
        else if (x == "medium")return 1;
        else if (x == "hard")return 2;
    }
    int fun3(std::string x) {
        if (x == "on")return 0;
        else return 1;
    }
    int fun4(std::string x) {
        if (x == "black")return 0;
        else if (x == "blue")return 1;
        else if (x == "cyan")return 2;
        else if (x == "yellow")return 3;
        else if (x == "orange")return 4;
    }

public:

    Opcje() :  selectedIndex(0), controlsTextIndex(0), difficultyTextIndex(1), soundTextIndex(2), themeTextIndex(3) {


        window.create(sf::VideoMode(WIDTH, HEIGHT), "opcje");
        window.setFramerateLimit(60);

        OdczytPliku o1("D:\\c++ graphic\\Project1\\Project1\\ustawienia\\ustawieniagry.txt", "D:\\c++ graphic\\Project1\\Project1\\ustawienia\\ustawieniasnake.txt");
        
        sO1 = o1.odczytaneDane();


        isAWSD = fun1(sO1.controls);
        difficultyLevel = fun2(sO1.difficulty);
        soundLevel = fun3(sO1.sound);
        themeLevel = fun4(sO1.theme);

        font.loadFromFile("BAHNSCHRIFT 1.TTF");

        capitonText.setFont(font);
        capitonText.setString("OPTIONS");
        capitonText.setCharacterSize(80);
        capitonText.setFillColor(sf::Color::White);
        capitonText.setPosition(sf::Vector2f(240.0f, 80.0f));


        // Inicjalizacja przycisków
        for (int i = 0; i < 5; i++) {
            sf::Text button;
            button.setFont(font);
            button.setCharacterSize(40);
            button.setFillColor(sf::Color::White);
            button.setPosition(230, 200 + (i * 50));
            buttons.push_back(button);
        }

        // Ustawienie etykiet przycisków
        buttons[0].setString("CONTROLS  ");
        buttons[1].setString("DIFFICULTY");
        buttons[2].setString("SOUND           ");
        buttons[3].setString("THEME            ");
        buttons[4].setString("EXIT");

        // Inicjalizacja tekstów obok przycisków
        sf::Text controlsText;
        controlsText.setFont(font);
        controlsText.setCharacterSize(30);
        controlsText.setFillColor(sf::Color::White);
        controlsText.setString(sO1.controls);
        controlsText.setPosition(buttons[0].getPosition().x + buttons[0].getGlobalBounds().width + 20, buttons[0].getPosition().y);
        texts.push_back(controlsText);

        sf::Text difficultyText;
        difficultyText.setFont(font);
        difficultyText.setCharacterSize(30);
        difficultyText.setFillColor(sf::Color::White);
        difficultyText.setString(sO1.difficulty);
        difficultyText.setPosition(buttons[1].getPosition().x + buttons[1].getGlobalBounds().width + 20, buttons[1].getPosition().y);
        texts.push_back(difficultyText);

        sf::Text soundText;
        soundText.setFont(font);
        soundText.setCharacterSize(30);
        soundText.setFillColor(sf::Color::White);
        soundText.setString(sO1.sound);
        soundText.setPosition(buttons[2].getPosition().x + buttons[2].getGlobalBounds().width + 20, buttons[2].getPosition().y);
        texts.push_back(soundText);

        sf::Text themeText;
        themeText.setFont(font);
        themeText.setCharacterSize(30);
        themeText.setFillColor(sf::Color::White);
        themeText.setString(sO1.theme);
        themeText.setPosition(buttons[3].getPosition().x + buttons[3].getGlobalBounds().width + 20, buttons[3].getPosition().y);
        texts.push_back(themeText);
    }

    void handleEvents() {
        sf::Event event;
        while (window.pollEvent(event)) {
            if (event.type == sf::Event::Closed) {

                window.close();
            }

            if (event.type == sf::Event::KeyPressed) {
                handleKeyPress(event.key);
            }

            if (event.type == sf::Event::MouseButtonPressed) {
                if (event.mouseButton.button == sf::Mouse::Left) {
                    sf::Vector2i mousePos = sf::Mouse::getPosition(window);
                    handleMouseClick(mousePos);
                }
            }
        }
    }

    void update() {

    }

    void render() {
        window.clear();
        b1.drawEnd(window);

        window.draw(capitonText);
        for (int i = 0; i < buttons.size(); i++) {
            buttons[i].setFillColor(i == selectedIndex ? sf::Color::Green : sf::Color::White);
            window.draw(buttons[i]);
        }

        for (int i = 0; i < texts.size(); i++) {
            if (i == controlsTextIndex) {
                texts[i].setString(isAWSD ? "awsd" : "arrows");
                if (isAWSD) {
                    sO1.controls = "awsd";
                }
                else {
                    sO1.controls = "arrows";
                }

            }
            else if (i == difficultyTextIndex) {
                std::string difficulty;
                if (difficultyLevel == 0) {
                    difficulty = "easy";
                }
                else if (difficultyLevel == 1) {
                    difficulty = "medium";
                }
                else if (difficultyLevel == 2) {
                    difficulty = "hard";
                }
                texts[i].setString(difficulty);
                sO1.difficulty = difficulty;
            }
            else if (i == soundTextIndex) {
                std::string s;
                if (soundLevel == 0) {
                    s = "on";
                }
                else if (soundLevel == 1) {
                    s = "off";
                }
                texts[i].setString(s);
                sO1.sound = s;
            }
            else if (i == themeTextIndex) {
                std::string t;
                if (themeLevel == 0) {
                    t = "black";
                }
                else if (themeLevel == 1) {
                    t = "blue";
                }
                else if (themeLevel == 2) {
                    t = "cyan";
                }
                else if (themeLevel == 3) {
                    t = "yellow";
                }
                else if (themeLevel == 4) {
                    t = "orange";
                }
                texts[i].setString(t);
                sO1.theme = t;
            }
            window.draw(texts[i]);
        }
        window.display();
    }

    void run() {
        while (window.isOpen()) {
            handleEvents();
            update();
            render();
        }
    }

private:


    sf::Text capitonText;
    Board b1;
    sf::RenderWindow window;
    sf::Font font;
    std::vector<sf::Text> buttons;
    std::vector<sf::Text> texts;
    int selectedIndex; // Indeks wybranego przycisku
    int controlsTextIndex; // Indeks tekstu obok przycisku CONTROLS
    int difficultyTextIndex; // Indeks tekstu obok przycisku DIFFICULTY
    int soundTextIndex;
    int themeTextIndex;

    sOptions sO1;

    bool isAWSD; // Flaga okreœlaj¹ca, czy jest wyœwietlany tekst "awsd"
    int difficultyLevel; // Poziom trudnoœci (0 - easy, 1 - medium, 2 - hard)
    int soundLevel;
    int themeLevel;

    void handleKeyPress(const sf::Event::KeyEvent& keyEvent) {
        if (keyEvent.code == sf::Keyboard::Up) {
            selectedIndex = (selectedIndex - 1 + buttons.size()) % buttons.size();
        }
        else if (keyEvent.code == sf::Keyboard::Down) {
            selectedIndex = (selectedIndex + 1) % buttons.size();
        }
        else if (keyEvent.code == sf::Keyboard::Enter) {
            handleButtonClicked(selectedIndex);
        }
    }

    void handleMouseClick(const sf::Vector2i& mousePos) {
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons[i].getGlobalBounds().contains(mousePos.x, mousePos.y)) {
                handleButtonClicked(i);
                break;
            }
        }
    }

    void handleButtonClicked(int index) {
        std::cout << "Button clicked: " << buttons[index].getString().toAnsiString() << std::endl;

        if (index == 0) {
            // Przycisk "CONTROLS" zosta³ klikniêty
            isAWSD = !isAWSD; // Odwracamy flagê
        }
        else if (index == 1) {
            // Przycisk "DIFFICULTY" zosta³ klikniêty
            difficultyLevel = (difficultyLevel + 1) % 3; // Zwiêkszamy poziom trudnoœci (0 - easy, 1 - medium, 2 - hard)
        }
        else if (index == 2) {
            soundLevel = (soundLevel + 1) % 2;
        }
        else if (index == 3) {
            themeLevel = (themeLevel + 1) % 5;
        }
        else if (index == 4) {
            ZapisPliku("D:\\c++ graphic\\Project1\\Project1\\ustawienia\\ustawieniagry.txt", "D:\\c++ graphic\\Project1\\Project1\\ustawienia\\ustawieniasnake.txt", sO1);
            std::cout << sO1;

            // Przycisk "EXIT" zosta³ klikniêty, mo¿na zamkn¹æ okno lub zakoñczyæ program
            window.close(); // Odkomentuj tê liniê, jeœli chcesz zamkn¹æ okno po klikniêciu przycisku "EXIT"
        }
    }

};

