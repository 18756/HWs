#include <iostream>
#include "Polynomial.h"

using namespace std;

struct A
{
public:
    static int id;


    A(int a) : a(a), lId(id++)
    {
        cout << "Constructor" << lId << endl;
    }

    A(A const& a2)
    {
        cout << "Copy" << a2.lId << endl;
        a = a2.a;
        lId = id++;
    }

    ~A() {
        cout << "Destructor" << lId << endl;
        a = 0;
    }

    A& operator=(A const& a2)
    {
        cout << "Equal" << a2.lId << endl;
        a = a2.a;
        return *this;
    }

    int a;
    int lId;
};

int A::id = 0;

A& foo(A& a)
{
    return a;
}

int main()
{
    auto p1 = Polynomial(1, new int[1]{1});
    auto*p2 = new Polynomial(4, new int[4]{1, 1, 1, 1});

    p1->printPolynomial();
    p2->printPolynomial();
    cout << p1 << endl;
    cout << (*p2)[3] << endl;

    /*auto *arr = new double[2]{1, 2};
    Polynomial p1 = Polynomial(2, arr);

    auto *arr2 = new double[2]{0, 2};
    Polynomial p2 = Polynomial(2, arr2);
    p1 = (p2 + p2 - p2) / 1.0;
    p1.printPolynomial();
    p2.printPolynomial();
    cout << (p1 == p2) << endl;
    cout << (p1 != p2) << endl;
    cout << (p1 > p2) << endl;
    cout << (p1 < p2) << endl;
    */
    return 0;
}