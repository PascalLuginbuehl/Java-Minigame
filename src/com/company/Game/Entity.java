package com.company.Game;

import java.awt.*;

/**
 * Created by Pascal on 12.12.2016.
 */
public class Entity extends Body {

    public Vector velocity;
    public Vector force;
    private double spritePositon;
    public int lastDirection;

    /**
     * Entity with many parameters for movement
     * @param position position in map
     * @param model model of block (texture...)
     * @param force force in which direction it moves
     * @param velocity actual speed
     */
    Entity(Vector position, Model model, Vector force, Vector velocity) {
        super(position, model);

        this.velocity = velocity;
        this.force = force;
        this.lastDirection = 0;
        this.spritePositon = 0.0;
    }

    Entity(Vector position, Model model) {
        super(position, model);

        this.velocity = new Vector(0.0, 0.0);
        this.force = new Vector(0.0, 0.0);
        this.lastDirection = 0;
        this.spritePositon = 0.0;
    }

    void render(Graphics2D ctx) {
        if ((int) Math.ceil(this.spritePositon) >= this.model.spriteMax) {
            this.spritePositon = 0.0;
        }

        int width = this.model.texture.getWidth(null) / this.model.spriteMax;
        int height = this.model.texture.getHeight(null) / this.model.spriteHeight;

        int textureX = 0;
        switch (this.lastDirection) {
            case 1:
            case 2:
            case 3:
                textureX = 3;
                break;
            case -1:
            case -2:
            case -3:
                textureX = 1;
                break;
            case -4:
            case 4:
                textureX = 2;
                break;
        }

        double speed = Math.sqrt(this.velocity.y*this.velocity.y + this.velocity.x * this.velocity.x);

        int test = width * (int) Math.floor(this.spritePositon);
        int test2 = height * textureX;

        this.spritePositon += speed / 1000;
        ctx.drawImage(
                this.model.texture,

//               Position
                (int) this.position.x,
                (int) this.position.y,

                // Size
                (int) this.model.textureSize.x + (int) this.position.x,
                (int) this.model.textureSize.y + (int) this.position.y,


                // Sprite position
                width * (int) Math.floor(this.spritePositon),
                height * textureX,


                // Width and height of single part
                width + width * (int) Math.floor(this.spritePositon),
                height + height * textureX,

                null
        );
    }

    public int getDirection() {
        if (Math.floor(Math.abs(this.velocity.x)) < 10 && Math.floor(Math.abs(this.velocity.y)) < 10) {
            return this.lastDirection;
        } else {
            double rad = Math.atan2(this.velocity.x, this.velocity.y);
            return (int) Math.round(rad / Math.PI * 4);
        }
    }
}
