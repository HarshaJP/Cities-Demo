The application is developed for the assessment purpose only.

# Tools:
1. Intellij (Minimum Java-8 configured)
2. Postman/Insomnia for API testing.

# Assumptions:
1. There are multiple entries for same city, considering the most recent ones for the same city in the database.

# How to run:
1. Import the codebase as maven project in the IDE.
2. Run the CityImageProcessingApplication.java file and application is ready for test (or build war from the maven install and deploy in tomcat)
3. The DataLoader will load the data from csv file and writes into the database.

# Things covered in the code
1. SOLID principles followed in the codebase by writing individual classes for different functionality. like controller, service file, POJO file.
2. Exceptions are handled. One custom exception is written for Not-Found Exception.
3. Unit test cases for Controller and service file are added.

# Things to improve
1. Logging can be handled for debugging purposes to a specific file/directory.
2. Additional exceptions can be handled as per the message classification required by the application.
3. Authentication and Authorizations can be added
