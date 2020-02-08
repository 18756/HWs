//
// Created by alex on 15.05.19.
//

#include <cstring>
#include <iostream>
#include "String.h"

using namespace std;

String::String(const char* inputStr) : size(strlen(inputStr)), str(new char[size + 1])
{
    strcpy(str, inputStr);
}

String::String(char c, size_t amount)
{
    size = amount;
    str = new char[size + 1];
    for (size_t i = 0; i < size; ++i)
    {
        str[i] = c;
    }
    str[size] = '\0';
}

String::String(String const& inputStr) : size(inputStr.size), str(new char[size + 1])
{
    strcpy(str, inputStr.str);
}

String& String::operator=(String const& inputStr)
{
    if (this != &inputStr)
    {
        delete[] str;
        size = inputStr.size;
        str = new char[size + 1];
        strcpy(str, inputStr.str);
    }
    return *this;
}

String::~String()
{
    delete[] str;
}


void String::append(String const& inputStr)
{
    size += inputStr.size;
    char* newStr = new char[size + 1];
    strcpy(newStr, str);
    strcat(newStr, inputStr.str);
    delete[] str;
    str = newStr;
}

void String::printStr() const
{
    cout << str << endl;
}