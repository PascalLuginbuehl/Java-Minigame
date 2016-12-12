package com.company.Game;

/**
 * Created by Pascal on 12.12.2016.
 */
public class Block extends Body {
    public boolean collision;
    Block(Vector position, Model model, boolean collision) {
        super(position, model);
        this.collision = collision;
    }
}
