// #pragma once
#ifndef PRODUCT_H
#define PRODUCT_H
#include <iostream>
#include <bits/stdc++.h>

using namespace std;

class product
{
    private:

        string productName;
        string productUniqueName;
        float price;
        int quantityAvailable;

    public:

        product(string _productName, string _productUniqueName, float _price, int _quantityAvailable);
        void getDetails();
        float getPrice();
        string getName();
};
#endif