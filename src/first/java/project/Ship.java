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
public class Ship implements Serializable
{

    public void setSize(int size)
    {
        this.size = size;
    }

    public void setSquare_of_ship(List<square> square_of_ship)
    {
        this.square_of_ship = square_of_ship;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public void setX1(int x1)
    {
        this.x1 = x1;
    }

    public void setX2(int x2)
    {
        this.x2 = x2;
    }

    public void setY1(int y1)
    {
        this.y1 = y1;
    }

    public void setY2(int y2)
    {
        this.y2 = y2;
    }

    public int getSize()
    {
        return size;
    }

    public List<square> getSquare_of_ship()
    {
        return square_of_ship;
    }

    public String getState()
    {
        return state;
    }

    public int getX1()
    {
        return x1;
    }

    public int getX2()
    {
        return x2;
    }

    public int getY1()
    {
        return y1;
    }

    public int getY2()
    {
        return y2;
    }

    private int size;
    private List<square> square_of_ship = new ArrayList<square>();//square of position ship
    private String state;
    private int x1, x2;//the first and end of x
    private int y1, y2;//the first and end of y

    public Ship(int size, String state, int x1, int y1, int x2, int y2)
    {
        this.state = state;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.size = size;
        for (int i = x1; i <= x2; i++)
            for (int j = y1; j <= y2; j++)
                square_of_ship.add(new square("good", i, j));
    }

    public boolean hit_ship(Battleshipmove goal)
    {
        for (square s : square_of_ship)
            if (s.getX() == goal.getX() && s.getY() == goal.getY())
            {
                s.setStatus("destroyed");
                return true;
            }
        return false;
    }

    public boolean distroy_ship()//calls this method when my goal is hit ship -_-
    {
        //System.out.println("Roulla");
        int count = 0;
        for (square a : square_of_ship)
        {
            //System.out.println("SQUARE : " + a.getStatus());
            if ("destroyed".equals(a.getStatus()))
                count++;
        }
        if (count == square_of_ship.size())
            return true;
        else
            return false;
    }

    void DrawSquare()
    {
//        System.out.println("*****************************");
//        for (square sq : square_of_ship)
//            System.out.println(sq.getX() + " " + sq.getY() + " : " + sq.getStatus());
//        System.out.println("*****************************");

    }

}
