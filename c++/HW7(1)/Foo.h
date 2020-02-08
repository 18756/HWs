//
// Created by alex on 15.05.19.
//

#ifndef UNDERTASK2_FOO_H
#define UNDERTASK2_FOO_H


struct Foo
{
public:
    void say() const;

protected:
    Foo(const char* msg);

private:
    const char* msg;
};

void fooSays(const Foo& foo);

Foo getFoo(const char* msg);


#endif //UNDERTASK2_FOO_H
