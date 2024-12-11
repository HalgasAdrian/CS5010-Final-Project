package clinic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

/**
 * Represents a graphical representation of a room.
 */
public class RoomGraphics {
  private final int x; 
  private final int y; 
  private final int width;
  private final int height;
  private final String roomName;
  private final List<String> patients;
  private final List<String> staff;
  private final boolean isOccupied;
  
  /**
   * Generates graphic for each room.
   * @param x X coord.
   * @param y Y coord.
   * @param width Width of the room.
   * @param height Height of the room.
   * @param roomName Name of the room.
   * @param isOccupied Occupancy status.
   * @param patients Patient in room.
   * @param staff Staff in room.
   */
  public RoomGraphics(int x, int y, int width, int height, String roomName, 
      boolean isOccupied, List<String> patients, List<String> staff) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.roomName = roomName;
    this.isOccupied = isOccupied;
    this.patients = patients;
    this.staff = staff;
  }
  
  /**
   * Draws the room on the given graphics context.
   * 
   * @param g The graphics context to draw on.
   */
  public void draw(Graphics g) {
    g.setColor(isOccupied ? Color.RED : Color.GREEN); // Red for occupied, green for empty
    g.fillRect(x, y, width, height);

    g.setColor(Color.BLACK);
    g.drawRect(x, y, width, height);

    g.drawString(roomName, x + 5, y + 15); // Room name
    
    int textY = y + 30;
    g.setFont(new Font("Arial", Font.PLAIN, 12));
    
    for (String patient : patients) {
      g.drawString("Patient: " + patient, x + 5, textY);
      textY += 15;
    }
    
    for (String staffMember : staff) {
      g.drawString("Staff: " + staffMember, x + 5, textY);
      textY += 15;
    }
    
  }

}
