package me.Sshawarma.DesignPatterns;

//Shares objects to reduce memory usage (not necessarily time!)
//Useful when instantiating the same/similar object many many times
//Similar to factory pattern
//Note that concrete implementations of your flyweight should be immutable

//In this example, rectangles drawn on the screen that are new are saved in a hashmap
//When a rectangle with the same properties is asked for, the hashmap version is returned,
//Else the desired rectangle is created and stored in the map

//Side note, comparisons with creating new objects every time shows similar memory usage.
//I am not sure why, perhaps some behind the scenes optimization/GC, but I have tested the hashmap and it is working as
//expected.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

//Flyweight
interface Shape {
    public Color getColor();
    public void draw(Graphics g);
}

//Concrete Implementations (IMMUTABLE)
class Rectangle implements Shape {

    private Color color;


    public Rectangle(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(0, 0, 300, 300);
    }

}

class ShapeFactory {

    //Cache of shapes based on color
    final Map<Color, Shape> cache = new HashMap<Color, Shape>();

    public Shape getRectangle(Color c) {

        //Check if map has the object
        if(cache.containsKey(c)) {
            return cache.get(c);
        }
        //Else we create the object
        else{
            Shape newRectangle = new Rectangle(c);
            cache.put(c, newRectangle);
            return newRectangle;
        }

    }

}

public class FlyWeightPattern extends JFrame {

    JButton startDrawing;
    //JButton startDrawingWithPattern;
    int width = 1000;
    int height = 500;

    //Randomly choose between these
    Color[] shapeColor = {Color.orange, Color.red, Color.yellow, Color.blue, Color.pink, Color.cyan, Color.magenta,
                            Color.black, Color.gray};

    public FlyWeightPattern() {


        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Flyweight Demo");

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        final JPanel drawingPanel = new JPanel();

        startDrawing = new JButton("START DEMO");
        //startDrawingWithPattern = new JButton("With Pattern");

        contentPane.add(drawingPanel, BorderLayout.CENTER);
        contentPane.add(startDrawing, BorderLayout.SOUTH);
        //contentPane.add(startDrawingWithPattern, BorderLayout.SOUTH);

        startDrawing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Graphics g = drawingPanel.getGraphics();

                ShapeFactory factory = new ShapeFactory();
                for(int i=0; i<10000000; i++) {
                    //Pulls from hashmap instead of creating a new rect object every time
                    Shape rect = factory.getRectangle(getRandomColor());
                    rect.draw(g);
                }

            }

        });

        this.add(contentPane);
        this.setVisible(true);



    }

    private Color getRandomColor() {
        return shapeColor[(int)(Math.random() * 9)];
    }

}
