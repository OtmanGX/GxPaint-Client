/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapespkg;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
/**
 *
 * @author otmangx
 */
public class Rrectangle extends Rectangle implements ShapeRender, Bordered{
    
    private Color cl;
    private boolean visible=true;
    public static int nbr=0;
    private int mynumber;
    
    float borderSize = 1.0f;
    transient Stroke border = null;
    String borderType = "solid";
    Color borderColor = Color.BLUE;
    
    public Rrectangle(Point p1, Point p2) {
        update(p1, p2);
        this.cl = Color.BLACK ;
        nbr++;
        mynumber = nbr ;
    }
    public Rrectangle(Point p1, Point p2, Color cl) {
        this(p1, p2);
        this.cl = cl ;
        
    }
    
    public void update(Point p1, Point p2) {
        super.setBounds(Math.min(p1.x, p2.x), 
                        Math.min(p1.y, p2.y), 
                        Math.abs(p2.x-p1.x), 
                        Math.abs(p2.y-p1.y));
        
    }
    
    
    //Getters
    public Color getColor() {
        return this.cl;
    }

    public void setColor(Color color) {
        cl = color;
    }
    
    public void setVisible(boolean x){
        visible = x;
    }
    
    public void paint(Graphics2D g) {
        
        g.setPaint(cl);
        g.fill(this);
        //draw border
        g.setPaint(borderColor);
        border = (Stroke) Border.get(borderType, borderSize) ;
        g.setPaint(borderColor);
        if (border!=null) {
            g.setStroke(border);
            g.draw(this);
        }
        g.setStroke(new BasicStroke(1.0f));
        
    }
    
    public String getName(){
        return "Rect"+mynumber;
    }
    
    @Override
    public void updateLocation(int x, int y) {
        super.setLocation((int)getX()+x, (int)getY()+y);
        
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setBorderSize(float n) {
        borderSize = n ;
    }

    @Override
    public void setBorderType(String s) {
        borderType = s;
    }

    @Override
    public void setBorderColor(Color color) {
        borderColor = color ;
    }

    @Override
    public float getBorderSize() {
        return borderSize;
    }

    @Override
    public String getBorderType() {
        return borderType;
    }

    @Override
    public Color getBorderColor() {
        return borderColor;
    }
}
