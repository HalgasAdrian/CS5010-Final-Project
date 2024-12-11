package clinic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *  Clinical Model setup, lets us communicate with the controller.
 *  Here we can manage rooms, manage our patients, and manage our staff.
 */

public class ClinicModel implements InterfaceClinicModel {
  private List<Patient> patients = new ArrayList<>();
  private List<Staff> staffMembers = new ArrayList<>();
  private List<Room> rooms = new ArrayList<>();
  private GraphicsHandler graphicsHandler;
  private InterfaceClinicView view;
  
  /**
   * Creates our graphic size 800x600 pixels.
   */
  public ClinicModel() {
    this.graphicsHandler = new GraphicsHandler(800, 600);
    updateGraphics();
  }
  
  /**
   * Set the view to be notified of updates.
   * @param view The view to set. 
   */
  public void setView(InterfaceClinicView view) {
    this.view = view;
  }
  
  private void updateGraphics() {
    graphicsHandler.initializeGraphics();
    for (Room room : rooms) {
      boolean isOccupied = !room.getAssignedPatients().isEmpty();
      List<String> patientNames = room.getAssignedPatients().stream()
          .map(Patient::getName)
          .collect(Collectors.toList());
      List<String> staffNames = room.getAssignedPatients().stream()
          .flatMap(p -> p.getAssignedClinicalStaff().stream())
          .distinct()
          .map(Staff::getFullName)
          .collect(Collectors.toList());
      
      graphicsHandler.drawRoom(room.getX1(), room.getY1(), room.getX2() - room.getX1(),
          room.getY2() - room.getY1(), room.getRoomName(), isOccupied,
          patientNames, staffNames);
    }
    graphicsHandler.saveImage("clinic_layout.png");
    notifyViewUpdate(); // Notify the view of updates
  }
  
  @Override
  public void addPatient(Patient patient) {
    patients.add(patient);
    updateGraphics();
  }

  
  @Override
  public void addStaff(Staff staff) {
    staffMembers.add(staff);
    updateGraphics();
  }
  
  @Override
  public void addRoom(Room room) {
    rooms.add(room);
    updateGraphics();
  }
  
  @Override
  public Room getRoom(int index) {
    return rooms.get(index);
  }
  
  @Override
  public String getRoomDetails(int roomIndex) {
    Room room = getRoom(roomIndex);
    return room != null ? room.toString() : "Room not found";
  }
  
  @Override
  public List<Room> getAllRooms() {
    return new ArrayList<>(rooms);
  }
  
  @Override
  public void activatePatient(Patient patient) {
    patient.activate();
    updateGraphics();
  }
  
  @Override 
  public void deactivatePatient(Patient patient, ClinicalStaff approver) {
    patient.deactivate();
    patient.setDeactivationApprover(approver);
    updateGraphics();
  }
  
  @Override 
  public void activateStaff(Staff staff) {
    staff.activate();
    updateGraphics();
  }
  
  @Override
  public void deactivateStaff(Staff staff) {
    staff.deactivate();
    updateGraphics();
  }
  
  @Override
  public List<Patient> getAllPatients() {
    return new ArrayList<>(patients);
  }
  
  @Override
  public List<Staff> getAllStaff() {
    return new ArrayList<>(staffMembers);
  }
  
  @Override
  public String getSeatingChart() {
    return rooms.stream()
        .map(Room::toString)
        .collect(Collectors.joining("\n"));
  }
  
  @Override
  public void assignPatientToRoom(Patient patient, Room targetRoom) {
    if (targetRoom == null) {
      throw new IllegalArgumentException("The target room cannot be null.");
    }
    Room currentRoom = patient.getAssignedRoom();    
    if (currentRoom != null) {
      currentRoom.removePatient(patient);
    }
    if (patient != null) {
      Patient defensiveCopy = new Patient(
          new String(patient.getFirstName()), 
          new String(patient.getLastName()), 
          new String(patient.getDob())
      );
      targetRoom.assignPatient(defensiveCopy);
      defensiveCopy.assignRoom(targetRoom); 
    }
    updateGraphics();
  }
  
  @Override
  public Staff findStaffByName(String firstName, String lastName) {
    return staffMembers.stream()
        .filter(s -> s.getFirstName().equals(firstName) && s.getLastName().equals(lastName))
        .findFirst()
        .orElse(null);
  }
  
