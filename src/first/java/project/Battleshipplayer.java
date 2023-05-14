/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package first.java.project;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author RoR
 */
public abstract class Battleshipplayer extends Player implements Serializable {

    //data of player
    private Grid special_grid = new Grid("special");
    private Grid temp_grid = new Grid("temp");
    protected Battleshipmove current_move;
    String Result;

    //ship's player//for incapsulation
    public void allocation_ship(Ship ship1) {
        special_grid.add_ship(ship1);
    }

    public void setCurrent_move(Battleshipmove current_move) {
        this.current_move = current_move;
    }

    public Battleshipmove getCurrent_move() {
        return current_move;
    }

    public Grid getSpecial_grid() {
        return special_grid;
    }

    public void setSpecial_grid(Grid special_grid) {
        this.special_grid = special_grid;
    }

    public void setTemp_grid(Grid temp_grid) {
        this.temp_grid = temp_grid;
    }

    public Grid getTemp_grid() {
        return temp_grid;

    }

    public Battleshipplayer()//defualt constructer
    {
        temp_grid = new Grid("temp");
        special_grid = new Grid("special");
    }

    public abstract Battleshipmove getnextmove();
    
    public void setResult(String Result)
    {
        this.Result = Result;
    }

    public Battleshipmoveres acceptplayermove(Battleshipmove move)//sho sar 3nd al5asem
    {
        String state = special_grid.Roro(move);
        return new Battleshipmoveres(state);
    }

    public void notify_player(Battleshipmoveres res) {
        if ("failing".equals(res.getResult())) {
            temp_grid.setsquare(new square("water", current_move.getX(), current_move.getY()));
        } else if ("destroyed shoot".equals(res.getResult())) {
            temp_grid.setsquare(new square("destroyed shoot", current_move.getX(), current_move.getY()));
        } else {
            temp_grid.setsquare(new square("part destroyed", current_move.getX(), current_move.getY()));
        }
    }

    public boolean checkship(Ship ship1) {
        return special_grid.check_grid(ship1);
    }

    public String get_state_sq(int x, int y) {
        return temp_grid.get_status_square(x, y);
    }

    public String get_state_sq_special(int x, int y) {
        return special_grid.get_status_square(x, y);
    }

    public void add_ship(Ship ship1) {
        special_grid.add_ship(ship1);
    }
    
     public List<Ship> getListship() {
        return special_grid.listship;
    }
     
     
    
    

    void DrawShip() {
        this.special_grid.DrawShip();
    }

    //// SHOOOOOOOOOOOOOOOOOOOOOOOOOOOOOT *////s
    void addshoot() {
        special_grid.addshoot(this);
    }

    String getResult() {
        return Result;
    }
}
