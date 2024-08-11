E-Commerce Application
This project is a Maven-based Java application. Maven handles all the dependencies for you. It runs on Java, version "17.0.1" and is backwards compatible.

How to Run the Application:
There are two ways to run the application:

1. Run the Executable JAR:
Locate the executable JAR file at the root of the project directory, named e-commerce-app-1.0-SNAPSHOT.jar.
Double-click the JAR file to run the application.
	

Note; You could also run the jar file from the command line using;
java -jar e-commerce-app-1.0-SNAPSHOT.jar




2. Run the Compiled Class File:

Open a command line terminal (preferably Bash).
Navigate to the target/classes directory using the following command:
cd e-commerce-app/target/classes

Execute the compiled App class(which contains the main method) using the following command;
java com.ecommerce.app.App

Note: There is no need to compile the .java files, as they have already been compiled.


Building the Project:
This project uses Maven for build automation. If you need to build the project from source, follow these steps;

Install Maven:
Download and install Maven from the official website; https://maven.apache.org/download.cgi
Note; make sure to download the correct version and add it to the path environmental variable.

Build the Project:

Navigate to the root directory of the project and run;
mvn clean package
This command compiles the code, runs tests, and packages the application into an executable JAR file located in the target directory.