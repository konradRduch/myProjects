#pragma once
#include <SFML/Graphics.hpp>
#include <iostream>
#include "Button.h"
#include "header.h"
#include "Board.h"
#include <fstream>
#include <vector>
#include <string>
class HScores
{

	std::vector<sf::Text> buttons;
	std::vector<sf::Text> texts;

	sf::RenderWindow window;
	sf::Font font;
	std::vector<sf::Text>Texts;
	Button b1;
	std::vector<Button>but;
	sf::Text titleCaption;

	sf::Text t_mode;
	sf::Text firstPlace;
	sf::Text secondPlace;
	sf::Text thirdPlace;
	sf::Text fourthPlace;
	sf::Text fifthPlace;
	sf::Text sixthPlace;
	sf::Text seventhPlace;
	sf::Text eighthPlace;
	sf::Text ninthPlace;
	sf::Text tenthPlace;

	int selectedItemIndex;
	Board b2;

	std::vector<sf::Text>numbers;
	std::vector<sf::Text>scores;


	std::vector<std::string> easyList;
	std::vector<std::string> mediumList;
	std::vector<std::string> hardList;


	int index ;

	void handleEvents() {
		sf::Event event;
		while (window.pollEvent(event)) {
			if (event.type == sf::Event::Closed) {
				window.close();
			}

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
						
						if (index % 3 == 0) {
							t_mode.setString("MEDIUM");
							std::ifstream odczyt;
					
							std::string l;
							mediumList.clear();
							odczyt.open("D:\\c++ graphic\\Project1\\Project1\\rekordy\\medium.txt");
							while (odczyt.good()) {
								getline(odczyt, l);
								mediumList.push_back(l);
							}
							odczyt.close();

							for (auto i : mediumList) {
								std::cout << i;
							}
							std::cout<<std::endl;

							std::vector<std::string>w;
							w = mediumList;
		
							firstPlace.setString("1ST         " + w[0]);
							secondPlace.setString("2ST        " + w[1]);
							thirdPlace.setString("3ST        " + w[2]);
							fourthPlace.setString("4ST        " + w[3]);
							fifthPlace.setString("5ST        " + w[4]);
							sixthPlace.setString("6ST        " + w[5]);
							seventhPlace.setString("7ST        " + w[6]);
							eighthPlace.setString("8ST        " + w[7]);
							ninthPlace.setString("9ST        " + w[8]);
							tenthPlace.setString("10ST      " + w[9]);

						}
						else if (index % 3 == 1) {
							t_mode.setString("HARD");
							std::ifstream odczyt;
							std::string l;
							hardList.clear();
							odczyt.open("D:\\c++ graphic\\Project1\\Project1\\rekordy\\hard.txt");
							while (odczyt.good()) {
								getline(odczyt, l);
								hardList.push_back(l);
							}
							odczyt.close();

							for (auto i : hardList) {
								std::cout << i;
							}
							std::cout << std::endl;

							std::vector<std::string>w;
							w = hardList;

							firstPlace.setString("1ST         " + w[0]);
							secondPlace.setString("2ST        " + w[1]);
							thirdPlace.setString("3ST        " + w[2]);
							fourthPlace.setString("4ST        " + w[3]);
							fifthPlace.setString("5ST        " + w[4]);
							sixthPlace.setString("6ST        " + w[5]);
							seventhPlace.setString("7ST        " + w[6]);
							eighthPlace.setString("8ST        " + w[7]);
							ninthPlace.setString("9ST        " + w[8]);
							tenthPlace.setString("10ST      " + w[9]);


						}
						else if (index % 3 == 2) {
							t_mode.setString("EASY");
							std::ifstream odczyt;
							std::string l;
							easyList.clear();
							odczyt.open("D:\\c++ graphic\\Project1\\Project1\\rekordy\\easy.txt");
							while (odczyt.good()) {
								getline(odczyt, l);
								easyList.push_back(l);
							}
							odczyt.close();

							for (auto i : easyList) {
								std::cout << i;
							}
							std::cout << std::endl;

							std::vector<std::string>w;
							w = easyList;

							firstPlace.setString("1ST         " + w[0]);
							secondPlace.setString("2ST        " + w[1]);
							thirdPlace.setString("3ST        " + w[2]);
							fourthPlace.setString("4ST        " + w[3]);
							fifthPlace.setString("5ST        " + w[4]);
							sixthPlace.setString("6ST        " + w[5]);
							seventhPlace.setString("7ST        " + w[6]);
							eighthPlace.setString("8ST        " + w[7]);
							ninthPlace.setString("9ST        " + w[8]);
							tenthPlace.setString("10ST      " + w[9]);

						}
						
					}
					else if (selectedItemIndex == 1)
					{
						window.close();

					}
					
					index++;
				}
			}
		}
	}

       
	void update() {


	}
	void render() {



		b2.drawEnd(window);
		for (int i = 0;i < but.size();i++) {
			but[i].draw(window);
		}
		window.draw(t_mode);
		window.draw(titleCaption);
		window.draw(firstPlace);
		window.draw(secondPlace);
		window.draw(secondPlace	 );
		window.draw(thirdPlace	 );
		window.draw(fourthPlace	 );
		window.draw(fifthPlace	 );
		window.draw(sixthPlace	 );
		window.draw(seventhPlace );
		window.draw(eighthPlace	 );
		window.draw(ninthPlace	 );
		window.draw(tenthPlace	 );




		window.display();
	}

