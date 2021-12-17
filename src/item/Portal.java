package item;

import graphics.Sprite;
import main.GamePanel;
import main.Sound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Portal extends Item {
    private GamePanel gp;

    public Portal(GamePanel gp, int xUnit, int yUnit) {
        this.gp = gp;
        isPortal = true;
        worldX = xUnit * GamePanel.tileSize;
        worldY = yUnit * GamePanel.tileSize;
        getImage();
    }

    @Override
    public void getImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(Sprite.portalItem));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() throws InterruptedException {
        collision = false;
        gp.checkCollision.checkPowerUp(gp.bomber, this);
        if (collision && gp.tileManager.countBrick == 0) {
            gp.bomber.isVictory = true;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(image, worldX, worldY, GamePanel.tileSize, GamePanel.tileSize, null);
    }
}
