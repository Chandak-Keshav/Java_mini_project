#include "./demo/DemoPortal.h"
#include "./demo/product.h"
#include "./demo/DemoPortal.cpp"
#include "./demo/product.cpp"

int main(void)
{
    // Create a portal object to have an interface from user
    // To Applicaton.
    DemoPortal portal;

    while(1)
    {
        // Input any command input by the user.
        string command; getline(cin, command);

        // If the command inpyt by the user is not "Check" then
        // Process the command by using the below method.
        if(command != "Check") portal.processUserCommand(command);
        
        // Else If the command input is "Check" then while the
        // Check Response method returns true keep sending
        // Data to the user.
        else if(command == "Check")
        {
            while(portal.checkResponse()) portal.sendUserData();
        }
    }

    return 0;
}