package game.model;

public enum SHIP {

    BLACK("game/view/resources/playerShip2_black.png"),
    BLUE("game/view/resources/playerShip2_blue.png"),
    GREEN("game/view/resources/playerShip2_green.png"),
    ORANGE("game/view/resources/playerShip2_orange.png");
   // ULTIMATE("game/view/resources/ulti_ship.png");

    public String urlShip;

    SHIP(String urlShip) {
        this.urlShip = urlShip;

    }

    public String getUrlShip() {
        return urlShip;
    }
}
