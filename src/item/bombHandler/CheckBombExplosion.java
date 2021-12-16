package item.bombHandler;

import entities.Entity;
import item.wall.Brick;
import main.GamePanel;

public class CheckBombExplosion {
    private GamePanel gp;

    public CheckBombExplosion(GamePanel gp) {
        this.gp = gp;
    }

//    public void checkBombExplosionCollision(Entity entity, Bomb bomb) {
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

    public void checkBombDestroy(Brick brick, Bomb bomb) {
        int brickLeft = brick.worldX;
        int brickTop = brick.worldY;
        int brickBottom = brickTop + GamePanel.tileSize;
        int brickRight = brickLeft + GamePanel.tileSize;

        int bombTop = bomb.worldY;
        int bombBottom = bombTop + GamePanel.tileSize;
        int bombLeft = bomb.worldX;
        int bombRight = bombLeft + GamePanel.tileSize;

        if ((brickBottom == bombTop || brickTop == bombBottom) && brickLeft == bombLeft && brickRight == bombRight) {
            brick.collision = true;
        } else if ((brickLeft == bombRight || brickRight == bombLeft) && brickBottom == bombBottom && brickTop == bombTop) {
            brick.collision = true;
        }
    }
}
