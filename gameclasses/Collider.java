package gameclasses;

import java.util.Arrays;


public class Collider {
    Entity currEntity;

    public Collider(Entity e) {
        this.currEntity = e;
    }

    public boolean checkForCollision(Building[][] buildings) {
        Building[][] buildings2 = Arrays.copyOf(buildings, buildings.length);
        for (Building[] bArray : buildings2) {
            for (Building b : bArray) {
                if (this.collidesWith(b) && !b.isEmpty()) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean collidesWith(Building b) {
        //Offset so player doesn't enter inside the wall during animation.
        double offset = 0.05;
        return currEntity.getXPosition() - offset < b.getX() + b.getSize()
            && currEntity.getXPosition() + currEntity.getWidth() + offset > b.getX()
            && currEntity.getYPosition() - offset < b.getY() + b.getSize()
            && currEntity.getYPosition() + currEntity.getHeight() + offset > b.getY();

    }


}