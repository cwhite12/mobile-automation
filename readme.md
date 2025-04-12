# Mobile Automation Framework – OsmAnd

## Tech Stack
- Java 17
- Appium (Java Client 9.4.0)
- TestNG
- Maven
- Page Object Model (POM)
- SLF4J 
- Extent Reports 
- Android Studio
- OsmAnd apk - Open Source Navigation app used for testing

## How to Run

1. Clone the repo / unzip the project.
2. Run `mvn clean install` to install dependencies.
3. Launch Appium server manually (`appium` in terminal).
4. Start emulator/device. (Android studio)
5. Run tests with TestNG via IntelliJ or:
   mvn test

## Features
- Page object model 
- Utility classes for waits and touch gestures (used for revealing hidden UI)
- Screenshot on failure
- Reusable flows 

## Known issues
- Slow app, handled via wait methods
- No login functionality in app, these were replaced with navigation and POI tests