#include <iostream>
#include "Student.h"
#include "Date.h"
#include "Person.h"

using namespace std;


int main()
{
    Date date(21, 8, 2000);
    cout << date << endl;

    Person p("Alex", "Smirk", Date(1, 10, 1995), true);

    cout << p << endl;

    const Person p2("Eva", "Diary", Date(15, 4, 1997), false);

    cout << p2 << endl;

    Person* p3 =  new Student("Karen", "Vaccine", Date(6, 12, 1999), false, 3135);

    cout << *p3 << endl;

    return 0;
}