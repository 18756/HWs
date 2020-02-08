//
// Created by alex on 15.05.19.
//

#include <iostream>
#include <cmath>
#include "Polynomial.h"

using namespace std;

Polynomial::Polynomial(size_t n, int* coefficients) : n(n), coefficients(new int[n])
{
    copyCoefficients(coefficients, n);
}

Polynomial::Polynomial(Polynomial const& polynomial) : n(polynomial.n), coefficients(new int[n])
{
    copyCoefficients(polynomial.coefficients, n);
}

Polynomial::~Polynomial()
{
    delete[] coefficients;
}

Polynomial& Polynomial::operator=(Polynomial const& polynomial)
{
    if (*this != polynomial)
    {
        delete[] coefficients;
        n = polynomial.n;
        coefficients = new int[n];
        copyCoefficients(polynomial.coefficients, n);
    }
    return *this;
}

Polynomial Polynomial::operator+(Polynomial const& polynomial) const
{
    size_t newN = max(n, polynomial.n);
    auto* newCoefficients = new int[newN];
    for (size_t i = 0; i < newN; ++i)
    {
        newCoefficients[i] = (i < n ? coefficients[i] : 0) + (i < polynomial.n ? polynomial.coefficients[i] : 0);
    }
    return Polynomial(newN, newCoefficients);
}

Polynomial Polynomial::operator+() const
{
    return *this;
}

Polynomial Polynomial::operator+(int value) const
{
    Polynomial res = Polynomial(*this);
    res.coefficients[0] += value;
    return res;
}

Polynomial Polynomial::operator*(int scalar) const
{
    auto* newCoefficients = new int[n];
    for (size_t i = 0; i < n; ++i)
    {
        newCoefficients[i] = coefficients[i] * scalar;
    }
    return Polynomial(n, newCoefficients);
}

Polynomial Polynomial::operator-() const
{
    return *this * -1;
}

Polynomial Polynomial::operator-(int value) const
{
    return *this + (-value);
}

Polynomial Polynomial::operator-(Polynomial const& polynomial) const
{
    return -polynomial + *this;
}


Polynomial Polynomial::operator/(int divider) const
{
    auto* newCoefficients = new int[n];
    for (size_t i = 0; i < n; ++i)
    {
        newCoefficients[i] /= divider;
    }
    return Polynomial(n, newCoefficients);
}

Polynomial Polynomial::operator%(int mod) const
{
    return *this - ((*this / mod) * mod);
}

Polynomial& Polynomial::operator++()
{
    ++coefficients[0];
    return *this;
}


Polynomial Polynomial::operator++(int)
{
    Polynomial polynomial(*this);
    ++(*this);
    return polynomial;
}

Polynomial& Polynomial::operator--()
{
    --coefficients[0];
    return *this;
}

Polynomial Polynomial::operator--(int)
{
    Polynomial polynomial = Polynomial(*this);
    --(*this);
    return polynomial;
}


bool Polynomial::operator<(Polynomial const& polynomial) const
{
    for (size_t i = max(n, polynomial.n) - 1; i > 0; --i)
    {
        if ((i < n ? coefficients[i] : 0) != (i < polynomial.n ? polynomial.coefficients[i] : 0))
        {
            return (i < n ? coefficients[i] : 0) < (i < polynomial.n ? polynomial.coefficients[i] : 0);
        }
    }
    return coefficients[0] < polynomial.coefficients[0];
}

bool Polynomial::operator>(Polynomial const& polynomial) const
{
    return polynomial < *this;
}

bool Polynomial::operator<=(Polynomial const& polynomial) const
{
    return !(polynomial < *this);
}

bool Polynomial::operator>=(Polynomial const& polynomial) const
{
    return !(*this < polynomial);
}

bool Polynomial::operator==(Polynomial const& polynomial) const
{
    return !(*this > polynomial) && !(*this < polynomial);
}

bool Polynomial::operator!=(Polynomial const& polynomial) const
{
    return !(polynomial == *this);
}

Polynomial::operator bool() const
{
    if (n == 0)
    {
        return false;
    }
    for (size_t i = 0; i < n; ++i)
    {
        if (coefficients[i] != 0)
        {
            return true;
        }
    }
    return false;
}

bool Polynomial::operator!() const
{
    return !(bool) *this;
}

bool Polynomial::operator&&(Polynomial const& polynomial) const
{
    return (bool) *this && (bool) polynomial;
}

bool Polynomial::operator||(Polynomial const& polynomial) const
{
    return (bool) *this || (bool) polynomial;
}


void Polynomial::copyCoefficients(int const* coefficients, size_t size)
{
    cout << "copy" << endl;
    for (size_t i = 0; i < size; ++i)
    {
        this->coefficients[i] = coefficients[i];
    }
}

Polynomial Polynomial::operator~() const
{
    Polynomial newPolynomial = *this;
    for (size_t i = 0; i < newPolynomial.n; ++i)
    {
        newPolynomial.coefficients[i] = ~newPolynomial.coefficients[i];
    }
    return newPolynomial;
}

