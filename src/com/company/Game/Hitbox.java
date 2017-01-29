package com.company.Game;
import java.util.ArrayList;

/**
 * Created by Pascal on 12.12.2016.
 */
public class Hitbox {
    protected ArrayList<Rectangle> rectangles;
    public Rectangle collisionBox;

    /**
     * array of recangles which build hitbox
     * @param rectangles
     */
    Hitbox(ArrayList<Rectangle> rectangles) {
        this.rectangles = rectangles;
        this.collisionBox = this.getCollisionBox();
    }

    /**
     * checks collision first with outer collsion box, after true with normal checkung
     * @param origin own new position
     * @param originHitbox others position
     * @param hitbox hitbox of the orher
     * @return returns if collision occured
     */
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

    /**
     * Gets outer collision box so check collsion not always has to run
     * @return new Rectangle around everything
     */
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
