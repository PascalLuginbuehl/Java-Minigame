package com.company.Game;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by Pascal on 12.12.2016.
 */
public class Render {
    private Game game;
//    private canvas: HTMLCanvasElement;
//    private context: CanvasRenderingContext2D;
//    private mapCanvas: HTMLCanvasElement;
//    private mapContext: CanvasRenderingContext2D;
    private Entity cameraEntity;

    Render(Game game, Entity cameraEntity) {
        this.game = game;
        this.cameraEntity = cameraEntity;

//        this.canvas = document.createElement('canvas');
//        canvasParent.appendChild(this.canvas);
//
//        this.context = this.canvas.getContext('2d');
//
//
//        this.canvas.height = document.documentElement.clientHeight;
//        this.canvas.width = document.documentElement.clientWidth;
//
//
//        // Map canvas
//        this.mapCanvas = document.createElement('canvas');
//        this.mapCanvas.height = this.game.map.size.x;
//        this.mapCanvas.width = this.game.map.size.y;
//
//        this.mapContext = this.mapCanvas.getContext('2d');
//
//
//        window.addEventListener('resize', () => {
//                this.canvas.width = document.documentElement.clientWidth;
//        this.canvas.height = document.documentElement.clientHeight;
//    });


        // Preloading images

        int texturesToLoad =  this.game.models.size() + 1;;
        int loadedTextures = 0;

        // load global background
        Image background = Toolkit.getDefaultToolkit().getImage("yourFile.gif");

        background.src = "assets/images/grass.png";
        background.addEventListener('load', () => {
                loadedTextures++;
        this.game.map.background = this.context.createPattern(background, "repeat");
        if (loadedTextures >= texturesToLoad) {
            this.paintBlocks();
            setInterval(this.renderLoop.bind(this), 16);
        }
    })

        for(Map.Entry<String, HashMap> entry : selects.entrySet()) {
            String key = entry.getKey();
            HashMap value = entry.getValue();

            // do what you have to do here
            // In your case, an other loop.
        }
        this.game.models.forEach(() -> {

        });
        for (let modelName in this.game.models) {
            let model = this.game.models[modelName];
            model.texture = new Image();
            model.texture.src = model.texturePath;

            model.texture.addEventListener('load', () => {
                    loadedTextures++;
            if (model.hasPattern) {
                model.pattern = this.context.createPattern(model.texture, "repeat");
            }
            if (loadedTextures >= texturesToLoad) {
                this.paintBlocks();
                setInterval(this.renderLoop.bind(this), 16);
            }
      })
        }
        // setInterval(this.renderLoop.bind(this), 16);
    }

    public paintBlocks(): void {
        this.mapContext.rect(0, 0, this.mapCanvas.height, this.mapCanvas.width);
        this.mapContext.fillStyle = this.game.map.background;
        this.mapContext.fill();

        for (let i = 0; i < this.game.map.blocks.length; i++) {
            this.game.map.blocks[i].render(this.mapContext);
        }
    }

    /**
     * Renderloop, goes throug all blocks and entitys and draws them on canvas
     */
    private renderLoop(): void {
        this.context.clearRect(0, 0, this.canvas.width, this.canvas.height);
        this.context.save();

        this.context.translate(Math.round(this.cameraEntity.position.x) * -1 + Math.round(this.canvas.width/2), Math.round(this.cameraEntity.position.y) * -1  + Math.round(this.canvas.height/2));

        this.context.drawImage(this.mapCanvas, 0, 0);

        for (let i = 0; i < this.game.map.entitys.length; i++) {
            this.game.map.entitys[i].render(this.context);
            this.game.map.entitys[i].model.hitbox.drawHitbox(this.game.map.entitys[i].position, this.context);
        }

        this.context.restore();
        requestAnimationFrame(() => {});
    }
}
