package com.company.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * Created by piran on 28.1.17.
 */
public class Player {
    private Entity player;
    private HashMap<Character, Boolean> keys;


    public Player(int playerIndex, Render render, Game game) {
        player = game.map.entitys.get(playerIndex);
        keys = new HashMap<>();
        keys.put('w', false);
        keys.put('a', false);
        keys.put('s', false);
        keys.put('d', false);

        render.canvas.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                e.getKeyChar();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                char key = e.getKeyChar();
                if (keys.containsKey(key))  {
                    keys.replace(key, true);
                }

                player.force = getDirection();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                char key = e.getKeyChar();
                if (keys.containsKey(key))  {
                    keys.replace(key, false);
                }

                player.force = getDirection();
            }
        });
    }

    private Vector getDirection()  {
        Vector v = new Vector(0, 0);

        if (this.keys.get('w')) {
            v.y-- ;
        }

        if (this.keys.get('a')) {
            v.x--;
        }

        if (this.keys.get('s')) {
            v.y++;
        }

        if (this.keys.get('d')) {
            v.x++;
        }

        return v;
    }


//        window.addEventListener('keydown', (e) => {
//        if (this.keys.hasOwnProperty(e.key)) {
//            this.keys[e.key] = true;
//
//            this.input.setForce(index, this.getDirection(this.keys));
//
//            e.preventDefault();
//        }
//    });
//
//        window.addEventListener('keyup', (e) => {
//        if (this.keys.hasOwnProperty(e.key)) {
//            this.keys[e.key] = false;
//
//            this.input.setForce(index, this.getDirection(this.keys));
//
//            e.preventDefault();
//        }
}