  @Override
  public Patient findPatientByName(String firstName, String lastName) {
    return patients.stream()
        .filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
        .findFirst()
        .orElse(null);
  }
  
  @Override
  public ClinicalStaff getDeactivationApprover(Patient patient) {
    return patient.getDeactivationApprover(); // Get this directly from patient.
  }
  
  @Override
  public void assignClinicalStaffToPatient(ClinicalStaff staff, Patient patient) {
    patient.addClinicalStaff(staff); // Adding staff directly to patient's list.
  }
  
  @Override
  public Set<ClinicalStaff> getClinicalStaffAssignedToPatient(Patient patient) {
    return new HashSet<>(patient.getAssignedClinicalStaff()); // Convert list to set.
  }


  @Override
  public List<ClinicalStaff> listActiveClinicalStaff() {
    return patients.stream()
        .flatMap(patient -> patient.getAssignedClinicalStaff().stream())
        .distinct()
        .filter(staff -> staff.isActive() && hasActivePatients(staff))
        .collect(Collectors.toList());
  }
  
  private boolean hasActivePatients(ClinicalStaff staff) {
    return patients.stream()
        .anyMatch(patient -> 
            patient.getAssignedClinicalStaff().contains(staff) 
            && patient.getActiveVisits().size() > 0);
  }

  // List patients inactive for more than 365 days.
  @Override
  public List<Patient> listInactivePatients() {
    LocalDate oneYearAgo = LocalDate.now().minusDays(365);
    return patients.stream()
        .filter(p -> p.getVisitRecords().stream()
            .noneMatch(v -> v.getVisitTime().toLocalDate().isAfter(oneYearAgo)))
        .collect(Collectors.toList());
  }

  //Summary of patient visits over the past year
  @Override
  public Map<Patient, Long> patientVisitSummaryPastYear() {
    return patients.stream()
        .collect(Collectors.toMap(
            patient -> patient, 
            patient -> (long) patient.getVisitsLastYear().size(), 
            (existing, replacement) -> existing, 
            LinkedHashMap::new 
        ));
  }

  @Override
  public void unassignClinicalStaff(ClinicalStaff staff) {
    patients.forEach(patient -> 
        patient.getAssignedClinicalStaff().removeIf(s -> s.equals(staff)));
    updateGraphics();
  }

  @Override
  public void unassignClinicalStaffFromPatient(ClinicalStaff staff, Patient patient) {
    if (patient != null && staff != null) {
      patient.getAssignedClinicalStaff().remove(staff);
      updateGraphics();
    }    
  }

  @Override
  public Map<Staff, Integer> listStaffPatientCounts() {
    Map<Staff, Integer> staffPatientCounts = new HashMap<>();
    for (Staff staff : staffMembers) {
      int count = 0;
      for (Patient patient : patients) {
        if (patient.getAssignedClinicalStaff().contains(staff)) {
          count++;
        }
      }
      staffPatientCounts.put(staff, count);
    }
    return staffPatientCounts;
  }


  @Override
  public Map<ClinicalStaff, List<Patient>> listActiveStaffWithPatients() {
    Map<ClinicalStaff, List<Patient>> activeStaffPatients = new HashMap<>();
    for (Patient patient : patients) {
      if (!patient.getActiveVisits().isEmpty()) {
        for (ClinicalStaff staff : patient.getAssignedClinicalStaff()) {
          activeStaffPatients.computeIfAbsent(staff, k -> new ArrayList<>()).add(patient); 
        } 
      }
    }
    return activeStaffPatients;  
  }

  @Override
  public void notifyViewUpdate() {
    if (view != null) {
      try {
        System.out.println("Notifying the view to update graphics...");
        view.updateGraphics(); // Notify the view to refresh its graphical map.
      } catch (IllegalStateException e) {
        System.err.println("Error notifying view to update: " + e.getMessage());
      }
    } else {
      System.err.println("No view is attached to notify.");
    }
    
  }

