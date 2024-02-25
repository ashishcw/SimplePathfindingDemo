package org.example.pathfinding;

import org.example.constant.Constants;
import org.example.objects.grid.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AStarPathfinding {

    private static final int MOVE_STRAIGHT_COST = 10;
    private static final int MOVE_DIAGONAL_COST = 14;

    private ArrayList<Node>openList = new ArrayList<>();
    private ArrayList<Node>closedList = new ArrayList<>();

    public List<Node> search(Node startNode, Node endNode){
        //1. Add startNode to open list & set all nodes default value to max int
        this.openList.add(startNode);

        for(int i = 0; i < Node.nodes.length; i++){
            for(int j = 0; j < Node.nodes[i].length; j++){
                Node.nodes[i][j].setgCost(Integer.MAX_VALUE);
                Node.nodes[i][j].setfCost(Node.nodes[i][j].getgCost() + Node.nodes[i][j].gethCost());
                Node.nodes[i][j].setCameFromNode(null);
            }
        }

        startNode.setgCost(0);
        startNode.sethCost(calculateDistanceCost(startNode, endNode));
        startNode.setfCost(startNode.getgCost() + startNode.gethCost());


        //2. main pathfinding iteration
        while (this.openList.size() > 0){
            Node currentNode = getLowestFCostNode();

            if(currentNode == endNode){
                //reached goal
                return calculatePathNode(endNode);
            }

            this.openList.remove(currentNode);
            this.closedList.add(currentNode);

            //3. cycle throguh all the neighbours
            for (var neighbourNode : getNeighbourList(currentNode)) {

                //check if the neighbour node is already in the closed list
                if(this.closedList.contains(neighbourNode)){
                    continue;
                }

                int tentativeGCost = currentNode.getgCost() + calculateDistanceCost(currentNode, neighbourNode);
                if(tentativeGCost < neighbourNode.getgCost()){
                    neighbourNode.setCameFromNode(currentNode);
                    neighbourNode.setgCost(tentativeGCost);
                    neighbourNode.sethCost(calculateDistanceCost(neighbourNode, endNode));
                    neighbourNode.setfCost(neighbourNode.getgCost() + neighbourNode.gethCost());

                    if(!this.openList.contains(neighbourNode)){
                        this.openList.add(neighbourNode);
                    }
                }
            }
        }

        //out of all the possible nodes and we did not find the path
        System.out.println("No path found");
        return null;


    }

    private List<Node> getNeighbourList(Node currentNode){
        List<Node>allNeighboursList = new ArrayList<>();

        //Left
        if(currentNode.getCol()-1 >= 0){
            //Left Node
            allNeighboursList.add(Node.nodes[currentNode.getRow()][currentNode.getCol()-1]);

            //Left Down
            if(currentNode.getRow() - 1 >= 0){
                allNeighboursList.add(Node.nodes[currentNode.getRow()-1][currentNode.getCol()-1]);
            }

            //Left Up
            if(currentNode.getRow() + 1 < Constants.MAX_ROWS){
                allNeighboursList.add(Node.nodes[currentNode.getRow()+1][currentNode.getCol()-1]);
            }
        }


        //Right
        if(currentNode.getCol()+1 < Constants.MAX_COLS){
            //Right Node
            if(Node.nodes[currentNode.getRow()][currentNode.getCol()+1].getNodeType() != Node.NodeType.block){
                allNeighboursList.add(Node.nodes[currentNode.getRow()][currentNode.getCol()+1]);
            }


            //Right Up
            if(currentNode.getRow() - 1 >= 0){
                if(Node.nodes[currentNode.getRow()-1][currentNode.getCol()+1].getNodeType() != Node.NodeType.block){
                    allNeighboursList.add(Node.nodes[currentNode.getRow()-1][currentNode.getCol()+1]);
                }
            }


            //Right Down
            if(currentNode.getRow() + 1 < Constants.MAX_ROWS){
                if(Node.nodes[currentNode.getRow()+1][currentNode.getCol()+1].getNodeType() != Node.NodeType.block){
                    allNeighboursList.add(Node.nodes[currentNode.getRow()+1][currentNode.getCol()+1]);
                }

            }
        }

        //Down
        if(currentNode.getRow() + 1 < Constants.MAX_ROWS){
            if(Node.nodes[currentNode.getRow()+1][currentNode.getCol()].getNodeType() != Node.NodeType.block){
                allNeighboursList.add(Node.nodes[currentNode.getRow()+1][currentNode.getCol()]);
            }

        }

        //Up
        if(currentNode.getRow() - 1 >= 0){
            if(Node.nodes[currentNode.getRow()-1][currentNode.getCol()].getNodeType() != Node.NodeType.block){
                allNeighboursList.add(Node.nodes[currentNode.getRow()-1][currentNode.getCol()]);
            }

        }

        return allNeighboursList;
    }

    private int calculateDistanceCost(Node a, Node b){
        int xDistance = Math.abs(a.getxPos() - b.getxPos());
        int yDistance = Math.abs(a.getyPos() - b.getyPos());
        int remaining = Math.abs(xDistance-yDistance);
        return MOVE_DIAGONAL_COST * Math.min(xDistance, yDistance) + MOVE_STRAIGHT_COST * remaining;
    }

    private Node getLowestFCostNode(){
        Node lowestFCostNode = this.openList.get(0);
        for(int i = 0; i < this.openList.size(); i++){
            if(this.openList.get(i).getfCost() < lowestFCostNode.getfCost()){
                lowestFCostNode = this.openList.get(i);
            }
        }
        return lowestFCostNode;
    }

    private List<Node> calculatePathNode(Node endNode){
        List<Node> path = new ArrayList<>();
        path.add(endNode);
        var currentNode = endNode;

        while (currentNode.getCameFromNode() != null){
            path.add(currentNode.getCameFromNode());
            currentNode = currentNode.getCameFromNode();
        }
        Collections.reverse(path);
        return path;
    }



}
