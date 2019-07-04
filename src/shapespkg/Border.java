/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapespkg;

import java.awt.BasicStroke;


/**
 *
 * @author otmangx
 */
public class Border {
    public static Object get(String borderType, float borderSize){
        
         if (borderType.equals("solid")) {
            return new BasicStroke(borderSize);
        }
        else if (borderType.equals("dashed")) {
            return new BasicStroke(borderSize, BasicStroke.CAP_BUTT, 
                            BasicStroke.JOIN_MITER, 10.0f,new float[]{10.0f},0.0f);
        }
        else {
            return null;
        }
    }
}
