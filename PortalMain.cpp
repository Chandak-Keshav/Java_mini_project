#include "Portal.h"
#include "product.h"

int main(void)
{
    Portal portal = Portal();

    while(1)
    {
        string command; getline(cin, command);

        if(command != "Check") portal.processUserCommand(command);
        else if(command == "Check")
        {
            while(portal.checkResponse()) portal.sendUserData();
        }
    }

    return 0;
}