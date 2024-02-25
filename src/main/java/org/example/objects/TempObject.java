package org.example.objects;

import org.example.model.GameObject;

import java.awt.*;

public class TempObject extends GameObject {
    public TempObject(int xPosParam, int yPosParam) {
        super(xPosParam, yPosParam);
        this.setSizeWidth(16);
        this.setSizeHeight(16);
        this.setColor(Color.CYAN);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(this.getColor());
        g.fillRect(this.getxPos(), this.getyPos(), this.getSizeWidth(), this.getSizeHeight());
    }
}
