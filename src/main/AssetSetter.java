package main;

import entities.Entity;
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
  ArrayList<Entity> entities = new ArrayList<>();

  public AssetSetter(GamePanel gp) {
    this.gp = gp;
  }

  public void setObject() {
    this.items = gp.tileManager.getItems();
    this.entities = gp.tileManager.getEntities();
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
    for (Entity entity : entities) {
      if (entity.isDead) {
        items.remove(entity);
        break;
      } else {
        entity.update();
      }
    }
  }

  public void draw(Graphics2D g2) {
    for (Item item : items) {
      if (item != null) {
        item.draw(g2);
      }
    }
    for (Entity entity : entities) {
      if (entity != null) {
        entity.draw(g2);
      }
    }
  }
}
