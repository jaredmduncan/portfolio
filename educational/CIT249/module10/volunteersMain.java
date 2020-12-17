/**************************
    Author: Jared Duncan
    Class: CIT249 Java II
    Teacher: [redacted]
    Description: This program reads and writes to and from CSV files while also having an interactive system
    to create, delete, and display data. This program satisfies module 10 for CIT249.
 **************************/

/**************************
    Imports required for this class.
 **************************/
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class volunteersMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new volunteersPane());
        stage.setTitle("Volunteers");
        stage.setScene(scene);

        /**************************
            Taken from:
            https://stackoverflow.com/questions/6864540/how-to-set-a-javafx-stage-frame-to-maximized
            Purpose:
            Maximizes application window, without this the csv data is displayed off screen.
         **************************/
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        /**************************
            Display our stage.
         **************************/
        stage.show();
    }
}
