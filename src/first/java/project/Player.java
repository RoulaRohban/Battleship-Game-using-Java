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
abstract class Player implements Serializable
{
     Iplayable currentgame;

    void leavegame()
    {
       currentgame=null; 
    }
    
    void subscribeto(Iplayable game)
    {
        currentgame=game;
    }
    
}
