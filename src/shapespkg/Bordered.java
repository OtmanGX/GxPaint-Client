/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapespkg;

import java.awt.Color;

/**
 *
 * @author otmangx
 */
public interface Bordered {
    public void setBorderSize(float n);
    public float getBorderSize();
    public void setBorderType(String s);
    public String getBorderType();
    public void setBorderColor(Color color);
    public Color getBorderColor();
}
