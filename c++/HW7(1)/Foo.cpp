//
// Created by alex on 15.05.19.
//

#include <iostream>
#include "Foo.h"

using namespace std;

void Foo::say() const
{
    cout << "Foo says: " << msg << "\n";
}

Foo::Foo(const char* msg) : msg(msg)
{}


void fooSays(const Foo& foo)
{
    foo.say();
}

Foo getFoo(const char* msg) {
    struct Foo2 : Foo {
        Foo2(const char* msg) : Foo(msg) {}
    };
    return Foo2(msg);
}
