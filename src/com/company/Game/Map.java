package com.company.Game;

import java.util.ArrayList;

/**
 * Created by Pascal on 12.12.2016.
 */
public class Map {
    public ArrayList<Entity>entitys;
    public ArrayList<Block> blocks;
    public Vector size;

    Map(Game game, double sizeX, double sizeY) {
        this.size = new Vector(sizeX, sizeY);
        ArrayList<Block> block = new ArrayList<>();
        block.add(new Block(
            new Vector(20, 20),
            game.models.get("dirt"),
            true
        ));

        this.blocks = block;

        ArrayList<Entity> entity = new ArrayList<>();

        entity.add(new Entity(
            new Vector(100, 100),
            game.models.get("player")
        ));

        this.entitys = entity;
    }
}
