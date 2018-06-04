# UB-Book-Bidding-System
A web application for Bidding Books

University of Bridgeport is introducing a new system where members of UB can auction their books and other materials for educational purposes. This new facility is brought about in light of reducing the financial burden on the students, and at the same time to help the help the students earn some money. The auction is designed such that the seller decides who wins the auction. Each College under UB has an admin who oversees and facilitates the auction. Any member of the UB community can participate in the auction as a seller or a bidder.

This is a web-based application in Java using a webserver and database server. In the local environment, the system will be using Tomcat 8.5 web server along with the community edition of MYSQL. The front end will be developed using HTML and Bootstrap. The development and testing will be done using Eclipse IDE. 

The cloud deployment is done using GCP compute engine and MYSQL instance. 

Server Configuration:
Steps:
1.	GCP Compute Engine created with tomcat image
2.	Generate a static IP address for the compute engine
3.	Reserve this address for Compute Engine
4.	Start the server
5.	SSH into the server and install the following
a.	sudo apt-get update
b.	sudo apt-get install default-jdk
c.	sudo apt-get install maven
6.	Go to ipaddress:8080/manager/html 
7.	Deploy the war file on tomcat
8.	Go to the url ipaddress:8080/UBBidding/
