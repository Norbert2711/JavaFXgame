package game.model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

public class SmallInfoLabelPanel extends Label {

    public SmallInfoLabelPanel(String text) {

        setPrefHeight(50);
        setPrefWidth(130);
        String BACKGROUND_IMAGE = "game/view/resources/buttonBlue.png";
        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, null);
        setBackground(new Background(backgroundImage));
        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10, 10, 10, 10));
        setTextFont();
        setText(text);

    }

    private void setTextFont() {
        String FONT_PATH = "/game/model/buttons/resources/kenvector_future.ttf";
        setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 15));

    }

}
