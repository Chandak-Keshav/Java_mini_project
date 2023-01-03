#pragma once

#include <iostream>
#include <bits/stdc++.h>

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

    public:

        Portal();
        void processUserCommand(string command);
        void checkResponse();
        void sendUserData();
        static int getPortalId()
        {
            return ++incrementor;
        }
};