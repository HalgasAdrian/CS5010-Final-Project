package clinic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Setting up our graphics handler in order to visualize the clinic.
 * Will draw the room, and populate it with staff and patients.
 */
public class GraphicsHandler {
  private BufferedImage image;
  private Graphics graphics;
  private final int width;
  private final int height;
  
  /**
   * Sets up our base blank canvas.
   * @param width in pixels.
   * @param height in pixels.
   */
  public GraphicsHandler(int width, int height) {
    this.width = width;
    this.height = height;
    initializeGraphics();
  }
  
  /**
   * Sets up the white blank space.
   */
  public void initializeGraphics() {
    image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    graphics = image.getGraphics();
    graphics.setColor(Color.WHITE);
    graphics.fillRect(0, 0, width, height);
  }
  
  /**
   * Draws our room as we update it.
   * @param x size in pixels.
   * @param y size in pixels
   * @param width size in pixels.
   * @param height size in pixels.
   * @param roomName Name of the room.
   * @param isOccupied Occupied or not.
   * @param patientNames Patient inside name.
   * @param staffNames Staff inside name.
   */
  public void drawRoom(int x, int y, int width, int height, String roomName, boolean isOccupied,
      List<String> patientNames, List<String> staffNames) {
    graphics.setColor(isOccupied ? Color.RED : Color.GREEN);
    graphics.fillRect(x, y, width, height);
    graphics.setColor(Color.BLACK);
    graphics.drawRect(x, y, width, height);
    graphics.drawString(roomName + (isOccupied ? " - Occupied" : " - Empty"), x + 5, y + 20);
    
    int ty = y + 35;
    for (String name : patientNames) {
      graphics.drawString("Patient: " + name, x + 5, ty);
      ty += 15;
    }
    
    for (String name : staffNames) {
      graphics.drawString("Staff: " + name, x + 5, ty);
      ty += 15;
    }
  }
  
  /**
   * Saves the image we created.
   * @param path File path to store image.
   */
  public void saveImage(String path) {
    try {
      File outputFile = new File(path);
      ImageIO.write(image, "png", outputFile);
      
    } catch (IOException e) {
      System.out.println("Error saving image: " + e.getMessage());
    }
  }
}
