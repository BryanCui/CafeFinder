# Instruction


## How to Run

This applicaiton is implemented using **Android Studio**, with API 22. 
This application includes a cafe list, and a bonus Google Map fragment that shows all cafes around current location. The data of cafe list will refresh when location change is detected. 

To run the application, clone the project into a folder called FindCafe and use **Android Studio** to load the project. Then run it.

## Implementation

The application uses TabLayout to manage fragments. In this case, there are two fragments in the application. One is **CafeList** fragment, the other is **GoogleMap** fragment. 

For the **CafeList**, the application uses Foursquare API to get the data and store them into database. 

There is a AsyncTask that handle database. The task is called when the application starts. Then it will be called when the current location is changed. It calls the API server to get latest data.

Dependency management tool is Gradle.

There are tests which can be executed directly.

## Assumption

There is a problem: when using real android device connected to computer with cable to test the application, the progress dialog will suspend until time out. However, the application runs without connecting to **Android Studio**. 