#pragma once
#include <string>
#include <iostream>

struct sOptions
{   
  std::string controls;
  std::string difficulty;
  std::string sound;
  std::string theme;

  sOptions() :controls(""), difficulty(""), sound(""), theme("") {}

  sOptions& operator=(const sOptions& copy) {
      controls = copy.controls;
      difficulty = copy.difficulty;
      sound = copy.sound;
      theme = copy.theme;
      return *this;
  }

  friend std::ostream& operator<<(std::ostream& output, sOptions& obj) {
      output << obj.controls << "\n" << obj.difficulty << "\n" << obj.sound<< "\n" << obj.theme;
      return output;
  }
  
};

