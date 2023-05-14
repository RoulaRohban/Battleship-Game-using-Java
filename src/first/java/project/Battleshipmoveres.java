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
public class Battleshipmoveres implements Serializable
{
   private String result;
    //failing ,destroyed part , destroyed ship , destroyed all ship

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public Battleshipmoveres(String result)
    {
        this.result = result;
    }
    
    
}
