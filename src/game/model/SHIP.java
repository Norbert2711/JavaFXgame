package game.model;

public enum SHIP {

    BLACK("game/view/resources/playerShip2_black.png", "game/view/resources/playerLife2_black.png"),
    BLUE("game/view/resources/playerShip2_blue.png", "game/view/resources/playerLife2_blue.png"),
    GREEN("game/view/resources/playerShip2_green.png", "game/view/resources/playerLife2_green.png"),
    ORANGE("game/view/resources/playerShip2_orange.png", "game/view/resources/playerLife2_orange.png");
    // ULTIMATE("game/view/resources/ulti_ship.png");

    public String urlShip;
    public String ulrLife;

    SHIP(String urlShip, String ulrLife) {
        this.urlShip = urlShip;
        this.ulrLife = ulrLife;

    }

    public String getUrlShip() {
        return urlShip;
    }

    public String getUlrLife() {
        return ulrLife;
    }

}
