#pragma once

#include <iostream>
#include <bits/stdc++.h>

#include "product.h"

using namespace std;

class Portal
{
    private:

        int portalId; 
        int requestId = 0;
        static int incrementor;
        ofstream portalToPlatform;
        ifstream platformToPortal;
        string tempLine;
        string previousLine;
        map <string, string> mapIdToCommandType;
        queue <string> queueOfSortParameter;

    public:

        Portal();
        void processUserCommand(string command);
        bool checkResponse();
        void sendUserData();
        void sendUserList(vector <product> &listing);
        vector <product> sortPrevList(vector <product> &listing);
        static int getPortalId()
        {
            return ++ Portal::incrementor;
        }
};