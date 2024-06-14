import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
/**
 * A class that models a drawing board.
 */
public class Main extends JFrame {
	/**
	 * The serial version ID.
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Shape> allShapes = new ArrayList<>();

	private Container cp;
	private JPanel drawingPanel; // user draws here
	private JPanel statusBar;
	private JLabel statusLabel;// used to show status information

	private JMenuBar menuBar;
	private JMenu fileMenu, drawMenu, colourMenu, backgroundMenu;
	private EventHandler eh;

	private String myShape = "Line";
  public static int ColorInt = 0;
	
	private static String currentDirectory = ".";
	
	/**
	 * Calls the drawing board constructor.
	 * @param args none
	 */
	public static void main(String[] args) {
		Main lp = new Main();
		lp.setVisible(true);
	}
public static int getNum() {
        return ColorInt;
    }
	/**
	 * Creates a new drawing board.
	 */
	public Main() {
		setTitle("Drawing App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(400, 150);

		cp = getContentPane();
		cp.setLayout(new BorderLayout());

		eh = new EventHandler();

		drawingPanel = makeDrawingPanel();
		drawingPanel.addMouseListener(eh);
		drawingPanel.addMouseMotionListener(eh);

		statusBar = createStatusBar();

		cp.add(drawingPanel, BorderLayout.CENTER);
		cp.add(statusBar, BorderLayout.SOUTH);

		buildMenu();
		pack();
	}

	/**
	 * Creates the menu bar and the individual selections.
	 */
	private void buildMenu() {
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		drawMenu = new JMenu("Draw");
    colourMenu = new JMenu("Colour");
    backgroundMenu = new JMenu("Background");

		JMenuItem menuItem = new JMenuItem("New");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Open");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);
				
		menuItem = new JMenuItem("Line");
		menuItem.addActionListener(eh);
		drawMenu.add(menuItem);
		
		menuItem = new JMenuItem("Rectangle");
		menuItem.addActionListener(eh);
		drawMenu.add(menuItem);

    menuItem = new JMenuItem("Oval");
		menuItem.addActionListener(eh);
		drawMenu.add(menuItem);
    
    menuItem = new JMenuItem("Black");
		menuItem.addActionListener(eh);
		colourMenu.add(menuItem);

    menuItem = new JMenuItem("White");
		menuItem.addActionListener(eh);
		colourMenu.add(menuItem);

    menuItem = new JMenuItem("Red");
		menuItem.addActionListener(eh);
		colourMenu.add(menuItem);

    menuItem = new JMenuItem("Blue");
		menuItem.addActionListener(eh);
		colourMenu.add(menuItem);

    menuItem = new JMenuItem("Green");
		menuItem.addActionListener(eh);
		colourMenu.add(menuItem);

    menuItem = new JMenuItem("Pink");
		menuItem.addActionListener(eh);
		colourMenu.add(menuItem);

    menuItem = new JMenuItem("Black Background");
		menuItem.addActionListener(eh);
		backgroundMenu.add(menuItem);

    menuItem = new JMenuItem("White Background");
		menuItem.addActionListener(eh);
		backgroundMenu.add(menuItem);

		menuBar.add(fileMenu);
		menuBar.add(drawMenu);
    menuBar.add(colourMenu);
    menuBar.add(backgroundMenu);
		
		setJMenuBar(menuBar);
	}

	/**
	 * Creates a panel as the drawing board.
	 * @return the drawing panel
	 */
	private JPanel makeDrawingPanel() {
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(500, 400));
		p.setBackground(Color.WHITE);
		return p;
	}

