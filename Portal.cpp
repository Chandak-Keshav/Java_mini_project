#include "Portal.h"

Portal:: Portal()
{
    // portalId = Portal::getPortalId();
    portalId = 1;
    portalToPlatform.open("PortalToPlatform.txt", ios::app);
    platformToPortal.open("PlatformToPortal.txt");
}

void Portal::processUserCommand(string command)
{
    requestId = requestId + 1;
    string tempFirstWord = command.substr(0, command.find(" "));
    if(tempFirstWord == "List") 
    {
        auto index = command.find_last_of(' ');
        queueOfSortParameter.push(command.substr(++index));
    }

    if(portalToPlatform)
    {
        mapIdToCommandType[to_string(portalId) + to_string(requestId)] = tempFirstWord;
        tempLine = to_string(portalId) + " " + to_string(requestId) + " " + command;
        portalToPlatform << tempLine << endl;
    }
}

bool Portal::checkResponse()
{
    bool flag = false;

    getline(platformToPortal, tempLine);
    if(tempLine != previousLine)
    {
        previousLine = tempLine;
        flag = true;
    }

    return flag;
}

void Portal::sendUserData()
{
    string data; stringstream ss(previousLine);
    vector <string> dataRecieved; 

    while(getline(ss, data, ' ')) dataRecieved.push_back(data);

    vector <product> listing;

    string Id = dataRecieved[0] + dataRecieved[1]; string prevId;
    if(mapIdToCommandType[Id] == "Start")
    {
        if(!listing.empty())
        {
            vector <product> sortedList = sortPrevList(listing);
            sendUserList(sortedList);
        }
        for(int i = 2; i < dataRecieved.size(); i++) 
            cout << dataRecieved[i] << "\n";
    }

    else if(mapIdToCommandType[Id] == "List")
    {
        if(prevId != Id)
        {
            prevId = Id;
            
            if(!listing.empty())
            {
                vector <product> sortedList = sortPrevList(listing);
                sendUserList(sortedList);
            }   
        }

        listing.push_back(product(dataRecieved[2], dataRecieved[3], 
                                  stoi(dataRecieved[4]), stoi((dataRecieved[5]))));
    }
    
    else if(mapIdToCommandType[Id] == "Buy")
    {
        if(!listing.empty())
        {
            vector <product> sortedList = sortPrevList(listing);
            sendUserList(sortedList);
        }
        cout << dataRecieved[2] << "\n";
    }
    
}

vector <product> Portal::sortPrevList(vector <product> &listing)
{
    if(queueOfSortParameter.front() == "Price")
    {
        sort(listing.begin(), listing.end(), [](product &product1, product &product2){

        return product1.getPrice() < product2.getPrice();
        });
    }

    else if(queueOfSortParameter.front() == "Name")
    {
        sort(listing.begin(), listing.end(), [](product &product1, product &product2){

        return product1.getName() < product2.getName();
        });
    }

    queueOfSortParameter.pop();

    return listing;
}

void Portal::sendUserList(vector <product> &sortedList)
{
    for(int i = 0; i < sortedList.size(); i++)
        sortedList[i].getDetails();
}