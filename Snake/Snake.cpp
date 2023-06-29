#include "Snake.h"
#include "OdczytPliku.h"
Snake::Snake() {

   

    headTexture.loadFromFile("glowa.png");
    bodyTexture.loadFromFile("segment.png");

    if (!snakeTexture.loadFromFile("glowa.png")) {
        // Handle texture loading error
    }
    snakeSegment.setTexture(snakeTexture);
    snakeSegment.setScale(static_cast<float>(SIZE) / snakeSegment.getLocalBounds().width,
        static_cast<float>(SIZE) / snakeSegment.getLocalBounds().height);


    if (!eatSoundBuffer.loadFromFile("zjedzenie.wav")) {
        // Obs³uga b³êdu ³adowania pliku dŸwiêkowego
    }
    eatSound.setBuffer(eatSoundBuffer);

    if (!deathSoundBuffer.loadFromFile("videogame-death-sound-43894.wav")) {
        // Obs³uga b³êdu ³adowania pliku dŸwiêkowego
    }
    deathSound.setBuffer(deathSoundBuffer);

    isSoundPlaying = false;
    isWallSoundPlaying = false;
    lock = false;

    OdczytPliku o1("D:\\c++ graphic\\Project1\\Project1\\ustawienia\\ustawieniagry.txt", "D:\\c++ graphic\\Project1\\Project1\\ustawienia\\ustawieniasnake.txt");

    if (o1.odczytaneDane().sound == "on") {
        soundEnable = true;
    }
    else if (o1.odczytaneDane().sound == "off") {
        soundEnable = false;
    }
    
    //Inicjalizacja w¹tku dla odtwarzania dŸwiêku
    soundThread = new sf::Thread(&Snake::soundLoop, this); // Utwórz obiekt sf::Thread dynamicznie
   
    soundThread->launch(); // Uruchom w¹tek

}


Snake::~Snake() {
    
    soundThread->wait(); // Poczekaj na zakoñczenie w¹tku
    delete soundThread; // Zwolnij pamiêæ
    std::cout << "destruktor";
}

void Snake::reset() {
    lock = false;
    isWallSoundPlaying = false;
    snake.clear();
    snake.push_back(sf::Vector2i(21, 25)); // Initial snake position
    direction = sf::Vector2i(0, 0);        // Initial direction (none)
}

void Snake::draw(sf::RenderWindow& window) {
    snakeSegment.setTexture(headTexture);
    snakeSegment.setPosition(snake.front().x * SIZE, snake.front().y * SIZE);
    window.draw(snakeSegment);

    // Rysuj resztê cia³a wê¿a
    snakeSegment.setTexture(bodyTexture);
    for (size_t i = 1; i < snake.size(); ++i) {
        snakeSegment.setPosition(snake[i].x * SIZE, snake[i].y * SIZE);
        window.draw(snakeSegment);
    }


}

bool Snake::checkWall() {
    head = snake.front() + direction;
    if (!lock) {
        if (head.x < 1 || head.x >= (WIDTH / SIZE) - 1 || head.y < 5 || head.y >= (HEIGHT - 20) / SIZE) {
            lock = true;
            isWallSoundPlaying = true;
            return true;
        }
    }
    return false;
}
bool Snake::checkBody() {
    head = snake.front() + direction;
    for (size_t i = 1; i < snake.size(); ++i) {
        if(!lock){
        if (snake[i] == head) {
            lock = true;
            isWallSoundPlaying = true;
            return true;
        }
    }
    }
    return false;
}


void Snake::setDirection(sf::Vector2i newdirection) {
    direction = newdirection;
}

std::vector<sf::Vector2i>& Snake::getSnake() {
    return snake;
}



void Snake::move(Apple& a, int& score) {
    head = snake.front() + direction;
    snake.insert(snake.begin(), head);

    if (head == a.get_position()) {
        score++;
        a.generateApplePosition(snake);
        isSoundPlaying = true;
    }
    else {
        snake.pop_back();
        isSoundPlaying = false;
    }
}



sf::Vector2i& Snake::getDirection() {
    return direction;
}

void Snake::soundLoop() {
 while (true) {
     if (isSoundPlaying) {
         if (eatSound.getStatus() != sf::Sound::Playing) {
            if(soundEnable)eatSound.play();
             isSoundPlaying = false;
         }
     }
     if (isWallSoundPlaying) {
         if (deathSound.getStatus() != sf::Sound::Playing) {
           if(soundEnable) deathSound.play();
             isWallSoundPlaying = false;
 
         }
     }
     sf::sleep(sf::milliseconds(10));
 }
}


