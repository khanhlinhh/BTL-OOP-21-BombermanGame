package main;

import entities.enemies.Balloon;
import entities.enemies.Oneal;

import java.awt.*;

public class AssetSetter {
    GamePanel gp;
    Oneal oneals[] = new Oneal[2];
    Balloon balloons[] = new Balloon[3];

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
        oneals[0] = new Oneal(gp, gp.tileSize * 17, gp.tileSize);
        oneals[1] = new Oneal(gp, gp.tileSize * 24, gp.tileSize * 3);
        balloons[0] = new Balloon(gp, gp.tileSize * 13, gp.tileSize);
        balloons[1] = new Balloon(gp, gp.tileSize * 18, gp.tileSize * 3);
        balloons[2] = new Balloon(gp, gp.tileSize * 24, gp.tileSize * 5);
    }

    public void setObject() {

    }

    public void update() {
        oneals[0].update();
        oneals[1].update();
        balloons[0].update();
        balloons[1].update();
        balloons[2].update();
    }

    public void draw(Graphics2D g2) {
        oneals[0].draw(g2);
        oneals[1].draw(g2);
        balloons[0].draw(g2);
        balloons[1].draw(g2);
        balloons[2].draw(g2);
    }
}
