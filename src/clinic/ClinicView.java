package clinic;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Swing based implementation of the clinic's GUI.
 */
public class ClinicView extends JFrame implements InterfaceClinicView {
  private static final long serialVersionUID = 1L;
  private final InterfaceClinicModel model;
  private ClinicGraphicalController controller;
  private JPanel mainPanel;
  private JLabel statusLabel;
  // Support for clicking
  private Room selectedRoom;
  private Patient selectedPatient;
  
  /**
   * Constructs the ClinicView with the specific model.
   * @param model Clinic model to be interacted with.
   */
  public ClinicView(InterfaceClinicModel model) {
    this.model = model;
    setTitle("Clinic Management System");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setMinimumSize(new Dimension(300, 300));
    setLocationRelativeTo(null);
  }
  
  /**
   * Initializes and displays the GUI.
   */
  public void initialize() {
    setLayout(new BorderLayout());

    // Create the menu bar
    setJMenuBar(createMenuBar());

    // Create the main panel for graphical representation
    mainPanel = new GraphicsPanel(); 
    mainPanel.setLayout(new BorderLayout());
    JScrollPane scrollPane = new JScrollPane(mainPanel);
    add(scrollPane, BorderLayout.CENTER);

    // Status label for messages
    statusLabel = new JLabel("Welcome to the Clinic Management System");
    add(statusLabel, BorderLayout.SOUTH);
    
    // Functionality for mouse input
    RoomSelectionListener roomSelectionListener = new RoomSelectionListener(model, statusLabel);
    mainPanel.addMouseListener(roomSelectionListener);
    
    // Show the welcome screen initially
    showWelcomeScreen();
    setVisible(true);
  }
  
  /**
   * Creates the menu bar with file and actions menu.
   * @return The menu bar.
   */
  private JMenuBar createMenuBar() {
    JMenuBar menuBar = new JMenuBar();

    // File Menu
    JMenu fileMenu = new JMenu("File");
    JMenuItem loadItem = new JMenuItem("Load Clinic Data");
    loadItem.addActionListener(e -> showFileChooser());
    JMenuItem clearItem = new JMenuItem("Clear Records");
    clearItem.addActionListener(e -> controller.clearRecords());
    JMenuItem exitItem = new JMenuItem("Exit");
    exitItem.addActionListener(e -> System.exit(0));
    fileMenu.add(loadItem);
    fileMenu.add(clearItem);
    fileMenu.addSeparator();
    fileMenu.add(exitItem);

    menuBar.add(fileMenu);
    
    // Actions Menu
    JMenu actionsMenu = new JMenu("Actions");
    actionsMenu.add(createMenuItem("Register New Patient", e -> promptRegisterPatient()));
    actionsMenu.add(createMenuItem("Register Clinical Staff", e -> promptRegisterClinicalStaff()));
    actionsMenu.add(createMenuItem("Assign Patient to Room", e -> promptAssignRoom()));
    actionsMenu.add(createMenuItem("Assign Staff to Patient", e -> promptAssignStaff()));
    actionsMenu.add(createMenuItem("Show Patient Info", e -> promptShowPatientInfo()));
    actionsMenu.add(createMenuItem("Display Room Info", e -> promptDisplayRoomInfo()));
    actionsMenu.add(createMenuItem("Unassign Staff", e -> promptUnassignStaff()));
    actionsMenu.add(createMenuItem("Send Patient Home", e -> promptSendPatientHome()));
    actionsMenu.add(createMenuItem("Add Room", e -> promptAddRoom()));
    actionsMenu.add(createMenuItem("Deactivate Staff", e -> promptDeactivateStaff()));
    actionsMenu.add(createMenuItem("List Active Staff with Patients", 
        e -> promptListActiveStaffWithPatients()));
    actionsMenu.add(createMenuItem("List Inactive Patients", e -> promptListInactivePatients()));
    actionsMenu.add(createMenuItem("Patient Visit Summary (Past Year)", 
        e -> promptPatientVisitSummaryPastYear()));
    actionsMenu.add(createMenuItem("List All Staff and Patient Counts", 
        e -> promptListAllStaffAndPatientCounts()));
    menuBar.add(actionsMenu);

    return menuBar;
  }
  
  /**
   * Creates a menu item with the attached action listener.
   * @param text The text of the menu item.
   * @param actionListener The action listener to attach.
   * @return JMenuItem the created menu item.
   */
  private JMenuItem createMenuItem(String text, ActionListener actionListener) {
    JMenuItem menuItem = new JMenuItem(text);
    menuItem.addActionListener(actionListener);
    return menuItem;
  }
  
