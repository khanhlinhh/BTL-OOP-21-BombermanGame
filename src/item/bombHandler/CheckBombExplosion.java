package item.bombHandler;

import entities.Entity;
import item.Item;
import main.GamePanel;

public class CheckBombExplosion {
    private GamePanel gp;

    public CheckBombExplosion(GamePanel gp) {
        this.gp = gp;
    }

    public void checkBombExplosionCollision(Entity entity, Bomb bomb) {
        int entityLeft = entity.worldX + entity.solidArea.x;
        int entityRight = entityLeft + entity.solidArea.width;
        int entityTop = entity.worldY + entity.solidArea.y;
        int entityBottom = entityTop + entity.solidArea.height;
        int flameSize = gp.bomber.getFlameSize() - 1;
        int bottomBomb = bomb.worldY + GamePanel.tileSize + flameSize;
        int topBomb = bomb.worldY - flameSize;
        int leftBomb = bomb.worldX - flameSize;
        int rightBomb = bomb.worldX + GamePanel.tileSize + flameSize;

        if (entityBottom <= bottomBomb && entityBottom >= topBomb) {
            if (rightBomb >= entityLeft && leftBomb <= entityLeft) {
                entity.isDead = true;
            } else if (rightBomb >= entityRight && leftBomb <= entityRight) {
                entity.isDead = true;
            }
        }
    }

//    public void checkBombExplosionFlame(Item item, Bomb bomb) {
//        int entityLeft = entity.worldX + entity.solidArea.x;
//        int entityRight = entityLeft + entity.solidArea.width;
//        int entityTop = entity.worldY + entity.solidArea.y;
//        int entityBottom = entityTop + entity.solidArea.height;
//        int flameSize = gp.bomber.getFlameSize() - 1;
//        int bottomBomb = bomb.worldY + GamePanel.tileSize + flameSize;
//        int topBomb = bomb.worldY - flameSize;
//        int leftBomb = bomb.worldX - flameSize;
//        int rightBomb = bomb.worldX + GamePanel.tileSize + flameSize;
//
//        if (entityBottom <= bottomBomb && entityBottom >= topBomb) {
//            if (rightBomb >= entityLeft && leftBomb <= entityLeft) {
//                entity.isDead = true;
//            } else if (rightBomb >= entityRight && leftBomb <= entityRight) {
//                entity.isDead = true;
//            }
//        }
//    }
}
