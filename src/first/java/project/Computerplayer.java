/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package first.java.project;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author RoR
 */
public  class Computerplayer extends Battleshipplayer implements Serializable
{

    protected Abstractcomputerstrategy comstra;

    public Computerplayer()
    {
        super();
    }

    public Abstractcomputerstrategy getComstra()
    {
        return comstra;
    }

    public void setship(Ship news)
    {
        add_ship(news);
    }
    @Override
    public Battleshipmove getnextmove()
    {
    //    System.out.println("*********************");
        return this.comstra.getnextmove();
    }
}
