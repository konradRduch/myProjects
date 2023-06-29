#include <SFML/Graphics.hpp>
#include <iostream>
#include <vector>
#include "header.h"
#include "Game.h"
#include "Button.h"
#include "Board.h"

#include <fstream>
#include <filesystem>
#include "Opcje.h"
#include "OdczytPliku.h"
#include "HScores.h"

namespace fs = std::filesystem;
void createfiles() {
    std::string baseDir = "D:/c++ graphic/Project1/Project1/";

    // Tworzenie folderu "rekordy"
    std::string rekordyDir = baseDir + "rekordy/";
    if (!fs::exists(rekordyDir)) {
        fs::create_directory(rekordyDir);
        std::cout << "Utworzono folder 'rekordy'." << std::endl;
    }
    else {
        std::cout << "Folder 'rekordy' ju¿ istnieje." << std::endl;
    }

    // Sprawdzanie i tworzenie plików tekstowych w folderze "rekordy"
    std::string easyFilepath = rekordyDir + "easy.txt";
    if (!fs::exists(easyFilepath)) {
        std::ofstream easyFile(easyFilepath);
        if (easyFile.is_open()) {
            easyFile << "0" << std::endl;
            easyFile << "0" << std::endl;
            easyFile << "0" << std::endl;
            easyFile << "0" << std::endl;
            easyFile << "0" << std::endl;
            easyFile << "0" << std::endl;
            easyFile << "0" << std::endl;
            easyFile << "0" << std::endl;
            easyFile << "0" << std::endl;
            easyFile << "0" << std::endl;
            easyFile.close();
            std::cout << "Utworzono plik 'easy.txt' w folderze 'rekordy'." << std::endl;
        }
        else {
            std::cout << "B³¹d podczas tworzenia pliku 'easy.txt'." << std::endl;
        }
    }
    else {
        std::cout << "Plik 'easy.txt' ju¿ istnieje w folderze 'rekordy'. Pomijanie tworzenia." << std::endl;
    }

    std::string mediumFilepath = rekordyDir + "medium.txt";
    if (!fs::exists(mediumFilepath)) {
        std::ofstream mediumFile(mediumFilepath);
        if (mediumFile.is_open()) {
            mediumFile << "0" << std::endl;
            mediumFile << "0" << std::endl;
            mediumFile << "0" << std::endl;
            mediumFile << "0" << std::endl;
            mediumFile << "0" << std::endl;
            mediumFile << "0" << std::endl;
            mediumFile << "0" << std::endl;
            mediumFile << "0" << std::endl;
            mediumFile << "0" << std::endl;
            mediumFile << "0" << std::endl;
            mediumFile.close();
            std::cout << "Utworzono plik 'medium.txt' w folderze 'rekordy'." << std::endl;
        }
        else {
            std::cout << "B³¹d podczas tworzenia pliku 'medium.txt'." << std::endl;
        }
    }
    else {
        std::cout << "Plik 'medium.txt' ju¿ istnieje w folderze 'rekordy'. Pomijanie tworzenia." << std::endl;
    }

    std::string hardFilepath = rekordyDir + "hard.txt";
    if (!fs::exists(hardFilepath)) {
        std::ofstream hardFile(hardFilepath);
        if (hardFile.is_open()) {
            hardFile << "0" << std::endl;
            hardFile << "0" << std::endl;
            hardFile << "0" << std::endl;
            hardFile << "0" << std::endl;
            hardFile << "0" << std::endl;
            hardFile << "0" << std::endl;
            hardFile << "0" << std::endl;
            hardFile << "0" << std::endl;
            hardFile << "0" << std::endl;
            hardFile << "0" << std::endl;
            hardFile.close();
            std::cout << "Utworzono plik 'hard.txt' w folderze 'rekordy'." << std::endl;
        }
        else {
            std::cout << "B³¹d podczas tworzenia pliku 'hard.txt'." << std::endl;
        }
    }
    else {
        std::cout << "Plik 'hard.txt' ju¿ istnieje w folderze 'rekordy'. Pomijanie tworzenia." << std::endl;
    }

    // Tworzenie folderu "ustawienia"
    std::string ustawieniaDir = baseDir + "ustawienia/";
    if (!fs::exists(ustawieniaDir)) {
        fs::create_directory(ustawieniaDir);
        std::cout << "Utworzono folder 'ustawienia'." << std::endl;
    }
    else {
        std::cout << "Folder 'ustawienia' ju¿ istnieje." << std::endl;
    }

    // Sprawdzanie i tworzenie plików tekstowych w folderze "ustawienia"
    std::string ustawieniaGryFilepath = ustawieniaDir + "ustawieniagry.txt";
    if (!fs::exists(ustawieniaGryFilepath)) {
        std::ofstream ustawieniaGryFile(ustawieniaGryFilepath);
        if (ustawieniaGryFile.is_open()) {
            ustawieniaGryFile << "easy" << std::endl;
            ustawieniaGryFile << "black" << std::endl;
            ustawieniaGryFile.close();
            std::cout << "Utworzono plik 'ustawieniagry.txt' w folderze 'ustawienia'." << std::endl;
        }
        else {
            std::cout << "B³¹d podczas tworzenia pliku 'ustawieniagry.txt'." << std::endl;
        }
    }
    else {
        std::cout << "Plik 'ustawieniagry.txt' ju¿ istnieje w folderze 'ustawienia'. Pomijanie tworzenia." << std::endl;
    }

    std::string ustawieniaSnakeFilepath = ustawieniaDir + "ustawieniasnake.txt";
    if (!fs::exists(ustawieniaSnakeFilepath)) {
        std::ofstream ustawieniaSnakeFile(ustawieniaSnakeFilepath);
        if (ustawieniaSnakeFile.is_open()) {
            ustawieniaSnakeFile << "arrows" << std::endl;
            ustawieniaSnakeFile << "on" << std::endl;
            ustawieniaSnakeFile.close();
            std::cout << "Utworzono plik 'ustawieniasnake.txt' w folderze 'ustawienia'." << std::endl;
        }
        else {
            std::cout << "B³¹d podczas tworzenia pliku 'ustawieniasnake.txt'." << std::endl;
        }
    }
    else {
        std::cout << "Plik 'ustawieniasnake.txt' ju¿ istnieje w folderze 'ustawienia'. Pomijanie tworzenia." << std::endl;
    }
}

