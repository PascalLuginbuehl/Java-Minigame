package com.company.Game;
import java.awt.*;

/**
 * Created by Pascal on 12.12.2016.
 */
public class Model {
    public Hitbox hitbox;
//    public HTMLImageElement texture;
    public String name;
    public Vector textureSize;
    public String texturePath;
    public Image texture;
    public int spriteMax;
    public boolean hasPattern;
//    public CanvasPattern pattern;

    Model(Hitbox hitbox, String texturePath, String name, Vector textureSize, int spriteMax, boolean hasPattern) {
        this.hitbox = hitbox;
        this.name = name;
        this.textureSize = textureSize;
        this.hasPattern = hasPattern;


        this.spriteMax = spriteMax;
        this.texturePath = texturePath;
    }


    public boolean checkCollision(Vector origin, Vector originHitbox, Model model) {
        return this.hitbox.checkCollision(origin, originHitbox, model.hitbox);
    }
}
