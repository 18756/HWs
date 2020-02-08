//
// Created by sasha on 12.05.19.
//

#include "Person.h"

Person::Person(string const& name, string const& surname, Date const& birthDay, bool isMale) :
        name(name), surname(surname), birthDay(birthDay), isMale(isMale)
{}

string const& Person::getName() const
{
    return name;
}

string const& Person::getSurname() const
{
    return surname;
}

Date const& Person::getBirthDay() const
{
    return birthDay;
}

bool Person::getIsMale() const
{
    return isMale;
}

void Person::setName(string const& name)
{
    Person::name = name;
}

void Person::setSurname(string const& surname)
{
    Person::surname = surname;
}

void Person::setBirthDay(Date const& birthDay)
{
    Person::birthDay = birthDay;
}

void Person::setIsMale(bool isMale)
{
    Person::isMale = isMale;
}

void Person::printPerson(ostream& out) const
{
    out << name << " " << surname << ": ";
    if (isMale)
    {
        out << "male, ";
    } else
    {
        out << "female, ";
    }
    out << "born on " << birthDay;
}

ostream& operator<<(ostream& out, Person const& person)
{
    person.printPerson(out);
    return out;
}