  @Override
  public void loadClinicData(String filePath) {    
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty() || line.startsWith("#")) {
          continue;
        }
        String[] parts = line.split(",\\s*");
        switch (parts[0].toLowerCase()) {
          case "room":
            // Room, name, type, x1, y1, x2, y2
            String roomName = parts[1];
            String roomType = parts[2];
            int x1 = Integer.parseInt(parts[3]);
            int y1 = Integer.parseInt(parts[4]);
            int x2 = Integer.parseInt(parts[5]);
            int y2 = Integer.parseInt(parts[6]);
            addRoom(new Room(x1, y1, x2, y2, roomType, roomName));
            break;
           
          case "staff":
            // Staff, name, role, education, npi
            String staffName = parts[1];
            String role = parts[2];
            String educationLevel = parts[3];
            String npi = parts.length > 4 ? parts[4] : null;

            String[] nameParts = staffName.split("\\s+");
            String firstName = nameParts[0];
            String lastName = nameParts.length > 1 ? nameParts[1] : "";
            
            if ("Doctor".equalsIgnoreCase(role) || "Physician".equalsIgnoreCase(role)) {
              addStaff(new ClinicalStaff(firstName, lastName, educationLevel, npi, role));
            } else {
              addStaff(new NonClinicalStaff(firstName, lastName, 
                  educationLevel, "Basic", role));    
            }           
            break;
           
          case "patient":
            // Patient, name, dob, visitTime, complaint, temperature
            String patientName = parts[1].trim();
            String dob = parts[2].trim();
            String[] patientNameParts = patientName.split("\\s+");
            String patientFirstName = patientNameParts[0];
            String patientLastName = patientNameParts.length > 1 ? patientNameParts[1] : "";

            Patient patient = new Patient(patientFirstName, patientLastName, dob);
            addPatient(patient);
            
            // Adding a visit record if provided
            if (parts.length > 3) {
              try {
                LocalDateTime visitTime = LocalDateTime.parse(parts[3].trim());
                String chiefComplaint = parts[4].trim();
                double temperature = Double.parseDouble(parts[5].trim());

                InterfaceVisitRecord visitRecord = new VisitRecord(visitTime, 
                    chiefComplaint, temperature);
                patient.addVisitRecord(visitRecord);
              
              } catch (IllegalStateException e) {
                System.err.println("Error parsing visit record for patient: " 
                    + patientName + ". Skipping visit record.");  
              }  
            }   
            break;
            
          case "assign":
            // Assign, patientName, roomName
            Patient assignPatient = findPatientByName(parts[1].split(" ")[0], 
                parts[1].split(" ")[1]);
            Room assignRoom = rooms.stream()
                    .filter(r -> r.getRoomName().equalsIgnoreCase(parts[2]))
                    .findFirst()
                    .orElse(null);
            if (assignPatient != null && assignRoom != null) {
              assignPatientToRoom(assignPatient, assignRoom);
            }
            break;
            
          case "assignstaff":
            // AssignStaff, patientName, staffName
            Patient staffPatient = findPatientByName(parts[1].split(" ")[0], 
                parts[1].split(" ")[1]);
            Staff assignStaff = findStaffByName(parts[2].split(" ")[0], 
                parts[2].split(" ")[1]);
            if (staffPatient != null && assignStaff instanceof ClinicalStaff) {
              assignClinicalStaffToPatient((ClinicalStaff) assignStaff, staffPatient);
            }
            break;
           
          case "deactivatestaff":
            // DeactivateStaff, staffName
            Staff deactivateStaff = findStaffByName(parts[1].split(" ")[0], parts[1].split(" ")[1]);
            if (deactivateStaff != null) {
              deactivateStaff(deactivateStaff);
            }
            break;
            
          case "sendhome":
            // SendHome, patientName, approvingStaff
            Patient sendHomePatient = findPatientByName(parts[1].split(" ")[0], 
                parts[1].split(" ")[1]);
            ClinicalStaff approvingStaff = (ClinicalStaff) 
                findStaffByName(parts[2].split(" ")[0], parts[2].split(" ")[1]);
            if (sendHomePatient != null && approvingStaff != null) {
              deactivatePatient(sendHomePatient, approvingStaff);
              
            }
            break;
            
          default:
            System.err.println("Unknown directive: " + parts[0]);
        }    
      }
    } catch (IOException e) {
      System.err.println("Error reading clinic data file: " + e.getMessage());
      e.printStackTrace();
    } catch (NumberFormatException e) {
      System.err.println("Error parsing number in clinic data: " + e.getMessage());
    } catch (ArrayIndexOutOfBoundsException e) {
      System.err.println("Malformed line in clinic data file: " + e.getMessage());
    }
    notifyViewUpdate(); // Update view after loading data.    
  }

  @Override
  public void clearRecords() {
    patients.clear();
    staffMembers.clear();
    rooms.clear();
    notifyViewUpdate(); // Update view after clearing records.    
  }
}
