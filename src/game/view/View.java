package game.view;

import game.model.*;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class View {

    private final static int HEIGHT = 768;
    private final static int WIDTH = 1024;
    private AnchorPane mainPane;
    private Stage mainStage;

    private final static int MENU_BUTTON_X = 100;
    private final static int MENU_BUTTON_Y = 150;
    List<GameButtons> gameButtonsList;

    private GameSubscene creditsSubscene;
    private GameSubscene helpSubscene;
    private GameSubscene scoreSubscene;
    private GameSubscene shipChooser;
    private GameSubscene sceneToHide;

    List<ShipChooser> shipChooserListButton;
    private SHIP alreadyChosenShip;

    public View() {

        mainPane = new AnchorPane();
        Scene mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createBackground();
        addRocketGif();
        addStarsGif();
        createLogo();
        addCursorLook();
        createSubscens();
        gameButtonsList = new ArrayList<>();
        addButtons();

    }

    private void showSubscene(GameSubscene subscene) {

        if (sceneToHide != null) {
            sceneToHide.moveSubscene();

        }

        subscene.moveSubscene();
        sceneToHide = subscene;

    }

    private void createSubscens() {

//        creditsSubscene = new GameSubscene();
//        mainPane.getChildren().add(creditsSubscene);

        helpSubscene = new GameSubscene();
        mainPane.getChildren().add(helpSubscene);

        scoreSubscene = new GameSubscene();
        mainPane.getChildren().add(scoreSubscene);

        GameSubscene exitSubscene = new GameSubscene();
        mainPane.getChildren().add(exitSubscene);

        createShipChooserScene();
        createCreditScene();
        createHelpScene();

    }

    private void createShipChooserScene() {
        shipChooser = new GameSubscene();
        mainPane.getChildren().add(shipChooser);

        InfoLabel choseShipLabel = new InfoLabel("CHOOSE YOUR SHIP");
        choseShipLabel.setLayoutX(110);
        choseShipLabel.setLayoutY(25);
        shipChooser.getPane().getChildren().add(choseShipLabel);
        shipChooser.getPane().getChildren().add(createBoxesWithShipChose());
        shipChooser.getPane().getChildren().add(addPlayButtonAfterChoseShip());

    }

    private void createCreditScene() {

        creditsSubscene = new GameSubscene();
        mainPane.getChildren().add(creditsSubscene);
        TextLabel creditLabel = new TextLabel("THIS GAME WAS CREATED " + "\n" + "\n" + "WITH TUTORIAL ON 'YOUTUBE'"
                + "\n" + "\n" + "IT'S A SIMPLE GAME. HAVE FUN! " + "\n" + "\n" + "                  Norbert   :)");

        creditLabel.setLayoutX(110);
        creditLabel.setLayoutY(25);
        creditsSubscene.getPane().getChildren().add(creditLabel);
    }

    private void createHelpScene() {

        helpSubscene = new GameSubscene();
        mainPane.getChildren().add(helpSubscene);
        TextLabel helpLabel = new TextLabel("Control is simple!" + "\n" + " Use arrow keys" + "\n" + " <--- LEFT" + "\n" +
                " and " + "\n" + " RIGHT ---> " + "\n" + "on your keyboard");
        helpLabel.setLayoutX(110);
        helpLabel.setLayoutY(25);

        String CONTROL_EFFECT = "game/view/resources/arrows.gif";
        ImageView arrows = new ImageView(CONTROL_EFFECT);
        arrows.setLayoutX(350);
        arrows.setLayoutY(250);

        helpSubscene.getPane().getChildren().add(arrows);
        helpSubscene.getPane().getChildren().add(helpLabel);

    }

    private HBox createBoxesWithShipChose() {

        HBox hBox = new HBox();
        hBox.setSpacing(20);

        shipChooserListButton = new ArrayList<>();
        for (SHIP ship : SHIP.values()) {
            ShipChooser shipChooser = new ShipChooser(ship);
            shipChooserListButton.add(shipChooser);
            hBox.getChildren().add(shipChooser);

            shipChooser.setOnMouseClicked(mouseEvent -> {
                        for (ShipChooser chooser : shipChooserListButton) {
                            chooser.setIsCircleChosen(false);
                        }

                        shipChooser.setIsCircleChosen(true);
                        alreadyChosenShip = shipChooser.getShip();
                    }

            );

        }

        hBox.setLayoutX(300 - (118 * 2));
        hBox.setLayoutY(100);

        return hBox;

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
        gameButtonStart.setOnAction(actionEvent ->
                showSubscene(shipChooser));

    }

    private void addScoreButton() {
        GameButtons gameScoreButton = new GameButtons("SCORE");
        addButtonsInMenu(gameScoreButton);
        gameScoreButton.setOnAction(actionEvent ->
                showSubscene(scoreSubscene));
    }

    private void addHelpButton() {
        GameButtons gameHelpButton = new GameButtons("HELP");
        addButtonsInMenu(gameHelpButton);
        gameHelpButton.setOnAction(actionEvent ->
                showSubscene(helpSubscene));
    }

    private void addCreditButton() {
        GameButtons gameCreditButton = new GameButtons("CREDITS");
        gameCreditButton.setOnAction(actionEvent -> showSubscene(creditsSubscene));

        addButtonsInMenu(gameCreditButton);
    }

    private void addExitButton() {
        GameButtons gameExitButton = new GameButtons("EXIT");
        addButtonsInMenu(gameExitButton);
        gameExitButton.setOnAction(actionEvent ->
                mainStage.close());
    }

    private GameButtons addPlayButtonAfterChoseShip() {

        GameButtons playButton = new GameButtons("PLAY");
        playButton.setLayoutX(350);
        playButton.setLayoutY(300);
        playButton.setEffect(new DropShadow());

        playButton.setOnAction(actionEvent -> {

            if (alreadyChosenShip != null) {

                GameViewAndControlManager newGameView = new GameViewAndControlManager();
                newGameView.createNewGameAfterClickPlay(mainStage, alreadyChosenShip);

            }
        });

        return playButton;
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
