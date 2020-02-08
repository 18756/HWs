//
// Created by sasha on 12.05.19.
//

#include "Student.h"

Student::Student(string const& name, string const& surname, Date const& birthDay, bool isMale, int studentNumber) :
        Person(name, surname, birthDay, isMale), studentNumber(studentNumber)
{}

int Student::getStudentNumber() const
{
    return studentNumber;
}

void Student::setStudentNumber(int studentNumber)
{
    Student::studentNumber = studentNumber;
}

void Student::printPerson(ostream& out) const
{
    Person::printPerson(out);
    out << ", student number: " << studentNumber;
}
