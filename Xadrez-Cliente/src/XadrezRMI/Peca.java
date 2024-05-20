/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package XadrezRMI;

import java.io.Serializable;

/**
 *
 * @author raulm
 */
public class Peca implements Serializable {
    private int color, type;
    
    Peca(int color){
        this.color = color;
        this.type = type;
    }

    public int getColor() {
        return color;
    }

    public int getType() {
        return type;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    
    
}