Polynomial Polynomial::operator&(int value) const
{
    Polynomial newPolynomial = *this;
    for (size_t i = 0; i < newPolynomial.n; ++i)
    {
        newPolynomial.coefficients[i] &= value;
    }
    return newPolynomial;
}

Polynomial Polynomial::operator|(int value) const
{
    Polynomial newPolynomial = *this;
    for (size_t i = 0; i < newPolynomial.n; ++i)
    {
        newPolynomial.coefficients[i] |= value;
    }
    return newPolynomial;
}

Polynomial Polynomial::operator^(int value) const
{
    Polynomial newPolynomial = *this;
    for (size_t i = 0; i < newPolynomial.n; ++i)
    {
        newPolynomial.coefficients[i] ^= value;
    }
    return newPolynomial;
}

Polynomial Polynomial::operator<<(int value) const
{
    Polynomial newPolynomial = *this;
    for (size_t i = 0; i < newPolynomial.n; ++i)
    {
        newPolynomial.coefficients[i] <<= value;
    }
    return newPolynomial;
}

Polynomial Polynomial::operator>>(int value) const
{
    Polynomial newPolynomial = *this;
    for (size_t i = 0; i < newPolynomial.n; ++i)
    {
        newPolynomial.coefficients[i] >>= value;
    }
    return newPolynomial;
}

Polynomial& Polynomial::operator+=(Polynomial const& polynomial)
{
    *this = *this + polynomial;
    return *this;
}

Polynomial& Polynomial::operator+=(int value)
{
    *this = *this + value;
    return *this;
}

Polynomial& Polynomial::operator-=(Polynomial const& polynomial)
{
    *this = *this - polynomial;
    return *this;
}

Polynomial& Polynomial::operator-=(int value)
{
    *this = *this - value;
    return *this;
}

Polynomial& Polynomial::operator*=(int value)
{
    *this = *this * value;
    return *this;
}

Polynomial& Polynomial::operator/=(int value)
{
    *this = *this / value;
    return *this;
}

Polynomial& Polynomial::operator%=(int value)
{
    *this = *this % value;
    return *this;
}

Polynomial& Polynomial::operator&=(int value)
{
    *this = *this & value;
    return *this;
}

Polynomial& Polynomial::operator|=(int value)
{
    *this = *this | value;
    return *this;
}

Polynomial& Polynomial::operator^=(int value)
{
    *this = *this ^ value;
    return *this;
}

Polynomial& Polynomial::operator<<=(int value)
{
    *this = *this << value;
    return *this;
}

Polynomial& Polynomial::operator>>=(int value)
{
    *this = *this >> value;
    return *this;
}

int& Polynomial::operator[](int index)
{
    return coefficients[index];
}

int Polynomial::operator[](int index) const
{
    return coefficients[index];
}

Polynomial& Polynomial::operator*() const
{
    return const_cast<Polynomial&>(*this);
}

Polynomial* Polynomial::operator&()
{
    return this;
}

Polynomial* Polynomial::operator->()
{
    return this;
}

int& Polynomial::operator->*(int index)
{
    return (*this)[index];
}

int Polynomial::operator()(int x) const
{
    int result = 0;
    for (int i = 0; i < n; ++i)
    {
        result += coefficients[i] * pow(x, i);
    }
    return result;
}

Polynomial Polynomial::operator,(Polynomial const& polynomial) const
{
    return polynomial;
}

size_t Polynomial::getN() const
{
    return n;
}

int Polynomial::pow(int x, int power) const
{
    int result = 1;
    while (power)
    {
        if (power & 1)
        {
            result *= x;
        }
        power /= 2;
        x *= x;
    }
    return result;

}

void Polynomial::printPolynomial() const
{
    bool isFirst = true;
    if (coefficients[0] != 0)
    {
        cout << coefficients[0];
        isFirst = false;
    }
    for (size_t i = 1; i < n; ++i)
    {
        if (coefficients[i] != 0)
        {
            if (coefficients[i] > 0)
            {
                if (!isFirst)
                {
                    cout << " + ";
                }
            } else
            {
                cout << " - ";
            }
            if (abs(coefficients[i]) != 1)
            {
                cout << abs(coefficients[i]);
            }
            cout << "x";
            if (i > 1)
            {
                cout << "^" << i;
            }
            isFirst = false;
        }
    }
    cout << endl;
}

ostream& operator<<(ostream& out, Polynomial const& polynomial)
{
    polynomial.printPolynomial();
    return out;
}

istream& operator>>(istream& in, Polynomial& polynomial)
{
    in >> polynomial.n;
    delete [] polynomial.coefficients;
    polynomial.coefficients = new int[polynomial.n];
    for (size_t i = 0; i < polynomial.n; ++i)
    {
        in >> polynomial.coefficients[i];
    }
    return in;
}
