package game.view;

import game.model.GameButtons;
import game.model.GameSubscene;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Reflection;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
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
        addRocketGif();
        addStarsGif();
        createLogo();
        addCursorLook();

        gameButtonsList = new ArrayList<>();
        addButtons();

        GameSubscene gameSubscene = new GameSubscene();
        gameSubscene.setLayoutX(200);
        gameSubscene.setLayoutY(100);
        mainPane.getChildren().add(gameSubscene);
    }

    private void addCursorLook() {
        String CURSOR_EFFECT = "/game/model/buttons/resources/cursor.png";
        Image cursor = new Image(CURSOR_EFFECT);
        mainPane.setCursor(new ImageCursor(cursor));

    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void createBackground() {

        Image imageBackground = new Image("game/view/resources/purple.png", 256, 256, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(imageBackground, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(backgroundImage));

    }

    private void addRocketGif() {
        ImageView rocketGif = new ImageView("game/view/resources/flyingrocket.gif");
        rocketGif.setLayoutY(280);
        rocketGif.setX(180);
        mainPane.getChildren().add(rocketGif);


    }

    private void addStarsGif() {
        ImageView starsGif = new ImageView("game/view/resources/stars.gif");
        starsGif.setLayoutY(280);
        starsGif.setX(180);
        mainPane.getChildren().add(starsGif);

        ImageView rocket2Gif = new ImageView("game/view/resources/stars.gif");
        rocket2Gif.setLayoutY(180);
        rocket2Gif.setX(380);
        mainPane.getChildren().add(rocket2Gif);
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
        gameButtons.getEffect();
        gameButtonsList.add(gameButtons);
        mainPane.getChildren().add(gameButtons);
    }

    private void addStartButton() {
        GameButtons gameButtonStart = new GameButtons("START");
        addButtonsInMenu(gameButtonStart);
    }

    private void addScoreButton() {
        GameButtons gameScoreButton = new GameButtons("SCORE");
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

    private void createLogo() {

        ImageView logo = new ImageView("game/view/resources/logo2.png");
        logo.setLayoutY(50);
        logo.setLayoutX(50);
        logo.setOnMouseEntered(mouseEvent -> logo.setEffect(new SepiaTone()));
        logo.setOnMouseExited(mouseEvent -> logo.setEffect(new DropShadow()));

        mainPane.getChildren().add(logo);
    }

}
