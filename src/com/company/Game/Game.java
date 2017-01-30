package com.company.Game;

import com.sun.org.apache.regexp.internal.RE;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Pascal on 12.12.2016.
 */
public class Game implements Runnable {
    public Map map;
    public HashMap<String, Model> models;

    /**
     * Base class for everything, creates map, models and other stuff
     */
    public Game() {
        this.models = new HashMap<>();

        ArrayList<Rectangle> dirtHitbox = new ArrayList<>();
        dirtHitbox.add(new Rectangle(
                new Vector(0, 0),
                new Vector(10, 10)
        ));

        ArrayList<Rectangle> playerHitbox = new ArrayList<>();
        playerHitbox.add(new Rectangle(
                new Vector(0, 0),
                new Vector(24, 26)
        ));

        ArrayList<Rectangle> houseHitbox = new ArrayList<>();
        houseHitbox.add(new Rectangle(
                new Vector(0, 0),
                new Vector(190, 185)
        ));
        houseHitbox.add(new Rectangle(
                new Vector(190, 30),
                new Vector(64, 120)
        ));
        houseHitbox.add(new Rectangle(
                new Vector(63, 185),
                new Vector(15, 8)
        ));
        houseHitbox.add(new Rectangle(
                new Vector(112, 185),
                new Vector(15, 8)
        ));

        this.models.put("dirt", new Model(
            new Hitbox(dirtHitbox),
            "dirt.png",
            "dirt",
            new Vector(10, 10),
            1,
            false
        ));

        this.models.put("house", new Model(
                new Hitbox(houseHitbox),
                "house.png",
                "house",
                new Vector(254, 198),
                1,
                false
        ));

        this.models.put("player", new Model(
                new Hitbox(playerHitbox),
                "player.png",
                "player",
                new Vector(24, 26),
                10,
                false
        ));


        this.map = new Map(this, 1000, 1000);
    }

    long desiredFPS = 60;
    long desiredDeltaLoop = (1000*1000*1000)/desiredFPS;

    boolean running = true;

    public void run(){

        long beginLoopTime;
        long endLoopTime;
        long currentUpdateTime = System.nanoTime();
        long lastUpdateTime;
        long deltaLoop;

        while (running) {
            beginLoopTime = System.nanoTime();

            gameLoop();

            lastUpdateTime = currentUpdateTime;
            currentUpdateTime = System.nanoTime();
//            update((int) ((currentUpdateTime - lastUpdateTime)/(1000*1000)));

            endLoopTime = System.nanoTime();
            deltaLoop = endLoopTime - beginLoopTime;

            if(deltaLoop > desiredDeltaLoop){
                //Do nothing. We are already late.
            } else {
                try {
                    Thread.sleep((desiredDeltaLoop - deltaLoop)/(1000*1000));
                } catch(InterruptedException e) {
                    //Do nothing
                }
            }
        }
    }




    private void gameLoop() {
        double delay = 16.0 / 1000.0;


        for (int i = 0; i < this.map.entitys.size(); i++) {
            Entity entity = this.map.entitys.get(i);
            if (entity != null) {
                Vector acceleration = entity.force.scale(1500);
                double friction = .91;

                entity.velocity = entity.velocity.add(acceleration.scale(delay)).scale(friction).round();

                // new position (now check for collision)
                Vector position = entity.position.add(entity.velocity.scale(delay));
                ArrayList<Body> collision = new ArrayList<>();
                for (int o = 0; o < this.map.blocks.size(); o++) {
                    Block block = this.map.blocks.get(o);
                    if (block != null) {

                        if (block.collision) {
                            // Collision detection
                            if (entity.checkCollision(block, position)) {
                                collision.add(block);
                            }
                        }
                    }
                }

                for (int o = 0; o < this.map.entitys.size(); o++) {
                    Entity entity2 = this.map.entitys.get(o);
                    if (entity2 != null && entity != entity2) {

                        // Collision detection
                        if (entity.checkCollision(entity2, position)) {
                            collision.add(entity2);
                        }
                    }
                }

                // sets new position or keeps last depending on collision
                if (new Rectangle(new Vector(0, 0), this.map.size).checkCollision(new Rectangle(position, entity.model.hitbox.collisionBox.max))) {
                    if (collision.size() > 0) {
                        Vector newPosition = new Vector(position.x, position.y);
                        Vector newVelocity = new Vector(entity.velocity.x, entity.velocity.y);

                        for (int p = 0; p < collision.size(); p++) {
                            Body body = collision.get(p);
                            Vector[] ret = entity.getCollisionPosition(newPosition, newVelocity, body);
                            newPosition = ret[0];
                            newVelocity = ret[1];
                        }
                        entity.position = newPosition;
                        entity.velocity = newVelocity;
                    } else {
                        entity.position = position;
                    }
                } else {
                    entity.velocity = entity.velocity.scale(.1);
                }

                entity.lastDirection = entity.getDirection();
            }
        }
    }
}
