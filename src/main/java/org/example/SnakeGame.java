package org.example;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.LinkedList;
import java.util.Random;

import static se.bilting.snake.Screen.*;

public class SnakeGame {
    static final public int STARTLENGTH = 10;
    private int ticks = 30; // small ticks means faster move
    private Position headPos = new Position(15, 15);
    private Screen screen;
    private LinkedList<Position> snake = new LinkedList<>();
    private Random random = new Random();
    private int growCount = 0;

    /**
     * constructor: add the original snake and the first treasure
     */
    public SnakeGame(Screen screen)  {
            this.screen = screen;
            for (int i = STARTLENGTH-1; i >=0; i--) {
                snake.add(new Position(headPos.getCol()-i ,headPos.getRow()));
                screen.putChar(headPos.getCol()-i, headPos.getRow(), 'X', GREEN, BLACK);
            }
            addTreasure();
    }

    /**
     * Handles the keystroke input.
     * Should react correctly if a legal key is pressed and return true
     * Ignores illegal keys and returns false
     * Ends the game with message if tryMove says so!
     */
    public boolean handleKey (KeyStroke keyStroke) {
        String result = "";
        if (keyStroke == null) return false;

        KeyType kt = keyStroke.getKeyType();
        switch (kt) {
            case ArrowDown,ArrowUp,ArrowRight,ArrowLeft:
                result = tryMove(kt);
                break;
            default:
                //ignore all other key!!
                return false;
        }
        if (result.equals("Continue")) {
            return true;
        } else {
            screen.putString(10,15,result);
            screen.putString(10,17, "End of game. Score = " + getLength());
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                // ignore
            }
            System.exit(0);
            return false;
        }

    }

    /**
     * try to move the snake one step in a direction.
     * If it hits itself or wall of blocks it returns
     * a message to say which.
     * Return message "Continue" means the move is OK
     */
    private String tryMove(KeyType kt)  {
        int row = headPos.getRow();
        int col = headPos.getCol();
        switch(kt) {
            case ArrowUp:
                row--;
                break;
            case ArrowDown:
                row++;
                break;
            case ArrowLeft:
                col--;
                break;
            case ArrowRight:
                col++;
                break;
            default:
                //ignore illegal keystrokes
                return "Continue";
        }
        char next = screen.getChar(col,row);
        switch (next) {
            case 'X':
                return "Walked into snake!";
            case BLOCK:
                return "Walked into wall!";
            default:
                if (Character.isDigit(next)) {
                    growCount += Integer.parseInt("" + next);
                    addTreasure();
                }
                moveSnake(col,row);
                return "Continue";
        }
    }

    /**
     * move the snake by adding new X at new head position
     * and erasing X at old tail position
     * Lets the snake grow, by not erasing tail as long as
     * growcount is > 0
     */
    private void moveSnake(int col, int row){
        //System.out.println("move" + col + " " + row);
        headPos = new Position(col,row);
        snake.add(headPos);
        screen.putChar(headPos.getCol(), headPos.getRow(), 'X', GREEN, BLACK);
        if (growCount == 0) {
            Position tail = snake.get(0);
            screen.putChar(tail.getCol(), tail.getRow(), ' ', BLACK, BLACK);
            snake.remove(0);
        } else {
            growCount--;
        }
    }

    /**
     * current length of the snake
     */
    private int getLength() {
        return snake.size();
    }

    /**
     * ticks is the number of 5 ms timeslices between snake auto movement
     * lower means faster
     */
    public int getTicks() {
        return ticks;
    }

    /**
     * puts a new treasure digit char at a random place
     * avoiding the snake body and same position as before
     */
    private void addTreasure() {
        int col, row;
        char c;
        do {
            col =  random.nextInt(COLS-2) + 1;
            row =  random.nextInt(ROWS-2) + 1;
            c = screen.getChar(col,row);
        } while (c == 'X' || Character.isDigit(c));
        char treasure = (char) (random.nextInt(9) + '1');
        screen.putChar(col,row,treasure, RED, WHITE);
    }
}