class StateManager;


class State
{
public:
    virtual void initialize() = 0;
    virtual void cleanup() = 0;
    virtual void handleEvent(const sf::Event& event, StateManager& stateManager) = 0;
    virtual void update(float deltaTime,sf::RenderWindow &window) = 0;
    virtual void render(sf::RenderWindow& window) = 0;
};

class StateManager
{
public:
    StateManager(sf::RenderWindow& window) : window(window), currentState(nullptr) {}

    void setState(State* state)
    {
        if (currentState != nullptr)
            currentState->cleanup();

        currentState = state;

        if (currentState != nullptr)
            currentState->initialize();
    }

    void handleEvent(const sf::Event& event)
    {
        if (currentState != nullptr)
            currentState->handleEvent(event, *this);
    }

    void update(float deltaTime)
    {
        if (currentState != nullptr)
            currentState->update(deltaTime,window);
    }

    void render()
    {
        window.clear();

        if (currentState != nullptr)
            currentState->render(window);

        window.display();
    }
    void quit() {
        window.close();
    }
private:
    sf::RenderWindow& window;
    State* currentState;
};

class MainMenu : public State
{
    sf::Font font;
    std::vector<sf::Text> menuTexts;
    sf::Text titleCaption;
    int selectedItemIndex;
    Button b1;
    std::vector<Button>but;
    Board *f1;
   

