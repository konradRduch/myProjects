#pragma once
#include <iostream>
#include "header.h"
#include "Board.h"
#include "GameOverCaption.h"
#include "PauseCaption.h"
#include "ScoreCaption.h"
#include "Snake.h"
#include "Apple.h"
#include "OdczytPliku.h"
#include <string>
#include <vector>
#include <algorithm>
#include <fstream>

class Game {
    std::string path;
    std::vector<int>rank;

    sf::RenderWindow window;
    Board board;
    GameOverCaption gameOverText;
    PauseCaption pauseText;
    ScoreCaption scoreText;
    Snake *snake;
    Apple apple;

    bool paused;
    bool gameOver;
    int score;
    bool lock;

    int up;
    int down;
    int left;
    int right;

    void save();
    void resetGame();
    void handleEvents();
    void update();
    void render();

public:
    Game();
    ~Game();
    void run();
};

