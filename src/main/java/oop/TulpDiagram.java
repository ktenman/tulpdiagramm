package oop;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TulpDiagram extends Application {

    private static final String INPUT_FILE = "numbers.txt";

    @Override
    public void start(Stage primaryStage) {
        // Read integers from file
        List<Integer> values = readValuesFromFile(INPUT_FILE);
        if (values.isEmpty()) {
            System.err.println("No data found or file reading error.");
            return;
        }

        // Window size: 535×535 to match your screenshot
        int width = 535;
        int height = 535;

        // Create the root pane and canvas
        Pane root = new Pane();
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Fill the background with SNOW (as in your screenshot)
        gc.setFill(Color.SNOW);
        gc.fillRect(0, 0, width, height);

        // Draw the bar chart
        drawBarChart(gc, values, width, height);

        root.getChildren().add(canvas);

        // Create and show the scene
        Scene scene = new Scene(root, width, height, Color.SNOW);
        primaryStage.setTitle("Tulpdiagramm");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Draws the bars near the center of the canvas (yBase ~ 300).
     * Each bar is colored red if val>50, else blue.
     * The label is drawn just below the bar, in black.
     */
    private void drawBarChart(GraphicsContext gc, List<Integer> values, int canvasWidth, int canvasHeight) {
        // Adjust these to fine-tune the look
        double barWidth = 30;
        double gap = 20;
        double xStart = 50;     // left margin
        double yBase = 300;     // vertical "baseline" for the top of each label
        double labelOffset = 15; // how far below the baseline to place the label

        // Optional: you can pick a different font or size
        gc.setFont(Font.font(14));
        gc.setTextAlign(TextAlignment.CENTER);

        for (int i = 0; i < values.size(); i++) {
            int val = values.get(i);

            // Choose color
            if (val > 50) {
                gc.setFill(Color.RED);
            } else {
                gc.setFill(Color.BLUE);
            }

            // The bar's height is the integer value itself (1:1)
            double barHeight = val;

            // Compute the (x, y) for the bar
            double x = xStart + i * (barWidth + gap);
            double y = yBase - barHeight;  // bars extend upwards

            // Draw the bar
            gc.fillRect(x, y, barWidth, barHeight);

            // Draw the numeric label in black, just below the bar
            gc.setFill(Color.BLACK);
            gc.fillText(String.valueOf(val), x + barWidth / 2.0, yBase + labelOffset);
        }
    }

    /**
     * Reads integers from a file (one per line).
     */
    private List<Integer> readValuesFromFile(String filename) {
        List<Integer> values = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    values.add(Integer.parseInt(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return values;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
