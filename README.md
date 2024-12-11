# CS 5010 Semester Project

This repo represents the coursework for CS 5010!

**Name:** Adrian Halgas

**Email:** halgas.a@northeastern.edu

**Preferred Name:** Adrian Halgas



### About/Overview

Give a general overview of the problem and how your program solve the problem

The semester project for CS5010 Fall 2024 @ Northeastern University is a fully implemented clinic management system which uses the Model-View-Controller (MVC) design pattern which organizes an application's logic into three components, the model, the view, and the controller. The goal of the three seperate milestones is to implement each portion of the MVC architecture, building upon previous milestone work. Teaching students about writing well thought out code and documentation, along with giving students experience with working on a large scale project.

### List of Features

List all features that are present in your program.

My clinic management system gives the user the following choices / functionality:

1. Register a new patient
2. Register a new clinical staff member
3. Display information about a specified patient
4. Display information about a specified room
5. Display information about all rooms
6. Assign a patient to a room
7. Assign a clinical staff member to a patient
8. Send a patient home
9. Deactivate a staff member
10. List active staff with current patients
11. List inactive patients
12. Patient visit summary for the past year
13. Unassign clinical staff from a patient
14. List all clinical staff and patient counts
15. Add a new room
16. Exit

### How to Run

Describe how to run your program from the JAR file. Describe what arguments are needed (if any) and what they mean.

1. Make Sure You Have Java Installed
Check if Java is installed on your computer by typing java -version in your command prompt or terminal. If it shows a version number, you're good to go.

2. Get the JAR File
A .jar file is like a package that contains all the code and resources needed to run a Java program.

3. Run the JAR File
Open your command prompt or terminal, go to the folder where the .jar file is located, and type: java -cp filename.jar com.example.Main

-cp specifies the classpath (the .jar file).
Replace com.example.Main with the main class of the program.

### How to Use the Program

Provide instructions on how to use the functionality in your program. If it is interactive, describe how to interact with your program. Pay particular attention to the parts that are not part of the example runs that you provide.

The clinic management program is launched by running Main.java, a GUI menu will apear. The main portion of the screen will be taken up by the clinic graphic display. Showing all of the different rooms along with patients and staff. 

There are two dropdown menus at the top which allow the user to upload clinic data from a txt file, along with clearing data and exiting the software. The second dropdown menu allows the user to use the functionality as shown in the features section above. They will then be prompted with input windows based on what they are function they are trying to use. 

### Example Runs

List any example runs that you have in res/ directory and provide a description of what each example represents or does. Make sure that your example runs are provided as *plain text files*.

The res/ directory contains the JAR file, and an example run .txt file where I create a few patients and clinical staff, along with rooms. This example run also performs some basic actions, such as assigning a patient to a staff memeber. Also, assigning a patient to the waiting room, then moving them into a different room.

The user can use the file upload feature on the GUI in order to upload either the example run, or the user can create their own text file and upload it. Another way to run the program is to simply use the various features built in to the program and create rooms, patients, and medical staff which they can then move around and organize, just like in a real clinic. 

Shown below is the option to load clinic data, this is where the user can upload a clinic .txt file.

