/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapespkg;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;



public class Line extends Line2D.Float implements ShapeRender{
    public static int nbr=0;
    private int mynumber;
    private boolean visible=true;
    private Color cl;
    //int x1, y1, x2, y2;
    public Line(Point p1, Point p2){
        super(p1, p2);
        this.cl = Color.BLACK ;
        nbr++;
        mynumber = nbr ;
        
    }
    
    public Line(Point p1, Point p2, Color color){
        this(p1, p2);
        this.cl = Color.BLACK ;
        this.cl = color;
        
    }
    
    //Getters
    public Color getColor() {
        return this.cl;
    }

    public void setColor(Color color) {
        cl = color;
    }
    
    
    public void paint(Graphics2D g) {
        g.setColor(cl);
        g.draw(this);

    }

    public String getName(){
        return "Line"+mynumber;
    }
    
    @Override
    public void update(Point p1, Point p2) {
        super.setLine(p1, p2);
    }

    @Override
    public void updateLocation(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean x) {
        visible = x ;
    }


    
}