  /**
   * Implementation of file upload system for ease of user use.
   */
  private void showFileChooser() {
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      controller.loadClinicData(selectedFile.getAbsolutePath());
      showMessage("File loaded: " + selectedFile.getName());
    } else {
      showMessage("File loading cancelled.");
    }
  }

  @Override
  public void showWelcomeScreen() {
    mainPanel.removeAll();
    
    // Create a panel to center align both labels vertically
    JPanel welcomePanel = new JPanel();
    welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
    
    // Main welcome message
    JLabel welcomeLabel = new JLabel("Welcome to the Clinic Management System", JLabel.CENTER);
    welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
    welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    welcomePanel.add(welcomeLabel);
    
    // Additional credit message
    JLabel creditLabel = new JLabel("Created by Adrian Halgas", JLabel.CENTER);
    creditLabel.setFont(new Font("Arial", Font.ITALIC, 14));
    creditLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    welcomePanel.add(creditLabel);
    
    // Add the panel to the mainPanel
    mainPanel.add(welcomePanel, BorderLayout.CENTER);
    mainPanel.revalidate();
    mainPanel.repaint();    
  }

  @Override
  public void updateGraphics() {
    // Clear the main panel to prepare for new content
    mainPanel.removeAll();

    // Reload the updated image from the file
    ImageIcon clinicImage = new ImageIcon("clinic_layout.png");

    // Scale the image to fit the main panel size if necessary
    Image scaledImage = clinicImage.getImage().getScaledInstance(
            mainPanel.getWidth(), mainPanel.getHeight(), Image.SCALE_SMOOTH);
    JLabel graphicLabel = new JLabel(new ImageIcon(scaledImage));

    // Add the updated image to the panel
    mainPanel.add(graphicLabel, BorderLayout.CENTER);

    // Re-validate and repaint to ensure proper rendering
    mainPanel.revalidate();
    mainPanel.repaint();

    // Debugging message
    System.out.println("Graphics updated successfully.");
  }

  @Override
  public void showMessage(String message) {
    JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);    
  }

  @Override
  public void showError(String errorMessage) {
    JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);    
  }

  @Override
  public String getUserInput(String prompt) {
    return JOptionPane.showInputDialog(this, prompt);
  }

  @Override
  public void clearRecords() {
    model.clearRecords();
    updateGraphics();
    showMessage("All records have been cleared.");    
  }

  @Override
  public void loadClinicData(String filePath) {
    model.loadClinicData(filePath);
    updateGraphics();
    showMessage("Clinic data loaded successfully.");    
  }

  @Override
  public void updateView() {
    updateGraphics();    
  }

  @Override
  public void setController(ActionListener controller) {
    JMenuBar menuBar = getJMenuBar();
    if (menuBar != null) {
      for (int i = 0; i < menuBar.getMenuCount(); i++) {
        JMenu menu = menuBar.getMenu(i);
        for (int j = 0; j < menu.getItemCount(); j++) {
          JMenuItem menuItem = menu.getItem(j);
          if (menuItem != null) {
            menuItem.addActionListener(controller); 
          } 
        } 
      }
    }
    this.controller = (ClinicGraphicalController) controller; 
  }

  @Override
  public void promptRegisterPatient() {
    String firstName = getUserInput("Enter patient's first name:");
    String lastName = getUserInput("Enter patient's last name:");
    String dob = getUserInput("Enter patient's date of birth (YYYY-MM-DD):");
    
    if (firstName == null || lastName == null || dob == null) {
      showError("Registration cancelled.");
      return;
    }
    controller.registerNewPatient(firstName, lastName, dob);    
    
    // Collect visit record.
    LocalDateTime visitTime;
    try {
      String dateTimeStr = getUserInput("Enter date and time of visit (YYYY-MM-DD HH:MM):");
      if (dateTimeStr == null) {
        showError("Visit record entry cancelled.");
        return;
      }
      visitTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    } catch (IllegalStateException e) {
      showError("Invalid date/time format.");
      return; 
    }
    String chiefComplaint = getUserInput("Enter chief complaint:");
    if (chiefComplaint == null) {
      showError("Visit record entry cancelled.");
      return;
    }
    String temperatureStr = getUserInput("Enter body temperature (Celsius):");
    if (temperatureStr == null) {
      showError("Visit record entry cancelled.");
      return;
    }
    double temperature;
    try {
      temperature = Double.parseDouble(temperatureStr);
    } catch (NumberFormatException e) {
      showError("Invalid temperature format.");
      return;    
    }
    controller.addInitialVisitRecord(firstName, lastName, visitTime, chiefComplaint, temperature);
    showMessage("Patient registered successfully with initial visit record."); 
  }

  @Override
  public void promptAssignRoom() {
    if (selectedRoom == null) {
      String roomName = getUserInput("No room selected on the map. Enter room name:");
      if (roomName == null) {
        return; // Cancelled
      }
      selectedRoom = model.getAllRooms().stream()
              .filter(room -> room.getRoomName().equalsIgnoreCase(roomName))
              .findFirst()
              .orElse(null);
    }
    if (selectedPatient == null) {
      String patientName = getUserInput("No patient selected on the map. "
          + "Enter patient's full name (e.g., John Doe):");
      if (patientName == null) {
        return; // Cancelled
      }
      selectedPatient = model.findPatientByName(patientName.split(" ")[0], 
          patientName.split(" ")[1]);
    }
    
    if (selectedRoom != null && selectedPatient != null) {
      controller.assignRoom(selectedPatient, selectedRoom);
      showMessage("Assigned " + selectedPatient.getFullName() 
          + " to " + selectedRoom.getRoomName());
    } else {
      showError("Invalid room or patient.");
    }
    
    // Clear selections
    selectedRoom = null;
    selectedPatient = null;
  }

  @Override
  public void promptAssignStaff() {
    String patientName = getUserInput("Enter patient's full name (e.g., John Doe):");
    String staffName = getUserInput("Enter staff's full name (e.g., Dr. Smith):");
    Patient patient = model.findPatientByName(patientName.split(" ")[0], 
        patientName.split(" ")[1]);
    ClinicalStaff staff = (ClinicalStaff) model.findStaffByName(staffName.split(" ")[0], 
        staffName.split(" ")[1]);
    if (patient != null && staff != null) {
      controller.assignStaff(patient, staff);
    } else {
      showError("Invalid patient or staff name."); 
    }    
  }

  @Override
  public void promptShowPatientInfo() {
    String patientName = getUserInput("Enter patient's full name (e.g., John Doe):");
    Patient patient = model.findPatientByName(patientName.split(" ")[0], 
        patientName.split(" ")[1]);
    if (patient != null) {
      showMessage(patient.toString());
    } else {
      showError("Patient not found.");
    }    
  }

  @Override
  public void promptUnassignStaff() {
    String patientName = getUserInput("Enter patient's full name (e.g., John Doe):");
    String staffName = getUserInput("Enter staff's full name (e.g., Dr. Smith):");
    Patient patient = model.findPatientByName(patientName.split(" ")[0], 
        patientName.split(" ")[1]);
    ClinicalStaff staff = (ClinicalStaff) model.findStaffByName(staffName.split(" ")[0], 
        staffName.split(" ")[1]);
    if (patient != null && staff != null) {
      controller.unassignStaff(patient, staff);
    } else {
      showError("Invalid patient or staff name.");
    }
  }

  @Override
  public void promptSendPatientHome() {
    String patientName = getUserInput("Enter patient's full name (e.g., John Doe):");
    Patient patient = model.findPatientByName(patientName.split(" ")[0], 
        patientName.split(" ")[1]);
    String staffName = getUserInput("Enter physician's full name (e.g., Dr. Smith):");
    ClinicalStaff staff = (ClinicalStaff) model.findStaffByName(staffName.split(" ")[0], 
        staffName.split(" ")[1]);
    if (patient != null && staff != null && "Doctor".equalsIgnoreCase(staff.getRole())) {
      model.deactivatePatient(patient, staff);
      updateGraphics();
      showMessage(patient.getFullName() + " has been sent home."); 
    } else {
      showError("Invalid patient or physician.");
    }    
  }

  @Override
  public void promptAddRoom() {
    String roomName = getUserInput("Enter room's name:");
    String roomType = getUserInput("Enter room type (e.g., Exam, Surgery, Waiting):");
    int x1 = Integer.parseInt(getUserInput("Enter room dimensions - X1 coordinate:"));
    int y1 = Integer.parseInt(getUserInput("Enter room dimensions - Y1 coordinate:"));
    int x2 = Integer.parseInt(getUserInput("Enter room dimensions - X2 coordinate:"));
    int y2 = Integer.parseInt(getUserInput("Enter room dimensions - Y2 coordinate:"));
    Room room = new Room(x1, y1, x2, y2, roomType, roomName);
    model.addRoom(room);
    updateGraphics();
    showMessage("Room added successfully: " + room.getRoomName());
  }

  @Override
  public void promptRegisterClinicalStaff() {
    String firstName = getUserInput("Enter staff's first name:");
    String lastName = getUserInput("Enter staff's last name:");
    String education = getUserInput("Enter staff's education level:");
    String role = getUserInput("Enter role (Doctor/Nurse):");
    String npi = getUserInput("Enter NPI (optional):");
    controller.registerNewClinicalStaff(firstName, lastName, education, role, npi);    
  }

  @Override
  public void promptDisplayRoomInfo() {
    String roomIndex = getUserInput("Enter room index:");
    controller.displayRoomInfo(Integer.parseInt(roomIndex));    
  }

  @Override
  public void promptDeactivateStaff() {
    String firstName = getUserInput("Enter staff's first name:");
    String lastName = getUserInput("Enter staff's last name:");
    controller.deactivateStaff(firstName, lastName);    
  }

  @Override
  public void promptListActiveStaffWithPatients() {
    controller.listActiveStaffWithPatients();    
  }

  @Override
  public void promptListInactivePatients() {
    controller.listInactivePatients();    
  }

  @Override
  public void promptPatientVisitSummaryPastYear() {
    controller.patientVisitSummaryPastYear();    
  }

  @Override
  public void promptListAllStaffAndPatientCounts() {
    controller.listAllStaffAndPatientCounts();    
  }
}
