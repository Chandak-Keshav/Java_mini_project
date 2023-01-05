#pragma once

#include <iostream>
#include <bits/stdc++.h>

using namespace std;

class product
{
    private:

        string productName;
        string productUniqueName;
        int price;
        int quantityAvailable;

    public:

        product(string _productName, string _productUniqueName, int _price, int _quantityAvailable);
        void getDetails();
        int getPrice();
        string getName();
};