/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package first.java.project;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author RoR
 */
public class prev_games implements Serializable {

    private int id_game;
    private String Name_Player;
    private String strategy_computer;//smart or random
    private Grid computergrid, humangrid;//for gets allocation ship
    private List<info_goal> info_goals_human = new ArrayList<info_goal>();//goal and result goal
    private List<info_goal> info_goals_computer = new ArrayList<info_goal>();//goal and result goal
    // private String End_result;
    private String winner;
    private String first_time, last_time;

    public prev_games() {
    }

    public prev_games(int id_game, String Name_Player, String strategy_computer, Grid computergrid, Grid humangrid, String winner, String first_time, String last_time) {
        this.id_game = id_game;
        this.Name_Player = Name_Player;
        this.strategy_computer = strategy_computer;
        this.computergrid = computergrid;
        this.humangrid = humangrid;
        //this.End_result = End_result;
        this.winner = winner;
        this.first_time = first_time;
        this.last_time = last_time;
    }

    public String getName_Player() {
        return Name_Player;
    }

    public void setName_Player(String Name_Player) {
        this.Name_Player = Name_Player;
    }

    public int getId_game() {
        return id_game;
    }

    public String getName_player() {
        return Name_Player;
    }

    public String getStrategy_computer() {
        return strategy_computer;
    }

    public Grid getComputergrid() {
        return computergrid;
    }

    public Grid getHumangrid() {
        return humangrid;
    }

    public List<info_goal> getInfo_goals_human() {
        return info_goals_human;
    }

    public List<info_goal> getInfo_goals_computer() {
        return info_goals_computer;
    }

    /* public String getEnd_result() {
        return End_result;
    }*/
    public String getWinner() {
        return winner;
    }

    public String getFirst_time() {
        return first_time;
    }

    public String getLast_time() {
        return last_time;
    }

    public void setId_game(int id_game) {
        this.id_game = id_game;
    }

    public void setName_player(String Name_Player) {
        this.Name_Player = Name_Player;
    }

    public void setStrategy_computer(String strategy_computer) {
        this.strategy_computer = strategy_computer;
    }

    public void setComputergrid(Grid computergrid) {
        this.computergrid = computergrid;
    }

    public void setHumangrid(Grid humangrid) {
        this.humangrid = humangrid;
    }

    public void setInfo_goals_human(List<info_goal> info_goals_human) {
        this.info_goals_human = info_goals_human;
    }

    public void setInfo_goals_computer(List<info_goal> info_goals_computer) {
        this.info_goals_computer = info_goals_computer;
    }
    
    public void addInfo_goals_human(info_goal x)
    {
        this.info_goals_human.add(x);
    }
        public void addInfo_goals_computer(info_goal x)
    {
        this.info_goals_computer.add(x);
    }

    /* public void setEnd_result(String End_result) {
        this.End_result = End_resul
        t;
    }*/
    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setFirst_time(Date first_time) {
        //** for convert from date to string ***///
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.first_time = formatter.format(first_time);
    }

    public void setLast_time(Date last_time) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.last_time = formatter.format(last_time);
    }
}
