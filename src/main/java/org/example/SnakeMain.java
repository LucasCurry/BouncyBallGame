package org.example;

import com.googlecode.lanterna.input.KeyStroke;


public class SnakeMain {
    static Screen screen = new Screen();
    static SnakeGame snakeGame = new SnakeGame(screen);

    public static void main(String[] args) throws Exception {
        screen.border();
        autoRepeatedKey();
        // singleKey(); // not meant for this game but it works
    }


    /**
     * get keystrokes from keyboard, will autorepeat the last valid
     * keystroke if nothing is pressed
     */
    private static void autoRepeatedKey() throws InterruptedException {
        KeyStroke latestValidKeyStroke = null;
        int counter = 0;
        while (true) {
            KeyStroke keyStroke;
            keyStroke = screen.getKeyStroke();
            if (snakeGame.handleKey(keyStroke)) { // true for valid keys
                latestValidKeyStroke = keyStroke;
            } else {
                counter++;
                if (counter > snakeGame.getTicks()) {
                    counter = 0;
                    snakeGame.handleKey(latestValidKeyStroke);
                    // called automatically to repeat key
                    // every ticks no of 5 ms intervals
                }
                Thread.sleep(5);
            }

        }
    }

    /**
     * Will send keystrokes to the snakeGame, assuming
     * handleKey will ignore invalid keys
     */
    private static void singleKey() throws InterruptedException {
        KeyStroke keyStroke;
        while (true) {
            keyStroke = screen.getKeyStroke();
            snakeGame.handleKey(keyStroke);
            Thread.sleep(5);
        }
    }
}


