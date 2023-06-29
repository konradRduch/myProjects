#include "TextFont.h"

TextFont::TextFont() {
    if (!font.loadFromFile("BAHNSCHRIFT 1.TTF")) {
        // Handle font loading error
    }
}