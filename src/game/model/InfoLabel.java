package game.model;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class InfoLabel extends Label {

    private static String FONT_PATH = "/game/model/buttons/resources/kenvector_future.ttf";

    public InfoLabel(String text) {

        setPrefHeight(400);
        setWidth(600);
        setPadding(new Insets(40,40,40,40));
        setWrapText(true);
        setText(text);
        setLabelFont();

    }

    private void setLabelFont() {
        setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 23));

    }

}
