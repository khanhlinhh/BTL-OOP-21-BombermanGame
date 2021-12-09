package Bomberman.entities;

import javafx.scene.image.Image;

public class Bomb extends Entity {
    private int level = 1;
    private int timeToExploded;
    private boolean isExploded;

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        timeToExploded = 150;
        isExploded = false;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isExploded() {
        return isExploded;
    }

    public void explode() {
        if (timeToExploded > 0) {
            timeToExploded--;
        }
        if (timeToExploded == 0 && !isExploded) {
            isExploded = true;
        }
    }

    @Override
    public void update() {
        explode();
    }
}
