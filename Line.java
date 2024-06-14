
import java.awt.Color;
import java.awt.Graphics;

/**
 * A class that models a Line shape object.
 */
public class Line extends Shape {
	private int startX, startY, endX, endY;
	
	/**
	 * Creates a Line shape object.
	 * @param sX the starting X coordinate of this Line
	 * @param sY the starting Y coordinate of this Line
	 * @param eX the ending X coordinate of this Line
	 * @param eY the ending Y coordinate of this Line
	 */
	public Line(int sX, int sY, int eX, int eY) {	
		this.rgbValue = String.valueOf(myColor.getRGB());
		startX = sX;
		startY = sY;
		endX = eX;
		endY = eY;
	}
	
	/**
	 * Creates a Line shape object with its color.
	 * @param rgbColor the color of this Line
	 * @param sX the starting X coordinate of this Line
	 * @param sY the starting Y coordinate of this Line
	 * @param eX the ending X coordinate of this Line
	 * @param eY the ending Y coordinate of this Line
	 */
	public Line(String rgbColor, int sX, int sY, int eX, int eY) {
		this.rgbValue = rgbColor;
		myColor = new Color(Integer.parseInt(rgbColor));
		startX = sX;
		startY = sY;
		endX = eX;
		endY = eY;
	}
	
	/**
	 * Draws the Line shape object.
	 */
	public void draw(Graphics g) {
		g.setColor(myColor);
		g.drawLine(startX, startY, endX, endY);
	}

	/**
	 * The string representation of the characteristics of the Line shape object.
	 */
	public String toString() {
		String line = "";
		
		line += "L,";
		line += this.rgbValue;
		line += ",";
		line += startX;
		line += ",";
		line += startY;
		line += ",";
		line += endX;
		line += ",";
		line += endY;
		
		return line;
	}
	
	/**
	 * Gets the string representation of this shape object called "Line."
	 */
	public String getShape() {
		return "Line";
	}
	
	/**
	 * Gets the starting X coordinate of this Line.
	 */
	public int getStartX() {
		return startX;
	}
	
	/**
	 * Gets the starting Y coordinate of this Line.
	 */
	public int getStartY() {
		return startY;
	}
	
	/**
	 * Gets the ending X coordinate of this Line.
	 */
	public int getEndX() {
		return endX;
	}
	
	/**
	 * Gets the ending Y coordinate of this Line. 
	 */
	public int getEndY() {
		return endY;
	}
}
