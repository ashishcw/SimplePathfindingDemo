package org.example.handlers.input;

import org.example.objects.grid.Node;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInputHandler extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        int x = e.getX();
        int y = e.getY();

        var clickedTile = Node.getClickedTile(new Point(x, y));

        if(clickedTile != null){
//            System.out.println("Row : " + clickedTile.getRow());
//            System.out.println("Col : " + clickedTile.getCol());
//            System.out.println("Node Type : " + clickedTile.getNodeType());
        }else {
            System.out.println("No tile found");
        }

    }
}
