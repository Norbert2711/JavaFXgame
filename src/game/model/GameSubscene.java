package game.model;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class GameSubscene extends SubScene {

    private static String FONT_PATH = "/game/model/buttons/resources/kenvector_future.ttf";
    private static String BACKGROUND_IMAGE = "game/view/resources/yellow_panel.png";
    boolean isHidden = true;

    public GameSubscene() {

        super(new AnchorPane(), 600, 400);
        prefHeight(400);
        prefWidth(600);

        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE, 600, 400, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane root2 = (AnchorPane) this.getRoot();

        root2.setBackground(new Background(backgroundImage));

        setLayoutX(1024);
        setLayoutY(180);

    }

    public void moveSubscene() {

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(0.2));
        translateTransition.setNode(this);

        //translateTransition.setToY(150);

        if (isHidden) {
            translateTransition.setToX(-650);
            isHidden = false;

        } else {
            translateTransition.setToX(0);
            isHidden = true;

        }

        translateTransition.play();

    }

}