public:
	HScores() {
		window.create(sf::VideoMode(WIDTH, HEIGHT), "High Scores");
		window.setFramerateLimit(60);


		if (!font.loadFromFile("BAHNSCHRIFT 1.ttf"))
		{
			//std::cerr << "Failed to load font!" << std::endl;
			return;
		}



	b1.setbutton(sf::Vector2f(325.0f, 700.0f), sf::Vector2f(200.0f, 50.0f), "NEXT");
	b1.changeColor(sf::Color::White);
	but.push_back(b1);
	b1.setbutton(sf::Vector2f(325.0f, 800.0f), sf::Vector2f(200.0f, 50.0f), "EXIT");
	b1.changeColor(sf::Color::White);
	but.push_back(b1);
	
	float sd= 280.0f;
	
	titleCaption.setFont(font);
	titleCaption.setString("HIGH SCORES");
	titleCaption.setCharacterSize(80);
	titleCaption.setFillColor(sf::Color::White);
	titleCaption.setPosition(sf::Vector2f(180.0f, 100.0f));

	std::vector<std::string>w;

	std::ifstream odczyt;

	std::string l;
	easyList.clear();
	odczyt.open("D:\\c++ graphic\\Project1\\Project1\\rekordy\\easy.txt");
	while (odczyt.good()) {
		getline(odczyt, l);
		easyList.push_back(l);
	}
	odczyt.close();

	w = easyList;

	t_mode.setFont(font);
	t_mode.setString("EASY");
	t_mode.setCharacterSize(30);
	t_mode.setFillColor(sf::Color::White);
	t_mode.setPosition(sf::Vector2f(380.0f, 220.0f));

	firstPlace.setFont(font);
	firstPlace.setString("1ST         "+w[0]);
	firstPlace.setCharacterSize(30);
	firstPlace.setFillColor(sf::Color::White);
	firstPlace.setPosition(sf::Vector2f(350.0f, sd));
	
	sd += 30.0f;
	secondPlace.setFont(font);
	secondPlace.setString("2ST        " + w[1]);
	secondPlace.setCharacterSize(30);
	secondPlace.setFillColor(sf::Color::White);
	secondPlace.setPosition(sf::Vector2f(350.0f, sd));
	sd += 30.0f;
	thirdPlace.setFont(font);
	thirdPlace.setString("3ST        " + w[2]);
	thirdPlace.setCharacterSize(30);
	thirdPlace.setFillColor(sf::Color::White);
	thirdPlace.setPosition(sf::Vector2f(350.0f, sd));
	sd += 30.0f;
	fourthPlace.setFont(font);
	fourthPlace.setString("4ST        " + w[3]);
	fourthPlace.setCharacterSize(30);
	fourthPlace.setFillColor(sf::Color::White);
	fourthPlace.setPosition(sf::Vector2f(350.0f, sd));
	sd += 30.0f;
	fifthPlace.setFont(font);
	fifthPlace.setString("5ST        " + w[4]);
	fifthPlace.setCharacterSize(30);
	fifthPlace.setFillColor(sf::Color::White);
	fifthPlace.setPosition(sf::Vector2f(350.0f, sd));
	sd += 30.0f;
	sixthPlace.setFont(font);
	sixthPlace.setString("6ST        " + w[5]);
	sixthPlace.setCharacterSize(30);
	sixthPlace.setFillColor(sf::Color::White);
	sixthPlace.setPosition(sf::Vector2f(350.0f, sd));
	sd += 30.0f;
	seventhPlace.setFont(font);
	seventhPlace.setString("7ST        " + w[6]);
	seventhPlace.setCharacterSize(30);
	seventhPlace.setFillColor(sf::Color::White);
	seventhPlace.setPosition(sf::Vector2f(350.0f, sd));
	sd += 30.0f;
	eighthPlace.setFont(font);
	eighthPlace.setString("8ST        " + w[7]);
	eighthPlace.setCharacterSize(30);
	eighthPlace.setFillColor(sf::Color::White);
	eighthPlace.setPosition(sf::Vector2f(350.0f, sd));
	sd += 30.0f;

	ninthPlace.setFont(font);
	ninthPlace.setString("9ST        " + w[8]);
	ninthPlace.setCharacterSize(30);
	ninthPlace.setFillColor(sf::Color::White);
	ninthPlace.setPosition(sf::Vector2f(350.0f, sd));
	sd += 30.0f;
	tenthPlace.setFont(font);
	tenthPlace.setString("10ST      " + w[9]);
	tenthPlace.setCharacterSize(30);
	tenthPlace.setFillColor(sf::Color::White);
	tenthPlace.setPosition(sf::Vector2f(350.0f, sd));
	


	selectedItemIndex = 0;
	but[selectedItemIndex].changeColor(sf::Color::Green);

	index = 0;

	}
	void run() {
		while (window.isOpen()) {
			handleEvents();
			update();
			render();
		}
	}

};
