package com.company.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.HashMap;

/**
 * Created by Pascal on 12.12.2016.
 */
public class Render {
//    private canvas: HTMLCanvasElement;
//    private context: CanvasRenderingContext2D;
//    private mapCanvas: HTMLCanvasElement;
//    private mapContext: CanvasRenderingContext2D;

    private Game game;
    JFrame frame;
    Canvas canvas;
    Canvas mapCanvas;
    BufferStrategy bufferStrategy;
    private Entity cameraEntity;

    Render(Game game, Entity cameraEntity) {
        this.game = game;
        this.cameraEntity = cameraEntity;


        // Preloading images

        int texturesToLoad =  this.game.models.size() + 1;;
        int loadedTextures = 0;

        // load global background
        Image background = Toolkit.getDefaultToolkit().getImage("assets/images/grass.png");


        for(java.util.Map.Entry<String, Model> entry : this.game.models.entrySet()) {
            String modelName = entry.getKey();
            Model model = entry.getValue();


            model.texture = Toolkit.getDefaultToolkit().getImage(model.texturePath);
        }
    }

    public paintBlocks(): void {
        this.mapContext.rect(0, 0, this.mapCanvas.height, this.mapCanvas.width);
        this.mapContext.fillStyle = this.game.map.background;
        this.mapContext.fill();

        for (int i = 0; i < this.game.map.blocks.size(); i++) {
            this.game.map.blocks.get(i).render(this.mapContext);
        }
    }

    /**
     * Renderloop, goes throug all blocks and entitys and draws them on canvas
     */
    private renderLoop(): void {
        this.context.clearRect(0, 0, this.canvas.width, this.canvas.height);

        this.context.drawImage(this.mapCanvas, 0, 0);

        for (int i = 0; i < this.game.map.entitys.size(); i++) {
            this.game.map.entitys.get(i).render(this.context);
        }
    }
}
