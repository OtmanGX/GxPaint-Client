package shapespkg;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author otmangx
 */
public class Ellipse extends Ellipse2D.Float implements ShapeRender, Bordered{

    private Color cl;
    private boolean visible=true;
    public static int nbr=0;
    private int mynumber;
    
    float borderSize = 1.0f;
    transient Stroke border;
    String borderType = "solid";
    Color borderColor = Color.BLUE;
    
    public Ellipse(Point p1, Point p2) {
        update(p1, p2);
        this.cl = Color.BLACK ;
        nbr++;
        mynumber = nbr ;
        
    }
    
    public Ellipse(Point p1, Point p2, Color cl) {
        this(p1, p2);
        this.cl = cl ;
        
        
    }
    
    public void update(Point p1, Point p2) {
        super.setFrame(Math.min(p1.x, p2.x), 
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
        border = (Stroke) Border.get(borderType, borderSize) ;
        g.setPaint(borderColor);
        if (border!=null) {
            g.setStroke(border);
            g.draw(this);
        }
        g.setStroke(new BasicStroke(1.0f));
    }
    
    public String getName(){
        return "Ellipse"+mynumber;
    }
    
    @Override
    public void updateLocation(int x, int y) {
        this.x = (int)getX()+x;
        this.y = (int)getY()+y;
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
