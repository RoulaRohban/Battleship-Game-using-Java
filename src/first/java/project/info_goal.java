package first.java.project;

import java.io.Serializable;

public class info_goal implements Serializable {

    Battleshipmove moving;
    int x, y;
    String last_time, result;
    Battleshipmoveres res;

    public info_goal(Battleshipmove moving, String last_time, Battleshipmoveres res) {
        this.moving = moving;
        this.x = this.moving.getX() + 1;
        this.y = this.moving.getY() + 1;
        this.last_time = last_time;
        this.res = res;
        this.result = this.res.getResult();

    }

    public Battleshipmove getMoving() {
        return moving;
    }

    public void setMoving(Battleshipmove moving) {
        this.moving = moving;
        x = this.moving.getX();
        y = this.moving.getY();
    }

    public String getLast_time() {
        return last_time;
    }

    public void setLast_time(String last_time) {
        this.last_time = last_time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Battleshipmoveres getRes() {
        return res;
    }

    public void setRes(Battleshipmoveres res) {
        this.res = res;
    }

}
