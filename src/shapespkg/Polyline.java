/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapespkg;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.*;

/**
 *
 * @author otmangx
 */
public class Polyline implements Shape, Cloneable, Serializable, ShapeRender{
    public static int nbr=0;
    private int mynumber;
    private boolean visible=true;

    public int npoints;

    public int[] xpoints;

    public int[] ypoints;

    private Color cl;
    /**
     * Creates an empty Polyline2D.
     */
    public Polyline(Point... p) {
        xpoints = new int[1];
        ypoints = new int[1];
        for (int i=0;i<p.length;i++) {
            addPoint(p[i]);
        }
        cl = Color.BLACK;
        nbr++;
        mynumber = nbr ;
        
    }

    public Polyline(int[] xpoints, int[] ypoints, int npoints) {
        if (npoints > xpoints.length || npoints > ypoints.length) {
            throw new IndexOutOfBoundsException("npoints > xpoints.length || npoints > ypoints.length");
        }
        this.npoints = npoints;
        this.xpoints = new int[npoints+1];   // make space for one more to close the polyline
        this.ypoints = new int[npoints+1];   // make space for one more to close the polyline
        System.arraycopy(xpoints, 0, this.xpoints, 0, npoints);
        System.arraycopy(ypoints, 0, this.ypoints, 0, npoints);
        nbr++;
        mynumber = nbr ;
    }



    //Getters
    public Color getColor() {
        return this.cl;
    }
    
    public void setColor(Color color) {
        cl = color;
    }
   

    public Object clone() {
        Polyline pol = new Polyline();
        for (int i = 0; i < npoints; i++) {
            pol.addPoint(xpoints[i], ypoints[i]);
        }
        return pol;
    }



    public void addPoint(Point2D p) {
        addPoint((int)p.getX(), (int)p.getY());
    }


    public void addPoint(int x, int y) {
        if (npoints == xpoints.length) {
            int[] tmp;

            tmp = new int[npoints * 2];
            System.arraycopy(xpoints, 0, tmp, 0, npoints);
            xpoints = tmp;

            tmp = new int[npoints * 2];
            System.arraycopy(ypoints, 0, tmp, 0, npoints);
            ypoints = tmp;
        }
        xpoints[npoints] = x;
        ypoints[npoints] = y;
        npoints++;
    }

    public void paint(Graphics2D g) {
        //Graphics2D g2 = (Graphics2D) g;
        g.setColor(cl);
        g.drawPolyline(xpoints, ypoints, npoints);
    }
    
    public String getName(){
        return "PolyLine"+mynumber;
    }
    @Override
    public Rectangle getBounds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle2D getBounds2D() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(double x, double y) {
        return false;
    }

    @Override
    public boolean contains(Point2D p) {
        return false;
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean intersects(Rectangle2D r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(Rectangle2D r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Point p1, Point p2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    visible = x;    
    }

}