	/**
	 * Creates a status bar, located below the drawing panel.
	 * @return the status bar
	 */
	private JPanel createStatusBar() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		statusLabel = new JLabel("No message");
		panel.add(statusLabel, BorderLayout.CENTER);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		return panel;
	}

	/**
	 * Overrides the paint method defined in JFrame.
	 */
	// this method overrides the paint method defined in JFrame
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics g1 = drawingPanel.getGraphics();

		// Send a message to each line in the drawing to draw itself on g1
		for (Shape s: allShapes)
			s.draw(g1);
	}
	
	/**
	 * Draws shapes to this file.
	 * Adds to the array list of shapes.
	 * Catches any errors regarding the type of file that was opened.
	 * @param file the file that is opened
	 */
	private void drawShapes(File file) {
		System.out.println("Opening file: " + file.getName() + "\n");
		try {
			int numberOfLines = 0;
		    int numberOfShapes = 0;
		    int drawStartX, drawStartY, drawEndX, drawEndY;
		    String rgbValue;
			
			Scanner scannerDrawShapes = new Scanner(file);
			while (scannerDrawShapes.hasNext()) {
				String line = scannerDrawShapes.nextLine().trim();
				String[] data = line.split(",", 0);
				if (data.length < 3) {
					if (Integer.valueOf(data[0]) >= 0) {
						numberOfShapes = Integer.valueOf(data[0]);
						
						if (numberOfShapes == 0) {
							System.out.println("There are no shapes in this file.");
						}
					}
				}
				else {
					//lines describing how to draw shapes
					rgbValue = data[1];
					drawStartX = Integer.valueOf(data[2]);
					drawStartY = Integer.valueOf(data[3]);
					drawEndX = Integer.valueOf(data[4]);
					drawEndY = Integer.valueOf(data[5]);
					
					if (data[0].charAt(0) == 'L') {
						//for drawing Lines
						Line newLine = new Line(rgbValue, drawStartX, drawStartY, drawEndX, drawEndY);
						allShapes.add(newLine);
					}
					else if (data[0].charAt(0) == 'R') {
						//for drawing Rectangles
						Rectangle newRectangle = new Rectangle(rgbValue, drawStartX, drawStartY, 
								drawEndX, drawEndY);
						allShapes.add(newRectangle);
					}
					else if (data[0].charAt(0) == 'O') {
						//for drawing Ovals
						Oval newOval = new Oval(rgbValue, drawStartX, drawStartY, 
								drawEndX, drawEndY);
						allShapes.add(newOval);
					}
				}
				numberOfLines++;
			}
			if (numberOfLines - 1 != numberOfShapes) {
				if ((numberOfLines & numberOfShapes) == 0) {
					System.out.println("FILE ERROR\nThis file does not contain a drawing.");
				}
				else {
					System.out.println("FILE ERROR\nThis file does not contain all shapes drawn in "
							+ "this drawing.\nThis file contains " + numberOfShapes + " shapes."
							+ "\nThis file is supposed to contain " + Integer.valueOf(numberOfLines-1) 
							+ " shapes.\n");
				}
			}
      System.out.print(file);
			scannerDrawShapes.close();
		}
		catch (IOException e) {
			System.out.println("File name is invalid.");
		}
		catch (NumberFormatException n) {
			System.out.println("This file is not a drawing.");
        }
		
		Graphics g = drawingPanel.getGraphics();
        for (Shape s: allShapes) {  
     	   s.draw(g);
           System.out.println("Drawing this " + s.getShape());
        }
        System.out.println();
        statusLabel.setText("Number of shapes drawn to file: " + Integer.toString(allShapes.size()));
        printShapes();
	}
	
	/**
	 * Saves the drawn shapes to this file.
	 * @param file the file being selected for saving
	 */
	private void saveShapes(File file) {
		System.out.println("Saving this drawing to file: " + file.getName());
		int shapes = allShapes.size();
		String fileName = file.getAbsolutePath();
		try {
			FileWriter writingFile = new FileWriter(fileName);
			writingFile.write(Integer.toString(shapes) + "\n");
			for (Shape s: allShapes) {
				writingFile.write(s.toString() + "\n");													
		    }
			writingFile.close();
			statusLabel.setText("Total number of shapes saved to file: " + shapes);
	        System.out.println("Completed saving all drawings.\n");
		}
		catch (IOException e) {
			System.out.println("This file's name is invalid.\n");
		}
	}
	
	/**
	 * Prints all drawn shapes being created to the array list of shapes.
	 */
	private void printShapes() {
		System.out.println("All shapes in file: ");
		for (Shape s: allShapes) {
			System.out.println(s.toString());
		}
		System.out.println();
	}
	
	/**
	 * A class that models the actions of the user.
	 */
	// Inner class - instances of this class handle action events
	private class EventHandler implements ActionListener, MouseListener, MouseMotionListener {

		private int startX, startY; // line's start coordinates
		private int lastX, lastY; // line's most recent end point

		/**
		 * Performs an action that occurs when the user selects an item in the menu bar.
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			repaint();
			if (arg0.getActionCommand().equals("Exit")) {
				statusLabel.setText("Exiting program...");
				System.exit(0);
			}	
		    if (arg0.getActionCommand().equals("New")) {
				statusLabel.setText("All shapes deleted. The drawing board is cleared.");
				allShapes.clear();
			}
		    if (arg0.getActionCommand().equals("Open")) {
				statusLabel.setText("Opening file...");
				JFileChooser chooser = new JFileChooser(currentDirectory);
				int status = chooser.showOpenDialog(cp);

	            if (status == JFileChooser.APPROVE_OPTION) {
	                File file = chooser.getSelectedFile();
					currentDirectory = file.getAbsolutePath();
	                drawShapes(file);
	            }
			}
		    if (arg0.getActionCommand().equals("Save")) {
				statusLabel.setText("Saving file...");
				JFileChooser chooser = new JFileChooser(currentDirectory);
	            int status = chooser.showSaveDialog(cp);

	            if (status == JFileChooser.APPROVE_OPTION) {
	                File file = chooser.getSelectedFile();
	                currentDirectory = file.getAbsolutePath();
	                saveShapes(file);
	            }
			}
		    
		    if (arg0.getActionCommand().equals("Line")) {
		    	statusLabel.setText("Selected a Line shape.");
		    	myShape = "Line";
		    }
		    if (arg0.getActionCommand().equals("Rectangle")) {
		    	statusLabel.setText("Selected a Rectangle shape.");
		    	myShape = "Rectangle";
		    }
      if (arg0.getActionCommand().equals("Oval")) {
		    	statusLabel.setText("Selected a Oval shape.");
		    	myShape = "Oval";
		    }
      if (arg0.getActionCommand().equals("Black")){
              ColorInt = 0;
		    }
      if (arg0.getActionCommand().equals("White")){
              ColorInt = 1;
		    }
      if (arg0.getActionCommand().equals("Red")){
              ColorInt = 2;
		    }
      if (arg0.getActionCommand().equals("Blue")){
              ColorInt = 3;
		    }
      if (arg0.getActionCommand().equals("Green")){
              ColorInt = 4;
		    }
      if (arg0.getActionCommand().equals("Pink")){
              ColorInt = 5;
		    }
      if (arg0.getActionCommand().equals("Black Background")){
              drawingPanel.setBackground(Color.BLACK);
		    }
      if (arg0.getActionCommand().equals("White Background")){
              drawingPanel.setBackground(Color.WHITE);
		    }
		}

		/**
		 * Performs an action that occurs when the user presses on the mouse. 
		 */
		@Override
		public void mousePressed(MouseEvent e) {

			startX = e.getX();
			startY = e.getY();
			// initialize lastX, lastY
			lastX = startX;
			lastY = startY;
		}

		/**
		 * Performs an action that occurs when the user drags the mouse.
		 */
		@Override
		public void mouseDragged(MouseEvent e) {
			// Implement rubber-band cursor
			Graphics g = drawingPanel.getGraphics();
            Color[] colors = {Color.black,Color.white, Color.red, Color.blue, Color.green, Color.pink};
			g.setColor(colors[Main.getNum()]);
			g.setXORMode(drawingPanel.getBackground());

			if (myShape == "Line") {
				g.drawLine(startX, startY, lastX, lastY);
				g.drawLine(startX, startY, e.getX(), e.getY());
			}
			else if (myShape == "Rectangle") {
				g.drawRect(startX, startY, lastX-startX, lastY-startY);
				g.drawRect(startX, startY, e.getX()-startX, e.getY()-startY);
			}
      	else if (myShape == "Oval") {
				g.drawOval(startX, startY, lastX, lastY);
				g.drawOval(startX, startY, e.getX(), e.getY());
			}
			lastX = e.getX();
			lastY = e.getY();
		}

		/**
		 * Performs an action that occurs when the user releases the mouse.
		 */
		@Override
		public void mouseReleased(MouseEvent arg0) {
			if (startX == lastX && startY == lastY) {
				System.out.println("No shape has been drawn.");
				return;
			}
			
			Graphics graphics = drawingPanel.getGraphics();
			if (myShape.equals("Line")) {
				Line addNewLine = new Line(startX, startY, arg0.getX(), arg0.getY());
				graphics.setColor(addNewLine.getMyColor());
				addNewLine.draw(graphics);
				allShapes.add(addNewLine);
			}
			else if (myShape.equals("Rectangle") ) {
				Rectangle addNewRectangle = new Rectangle(startX, startY, arg0.getX()-startX, 
						arg0.getY()-startY);
				graphics.setColor(addNewRectangle.getMyColor());
				addNewRectangle.draw(graphics);
				allShapes.add(addNewRectangle);
			}
      else if (myShape.equals("Oval")) {
				Oval addNewOval = new Oval(startX, startY, arg0.getX(), arg0.getY());
				graphics.setColor(addNewOval.getMyColor());
				addNewOval.draw(graphics);
				allShapes.add(addNewOval);
			}
			repaint();
			statusLabel.setText("Total number of shapes: "+ Integer.toString(allShapes.size()));
			printShapes();
		}

		/**
		 * Performs an action that occurs when the user clicks the mouse.
		 */
		@Override
		public void mouseClicked(MouseEvent arg0) {
			int keyBoardCtrlLeftClick = InputEvent.BUTTON1_MASK + InputEvent.CTRL_MASK;
			if ((arg0.getModifiers() & keyBoardCtrlLeftClick) != keyBoardCtrlLeftClick) {
				return;
			}	
			
			Point point = arg0.getPoint();
			Shape selectedShape = null;
			int deleteStartX, deleteStartY, deleteEndX, deleteEndY;
			
			for (Shape s: allShapes) {
				deleteStartX = s.getStartX();
				deleteStartY = s.getStartY();
				deleteEndX = s.getEndX();
				deleteEndY = s.getEndY();
				
				if (s.getShape() == "Line") {
					Point firstPoint = new Point(deleteStartX, deleteStartY);
					Point secondPoint = new Point(deleteEndX, deleteEndY);
					
					double firstDistance = firstPoint.distance(point);
					double secondDistance = secondPoint.distance(point);
					double firstAndSecondDistance = firstPoint.distance(secondPoint);
					
					if (firstDistance + secondDistance - firstAndSecondDistance <= 2.0) {
						selectedShape = s;
					}
				}
				else if (s.getShape() == "Rectangle") {
					java.awt.Rectangle rectangle = new java.awt.Rectangle(deleteStartX, deleteStartY, 
								deleteEndX, deleteEndY);
					if (rectangle.contains(point)) {
							selectedShape = s;
					}
				}
				}
			if (selectedShape == null) {
				statusLabel.setText("No shape selected. Unable to delete shape.");
				return;
			} 
			else {
				statusLabel.setText("Attempting to delete this " + selectedShape.getShape());	  
				allShapes.remove(selectedShape);
		    	statusLabel.setText("This " + selectedShape.getShape() + " is deleted. " 
				+ "Total number of shapes remaining in file: " 
		    	+ Integer.toString(allShapes.size()));
		    	repaint();
		    	printShapes();
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {

		}

		@Override
		public void mouseExited(MouseEvent arg0) {

		}

		@Override
		public void mouseMoved(MouseEvent arg0) {

		}
	}
}