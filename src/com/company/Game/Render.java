package com.company.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

/**
 * Created by Oliver Stalder on 12.12.2016.
 */
public class Render extends Component implements Runnable {

    private Game game;
    private JFrame frame;
    public Canvas canvas;
    private BufferStrategy bufferStrategy;
    private BufferedImage mapBufferedImage;
    private Entity cameraEntity;
    final int HEIGHT = 1000;
    final int WIDTH = 1000;

    /**
     * Initialization
     * @param game game as parameter so it has all informations
     */
    public Render (Game game, Entity cameraEntity) {
        this.game = game;
        this.cameraEntity = cameraEntity;

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


        MediaTracker m = new MediaTracker(this);

        Image background = Toolkit.getDefaultToolkit().getImage("dirt.png");
        m.addImage( background, 0);

        // Loading images
        int counter = 1;
        for(java.util.Map.Entry<String, Model> entry : this.game.models.entrySet()) {
            String modelName = entry.getKey();
            Model model = entry.getValue();


            model.texture = Toolkit.getDefaultToolkit().getImage(model.texturePath);
            m.addImage( model.texture, counter);

            counter++;
        }

        try {
            m.waitForAll();
        }
        catch (InterruptedException e) {
            System.out.println("Loading of the image was interrupted" );
        }


        if( m.statusAll(false) == MediaTracker.LOADING)
            System.out.println("Still Loading - oops, we should never be here!");
        if( m.statusAll(false) == MediaTracker.ABORTED)
            System.out.println("Loading of image aborted");
        if( m.statusAll(false) == MediaTracker.ERRORED)
            System.out.println("Image was errored");
        if( m.statusAll(false) == MediaTracker.COMPLETE)
            System.out.println("Image load complete!");


//      load global background
//      Image background = Toolkit.getDefaultToolkit().getImage("assets/images/grass.png");

//      mapCanvas
        mapBufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);

        Graphics2D mapGraphics = mapBufferedImage.createGraphics();

//        mapGraphics.setPaint(new TexturePaint((BufferedImage) background, new java.awt.Rectangle(0, 0, 16, 16)));
        mapGraphics.drawRect(0,0, WIDTH, HEIGHT);
        for (int i = 0; i < this.game.map.blocks.size(); i++) {
            this.game.map.blocks.get(i).render(mapGraphics);
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


        g.translate( cameraEntity.position.x * -1 + WIDTH / 2, cameraEntity.position.y * -1 + HEIGHT / 2);

        g.drawImage(mapBufferedImage, null, 0, 0);


        for (int i = 0; i < this.game.map.entitys.size(); i++) {
            this.game.map.entitys.get(i).render(g);
        }

        g.dispose();
        bufferStrategy.show();
    }
}
