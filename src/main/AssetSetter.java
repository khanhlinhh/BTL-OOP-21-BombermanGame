package main;

import entities.Entity;
import item.Item;

import java.awt.*;
import java.util.ArrayList;

public class AssetSetter {
  GamePanel gp;
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
    if (!items.isEmpty()) {
      for (Item item : items) {
        if (item.isDisappeared) {
          items.remove(item);
          break;
        } else {
          item.update();
        }
      }
    }
    if (!entities.isEmpty()) {
      for (Entity entity : entities) {
        if (entity.isDead) {
          entities.remove(entity);
          break;
        } else {
          entity.update();
        }
      }
    }
  }

  public void draw(Graphics2D g2) {
    if (!items.isEmpty()) {
      for (Item item : items) {
        if (item != null) {
          item.draw(g2);
        }
      }
    }
    if (!entities.isEmpty()) {
      for (Entity entity : entities) {
        if (entity != null) {
          entity.draw(g2);
        }
      }
    }
  }
}
