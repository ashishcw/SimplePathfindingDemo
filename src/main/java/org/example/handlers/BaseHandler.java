package org.example.handlers;

import org.example.model.GameObject;

import java.awt.*;
import java.util.ArrayList;

public class BaseHandler{

    public ArrayList<GameObject>allGameObjectsList = new ArrayList<>();

    public BaseHandler() {
    }

    public void tick(){
        if(this.allGameObjectsList.size() > 0){
            for(int i = 0; i < this.allGameObjectsList.size(); i++){
                this.allGameObjectsList.get(i).tick();
            }
        }
    }

    public void render(Graphics g){
        if(this.allGameObjectsList.size() > 0){
            for(int i = 0; i < this.allGameObjectsList.size(); i++){
                this.allGameObjectsList.get(i).render(g);
            }
        }
    }


    public void addObjectToList(GameObject tempObject){
        if(!this.allGameObjectsList.contains(tempObject)){
            this.allGameObjectsList.add(tempObject);
        }
    }

    public void removeObjectFromList(GameObject tempObject){
        if(this.allGameObjectsList.contains(tempObject)){
            this.allGameObjectsList.remove(tempObject);
        }
    }
}
