package clinic;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

/**
 * Adding support for our mouse interactions with the program.
 * Will allow the user to select a room and or patient using mouse. 
 */
public class RoomSelectionListener extends MouseAdapter {
  private InterfaceClinicModel model;
  private Room selectedRoom;
  private Patient selectedPatient;
  private JLabel statusLabel;
  
  /**
   * Constructor to initialize RoomSelectionListener.
   * 
   * @param model The model.
   * @param statusLabel The label to update status.
   */
  public RoomSelectionListener(InterfaceClinicModel model, JLabel statusLabel) {
    this.model = model;
    this.statusLabel = statusLabel;
  }
  
  @Override
  public void mouseClicked(MouseEvent e) {
    int x = e.getX();
    int y = e.getY();
    
    // Iterate through all rooms to find the clicked one
    for (Room room : model.getAllRooms()) {
      if (x >= room.getX1() && x <= room.getX2() && y >= room.getY1() && y <= room.getY2()) {
        selectedRoom = room;

        // Convert Set to List for easy indexing
        List<Patient> patients = new ArrayList<>(room.getAssignedPatients());
        selectedPatient = patients.isEmpty() ? null : patients.get(0);

        // Update the status label to show the selected room and patient
        String message = "Selected room: " + room.getRoomName();
        if (selectedPatient != null) {
          message += ", Selected patient: " + selectedPatient.getFullName();
        } else {
          message += ", No patient selected";
        }
        statusLabel.setText(message);
        return; 
      }
    }
    // If no room was clicked
    selectedRoom = null;
    selectedPatient = null;
    statusLabel.setText("No room or patient selected.");  
  }
  
  /**
   * Selected room with mouse.
   * @return Selected room.
   */
  public Room getSelectedRoom() {
    return selectedRoom;
  }
  
  /**
   * Selected patient with mouse.
   * @return Selected patient.
   */
  public Patient getSelectedPatient() {
    return selectedPatient;
  }
}
