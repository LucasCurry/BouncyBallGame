package org.example;

public class Ball {
    private final char ball = '\u26BD';
    private Screen screen;

    int row;
    int col;
    int deltarow;
    int deltacol;


    public Ball(Screen screen) {
        this.screen = screen;
        col = 40;
        row = 12;
        deltarow = +1;
        deltacol = -1;
        screen.putChar(col, row, ball);

    }

    public void downRight() {
        do {
            screen.putChar(col + 40, row + 12, ball);
            screen.putChar(col + 40, row + 12, ' ');
            row++;
            col++;
        } while (true);
    }

    public void upRight() {
        do {
            screen.putChar(col + 40, row + 12, ball);
            screen.putChar(col + 40, row + 12, ' ');
            row--;
            col++;
        } while (true);
    }

    public void move() {
        int nextrow = row+deltarow;
        int nextcol = col+deltacol;
        char idChar = screen.getChar(nextcol, nextrow);
        if (idChar == screen.getSides()) {

        }
        screen.putChar(col, row, ' ');
        row += deltarow;
        col += deltacol;
        screen.putChar(col, row, ball);

    }

    public void upLeft() {
        do {
            screen.putChar(col + 40, row + 12, ball);
            screen.putChar(col + 40, row + 12, ' ');
            row--;
            col--;
        } while (true);

    }

    public void ballBounce() {

    }


}
