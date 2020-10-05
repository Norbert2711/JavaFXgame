package game.model;

import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class GameSubscene extends SubScene {

    private static String FONT_PATH = "/game/model/buttons/resources/kenvector_future.ttf";
    private static String BACKGROUND_IMAGE = "game/view/resources/yellow_panel.png";

    public GameSubscene() {

        super(new AnchorPane(), 600, 400);
        prefHeight(400);
        prefWidth(600);

        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE, 600, 400, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane root2 = (AnchorPane) this.getRoot();

        root2.setBackground(new Background(backgroundImage));
    }

}
