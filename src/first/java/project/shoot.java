package first.java.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class shoot implements Serializable{

    private int x, y;
    Battleshipplayer owner;
    private String status;

    public int getX() {
        return x;
    }

    public void setOwner(Battleshipplayer owner) {
        this.owner = owner;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Battleshipplayer getOwner() {
        return owner;
    }

    public String getStatus() {
        return status;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public shoot(Battleshipplayer owner) {
        this.owner = owner;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ("water".equals(owner.get_state_sq_special(i, j))) {
                    this.x = i;
                    this.y = j;
                    this.status="good";
                }
            }
        }
    }

    public void move() {
        int dx[] = {1, -1, 0, 0};
        int dy[] = {0, 0, 1, -1};
        List<square> ListSquer = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int u, v;
            u = this.x + dx[i];
            v = this.y + dy[i];
            if ((u >= 0 && u < 10) && (v >= 0 && v < 10) && "water".equals(owner.get_state_sq_special(u, v))) {
                {
                    ListSquer.add(new square("", u, v));
                }
            }
        }
        if (ListSquer.size() > 0) {
            Random rand = new Random();
            int a = rand.nextInt(ListSquer.size());
            int NextX = ListSquer.get(a).getX();
            int NextY = ListSquer.get(a).getY();
            owner.getSpecial_grid().Moveshoot(new Battleshipmove(NextX, NextY));
            this.x = NextX;
            this.y = NextY;
        }
    }
    public boolean hit_shoot(Battleshipmove goal)
    {
            if (x == goal.getX() && y == goal.getY())
            {
                status="destroyed";
                return true;
            }
        return false;
    }

}
