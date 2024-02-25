package org.example.objects.grid;

import org.example.constant.Constants;
import org.example.model.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Node extends GameObject {
    public static Node[][]nodes = new Node[Constants.MAX_ROWS][Constants.MAX_COLS];

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
                }

                if(i == Constants.MAX_ROWS-1 && j == Constants.MAX_COLS-1){
                    nodes[i][j].setNodeType(NodeType.end);
                }
            }
        }
    }


}
