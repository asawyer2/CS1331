
// Created for CS1331 at Georgia Tech
// By Allison Sawyer
// Spring 2019


import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

 /** I worked on the homework assignment alone, using only course materials,
  * and stackoverflow as a reference source
  */

/**
 * This interface represents a paint application
 * @author asawyer8
 * @version 1.0
 */
public class CSPaint extends Application {

    private Tool  currentTool = null;
    private static int counter = 0;
    /**
     * start method which initializes the main content of the program
     *
     * @param stage The Application's stage
     */
    public void start(Stage stage) {
        Canvas canvas = new Canvas(650, 450);
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        Canvas tempCanvas = new Canvas(650, 450);
        BorderPane borderPane = new BorderPane();
        StackPane canvasStack = new StackPane(canvas, tempCanvas);
        canvasStack.setStyle("-fx-background-color: #FFFFFF");
        //creating labels for the counter and mouse location
        Label labelCoords = new Label();
        Label numCircles = new Label();
        //instantiating the objects I made in the other classes
        Draw drawTool = new Draw();
        Oval ovalTool = new Oval();
        Eraser eraserTool = new Eraser();
        //creating the buttons
        TextField colorText = new TextField();
        Button clearButton = new Button("Clear");
        RadioButton draw = new RadioButton("Draw");
        RadioButton oval = new RadioButton("Circle");
        RadioButton eraser = new RadioButton("Erase");
        ToggleGroup group = new ToggleGroup();
        draw.setToggleGroup(group);
        oval.setToggleGroup(group);
        eraser.setToggleGroup(group);

        colorText.setOnAction(e -> {
                try {
                    String color = new String(colorText.getText());
                    color.toUpperCase();
                    graphics.setStroke(Color.valueOf(color));
                    graphics.setFill(Color.valueOf(color));
                    colorText.clear();
                } catch (Exception a) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Look, you selected a wrong color. yay!");
                    alert.setContentText("Hello? That color doesn't exist. "
                        + "Well, at least in javafx.");
                    alert.showAndWait();
                }
            });
        //putting implementation of the tools / toggle buttons
        draw.setOnAction(e -> {
                currentTool = drawTool;
            });
        oval.setOnAction(e -> {
                currentTool = ovalTool;
            });
        eraser.setOnAction(e -> {
                currentTool = eraserTool;
            });
        clearButton.setOnAction(e -> {
                graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                counter = 0;
                String s = new String("Number of Shapes: " + counter);
                numCircles.setText(s);
            });
        VBox buttons = new VBox(5, new Text("Tools"), clearButton, draw,
            oval, eraser, colorText);
        HBox hButtons = new HBox(10, new Text("Compass and counter"),
            labelCoords, numCircles);
        buttons.setAlignment(Pos.TOP_CENTER);
        buttons.setMinWidth(100);
        borderPane.setLeft(buttons);
        borderPane.setBottom(hButtons);
        borderPane.setCenter(canvasStack);
        tempCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
                if (currentTool != null) {
                    currentTool.onPress(e, graphics);
                }
                if (currentTool == ovalTool) {
                    counter++;
                    String s = new String("Number of Shapes: " + counter);
                    numCircles.setText(s);
                }
            });
        tempCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
                if (currentTool != null) {
                    currentTool.onDrag(e, graphics);
                }
            });
        tempCanvas.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
                if (currentTool != null) {
                    currentTool.onRelease(e, graphics);
                }
            });

        tempCanvas.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
                String s = new String("(" + (double) (e.getX()) + ", "
                    + (double) (e.getY()) + ")");
                labelCoords.setText(s);
            });
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setTitle("CSPaint");
        stage.show();
    }
}
