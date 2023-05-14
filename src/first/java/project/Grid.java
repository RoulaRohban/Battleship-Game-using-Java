/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package first.java.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RoR
 */
public class Grid implements Serializable {

    String type_grid;
    square grid[][] = new square[10][10];
    List<Ship> listship = new ArrayList<>();
    shoot myshoot; //// new Shoot

    public Grid Copy() {
        Grid temp = new Grid("temp");

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                try {
                    temp.grid[i][j] = (square) this.grid[i][j].clone();
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(Grid.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return temp;
    }

    Grid(String type_grid)//constructer=init
    {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ("temp".equals(type_grid)) {
                    grid[i][j] = new square("unknown", i, j);//status is unknown or water because not goals yet
                } else {
                    grid[i][j] = new square("water", i, j);
                }
            }
        }
    }

    public String get_status_square(int x, int y)//return status of square
    {
        return grid[x][y].getStatus();
    }

    public boolean check_grid(Ship ship1) {
        for (int i = ship1.getX1(); i <= ship1.getX2(); i++) {
            for (int j = ship1.getY1(); j <= ship1.getY2(); j++) {
                if (i >= 10 || j >= 10 || "part good".equals(grid[i][j].getStatus())) {
                    return false;
                }
            }
        }
        return true;

    }

    public void add_ship(Ship ship1) {
        for (int i = ship1.getX1(); i <= ship1.getX2(); i++) {
            for (int j = ship1.getY1(); j <= ship1.getY2(); j++) {
                grid[i][j].setStatus("part good");
            }
        }
        listship.add(ship1);
    }

    public void setListship(List<Ship> listship) {
        this.listship = listship;
    }

    boolean destroyallship() {
        int count = 0;
        for (Ship s : listship) {
            if (s.distroy_ship()) {
                count++;
            }
        }
        if (count == listship.size()) {
            return true;
        } else {
            return false;
        }
    }

    String Roro(Battleshipmove move)//status of battleshipresult
    {

        String status = "failing";

        grid[move.getX()][move.getY()].setStatus("destroyed");
        if (myshoot.hit_shoot(move)) {
            //System.out.println("11111111111111111111111");
            status = "destroyed shoot";
            return status;
        }
        for (Ship s : listship) {
            if (s.hit_ship(move))//destroyed
            {
                status = "destroyed part";
                if (s.distroy_ship()) {
                    status = "destroyedship";
                }
                if (destroyallship()) {
                    status = "destroyd all ship";
                }
                break;
            }
        }
        return status;
    }

    public void setsquare(square s) {
        grid[s.getX()][s.getY()] = s;
    }

    public void DrawShip() {
        for (Ship u : listship) {
            u.DrawSquare();
        }
    }

    //// SHOOOOOOOOOOOOOOOOOOOOOOOOOOOOOT *////
    void setshoot(shoot myshoot) {
        this.myshoot = myshoot;
        grid[myshoot.getX()][myshoot.getY()].setStatus("shoot");
    }

    void Moveshoot(Battleshipmove battleshipmove) {

        grid[myshoot.getX()][myshoot.getY()].setStatus("water");
        grid[battleshipmove.getX()][battleshipmove.getY()].setStatus("shoot");

        myshoot.setX(battleshipmove.getX());
        myshoot.setY(battleshipmove.getY());

    }

    void addshoot(Battleshipplayer owner) {
        myshoot = new shoot(owner);
        setshoot(myshoot);
    }

    shoot getshoot() {
        return myshoot;
    }

}
