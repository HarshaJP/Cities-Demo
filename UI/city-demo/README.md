# City-demo APP
This project was created for assignment purposes. The Application holds the User-Interface(UI) code-base for the list, search and updates city information, from the server. The application communicates to the server by making Http calls. The application uses material-design for list implementation. Its basic plain Angular Application with Bootstrap CSS.

# Prerequisites
1. Angular CLI
2. Nodejs
3. Npm
please refer the below links for installation and basic understanding
1. `https://www.zeolearn.com/magazine/setup-angular-windows`cls
2. `https://www.tutorialspoint.com/angular4/angular4_project_setup.htm`

# Installation
Once the required software is installed,
1. Navigate to city-demo folder
2. run 'ng build --env=prod'
   The command will generate the required files to deploy in the server under /dist folder (at same folder location)
3. Then navigate to index.html and change the <base href="/"> to <base href="."> 
4. Then copy the entire dist folder (renaming the dist folder to some meaningful name eg: city-demo, So the URL will be meaningful)
5. Then go to the browser and navigate to eg: "http://localhost:8180/city-demo" will list the files (provided backend services are already running in the server).


# Development setup
1. Download the IDE for the Angular application.
2. Import the project into the IDE.
3. Navigate to the city-demo folder.
4. Open the Terminal and run 'npm install'
5. To start the server run 'npm run start'
6. The application will start and access it in any browser by URL. eg(http://localhost:4200).

# Things covered in the code
1. SOLID principles followed in the codebase by writing individual classes for different functionality. like list-component, service file, model file.
2. Exceptions are handled.
3. Validation for edit has been handled, if the url pattern is wrong or if the user try to send empty string for the name, error message will be displayed in ui.


