package game.model;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Font;

public class GameButtons extends Button {

    private static String FONT_PATH = "/game/model/buttons/resources/kenvector_future.ttf";
    private static String BUTTON_PRESSED_STYLE = " -fx-background-color: transparent; -fx-background-image: url('/game/model/buttons/resources/buttonRed.png');";
    private static String BUTTON_FREE_STYLE = " -fx-background-color: transparent; -fx-background-image: url('/game/model/buttons/resources/buttonYellow.png');";

    public GameButtons(String text) {
        setText(text);
        setButtonFont();
        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(49);
        setPrefWidth(190);
        someMouseEventsOnButtons();

    }

    private void setButtonFont() {
        setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 23));

    }

    private void setButtonPressedStyle() {
        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);

    }

    private void setButtonFreeStyle() {
        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);
    }

    private void someMouseEventsOnButtons() {

        setOnMousePressed(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                setButtonPressedStyle();
            }
        });

        setOnMouseReleased(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                setButtonFreeStyle();
            }
        });

        setOnMouseEntered(mouseEvent -> setEffect(new DropShadow()));

        setOnMouseExited(mouseEvent -> setEffect(null));
    }

}
