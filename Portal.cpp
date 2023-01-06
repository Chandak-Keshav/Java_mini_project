#include "Portal.h"

Portal:: Portal()
{
    // Sets up a unique portal id pertaining
    // To a particular Portal

    std::random_device rd;  // Obtain a random number from hardware
    std::mt19937 gen(rd()); // Seed the generator
    std::uniform_int_distribution<> distr(10, 1000); // Define the range
    
    portalId = distr(gen);
    
    // Open PortalToPlatform.txt in append mode.
    // Open PlatformToPortal.txt in read mode.
    portalToPlatform.open("PortalToPlatform.txt", ios::app);
    platformToPortal.open("PlatformToPortal.txt");
}

// Processed the command sent by user to the Portal.
// And sends the appropriate message to Platform.
void Portal::processUserCommand(string command)
{
    // Generates a request id unique to every instruction
    // Sent by the user.
    requestId = requestId + 1;

    // Get first word in a string (that is before a space.)
    // Ex. If command is "List Book Price". Then tempFirstWord is List.
    string tempFirstWord = command.substr(0, command.find(" "));
    
    // If the instruction by user is that of list then store the
    // Sort Parameter in a queue to be accessed later when
    // User sends Check Instruction.
    if(tempFirstWord == "List") 
    {
        auto index = command.find_last_of(' ');
        queueOfSortParameter.push(command.substr(++index));
    }

    // If portal to platform file is existing then map
    // "String(<Portal ID>)String(<Request ID>)" to the type of instructon
    // That is tempFirstWord, to be used later to give output to the user.
    // Then Send message to Platform through PortalToPlatform.txt by storing
    // The message in buffer variable like tempLine and passing it to
    // PortalToPlatform.
    if(portalToPlatform)
    {
        mapIdToCommandType[to_string(portalId) + to_string(requestId)] = tempFirstWord;
        tempLine = to_string(portalId) + " " + to_string(requestId) + " " + command;
        portalToPlatform << tempLine << endl;
    }
}

// Checks reponse send by Platform in the PlatformToPortal.txt.
// Returns true or false depending to whether there is response or not.
bool Portal::checkResponse()
{
    // Flag to check whether there was response line-by-line
    // By Platform through PlatformToPortal.txt.
    bool flag = false;

    // Recieve line input from Platform through PlatformToPortal.txt.
    // Store in buffer variable like tempLine2.
    getline(platformToPortal, tempLine2);
    
    // Check whether there is response that is if tempLine2 is not empty.
    // To be safe check whether previously recieved response is different. 
    if(!tempLine2.empty() && previousLine != tempLine2)
    {
        // Store recieved response to be analysed later.
        previousLine = tempLine2;
        
        // Since there is response thus return true as there was response.
        // And also because this can be used to return true until there is
        // No response where false will be returned.
        flag = true;
    }

    // If there is no response then check whether listing is empty
    // corresponding to the fact that if it is not empty it is sorted
    // and hasn't been send to the user. Do that and clear the listing
    if(!flag && !listing.empty())
    {
        vector <product> sortedList = sortPrevList(listing);
        sendUserList(sortedList);
        listing.clear();
    }

    // Clear buffered input in platformToPortal NOT in the txt file.
    // For better FIO.
    if(!flag) platformToPortal.clear();

    return flag;
}

// Sends the data that has been recieved to user.
void Portal::sendUserData()
{
    // Variable to temporarily store data that is seperated by space.
    string data; stringstream ss(previousLine);
    
    // Vector to store data recieved in a line. (seperated by space)
    vector <string> dataRecieved; 

    // Seperate by space and store data.
    while(getline(ss, data, ' ')) dataRecieved.push_back(data);

    // Get Id = "<Portal Id><Request Id>"
    string Id = dataRecieved[0] + dataRecieved[1];
    
    if(mapIdToCommandType[Id] == "Start")
    {
        // If list of products is not empty then it implies
        // That the products have been sorted but not sent to
        // User on the terminal.
        if(!listing.empty())
        {
            vector <product> sortedList = sortPrevList(listing);
            sendUserList(sortedList);
            
            // After sending the user the sorted list, clear the list.
            listing.clear();
        }

        // Send data recieved corresponding to Start instruction.
        // That is All Categories.
        for(int i = 2; i < dataRecieved.size(); i++) 
            cout << dataRecieved[i] << "\n";
    }

    else if(mapIdToCommandType[Id] == "List")
    {
        // If Id stored previously is not the same as Id recieved now.
        // Then it means that the data recieved has moved onto a newer
        // List or other data corresponding to Start and Buy. But it 
        // must be a list as this Id is also mapped to List.
        // Thus now the previously stored List must be sent to user.
        // and the new data for the new list must be stored.
        if(prevId != Id)
        {
            // Set the previous Id to newly recieved Id
            prevId = Id;
            
            if(!listing.empty())
            {
                vector <product> sortedList = sortPrevList(listing);
                sendUserList(sortedList);
                listing.clear();
            }   
        }

        listing.push_back(product(dataRecieved[2], dataRecieved[3], 
                                  stof(dataRecieved[4]), stoi((dataRecieved[5]))));
    }
    
    else if(mapIdToCommandType[Id] == "Buy")
    {
        // If list of products is not empty then it implies
        // That the products have been sorted but not sent to
        // User on the terminal.
        if(!listing.empty())
        {
            vector <product> sortedList = sortPrevList(listing);
            sendUserList(sortedList);
            
            // After sending the user the sorted list, clear the list.
            listing.clear();
        }

        // Send data recieved to user on terminal that is 
        // Whether transaction was succesful or not.
        cout << dataRecieved[2] << "\n";
    }
    
}

// Sorts the recieved list according to Name or Price as sort
// Parameter. 
// If Sort Parameter -> Price. Then it sorts it from lowest to highest.
// Else If Sort Parameter -> Name. Then it sorts the list lexicographically.
vector <product> Portal::sortPrevList(vector <product> &listing)
{
    // Use queue to recieve the sort parameter that had been
    // Passed in it to be checked at the time of User
    // Passing the command Check.
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

// Prints out the details of products which have been sorted
// In the sortedList.
void Portal::sendUserList(vector <product> &sortedList)
{
    for(int i = 0; i < sortedList.size(); i++)
        sortedList[i].getDetails();
}