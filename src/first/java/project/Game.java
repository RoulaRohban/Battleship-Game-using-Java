/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package first.java.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RoR
 */
abstract class Game implements Iplayable, Serializable {

    protected List<Player> players = new ArrayList<Player>();
    protected prev_games information;

    @Override
    public void subscribe(Player Roulla) {
        //To change body of generated methods, choose Tools | Templates.

        players.add(Roulla);//add player to list of player 
    }

    @Override
    public void leave(Player Roulla) {
        //To change body of generated methods, choose Tools | Templates.
        players.remove(Roulla);
    }

    abstract void start();

    abstract void stop();
}
