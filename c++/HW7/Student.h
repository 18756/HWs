//
// Created by sasha on 12.05.19.
//

#ifndef HW7_STUDENT_H
#define HW7_STUDENT_H


#include <ostream>
#include "Person.h"

struct Student : Person
{
public:
    Student(string const& name, string const& surname, Date const& birthDay, bool isMale, int studentNumber);

    int getStudentNumber() const;

    void setStudentNumber(int studentNumber);

    void printPerson(ostream& out) const override;

private:
    int studentNumber;
};


#endif //HW7_STUDENT_H
