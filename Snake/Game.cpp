#include "Game.h"

//TODO zrobic zapisywanie vectora rank do pliku tekstowego



void Game::save() {
    std::ofstream zapis;
    zapis.open(path);
    for (int i = 0;i < rank.size();i++) {
        zapis << rank[i] << std::endl;
   }
    zapis.close();
}

void insert(int x, std::vector<int>&rank ) {
        rank.push_back(x);
        std::sort(rank.begin(), rank.end(), std::greater<int>());
        rank.pop_back();
}

void newScore(int newNumber, std::vector<int>& rank) {
    insert(newNumber, rank);
    // Wypisanie posortowanych liczb
    std::cout << "Najwiêksze liczby:\n";
    for (int num : rank) {
        std::cout << num << " ";
    }
    std::cout << std::endl;

}



void Game::resetGame() {

    snake->reset();
    gameOver = false;
    paused = false;
    score = 0;
    lock = false;
    std::srand(static_cast<unsigned>(std::time(nullptr)));

    apple.generateApplePosition(snake->getSnake());

}

void Game::handleEvents() {
    sf::Event event;
    while (window.pollEvent(event)) {
        if (event.type == sf::Event::Closed or event.key.code == sf::Keyboard::Escape){
            window.close();
           //snake->end();
            std::cout << "Koniecc gry!\n";
           
      }

        else if (event.type == sf::Event::KeyPressed) {

            if (!gameOver) {
                if (event.key.code == up && snake->getDirection().y != 1)
                    snake->setDirection(sf::Vector2i(0, -1));
                else if (event.key.code == down && snake->getDirection().y != -1)
                    snake->setDirection(sf::Vector2i(0, 1));
                else if (event.key.code == left && snake->getDirection().x != 1)
                    snake->setDirection(sf::Vector2i(-1, 0));
                else if (event.key.code == right && snake->getDirection().x != -1)
                    snake->setDirection(sf::Vector2i(1, 0));

            }
            if (!gameOver && event.key.code == sf::Keyboard::Space) {
                paused = !paused;
            }
            else if (event.key.code == sf::Keyboard::Enter && gameOver) {
                save();
                resetGame();
            }

        }
    }
}

void Game::update() {
    if (!paused) {
        if (snake->checkWall()) {
            gameOver = true;
        }
        else {
            if (snake->checkBody()) {
                gameOver = true;
            }

            if (!gameOver) {
                snake->move(apple, score);
            }
        }
    }
}

void Game::render() {

    // plansza();
    board.draw(window);
    snake->draw(window);

    apple.draw(window);

    scoreText.draw(window, score);

    if (paused) {
        pauseText.draw(window);
    }

    if (gameOver) {
        if (!lock) { 
            lock = true;
            newScore(score, rank); 
        }
        board.drawEnd(window);
        gameOverText.draw(window,score,rank[0]);
    }

    window.display();
}

Game::Game() {
    OdczytPliku o1("D:\\c++ graphic\\Project1\\Project1\\ustawienia\\ustawieniagry.txt", "D:\\c++ graphic\\Project1\\Project1\\ustawienia\\ustawieniasnake.txt");
   
    if (o1.odczytaneDane().controls == "arrows") {
        up = 73;
        down = 74;
        left = 71;
        right = 72;
    }
    else if (o1.odczytaneDane().controls == "awsd") {
        up = sf::Keyboard::W;
        down = sf::Keyboard::S;
        left = sf::Keyboard::A;
        right = sf::Keyboard::D;
    }



    int mode;
    if (o1.odczytaneDane().difficulty == "easy") {
        mode = 10;
        path = "D:\\c++ graphic\\Project1\\Project1\\rekordy\\easy.txt";
    }
    else if (o1.odczytaneDane().difficulty == "medium") {
        mode = 15;
        path = "D:\\c++ graphic\\Project1\\Project1\\rekordy\\medium.txt";
    }
    else if (o1.odczytaneDane().difficulty == "hard") {
        mode = 20;
        path = "D:\\c++ graphic\\Project1\\Project1\\rekordy\\hard.txt";
    }
    int count = 0;
    std::cout << path;
    std::ifstream odczyt;
    std::string l;
    odczyt.open(path);
    while (odczyt.good()) {
        getline(odczyt, l);
       if (count < 10) rank.push_back(stoi(l));
        count++;
    }
    for (auto i : rank) {
        std::wcout << i;
    }

    odczyt.close();

    lock = false;

    snake = new Snake();
    window.create(sf::VideoMode(WIDTH, HEIGHT), "Snake Game");
    window.setFramerateLimit(mode);
    resetGame();
}

void Game::run() {
    while (window.isOpen()) {
        handleEvents();
        update();
        render();
    }
}

Game::~Game(){
    delete snake;
}
