
// Created for CS1331 at Georgia Tech
// By Allison Sawyer
// Spring 2019


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/**
 * Oval tool which draws ovals between the mouse click and release
 * @author asawyer8
 * @version 1.0
 */
public class Oval implements Tool {

    private double startingX;
    private double startingY;
    /**
     * Blank Oval constructor
     */
    public Oval() {
    }

    /**
     * Saves the initial click point
     *
     * @param e The mouseevent that fired this onPress.
     * @param g The current graphics context.
     */
    public void onPress(MouseEvent e, GraphicsContext g) {
        startingX = e.getX();
        startingY = e.getY();
        g.fillOval(startingX, startingY, 30, 30);
        g.strokeOval(startingX, startingY, 30, 30);
    }

    /**
     * Traces the oval so the user can see it before they place
     *
     * @param e The mouse event that fired this onDrag.
     * @param g The current graphics context.
     */
    public void onDrag(MouseEvent e, GraphicsContext g) {
    }

    /**
     * Draws a rectangle spanning between the initial click and final release
     *
     * @param e The mouseevent that fired this onRelease.
     * @param g The current graphics context.
     */
    public void onRelease(MouseEvent e, GraphicsContext g) {
    }

}
