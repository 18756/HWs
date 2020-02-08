//
// Created by alex on 15.05.19.
//
#include <cstddef>

#ifndef HW6_STRING_H
#define HW6_STRING_H

struct String
{
    String(char const* inputStr);

    String(char c, size_t amount);

    String(String const& inputStr);

    String& operator=(const String& inputStr);

    ~String();

    void append(String const& inputStr);

    void printStr() const;


private:
    size_t size;
    char* str;
};


#endif //HW6_STRING_H
