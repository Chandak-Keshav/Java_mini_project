#include "Portal.h"

Portal:: Portal()
{
    portalId = getPortalId();
    portalToPlatform.open("PortalToPlatform.txt", ios::app);
    platformToPortal.open("PlatformToPortal.txt");
}

void Portal::processUserCommand(string command)
{
    requestId = requestId + 1;
    string tempFirstWord = command.substr(0, command.find(" "));

    if(portalToPlatform)
    {
        mapIdToCommandType[to_string(portalId) + to_string(requestId)] = tempFirstWord;
        tempLine = to_string(portalId) + " " + to_string(requestId) + " " + command;
        portalToPlatform << tempLine << endl;
    }
}

void Portal::checkResponse()
{
    getline(platformToPortal, tempLine);
    if(tempLine != previousLine) previousLine = tempLine;
}

void Portal::sendUserData()
{
    string data; stringstream ss(previousLine);
    vector <string> dataRecieved; 

    while(getline(ss, data, ' ')) dataRecieved.push_back(data);

    string Id = dataRecieved[0] + dataRecieved[1];
    if(mapIdToCommandType[Id] == "Start")
    {
        for(int i = 2; i < dataRecieved.size(); i++) 
            cout << dataRecieved[i] << "\n";
    }

    else if(mapIdToCommandType[Id] == "List")
    {
        for(int i = 2; i < dataRecieved.size(); i++)
            cout << dataRecieved[i] << " ";
        
        cout << "\n";
    }
    
    else if(mapIdToCommandType[Id] == "Buy")
    {
        cout << dataRecieved[2] << "\n";
    }
}