package clinic;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Creating the graphics panel with which display GUI.
 */
public class GraphicsPanel extends JPanel {
  private static final long serialVersionUID = 1L;
  private final List<RoomGraphics> rooms;
  
  /**
   * Creating our graphics panel.
   */
  public GraphicsPanel() {
    this.rooms = new ArrayList<>();
    setPreferredSize(new Dimension(800, 600)); // Default size
    setBackground(Color.WHITE); // Background color
  }
  
  /**
   * Adds a room to the graphical layout.
   * 
   * @param room The room's graphical data.
   */
  public void addRoom(RoomGraphics room) {
    rooms.add(room);
    repaint();
  }
  
  /**
   * Clears all rooms from the layout.
   */
  public void clearRooms() {
    rooms.clear();
    repaint();
  }
  
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // Draw each room
    for (RoomGraphics room : rooms) {
      room.draw(g);
    }
  }
  
  /**
   * Updating rooms for graphic display.
   * @param updatedRooms Updated rooms.
   */
  public void updateRooms(List<RoomGraphics> updatedRooms) {
    clearRooms();
    rooms.addAll(updatedRooms);
    repaint();
    
  }
  
}
