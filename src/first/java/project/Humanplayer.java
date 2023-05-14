/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package first.java.project;
import java.io.Serializable;
import java.util.Scanner;
/**
 *
 * @author RoR
 */
public class Humanplayer extends Battleshipplayer implements Serializable
{

    public Humanplayer(Grid special_grid, Grid temp_grid, Battleshipmove current_move) {
       /* this.special_grid = special_grid;
        this.temp_grid = temp_grid;*/
        temp_grid=new Grid ("temp");
        special_grid=new Grid ("special");
        this.current_move=current_move;
    }
    

    Humanplayer()
    {
        super();
    }

    @Override
    void leavegame() 
    {
        
    }
   public Battleshipmove getnextmove()
    {
        Scanner s=new Scanner (System.in);
        System.out.println("Enter x of your goal:");
        int x=s.nextInt();
        System.out.println("Enter y of your goal:");
        int y=s.nextInt();
        Battleshipmove goal=new Battleshipmove(x,y);
        return goal;
    }
    }
