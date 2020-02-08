#include <iostream>
#include "String.h"

using namespace std;

int main()
{
    auto s1 = String("Hello");
    auto s2 = String(" world");
    s1.printStr();
    s2.printStr();
    s1.append(s2);
    s1.printStr();
    auto s3 = String('a', 10);
    s3.printStr();
    s3.append(s1);
    s3.printStr();
    s1 = s2 = s3;
    s1.printStr();
    s2.printStr();
    s3.printStr();
    auto s4 = String(s3);
    s4.printStr();
    s4.append(s4);
    s4 = s4;
    s4.printStr();
    return 0;
}