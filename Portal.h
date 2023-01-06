#pragma once

#include <iostream>
#include <random>
#include <bits/stdc++.h>

#include "product.h"

using namespace std;

class Portal
{
    private:

        int portalId; 
        int requestId = 0;
        string prevId = "0";
        
        // vector to store list of products
        vector <product> listing;
        
        // File Streams
        ofstream portalToPlatform;
        ifstream platformToPortal;
        string tempLine, tempLine2;
        string previousLine;
        map <string, string> mapIdToCommandType;
        
        // Queue to store sort parameters
        queue <string> queueOfSortParameter;

    public:

        Portal();
        void processUserCommand(string command);
        bool checkResponse();
        void sendUserData();
        void sendUserList(vector <product> &listing);
        vector <product> sortPrevList(vector <product> &listing);
};