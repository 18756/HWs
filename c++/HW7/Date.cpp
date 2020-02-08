//
// Created by sasha on 12.05.19.
//

#include "Date.h"

using namespace std;

Date::Date(int day, int month, int year) : day(day), month(month), year(year)
{}

int Date::getDay() const
{
    return day;
}

int Date::getMonth() const
{
    return month;
}

int Date::getYear() const
{
    return year;
}

void Date::setDay(int day)
{
    Date::day = day;
}

void Date::setMonth(int month)
{
    Date::month = month;
}

void Date::setYear(int year)
{
    Date::year = year;
}

void Date::printDate(ostream& out) const
{
    out << day << "/" << month << "/" << year;
}

ostream& operator<<(ostream& out, Date const& date)
{
    date.printDate(out);
    return out;
}




