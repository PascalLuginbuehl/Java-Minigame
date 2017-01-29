package com.company.Game;

/**
 * Created by Pascal on 12.12.2016.
 */
public class Block extends Body {
    public boolean collision;

    /**
     * Block
     * @param position of the block
     * @param model model of the block
     * @param collision if has collision
     */
    Block(Vector position, Model model, boolean collision) {
        super(position, model);
        this.collision = collision;
    }
}
