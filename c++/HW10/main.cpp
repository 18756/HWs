#include <iostream>
#include "Array.h"

using namespace std;


struct A
{
    A(int num) : number(num)
    {}

    A(A const& a2) : number(a2.number)
    {}

    ~A(){
        number = 0;
    }

    int number;

    friend ostream& operator<<(ostream& out, A const& a);

};

ostream& operator<<(ostream& out, A const& a)
{
    out << a.number;
    return out;
}

struct Comparator
{
    bool operator()(A const& a1, A const& a2)
    {
        return a1.number > a2.number;
    }
};



bool min2(A a1, A a2)
{
    return a2.number < a1.number;
}


int main()
{
    Array<A> arr = Array<A>(3, A(0));
    arr[0] = 123;
    arr[1] = 3;
    arr[2] = -123;
    Array<Array<A>> arr2 = Array<Array<A>>(2, arr);
    Array<Array<Array<A>>> arr3 = Array<Array<Array<A>>>(2, arr2);
    cout << minimum(arr, min2) << endl;
    cout << minimum(arr, Comparator()) << endl;
    flatten(arr3, cout);
    return 0;
}