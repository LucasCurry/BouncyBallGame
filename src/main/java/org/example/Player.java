package org.example;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;


import java.io.IOException;


public class Player {
    int x = 40;
    int y = 12;
    final char player = 'X';
    private Screen screen;

    public Player(Screen screen) {
        this.screen = screen;

    }

    public void setPlayer() throws IOException, InterruptedException {


      /*  terminal.setCursorPosition(x, y);
        terminal.putCharacter(player);
        terminal.setCursorVisible(false);
        terminal.flush();

        boolean contntinueReadingInput = true;
        while (contntinueReadingInput) {
            KeyStroke keyStroke;
            do {
                Thread.sleep(5); // might throw InterruptedException
                keyStroke = terminal.pollInput();
            } while (keyStroke == null);
            KeyType type = keyStroke.getKeyType();
            Character c = keyStroke.getCharacter();
            switch (type) {
                case ArrowLeft:
                    terminal.setCursorPosition(x, y);
                    terminal.putCharacter(' ');
                    x = x - 1;
                    terminal.setCursorPosition(x, y);
                    terminal.putCharacter(player);
                    break;
                case ArrowRight:
                    terminal.setCursorPosition(x, y);
                    terminal.putCharacter(player);
                    break;
            }
            terminal.flush();
            if (c == Character.valueOf('q')) {
                contntinueReadingInput = false;
                System.out.println("Quit Game");
                terminal.close();
            }


        }*/


    }
}
