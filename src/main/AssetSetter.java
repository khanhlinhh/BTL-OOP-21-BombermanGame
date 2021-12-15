package main;

import entities.enemies.Balloon;
import entities.enemies.Oneal;
import item.Item;

import java.awt.*;
import java.util.ArrayList;

public class AssetSetter {
  GamePanel gp;
  Oneal oneals[] = new Oneal[2];
  Balloon balloons[] = new Balloon[3];
  ArrayList<Item> items = new ArrayList<>();

  public AssetSetter(GamePanel gp) {
    this.gp = gp;
    oneals[0] = new Oneal(gp, GamePanel.tileSize * 17, GamePanel.tileSize);
    oneals[1] = new Oneal(gp, GamePanel.tileSize * 24, GamePanel.tileSize * 3);
    balloons[0] = new Balloon(gp, GamePanel.tileSize * 13, GamePanel.tileSize);
    balloons[1] = new Balloon(gp, GamePanel.tileSize * 18, GamePanel.tileSize * 3);
    balloons[2] = new Balloon(gp, GamePanel.tileSize * 24, GamePanel.tileSize * 5);
  }

  public void setObject() {
    this.items = gp.tileManager.getItems();

  }

  public void update() throws InterruptedException {
    for (Item item : items) {
      if (item.isDisappeared) {
        items.remove(item);
        break;
      } else {
        item.update();
      }
    }
    oneals[0].update();
    oneals[1].update();
    balloons[0].update();
    balloons[1].update();
    balloons[2].update();
  }

  public void draw(Graphics2D g2) {
    for (Item item : items) {
      item.draw(g2);
    }
    oneals[0].draw(g2);
    oneals[1].draw(g2);
    balloons[0].draw(g2);
    balloons[1].draw(g2);
    balloons[2].draw(g2);
  }
}
