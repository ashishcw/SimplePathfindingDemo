package org.example.handlers.input;

import org.example.objects.grid.Node;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInputHandler extends KeyAdapter {

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);

        //Set Start Node
        if(e.getKeyCode() == KeyEvent.VK_S){
            Node.createStartNode = true;
            Node.createEndNode = false;
            Node.createClearNode = false;
            Node.createBlockedNode = false;
        }

        //Set End Node
        if(e.getKeyCode() == KeyEvent.VK_E){
            Node.createStartNode = false;
            Node.createEndNode = true;
            Node.createClearNode = false;
            Node.createBlockedNode = false;
        }

        //Set Blocked Node
        if(e.getKeyCode() == KeyEvent.VK_B){
            Node.createStartNode = false;
            Node.createEndNode = false;
            Node.createClearNode = false;
            Node.createBlockedNode = true;
        }

        //Set Clear Node
        if(e.getKeyCode() == KeyEvent.VK_C){
            Node.createStartNode = false;
            Node.createEndNode = false;
            Node.createClearNode = true;
            Node.createBlockedNode = false;
        }
    }
}
