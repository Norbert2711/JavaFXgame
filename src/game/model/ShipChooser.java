package game.model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class ShipChooser extends VBox {

    private ImageView circleImage;
    private ImageView shipImage;
    private static String EMPTY_CIRCLE = "game/view/resources/empty_circle.png";
    private static String CLICKED_CIRCLE = "game/view/resources/clicked_circle2.png";
    private SHIP ship;
    private boolean isCircleChosen;

    public ShipChooser(SHIP ship) {
        this.ship = ship;
        circleImage = new ImageView(EMPTY_CIRCLE);
        shipImage = new ImageView(ship.getUrlShip());
        this.isCircleChosen = false;
        setAlignment(Pos.CENTER);
        setSpacing(20);
        getChildren().add(circleImage);
        getChildren().add(shipImage);

    }

    public SHIP getShip() {
        return ship;
    }

    public boolean getIsCircleChosen() {
        return isCircleChosen;
    }

    public void setIsCircleChosen(boolean isCircleChosen) {
        this.isCircleChosen = isCircleChosen;
        String imageToSet = this.isCircleChosen ? CLICKED_CIRCLE : EMPTY_CIRCLE;
        circleImage.setImage(new Image(imageToSet));

    }

}