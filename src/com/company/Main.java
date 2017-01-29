package com.company;
import com.company.Game.*;

import java.io.File;

public class Main {

    public static void main(String[] args) {
//        new Render(new Game());

        Game gameEx = new Game();
        new Thread(gameEx).start();

        Render renderEx = new Render(gameEx);
        new Thread(renderEx).start();

        new Player(0, renderEx, gameEx);
    }
}
