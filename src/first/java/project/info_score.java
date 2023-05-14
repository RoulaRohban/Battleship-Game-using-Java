/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package first.java.project;

import java.io.Serializable;

public class info_score implements Serializable {

    private String name;
    private int win, lose;
    private double mark;

    public info_score(String name, int win, int lose) {
        this.name = name;
        this.win = win;
        this.lose = lose;
        this.mark = (win * 100.0) / (win + lose);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }


    public double getMark() {
        return mark;
    }

    public void setMark() {
        this.mark = (win * 100.0) / (win + lose);
    }

}
