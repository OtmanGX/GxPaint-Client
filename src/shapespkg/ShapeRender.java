/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapespkg;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author otmangx
 */
public interface ShapeRender {
    public void paint(Graphics2D g);
    public Color getColor();
    public void setColor(Color color);
    
    public void update(Point p1, Point p2);
    public void updateLocation(int x, int y);
    public void setVisible(boolean x);
    public boolean isVisible();
    public String getName();
    public Object clone();
}
