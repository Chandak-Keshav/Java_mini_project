output: main.o product.o Portal.o
	g++ main.o product.o Portal.o -o output

PortalMain.o: PortalMain.cpp
	g++ -c main.cpp

product.o: product.cpp product.h
	g++ -c product.cpp

Portal.o: Portal.cpp Portal.h
	g++ -c Portal.cpp

clean:
	rm *.o output