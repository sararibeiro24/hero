package com.up202305327.ldts;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

import static com.googlecode.lanterna.TextCharacter.fromCharacter;

public class Game {
    private Screen screen;
    private Terminal terminal;
    private int x=10;
    private int y=10;
    public Game() {

    try{
        TerminalSize terminalSize = new TerminalSize(40, 20);
        DefaultTerminalFactory terminalFactory = new
                DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
         terminal = terminalFactory.createTerminal();
         screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
    }catch(IOException e){
        e.printStackTrace();
    }
        }
    private void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowUp:
                y -= 1;
                break;
            case ArrowDown:
                y += 1;
                break;
            case ArrowLeft:
                x -= 1;
                break;
            case ArrowRight:
                x += 1;
                break;
            case Character:
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
                System.exit(0);}

        }

    }

private void draw() throws IOException{
    screen.clear();
    screen.setCharacter(x,y, fromCharacter('X')[0]);
    screen.refresh();
}

public void run(){
    try {
        while (true){
            draw();
            KeyStroke key = screen.readInput();
           if (key.getKeyType()==KeyType.EOF) {break;}
           processKey(key);
        }

    } catch (IOException e) {
        throw new RuntimeException(e);
    }


}
}

