//
// Created by alex on 15.05.19.
//

#ifndef HW1_POLYNOMIAL_H
#define HW1_POLYNOMIAL_H


#include <cstddef>
#include <iostream>

using namespace std;


struct Polynomial
{
public:
    Polynomial(size_t n, int* coefficients);

    Polynomial(Polynomial const& polynomial);

    ~Polynomial();

    Polynomial& operator=(Polynomial const& polynomial);

    Polynomial operator+(Polynomial const& polynomial) const;

    Polynomial operator+() const;

    Polynomial operator+(int value) const;

    Polynomial operator*(int scalar) const;

    Polynomial operator-() const;

    Polynomial operator-(int value) const;

    Polynomial operator-(Polynomial const& polynomial) const;

    Polynomial operator/(int divider) const;

    Polynomial operator%(int mod) const;

    Polynomial& operator++(); // prefix

    Polynomial operator++(int); // postfix

    Polynomial& operator--(); // prefix

    Polynomial operator--(int); // postfix

    bool operator<(Polynomial const& polynomial) const;

    bool operator>(Polynomial const& polynomial) const;

    bool operator<=(Polynomial const& polynomial) const;

    bool operator>=(Polynomial const& polynomial) const;

    bool operator==(Polynomial const& polynomial) const;

    bool operator!=(Polynomial const& polynomial) const;

    operator bool() const;

    bool operator!() const;

    bool operator&&(Polynomial const& polynomial) const;

    bool operator||(Polynomial const& polynomial) const;

    Polynomial operator~() const;

    Polynomial operator&(int value) const;

    Polynomial operator|(int value) const;

    Polynomial operator^(int value) const;

    Polynomial operator<<(int value) const;

    Polynomial operator>>(int value) const;

    Polynomial& operator+=(Polynomial const& polynomial);

    Polynomial& operator+=(int value);

    Polynomial& operator-=(Polynomial const& polynomial);

    Polynomial& operator-=(int value);

    Polynomial& operator*=(int value);

    Polynomial& operator/=(int value);

    Polynomial& operator%=(int value);

    Polynomial& operator&=(int value);

    Polynomial& operator|=(int value);

    Polynomial& operator^=(int value);

    Polynomial& operator<<=(int value);

    Polynomial& operator>>=(int value);

    int& operator[](int index);

    int operator[](int index) const;

    Polynomial& operator*() const;

    Polynomial* operator&();

    Polynomial* operator->();

    int& operator->*(int index);

    int operator()(int x) const;

    Polynomial operator,(Polynomial const& polynomial) const;


    size_t getN() const;

    void printPolynomial() const;

    friend istream& operator>>(istream& in, Polynomial& polynomial);

private:
    int pow(int x, int power) const;
    void copyCoefficients(int const* polynomial, size_t size);

    size_t n;
    int* coefficients;
};


#endif //HW1_POLYNOMIAL_H
