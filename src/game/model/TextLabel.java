package game.model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class TextLabel extends Label {

    private static String FONT_PATH = "/game/model/buttons/resources/kenvector_future.ttf";

    public TextLabel(String text) {

        setWidth(280);
        setPrefHeight(200);
        setAlignment(Pos.BOTTOM_LEFT);
        setWrapText(true);
        setText(text);
        setTextFont();

    }

    private void setTextFont() {
        setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 23));

    }

}
