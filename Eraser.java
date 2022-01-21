
// Created for CS1331 at Georgia Tech
// By Allison Sawyer
// Spring 2019


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


/**
 * This tool represents an eraser which erases content on the Canvas
 * @author asawyer8
 * @version 1.0
 */
public class Eraser implements Tool {
    private Paint previousStroke;
    private double previousWidth;


    /**
     * Blank Eraser constructor
     */
    public Eraser() {
    }

    /**
     * Begins the path of the eraser and colors it white (to match the canvas)
     * and saves the graphic's stroke
     *
     * @param e The mouseevent that fired this onPress.
     * @param g The current graphics context.
     */
    public void onPress(MouseEvent e, GraphicsContext g) {
        previousStroke = g.getStroke();
        previousWidth = g.getLineWidth();
        g.setStroke(Color.WHITE);
        g.setLineWidth(10);
        g.beginPath();
    }

    /**
     * Erases a line from where the user dragged
     *
     * @param e The mouseevent that fired this onDrag.
     * @param g The current graphics context.
     */
    public void onDrag(MouseEvent e, GraphicsContext g) {
        g.lineTo(e.getX(), e.getY());
        g.stroke();
    }

    /**
     * Finishes the path and restores the stroke.
     *
     * @param e The mouseevent that fired this onRelease.
     * @param g The current graphics context.
     */
    public void onRelease(MouseEvent e, GraphicsContext g) {
        g.closePath();
        g.setStroke(previousStroke);
        g.setLineWidth(previousWidth);
    }


}
