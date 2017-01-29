package com.company.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by Pascal on 12.12.2016.
 */
public class Render implements Runnable {

    private Game game;
    JFrame frame;
    public Canvas canvas;
    BufferStrategy bufferStrategy;
    BufferedImage mapBufferedImage;
    private Entity cameraEntity;
    final int HEIGHT = 1000;
    final int WIDTH = 1000;

    /**
     * Initialization
     * @param game game as parameter so it has all informations
     */
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


//        mapCanvas
        mapBufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);

        Graphics2D mapGraphics = mapBufferedImage.createGraphics();

        for (int i = 0; i < this.game.map.blocks.size(); i++) {
            this.game.map.blocks.get(i).render(mapGraphics);
        }



        // load global background
//        Image background = Toolkit.getDefaultToolkit().getImage("assets/images/grass.png");


        // Loading images
        for(java.util.Map.Entry<String, Model> entry : this.game.models.entrySet()) {
            String modelName = entry.getKey();
            Model model = entry.getValue();


            model.texture = Toolkit.getDefaultToolkit().getImage(model.texturePath);
        }
    }


    long desiredFPS = 60;
    long desiredDeltaLoop = (1000*1000*1000)/desiredFPS;

    boolean running = true;

    /**
     * implemented from interface ex,
     * runs this as single thread
     */
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

    /**
     * Renderloop, goes throug all blocks and entitys and draws them on canvas
     */
    private void renderLoop() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        g.drawImage(mapBufferedImage, null, 0, 0);

//        for (int i = 0; i < this.game.map.blocks.size(); i++) {
//            this.game.map.blocks.get(i).render(g);
//        }

        for (int i = 0; i < this.game.map.entitys.size(); i++) {
            this.game.map.entitys.get(i).render(g);
        }

        g.dispose();
        bufferStrategy.show();
    }
}
