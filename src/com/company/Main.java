package com.company;
import com.company.Game.*;



public class Main {

    public static void main(String[] args) {
//        new Render(new Game());

        Game gameEx = new Game();
        new Thread(gameEx).start();

        Render renderEx = new Render(gameEx, gameEx.map.entitys.get(0));
        new Thread(renderEx).start();

        new Player(0, renderEx, gameEx);
    }
}
