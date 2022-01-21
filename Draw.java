
// Created for CS1331 at Georgia Tech
// By Allison Sawyer
// Spring 2019


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/**
 * Draw tool which draws a path as the user drags the mouse
 * @author asawyer8
 * @version 1.0
 */
public class Draw implements Tool {
    /**
     * Blank draw constructor
     */
    public Draw() {
    }

    /**
     * Begins the path of the line
     *
     * @param e The mouseevent that fired this onPress.
     * @param g The current graphics context.
     */
    public void onPress(MouseEvent e, GraphicsContext g) {
        g.beginPath();
    }

    /**
     * Draws a line between the previous point and current point
     *
     * @param e The mouseevent that fired this onDrag.
     * @param g The current graphics context.
     */
    public void onDrag(MouseEvent e, GraphicsContext g) {
        g.lineTo(e.getX(), e.getY());
        g.stroke();
    }

    /**
     * Stops the path
     *
     * @param e The mouseevent that fired this onRelease.
     * @param g The current graphics context.
     */
    public void onRelease(MouseEvent e, GraphicsContext g) {
        g.closePath();
    }

}
