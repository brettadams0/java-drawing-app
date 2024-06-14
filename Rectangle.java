
import java.awt.Color;
import java.awt.Graphics;

/**
 * A class that models a Rectangle shape object.
 */
public class Rectangle extends Shape {
	private int startX, startY;
	private int width, height;
	
	/**
	 * Creates a Rectangle shape object.
	 * @param sX the starting X coordinate of this Rectangle
	 * @param sY the starting Y coordinate of this Rectangle
	 * @param w the width of this Rectangle
	 * @param h the height of this Rectangle
	 */
	public Rectangle(int sX, int sY, int w, int h) {
		this.rgbValue = String.valueOf(myColor.getRGB());
		startX = sX;
		startY = sY;
		width = w;
		height = h;
	}
	
	/**
	 * Creates a Rectangle shape object with its color.
	 * @param rgbColor the color of this Rectangle
     * @param sX the starting X coordinate of this Rectangle
	 * @param sY the starting Y coordinate of this Rectangle
	 * @param w the width of this Rectangle
	 * @param h the height of this Rectangle
	 */
	public Rectangle(String rgbColor, int sX, int sY, int w, int h) {
		this.rgbValue = rgbColor;
		myColor = new Color(Integer.parseInt(rgbColor));
		startX = sX;
		startY = sY;
		width = w;
		height = h;
	}
	
	/**
	 * Draws the Rectangle shape object.
	 */
	public void draw(Graphics g) {		
		g.setColor(myColor);
		g.drawRect(startX, startY, width, height);
	}
	
	/**
	 * The string representation of the characteristics of the Rectangle shape object.
	 */
	public String toString() {
		String line = "";
		
		line += "R,";
		line += this.rgbValue;
		line += ",";
		line += startX;
		line += ",";
		line += startY;
		line += ",";
		line += width;
		line += ",";
		line += height;
		
		return line;
	}
	
	/**
	 * Gets the string representation of this shape object called "Rectangle."
	 */
	public String getShape() {
		return "Rectangle";
	}
	
	/**
	 * Gets the starting X coordinate of this Rectangle.
	 */
	public int getStartX() {
		return startX;
	}
	
	/**
	 * Gets the starting Y coordinate of this Rectangle.
	 */
	public int getStartY() {
		return startY;
	}
	
	/**
	 * Gets the width of this Rectangle.
	 */
	public int getEndX() {
		return width;
	}
	
	/**
	 * Gets the height of this Rectangle.
	 */
	public int getEndY() {
		return height;
	}
}
