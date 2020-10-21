package game.model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

public class InfoLabel extends Label {

    public InfoLabel(String text) {

        setWidth(380);
        setPrefHeight(49);
        setAlignment(Pos.CENTER);
        setWrapText(true);
        setText(text);
        setLabelFont();

        String BACKGROUND_IMAGE = "/game/view/resources/yellow_button13.png";
        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE, 500, 49,
                false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, null);
        setBackground(new Background(backgroundImage));


    }

    private void setLabelFont() {
        String FONT_PATH = "/game/model/buttons/resources/kenvector_future.ttf";
        setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 23));

    }

}
