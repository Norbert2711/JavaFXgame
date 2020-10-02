package game;

import game.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        View view = new View();
        primaryStage = view.getMainStage();
        primaryStage.setTitle("JavaFX Game");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
