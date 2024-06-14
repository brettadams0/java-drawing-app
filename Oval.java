
import java.awt.Color;
import java.awt.Graphics;

/**
 * A class that models a Oval shape object.
 */
public class Oval extends Shape {
	private int startX, startY, endX, endY;
	
	/**
	 * Creates a Oval shape object.
	 * @param sX the starting X coordinate of this Oval
	 * @param sY the starting Y coordinate of this Oval
	 * @param eX the ending X coordinate of this Oval
	 * @param eY the ending Y coordinate of this Oval
	 */
	public Oval(int sX, int sY, int eX, int eY) {	
		this.rgbValue = String.valueOf(myColor.getRGB());
		startX = sX;
		startY = sY;
		endX = eX;
		endY = eY;
	}
	
	/**
	 * Creates a Oval shape object with its color.
	 * @param rgbColor the color of this Oval
	 * @param sX the starting X coordinate of this Oval
	 * @param sY the starting Y coordinate of this Oval
	 * @param eX the ending X coordinate of this Oval
	 * @param eY the ending Y coordinate of this Oval
	 */
	public Oval(String rgbColor, int sX, int sY, int eX, int eY) {
		this.rgbValue = rgbColor;
		myColor = new Color(Integer.parseInt(rgbColor));
		startX = sX;
		startY = sY;
		endX = eX;
		endY = eY;
	}
	
	/**
	 * Draws the Oval shape object.
	 */
	public void draw(Graphics g) {
		g.setColor(myColor);
		g.drawOval(startX, startY, endX, endY);
	}

	/**
	 * The string representation of the characteristics of the Oval shape object.
	 */
	public String toString() {
		String Oval = "";
		
		Oval += "O,";
		Oval += this.rgbValue;
		Oval += ",";
		Oval += startX;
		Oval += ",";
		Oval += startY;
		Oval += ",";
		Oval += endX;
		Oval += ",";
		Oval += endY;
		
		return Oval;
	}
	
	/**
	 * Gets the string representation of this shape object called "Oval."
	 */
	public String getShape() {
		return "Oval";
	}
	
	/**
	 * Gets the starting X coordinate of this Oval.
	 */
	public int getStartX() {
		return startX;
	}
	
	/**
	 * Gets the starting Y coordinate of this Oval.
	 */
	public int getStartY() {
		return startY;
	}
	
	/**
	 * Gets the ending X coordinate of this Oval.
	 */
	public int getEndX() {
		return endX;
	}
	
	/**
	 * Gets the ending Y coordinate of this Oval. 
	 */
	public int getEndY() {
		return endY;
	}
}
