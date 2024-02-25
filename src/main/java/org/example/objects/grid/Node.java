package org.example.objects.grid;

import org.example.constant.Constants;
import org.example.model.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Node extends GameObject {
    public static Node[][]nodes = new Node[Constants.MAX_ROWS][Constants.MAX_COLS];
    public static Node startNode, endNode;

    public static boolean createStartNode = false, createEndNode = false, createBlockedNode = false, createClearNode = false;

    private enum NodeType{
        start,
        end,
        block,
        path,
        none
    }
    private NodeType nodeType = NodeType.none;





    public Node(int xPosParam, int yPosParam, int colParam, int rowParam, NodeType nodeTypeParam) {
        super(xPosParam, yPosParam);
        this.setCol(colParam);
        this.setRow(rowParam);
        this.setSizeWidth(Constants.NODE_SIZE);
        this.setSizeHeight(Constants.NODE_SIZE);
        this.setColor(Color.MAGENTA);
        this.setNodeType(nodeTypeParam);
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        nodeColorScheme();
        g.setColor(this.getColor());

        if(
                this.getNodeType() == NodeType.start
                ||
                this.getNodeType() == NodeType.end
                ||
                this.getNodeType() == NodeType.block
        ){
            g.fillRect(this.getxPos(), this.getyPos(), this.getSizeWidth(), this.getSizeHeight());
        }else {
            g.drawRect(this.getxPos(), this.getyPos(), this.getSizeWidth(), this.getSizeHeight());
            //g.fillRect(this.getxPos(), this.getyPos(), this.getSizeWidth(), this.getSizeHeight());
        }


    }

    private void nodeColorScheme(){
        switch (this.getNodeType()){
            case start -> this.setColor(Color.GREEN);
            case end -> this.setColor(Color.BLACK);
            case none -> this.setColor(Color.WHITE);
            case path -> this.setColor(Color.BLUE);
            case block -> this.setColor(Color.RED);
        }
    }

    public static void createNodeGrid(){
        for(int i = 0; i < Constants.MAX_ROWS; i++){
            for (int j = 0; j < Constants.MAX_COLS; j++){
                nodes[i][j] = new Node(j*Constants.NODE_OFFSET, i*Constants.NODE_OFFSET, j, i, NodeType.none);

                if(i == 0 && j == 0){
                    nodes[i][j].setNodeType(NodeType.start);
                    startNode = nodes[i][j];
                }

                if(i == Constants.MAX_ROWS-1 && j == Constants.MAX_COLS-1){
                    nodes[i][j].setNodeType(NodeType.end);
                    endNode = nodes[i][j];
                }
            }
        }
    }

    public static Node getClickedTile(Point clickedPosition){
        Node clickedNode = null;
//        if(!createStartNode && !createBlockedNode && !createEndNode && !createClearNode){
//
//        }
        var tempList = Arrays.stream(nodes)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());

        for (var tempNode : tempList) {
            if(
                    (clickedPosition.getX() >= tempNode.getxPos()  && clickedPosition.getX() <= tempNode.getxPos()+Constants.NODE_SIZE)
                            && (clickedPosition.getY() >= tempNode.getyPos()  && clickedPosition.getY() <= tempNode.getyPos()+Constants.NODE_SIZE)
            ){
                clickedNode = tempNode;
                break;
            }
        }

        if(createClearNode){
            clickedNode.setNodeType(NodeType.none);
        }

        if(createBlockedNode){
            if(clickedNode.getNodeType() != NodeType.block){
                clickedNode.setNodeType(NodeType.block);
            }else {
                clickedNode.setNodeType(NodeType.none);
            }

        }

        if(createStartNode){
            if(startNode != null){
                startNode.setNodeType(NodeType.none);
            }
            clickedNode.setNodeType(NodeType.start);
            startNode = clickedNode;
        }

        if(createEndNode){
            if(endNode != null){
                endNode.setNodeType(NodeType.none);
            }
            clickedNode.setNodeType(NodeType.end);
            endNode = clickedNode;
        }
        return clickedNode;
    }


}
