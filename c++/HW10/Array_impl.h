//
// Created by sasha on 28.05.19.
//

#include <iostream>
#include "Array.h"

#ifndef HW10_ARRAY_IMPL_H
#define HW10_ARRAY_IMPL_H


template<typename T>
Array<T>::Array(size_t size, const T& value)
{
    mSize = size;
    mArr = (T*) (new char[mSize * sizeof(T)]);
    for (size_t i = 0; i < mSize; ++i)
    {
        new(mArr + i) T(value);
    }
}

template<typename T>
Array<T>::Array(Array const& array)
{
    mSize = array.mSize;
    mArr = (T*) (new char[mSize * sizeof(T)]);
    for (size_t i = 0; i < mSize; ++i)
    {
        new(mArr + i) T(array[i]);
    }
}


template<typename T>
Array<T>& Array<T>::operator=(Array const& array)
{
    if (this != &array)
    {
        delete[] (char*) mArr;
        mSize = array.mSize;
        mArr = (T*) (new char[mSize * sizeof(T)]);
        for (size_t i = 0; i < mSize; ++i)
        {
            new(mArr + i) T(array[i]);
        }
    }
    return *this;
}

template<typename T>
size_t Array<T>::size() const
{
    return mSize;
}

template<typename T>
T& Array<T>::operator[](size_t index)
{
    return mArr[index];
}

template<typename T>
T const& Array<T>::operator[](size_t index) const
{
    return mArr[index];
}

template<typename T>
void Array<T>::print() const
{
    std::cout << "[";
    for (size_t i = 0; i < mSize - 1; ++i)
    {
        std::cout << mArr[i] << ", ";
    }
    std::cout << mArr[mSize - 1] << "]" << std::endl;
}

template<typename T>
Array<T>::~Array()
{
    for (size_t i = 0; i < mSize; ++i)
    {
        mArr[i].~T();
    }
    delete[] (char*) mArr;
}


template<typename T, typename C>
T const& minimum(Array<T> const& array, C comparator)
{
    size_t minId = 0;
    for (size_t i = 1; i < array.size(); ++i)
    {
        if (comparator(array[minId], array[i]))
        {
            minId = i;
        }
    }
    return array[minId];
}

template<typename T>
void flatten(Array<T> const& array, std::ostream& out)
{
    for (size_t i = 0; i < array.size(); ++i)
    {
        flatten(array[i], out);
    }
}

template<typename T>
void flatten(T const& t, std::ostream& out)
{
    out << t << " ";
}


#endif //HW10_ARRAY_IMPL_H
