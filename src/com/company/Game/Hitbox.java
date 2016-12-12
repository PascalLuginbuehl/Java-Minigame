package com.company.Game;
import java.util.ArrayList;

/**
 * Created by Pascal on 12.12.2016.
 */
public class Hitbox {
    protected ArrayList<Rectangle> rectangles;
    public Rectangle collisionBox;


    Hitbox(ArrayList<Rectangle> rectangles) {
        this.rectangles = rectangles;
        this.collisionBox = this.getCollisionBox();
    }

    public boolean checkCollision(Vector origin, Vector originHitbox, Hitbox hitbox) {
        Rectangle collisionBox = new Rectangle(this.collisionBox.min.add(originHitbox), this.collisionBox.max);
        Rectangle collisionBox2 = new Rectangle(hitbox.collisionBox.min.add(origin), hitbox.collisionBox.max);
        if (collisionBox.checkCollision(collisionBox2)) {
            // accurate collisionsdetection
            for (int i = 0; i < this.rectangles.size(); i++) {
                for (int o = 0; o < hitbox.rectangles.size(); o++) {
                    Rectangle otherRect = hitbox.rectangles.get(o);
                    Rectangle thisRect = this.rectangles.get(i);

                    Rectangle rect = new Rectangle(thisRect.min.add(originHitbox), thisRect.max);
                    Rectangle rect2 = new Rectangle(otherRect.min.add(origin), otherRect.max);

                    if (rect.checkCollision(rect2)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }


    private Rectangle getCollisionBox() {
        Vector max = new Vector(0, 0);

        for (int i = 0; i < this.rectangles.size(); i++) {
            Rectangle hitbox = this.rectangles.get(i);

            max = max.biggest(hitbox.min.add(hitbox.max));
        }

        Vector min = new Vector(max.x, max.y);

        for (int i = 0; i < this.rectangles.size(); i++) {
            min = min.smalest(this.rectangles.get(i).min);
        }

        return new Rectangle(min, max);
    }


//    public drawHitbox(Vector origin, ctx: CanvasRenderingContext2D) {
//        for (let i = 0; i < this.rectangles.length; i++) {
//            this.rectangles[i].drawRectangle(origin, ctx);
//        }
//    }
}
