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
public class Randomcomputerplayer extends Computerplayer implements Serializable
{

    public Randomcomputerplayer()
    {
        super();
        this.comstra = new Randomcomputerstrategy();
    }

}
