#pragma once
#include <string>
class Wezel
{
public:
	std::wstring dane;
	Wezel* nastepny;

	Wezel* podlista;
	Wezel()
	{
		dane = L"";
		nastepny = NULL;
		podlista = NULL;
	}

	Wezel(std::wstring d)
	{
		this->dane = d;
		this->nastepny = NULL;
		this->podlista = NULL;
	}

};

