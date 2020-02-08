//
// Created by sasha on 12.05.19.
//

#include <string>
#include <ostream>
#include "Date.h"

using namespace std;

#ifndef HW7_PERSON_H
#define HW7_PERSON_H


struct Person
{
public:
    Person(string const& name, string const& surname, Date const& birthDay, bool isMale);

    string const& getName() const;

    string const& getSurname() const;

    Date const& getBirthDay() const;

    bool getIsMale() const;

    void setName(const string& name);

    void setSurname(const string& surname);

    void setBirthDay(const Date& birthDay);

    void setIsMale(bool isMale);

    virtual void printPerson(ostream& out) const;

    friend ostream& operator<<(ostream& out, const Person& person);

private:
    string name;
    string surname;
    Date birthDay;
    bool isMale;
};


#endif //HW7_PERSON_H
