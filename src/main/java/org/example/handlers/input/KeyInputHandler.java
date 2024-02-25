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
            Node.createStartNode = !Node.createStartNode;
            Node.createEndNode = false;
            Node.createClearNode = false;
            Node.createBlockedNode = false;
            System.out.println("Create Start Node : " + Node.createStartNode);
        }

        //Set End Node
        if(e.getKeyCode() == KeyEvent.VK_E){
            Node.createStartNode = false;
            Node.createEndNode = !Node.createEndNode;
            Node.createClearNode = false;
            Node.createBlockedNode = false;
            System.out.println("Create End Node : " + Node.createEndNode);
        }

        //Set Blocked Node
        if(e.getKeyCode() == KeyEvent.VK_B){
            Node.createStartNode = false;
            Node.createEndNode = false;
            Node.createClearNode = false;
            Node.createBlockedNode = !Node.createBlockedNode;
            System.out.println("Create Blocked Node : " + Node.createBlockedNode);
        }

        //Set Clear Node
        if(e.getKeyCode() == KeyEvent.VK_C){
            Node.createStartNode = false;
            Node.createEndNode = false;
            Node.createClearNode = !Node.createClearNode;
            Node.createBlockedNode = false;
            System.out.println("Clear Node : " + Node.createClearNode);
        }
    }
}