![Screenshot 2024-12-10 at 9 43 20 PM](https://github.com/user-attachments/assets/ebe0bde0-873b-4b43-bb90-1e7ce91e319a)

Shown below is the list of features a user can select in order to further customize their experience using the GUI

![Screenshot 2024-12-10 at 9 43 26 PM](https://github.com/user-attachments/assets/736faf63-0b5a-4d11-af3e-6c5660bef2b9)

Shown below is an example run where a few different rooms are created, and a patient is assigned to a room.

![Screenshot 2024-12-10 at 9 43 38 PM](https://github.com/user-attachments/assets/a409dfaf-d7fc-4f46-ad9a-efd468f348f0)

### Design/Model Changes

Document what changes you have made from earlier designs. Why did you make those changes? Keep an on-going list using some form of versioning so it is clear when these changes occurred.

Refactoring the Model Design Paragraph

In order to transition our Clinic Management System from using a text-based controller to using a graphic user interface or GUI system, we must carefully restructure the model to retain any business logic that was previously managed by the text based controller and repurpose it for the gui. Key functionalities like registering patients, assigning patients and staff to rooms, along with patient and staff associations, and visit records must be moved into the model. Keeping these centralized prevents any interference from using a gui or text based controller, makin sure that our logic is reusable and consistent. Doing this allows the controller to focus on directing user inputs and triggering model updates. While our model handles data. This decoupling facilitates easier testing. We can run tests on the logic within the model without relying on our controller's behavior. 

Milestone 4 planned updates:

1. Decouple Logic from the Text-Based Controller.
   
Add methods for:
- Loading a clinic text file: Method will parse a file and populate rooms, patients, and staff in the model.
- Cleaning records: Reset rooms, patients, and staff in the model.

2. UML Class diagram updates
- Show relationships with GraphicsHandler for graphical updates.
- Include new methods in UML diagram

3. View design
- About screen, welcome message and credits, simple panel with JLabel components for text and a button/menu to process the main interface.

Testing plan:
- Test for clearing records, varify data structure reset.
- Verify that the updates trigger correct graphical changes.
- Mock the model, view, and controller.
- Verify clicking menu items calls the appropriate methods in the model.
- Data is passed correctly between model and view.
- Test GUI for functionality by hand

Current modifications done:

Classes

1. ClinicView
- Implements the graphical user interface (GUI) for the system using Swing.
- Menu-driven actions (e.g., registering patients, assigning rooms).
- Mouse interaction via RoomSelectionListener for room and patient selection.
- File uploads via JFileChooser.
- Includes status updates with a JLabel and dynamic updates of graphical content.
- Displays rooms and patients visually using the GraphicsPanel.
- Expanded loadClinicData to parse room, staff, and patient data from a file.
- Added support for dynamically updating the graphical layout (updateGraphics).
- Enhanced methods like assignPatientToRoom and assignClinicalStaffToPatient to ensure consistent state updates in both the model and GUI.
- Included error handling during file loading and data parsing.
  
2. RoomSelectionListener
- Extends MouseAdapter to handle room selection on the graphical layout.
- Allows users to click on rooms to select them or patients within the room.
- Updates statusLabel with the selected room and patient.

3. GraphicsPanel
- Extends JPanel to provide a custom-drawn panel for displaying the clinic layout.
- Displays rooms, patient names, and staff visually.

4. ClinicGraphicalController
- Acts as the bridge between the model and the view for GUI-based interaction.
- Action handling for menu items and mouse-based selections.
- Model updates in response to user interactions.
- Manages patient registrations, room assignments, and staff-patient associations.

5. GraphicsHandler
- Manages the creation and updating of the clinic layout image (clinic_layout.png).
- Handles drawing rooms and their occupancy details dynamically.
- Used in conjunction with GraphicsPanel.

5. Room
- Added methods for managing occupancy details (assignPatient, removePatient).
- Enhanced to support graphical updates with room types and dimensions (getRoomDetails, displayOccupancy).
- Differentiated between waiting rooms (multiple patients) and other rooms (single patient).

6. Patient
- Updated to support integration with GUI actions:
- Room assignments (assignRoom).
- Visit record management (addVisitRecord, getVisitRecords).
- Added methods to retrieve assigned clinical staff and their approval for deactivation.

7. ClinicalStaff
- Updated constructors to match data-parsing requirements (e.g., inclusion of npi and role).

8. NonClinicalStaff
- Updated constructors to align with GUI data parsing.

Interfaces

1. InterfaceClinicView
- Defines methods for the GUI to interact with the controller and model.
- Displaying messages or errors.
- Updating the graphical view.
- Prompts for user input (e.g., registration or assignment dialogs).

2. ClinicController
- Expanded to include GUI-specific actions like registerNewPatient, assignRoom, and more.
- Ensures consistent communication between the GUI (ClinicView) and the model.

3. InterfaceRoom
- Added/updated to include methods for visual representation (e.g., getRoomDetails, displayOccupancy).
- Supports dynamic updates of room data in the GUI.

### Assumptions

List any assumptions that you made during program development and implementations. Be sure that these do not conflict with the requirements of the project.

This is a simple project, teaching students how to use java in order to build applications. There is a user interface which should be simple enough for someone to use and understand without needing any technical documentation.

Staff Roles:
- There is a clear distinction between clinical staff (e.g., doctors, nurses) and non-clinical staff (e.g., administrators, aides).
- Non-clinical staff cannot be assigned patients.

Room Types:
- Rooms have fixed roles (e.g., exam rooms, waiting rooms, administrative rooms).
- Only waiting rooms can hold multiple patients.

Patient Visits:
- Each patient can have multiple visit records.
- Visit records must include a date/time, a chief complaint, and a body temperature.

### Limitations

What limitations exist in your program. This should include any requirements that were *not* implemented or were not working correctly (including something that might work some of the time).

This program does not handle unknown inputs well, it will give the user an error and make them retype all information they tried inputting for the current option. So the user must be careful and input the data with the same format as prompted.

Also, the current state of the program does not allow for any saves of all information. If the clinic management system is closed (user selects exit). It will simply end the program and delete all of the information stored inside. I need to implement a way of saving data, and allowing the user to re-launch an already in progress clinic.

### Citations

Be sure to cite your sources. A good guideline is if you take more than three lines of code from some source, you must include the information on where it came from. Citations should use proper [IEEE citation guidelines](https://ieee-dataport.org/sites/default/files/analysis/27/IEEE Citation Guidelines.pdf) and should include references (websites, papers, books, or other) for ***any site that you used to research a solution***. For websites, this includes name of website, title of the article, the url, and the date of retrieval**.** Citations should also include a qualitative description of what you used, and what you changed/contributed.



