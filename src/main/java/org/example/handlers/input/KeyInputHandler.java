package org.example.handlers.input;

import org.example.objects.grid.Node;
import org.example.pathfinding.AStarPathfinding;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class KeyInputHandler extends KeyAdapter {
    private AStarPathfinding aStarPathfinding = new AStarPathfinding();

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
            for(int i = 0; i < Node.nodes.length; i++){
                for(int j = 0; j < Node.nodes[i].length; j++){
                    Node.nodes[i][j].setNodeType(Node.NodeType.none);
                }
            }
        }

        //random map generate
        if(e.getKeyCode() == KeyEvent.VK_R){
            Random rnd = new Random();
            for(int i = 0; i < Node.nodes.length; i++){
                for(int j = 0; j < Node.nodes[i].length; j++){
                    if(rnd.nextInt(0, 10) > 8){
                        Node.nodes[i][j].setNodeType(Node.NodeType.block);
                    }
                }
            }
        }


        //pathfinding test
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            var allFoundNodes = aStarPathfinding.search(Node.startNode, Node.endNode);
            //var allFoundNodes = aStarPathfinding.executeThreadExternally(Node.startNode, Node.endNode);

            if(allFoundNodes == null || allFoundNodes.size() <= 0){
                System.out.println("No path found");
            }else{

                for (var nodesFound:allFoundNodes) {
                    if(
                            nodesFound.getNodeType() == Node.NodeType.start
                                    ||
                                    nodesFound.getNodeType() == Node.NodeType.end
                                    ||
                                    nodesFound.getNodeType() == Node.NodeType.block
                    ){
                        continue;
                    }
                    //nodesFound.setNodeType(Node.NodeType.path);
                    Node.nodes[nodesFound.getRow()][nodesFound.getCol()].setNodeType(Node.NodeType.path);
                }
            }

        }
    }
}
