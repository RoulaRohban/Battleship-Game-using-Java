/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package first.java.project;

import java.io.Serializable;

/**
 *
 * @author RoR
 */
public class square implements Serializable , Cloneable 
{
    private String status;//water or destroy part or destroy all of part or unknown
    private int x,y;

    public square(String status, int x, int y) 
    {
        this.status = status;
        this.x = x;
        this.y = y;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    public String getStatus() {
        return status;
    }

    public int getX() {
        return x;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }
    
    
}
