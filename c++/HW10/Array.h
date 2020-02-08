//
// Created by sasha on 28.05.19.
//

#include <cstddef>
#include <iostream>

#ifndef HW10_ARRAY_H
#define HW10_ARRAY_H


template<typename T>
struct Array
{
public:
    explicit Array(size_t size = 0, T const& value = T());

    Array(Array const& array);

    ~Array();

    Array& operator=(Array const& array);

    size_t size() const;

    T& operator[](size_t index);

    T const& operator[](size_t index) const;

    void print() const;


private:
    size_t mSize;
    T* mArr;
};

template<typename T, typename C>
T const& minimum(Array<T> const& array, C comparator);

template<typename T>
void flatten(Array<T> const& array, std::ostream& out);

template<typename T>
void flatten(T const& t, std::ostream& out);


#include "Array_impl.h"

#endif //HW10_ARRAY_H

