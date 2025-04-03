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

import java.util.List;

public class TulpDiagram extends Application {

    @Override
    public void start(Stage primaryStage) {
        List<Integer> values = List.of(45, 78, 69, 21, 33, 96, 47, 10, 76, 50);
        int laius = 535;
        int kõrgus = 535;

        Pane juur = new Pane();
        Canvas canvas = new Canvas(laius, kõrgus);

        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        graphicsContext2D.setFill(Color.SNOW);
        graphicsContext2D.fillRect(0, 0, laius, kõrgus);

        joonistaTulbad(graphicsContext2D, values);

        juur.getChildren().add(canvas);

        Scene scene = new Scene(juur, laius, kõrgus, Color.SNOW);
        primaryStage.setTitle("Tulpdiagramm");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void joonistaTulbad(GraphicsContext graphicsContext, List<Integer> values) {

        double tulbaLaius = 25;
        double vahe = 20;
        double xStart = 50;
        double yBase = 300;
        double labelOffset = 20;

        graphicsContext.setFont(Font.font(14));
        graphicsContext.setTextAlign(TextAlignment.CENTER);

        for (int i = 0; i < values.size(); i++) {
            int väärtus = values.get(i);

            if (väärtus > 50) {
                graphicsContext.setFill(Color.RED);
            } else {
                graphicsContext.setFill(Color.BLUE);
            }

            double tulbaKõrgus = väärtus;

            double x = xStart + i * (tulbaLaius + vahe);
            double y = yBase - tulbaKõrgus;

            graphicsContext.fillRect(x, y, tulbaLaius, tulbaKõrgus);

            graphicsContext.setFill(Color.BLACK);
            graphicsContext.fillText(String.valueOf(väärtus), x + tulbaLaius / 2.0, yBase + labelOffset);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
