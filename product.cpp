#include "product.h"

product:: product(string _productName, string _productUniqueName, 
                  int _price, int _quantityAvailable)
{
    productName = _productName;
    productUniqueName = _productUniqueName;
    price = _price;
    quantityAvailable = _quantityAvailable;
}

int product::getPrice() { return price; }
string product::getName() { return productName; }

void product::getDetails()
{
    string out = productName + " " + productUniqueName + " " + 
                 to_string(price) + " " + to_string(quantityAvailable);
    cout << out << "\n";
}