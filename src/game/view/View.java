package game.view;

import game.model.GameButtons;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class View {

    private final static int HEIGHT = 768;
    private final static int WIDTH = 1024;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private final static int MENU_BUTTON_X = 100;
    private final static int MENU_BUTTON_Y = 150;
    List<GameButtons> gameButtonsList;

    public View() {

        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createBackground();

        gameButtonsList = new ArrayList<>();
        addButtons();
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void createBackground() {

        Image imageBackground = new Image("game/view/resources/purple.png", 256, 256, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(imageBackground, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(backgroundImage));

    }

    private void addButtons() {
        addStartButton();
        addScoreButton();
        addCreditButton();
        addHelpButton();
        addExitButton();

    }

    private void addButtonsInMenu(GameButtons gameButtons) {
        gameButtons.setLayoutX(MENU_BUTTON_X);
        gameButtons.setLayoutY(MENU_BUTTON_Y + gameButtonsList.size() * 100);
        gameButtonsList.add(gameButtons);
        mainPane.getChildren().add(gameButtons);
    }

    private void addStartButton() {
        GameButtons gameButtonStart = new GameButtons("START");
        addButtonsInMenu(gameButtonStart);
    }

    private void addScoreButton() {
        GameButtons gameScoreButton = new GameButtons("CREDITS");
        addButtonsInMenu(gameScoreButton);
    }

    private void addHelpButton() {
        GameButtons gameHelpButton = new GameButtons("HELP");
        addButtonsInMenu(gameHelpButton);
    }

    private void addCreditButton() {
        GameButtons gameCreditButton = new GameButtons("CREDITS");
        addButtonsInMenu(gameCreditButton);
    }

    private void addExitButton() {
        GameButtons gameExitButton = new GameButtons("EXIT");
        addButtonsInMenu(gameExitButton);
    }


}
