package game.view;

import game.model.SHIP;
import game.model.SmallInfoLabelPanel;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;

public class GameViewAndControlManager {

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage mainStage;
    public Stage menuStage;

    private ImageView ship;

    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private int angle;      //  ==  0;

    private GridPane gridPane1;
    private GridPane gridPane2;
    public AnimationTimer gameTimer;

    private final static String BROWN_METEOR = "game/view/resources/meteorBrown.png";
    private final static String GREY_METEOR = "game/view/resources/meteorGrey.png";
    private ImageView[] brownMeteors;
    private ImageView[] greyMeteors;
    private Random randomPositionOfMeteorsGenerator;

    private ImageView starImage;
    private final static String GOLD_STAR_IMAGE = "game/view/resources/star_gold.png";
    private int points;
    private int playerLife;
    private ImageView[] playerLiefs;
    public SmallInfoLabelPanel pointsLabel;

    private static final int STAR_RADIUS = 12;
    private static final int SHIP_RADIUS = 28;
    private static final int METEOR_RADIUS = 20;

    public GameViewAndControlManager() {

        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, 600, 800);
        gameStage = new Stage();
        gameStage.getScene();
        gameStage.setScene(gameScene);
        createControlKeys();
        createGameBackground();
        randomPositionOfMeteorsGenerator = new Random();

    }

    public void createNewGameAfterClickPlay(Stage menuStage, SHIP chosenShip) {

        this.menuStage = menuStage; //wymagane do poruszania sie platformy
        this.menuStage.hide();
        showShipOnPane(chosenShip);
        createShipAnimationTime();
        createElementsOfGame(chosenShip);
        this.gameStage.show();

    }

    private void createGameBackground() {

        gridPane1 = new GridPane();
        gridPane2 = new GridPane();

        for (int i = 0; i < 12; i++) { //12 aby zapelnic cale tlo
            String BACKGROUND_IN_GAME = "game/view/resources/purple.png";
            ImageView imageBackground1 = new ImageView(BACKGROUND_IN_GAME);
            ImageView imageBackground2 = new ImageView(BACKGROUND_IN_GAME);
            GridPane.setConstraints(imageBackground1, i % 3, i / 3);
            GridPane.setConstraints(imageBackground2, i % 3, i / 3);
            gridPane1.getChildren().add(imageBackground1);
            gridPane2.getChildren().add(imageBackground2);

        }

        gridPane2.setLayoutY(-1024);
        gamePane.getChildren().addAll(gridPane1, gridPane2);
    }

    private void makeAnimationOfBackgroundImage() {

        gridPane1.setLayoutY(gridPane1.getLayoutY() + 0.6); // tepo
        gridPane2.setLayoutY(gridPane2.getLayoutY() + 0.6);     // przewijania tla gry

        if (gridPane1.getLayoutY() >= 1024) {
            gridPane1.setLayoutY(-1020);        //pane 1 lekko nachodzi na pane2 z powodu predkosci '1.5'
        }

        if (gridPane2.getLayoutY() >= 1024) {
            gridPane2.setLayoutY(-1020);
        }
    }

    private void createControlKeys() {

        gameScene.setOnKeyPressed(keyEvent -> {

            if (keyEvent.getCode() == KeyCode.LEFT) {
                isLeftKeyPressed = true;

            } else if (keyEvent.getCode() == KeyCode.RIGHT) {
                isRightKeyPressed = true;

            }
        });

        gameScene.setOnKeyReleased(keyEvent -> {

            if (keyEvent.getCode() == KeyCode.LEFT) {
                isLeftKeyPressed = false;

            } else if (keyEvent.getCode() == KeyCode.RIGHT) {
                isRightKeyPressed = false;

            }
        });

    }

    private void createShipAnimationTime() {

        gameTimer = new AnimationTimer() {

            @Override
            public void handle(long now) {

                makeAnimationOfBackgroundImage();
                moveElementsInGame();
                checkIfElementsInGameAreBelowTheShip();
                checkIfElementsInGameAreCollide();
                createControlShip();

            }

        };

        gameTimer.start();
    }

    private void createControlShip() {

        if (isLeftKeyPressed && !isRightKeyPressed) {

            if (angle > -30) {      //zlapanie kąta 30 stopni
                angle = angle - 5;      //szybkosc obrotu statku do kąta 30 stopni
            }

            ship.setRotate(angle);

            if (ship.getLayoutX() > -10) {      //ograniczenie przesunieca statku maxymalnie do lewej krawedzi planszy gry  (oś X)
                ship.setLayoutX(ship.getLayoutX() - 4);     //ustawienie "szybkosci" poruszania sie statku w lewo (oś X).
            }

        }

        if (isRightKeyPressed && !isLeftKeyPressed) {       //analogicznie poruszanie sie w prawo co do "if" z lewym przyciskiem

            if (angle < 30) {
                angle = angle + 5;
            }

            ship.setRotate(angle);

            if (ship.getLayoutX() < 500) {
                ship.setLayoutX(ship.getLayoutX() + 4);
            }

//            if (!isLeftKeyPressed && !isRightKeyPressed) {
//
//                if (angle < 0) {
//                    angle = angle + 5;
//
//                } else if (angle > 0) {           //metoda ustawia kat, gdy zaden z przyciskow lewo - prawo nie jest wcisniety
//                    angle = angle - 5;
//                }
//
//            }
            // ship.setRotate(angle);

            if (isLeftKeyPressed && isRightKeyPressed) {

                if (angle < 0) {
                    angle = angle + 5;
                } else if (angle > 0) {
                    angle = angle - 5;
                }

            }
            ship.setRotate(angle);
        }

    }


    private void showShipOnPane(SHIP chosenShip) {

        ship = new ImageView(chosenShip.getUrlShip());
        ship.setLayoutX(250);
        ship.setLayoutY(700);
        gamePane.getChildren().add(ship);

    }

    private void createElementsOfGame(SHIP chosenShip) {

        playerLife = 2;
        starImage = new ImageView(GOLD_STAR_IMAGE);
        setElementsInRandomPositionInGame(starImage);
        gamePane.getChildren().add(starImage);

        pointsLabel = new SmallInfoLabelPanel("POINTS: 00");
        pointsLabel.setLayoutX(460);
        pointsLabel.setLayoutY(20);
        gamePane.getChildren().add(pointsLabel);

        playerLiefs = new ImageView[3];
        for (int i = 0; i < playerLiefs.length; i++) {
            playerLiefs[i] = new ImageView(chosenShip.getUlrLife());
            playerLiefs[i].setLayoutX(455 + (i * 50));
            playerLiefs[i].setLayoutY(80);
            gamePane.getChildren().add(playerLiefs[i]);
        }

        brownMeteors = new ImageView[5];
        for (int i = 0; i < brownMeteors.length; i++) {
            brownMeteors[i] = new ImageView(BROWN_METEOR);
            setElementsInRandomPositionInGame(brownMeteors[i]);
            gamePane.getChildren().add(brownMeteors[i]);
        }

        greyMeteors = new ImageView[5];
        for (int i = 0; i < greyMeteors.length; i++) {
            greyMeteors[i] = new ImageView(GREY_METEOR);
            setElementsInRandomPositionInGame(greyMeteors[i]);
            gamePane.getChildren().add(greyMeteors[i]);
        }

    }

    private void setElementsInRandomPositionInGame(ImageView image) {

        image.setLayoutX(randomPositionOfMeteorsGenerator.nextInt(550)); //zasieg na osi X
        image.setLayoutY(-(randomPositionOfMeteorsGenerator.nextInt(3200) + 700));
        //rozproszenie meteorow na osi Y.
    }

    private void moveElementsInGame() {

        starImage.setLayoutY(starImage.getLayoutY() + 5);

        for (ImageView brownMeteor : brownMeteors) {

            brownMeteor.setLayoutY(brownMeteor.getLayoutY() + 7);
            brownMeteor.setRotate(brownMeteor.getRotate() + 4);

        }

        for (ImageView greyMeteor : greyMeteors) {

            greyMeteor.setLayoutY(greyMeteor.getLayoutY() + 7);
            greyMeteor.setRotate(greyMeteor.getRotate() + 4);

        }
    }

    private void checkIfElementsInGameAreBelowTheShip() {

        if (starImage.getLayoutY() > 1200) {
            setElementsInRandomPositionInGame(starImage);
        }

        for (ImageView brownMeteor : brownMeteors) {
            if (brownMeteor.getLayoutY() > 900) {
                setElementsInRandomPositionInGame(brownMeteor);
            }
        }

        for (ImageView greyMeteor : greyMeteors) {
            if (greyMeteor.getLayoutY() > 900) {
                setElementsInRandomPositionInGame(greyMeteor);
            }
        }

    }

    private void removeLifeAndFinishTheGame() {

        gamePane.getChildren().remove(playerLiefs[playerLife]);
        playerLife--;

        if (playerLife < 0) {
            gameTimer.stop();
            gameStage.close();
            //gameStage.show();
           // mainStage.show();
            System.out.println("GAME OVER");
        }

    }

    private double calculateDistance(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

    }

    private void checkIfElementsInGameAreCollide() {

        if (SHIP_RADIUS + STAR_RADIUS > calculateDistance(ship.getLayoutX() + 49, starImage.getLayoutX() + 15,
                ship.getLayoutY() + 37, starImage.getLayoutY() + 15)) {
            setElementsInRandomPositionInGame(starImage);

            points++;
            String setPointsSum = "POINTS : ";
            if (points < 10) {
                setPointsSum = setPointsSum + "0";
            }
            pointsLabel.setText(setPointsSum + points);
        }

        for (ImageView brownMeteor : brownMeteors) {
            if (METEOR_RADIUS + SHIP_RADIUS > calculateDistance(ship.getLayoutX() + 49, brownMeteor.getLayoutX() + 20,
                    ship.getLayoutY() + 37, brownMeteor.getLayoutY() + 20)) {
                removeLifeAndFinishTheGame();
                setElementsInRandomPositionInGame(brownMeteor);

            }
        }

        for (ImageView greyMeteor : greyMeteors) {
            if (STAR_RADIUS + METEOR_RADIUS > calculateDistance(ship.getLayoutX() + 49, greyMeteor.getLayoutX() + 20,
                    ship.getLayoutY() + 37, greyMeteor.getLayoutY() + 20)) {
                removeLifeAndFinishTheGame();
                setElementsInRandomPositionInGame(greyMeteor);

            }
        }
    }

}




