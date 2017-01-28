package com.company.Game;


import java.awt.*;

/**
 * Created by Pascal on 12.12.2016.
 */
public class Body {
    public Vector position;
    public Model model;

    Body(Vector positon, Model model) {
        this.position = positon;
        this.model = model;
    }

    void render(Graphics2D ctx) {
        ctx.drawImage(this.model.texture, (int) Math.round(this.position.x), (int) Math.round(this.position.y), null);
    }

    public boolean checkCollision(Body body, Vector newPosition) {
        return model.checkCollision(body.position, newPosition, body.model);
    }

//    public boolean checkCollision(Body a, Vector v) {
//        return true;
//        return this.model.checkCollision(body.position, newPosition, body.model);
//    }



        public Vector[] getCollisionPosition(Vector newPosition, Vector newVelocity, Body collidedBody) {
        Vector returnPosition = new Vector(newPosition.x, newPosition.y);
            Vector returnVelocity = new Vector(newVelocity.x, newVelocity.y);

        if (this.checkCollision(collidedBody, new Vector(newPosition.x, this.position.y))) {
            returnVelocity.x = 0;
            returnPosition.x = this.position.x;
        }

        if (this.checkCollision(collidedBody, new Vector(this.position.x, newPosition.y))) {
            returnVelocity.y = 0;
            returnPosition.y = this.position.y;
        }

        if (returnPosition.x == newPosition.x && returnPosition.y == newPosition.y) {
            returnVelocity.x = 0;
            returnVelocity.y = 0;
            returnPosition.x = this.position.x;
            returnPosition.y = this.position.y;
        }
//            return  returnPosition, velocity: returnVelocity};
        return new Vector[]{returnPosition, returnVelocity};
    }
}