    Game* game;
    Opcje* opt ;
    HScores* hs;

public:
    void initialize() override
    {

        b1.setbutton(sf::Vector2f(300.0f, 300.0f), sf::Vector2f(200.0f, 50.0f), "PLAY");
        b1.changeColor(sf::Color::White);
        but.push_back(b1);
        b1.setbutton(sf::Vector2f(300.0f, 400.0f), sf::Vector2f(200.0f, 50.0f), "HIGH SCORES");
        b1.changeColor(sf::Color::White);
        but.push_back(b1);
        b1.setbutton(sf::Vector2f(300.0f, 500.0f), sf::Vector2f(200.0f, 50.0f), "OPTIONS");
        b1.changeColor(sf::Color::White);
        but.push_back(b1);
        b1.setbutton(sf::Vector2f(300.0f, 600.0f), sf::Vector2f(200.0f, 50.0f), "EXIT");
        b1.changeColor(sf::Color::White);
        but.push_back(b1);

        if (!font.loadFromFile("BAHNSCHRIFT 1.ttf"))
        {
            std::cerr << "Failed to load font!" << std::endl;
            return;
        }

        titleCaption.setFont(font);
        titleCaption.setString("SNAKE");
        titleCaption.setCharacterSize(80);
        titleCaption.setFillColor(sf::Color::White);
        titleCaption.setPosition(sf::Vector2f(275.0f, 100.0f));

        selectedItemIndex = 0;
        but[selectedItemIndex].changeColor(sf::Color::Green);  

    }

    void cleanup() override {}

    void handleEvent(const sf::Event& event, StateManager& stateManager) override
    {
        if (event.type == sf::Event::KeyPressed)
        {
            if (event.key.code == sf::Keyboard::Up)
            {
                if (selectedItemIndex > 0)
                {
                    but[selectedItemIndex].changeColor(sf::Color::White);
                    selectedItemIndex--;
                    but[selectedItemIndex].changeColor(sf::Color::Green);
                }
            }
            else if (event.key.code == sf::Keyboard::Down)
            {
                if (selectedItemIndex < but.size() - 1)
                {
                    but[selectedItemIndex].changeColor(sf::Color::White);
                    selectedItemIndex++;
                    but[selectedItemIndex].changeColor(sf::Color::Green);
                }
            }
            else if (event.key.code == sf::Keyboard::Return)
            {
                if (selectedItemIndex == 0)
                {
                    // Rozpoczêcie gry
                    std::cout << "Rozpoczynam grê!" << std::endl;
                    game = new Game();
                    game->run();
                    
                }
                else if (selectedItemIndex == 1)
                {
                    
                    hs = new HScores();
                    hs->run();
                    std::cout << "Otwarcie menu rekordow!" << std::endl;
                    
                }
                else if (selectedItemIndex == 2)
                {
                    
                    opt = new Opcje();
                    opt->run();
                    std::cout << "Otwarcie menu opcji!" << std::endl;
                    delete f1;
                }
                else if (selectedItemIndex == 3)
                {
                    // Wyjœcie z gry
                    std::cout << "Zamkniecie aplikacji!" << std::endl;
                    stateManager.quit();
                    stateManager.setState(nullptr);
                    delete game;
                    delete opt;
                    delete hs;
                }
            }
        }
    }

    void update(float deltaTime,sf::RenderWindow &window) override {
       
    }

    void render(sf::RenderWindow& window) override
    {
        f1 = new Board();
        f1->drawEnd(window);
        for (int i = 0;i<but.size();i++) {
            but[i].draw(window);
        }
        window.draw(titleCaption);
    }
};







int main()
{
    createfiles();

    sf::RenderWindow window(sf::VideoMode(WIDTH, HEIGHT), "Snake Gamse");

    StateManager stateManager(window);
    MainMenu mainMenuState;

    stateManager.setState(&mainMenuState);

    while (window.isOpen())
    {
        sf::Event event;
        while (window.pollEvent(event))
        {
            if (event.type == sf::Event::Closed) {

                window.close();
            }
            stateManager.handleEvent(event);
        }

        stateManager.update(0.0f);
        stateManager.render();
    }
    

    return 0;
}

