//
// Created by sasha on 12.05.19.
//
#include <iostream>

#ifndef HW7_DATE_H
#define HW7_DATE_H

using namespace std;

struct Date
{
public:
    Date(int day, int month, int year);

    int getDay() const;

    int getMonth() const;

    int getYear() const;

    void setDay(int day);

    void setMonth(int month);

    void setYear(int year);

    virtual void printDate(ostream& out) const;

    friend ostream& operator<<(ostream& out, Date const& date);

private:
    int day;
    int month;
    int year;
};


#endif //HW7_DATE_H
