package com.company.Game;

/**
 * Created by Pascal on 12.12.2016.
 */
public class Rectangle {
    public Vector min;
    public Vector max;


    Rectangle(Vector min, Vector max) {
        this.min = min;
        this.max = max;
    }


    public boolean checkCollision(Rectangle rect) {
        Vector rectMin = rect.min;
        Vector thisMin = this.min;

        if (thisMin.x < rectMin.x + rect.max.x && this.max.x + thisMin.x > rectMin.x && thisMin.y < rect.max.y + rectMin.y && this.max.y + thisMin.y > rectMin.y) {
            return true;
        }

        return false;
    }
}
