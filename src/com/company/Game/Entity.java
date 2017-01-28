package com.company.Game;

import java.awt.*;

/**
 * Created by Pascal on 12.12.2016.
 */
public class Entity extends Body {

    public Vector velocity;
    public Vector force;
    private int spritePositon;
    public int lastDirection;

    Entity(Vector position, Model model, Vector force, Vector velocity) {
        super(position, model);

        this.velocity = velocity;
        this.force = force;
        this.lastDirection = 0;
    }
}
