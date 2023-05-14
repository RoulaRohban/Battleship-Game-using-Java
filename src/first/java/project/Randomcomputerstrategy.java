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
public class Randomcomputerstrategy extends Abstractcomputerstrategy implements Serializable
{

    public Battleshipmove getnextmove()
    {
        Random ran = new Random();
        int x = ran.nextInt(10);
        int y = ran.nextInt(10);
        Battleshipmove random_move = new Battleshipmove(x, y);
        return random_move;
    }
}
