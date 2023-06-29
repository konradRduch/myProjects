#pragma once
#include "header.h"
#include "Apple.h"
#include "SFML/Audio.hpp"
#include <thread>
#include <iostream>


class Snake {

    sf::Texture snakeTexture;
    sf::Sprite snakeSegment;

    std::vector<sf::Vector2i> snake;
    sf::Vector2i direction;

    sf::Texture bodyTexture;
    sf::Texture headTexture;

    sf::Vector2i head;

    sf::SoundBuffer eatSoundBuffer;
    sf::Sound eatSound;

    sf::SoundBuffer deathSoundBuffer;
    sf::Sound deathSound;

    bool isSoundPlaying;
    bool isWallSoundPlaying;
    bool lock;

    bool soundEnable;

    sf::Thread* soundThread;  // W¹tek dla odtwarzania dŸwiêku

    void soundLoop();  // Funkcja wykonywana w w¹tku dla odtwarzania dŸwiêk


public:
    Snake();
    ~Snake();
    void reset();
    void draw(sf::RenderWindow& window);
    bool checkWall();
    bool checkBody();
    void setDirection(sf::Vector2i newdirection);
    std::vector<sf::Vector2i>& getSnake();
    void move(Apple& a, int& score);
    sf::Vector2i& getDirection();

};

