package org.example;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class BouncyBallGameMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        Screen screen = new Screen();
        Player player = new Player(screen);
        screen.border();
//        Player player = new Player();
//        player.Player();

    }
}