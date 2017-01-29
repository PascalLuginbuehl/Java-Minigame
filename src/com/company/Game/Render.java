package com.company.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.HashMap;

/**
 * Created by Pascal on 12.12.2016.
 */
public class Render implements Runnable {

    private Game game;
    JFrame frame;
    public Canvas canvas;
    Canvas mapCanvas;
    BufferStrategy bufferStrategy;
    private Entity cameraEntity;
    final int HEIGHT = 1000;
    final int WIDTH = 1000;

    public Render(Game game) {
        this.game = game;

        frame = new JFrame("Basic Game3");

        JPanel panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(null);

        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();

        canvas.requestFocus();


        // Preloading images

        int texturesToLoad =  this.game.models.size() + 1;
        int loadedTextures = 0;

        // load global background
        Image background = Toolkit.getDefaultToolkit().getImage("assets/images/grass.png");


        for(java.util.Map.Entry<String, Model> entry : this.game.models.entrySet()) {
            String modelName = entry.getKey();
            Model model = entry.getValue();


            model.texture = Toolkit.getDefaultToolkit().getImage(model.texturePath);
        }
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

            renderLoop();

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


//    public void paintBlocks() {
//        this.mapContext.rect(0, 0, this.mapCanvas.height, this.mapCanvas.width);
//        this.mapContext.fillStyle = this.game.map.background;
//        this.mapContext.fill();
//
//        for (int i = 0; i < this.game.map.blocks.size(); i++) {
//            this.game.map.blocks.get(i).render(this.mapContext);
//        }
//    }

    /**
     * Renderloop, goes throug all blocks and entitys and draws them on canvas
     */
    private void renderLoop() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        for (int i = 0; i < this.game.map.blocks.size(); i++) {
            this.game.map.blocks.get(i).render(g);
        }

        for (int i = 0; i < this.game.map.entitys.size(); i++) {
            this.game.map.entitys.get(i).render(g);
        }

        g.dispose();
        bufferStrategy.show();
    }
}
