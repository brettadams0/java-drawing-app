# Java Drawing App

A simple Java application for drawing various shapes like lines, rectangles, and ovals. This application provides a graphical user interface (GUI) for users to draw and manipulate shapes.

## Features

- Draw lines, rectangles, and ovals.
- Specify colors and dimensions for each shape.
- Interactive GUI for creating and viewing shapes.
- Save drawings for later usage.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or later
- An IDE such as IntelliJ IDEA, Eclipse, or NetBeans (optional but recommended)

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/brettadams0/java-drawing-app.git
    ```
2. Open the project in your preferred IDE.

3. Compile and run the `Main.java` file to start the application.

### Usage

1. Run the `Main.java` file.
2. Use the GUI to select different shapes (line, rectangle, oval).
3. Draw shapes on the canvas by clicking and dragging the mouse.
4. Specify the color and dimensions of the shapes as needed.
5. Save drawings to .txt file. (see `example.txt`)

### Project Structure

- **Main.java**: Entry point of the application. Sets up the GUI and handles user interactions.
- **Line.java**: Represents a line shape with start and end coordinates.
- **Oval.java**: Represents an oval shape with position and dimensions.
- **Rectangle.java**: Represents a rectangle shape with position and dimensions.
- **Shape.java**: Abstract base class for all shapes. Provides common properties and methods for drawing.
