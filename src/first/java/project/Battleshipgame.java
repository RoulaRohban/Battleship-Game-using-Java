package first.java.project;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Battleshipgame extends Game implements Serializable {

    Battleshipplayer currentplayer;

    boolean Rungame(Battleshipmove move, String current) {
        Battleshipplayer nextplayer;
        Battleshipmove Move;
        if ("human".equals(current)) {
            currentplayer = (Battleshipplayer) players.get(0);
            nextplayer = (Battleshipplayer) players.get(1);

            Move = move;
        } else {
            currentplayer = (Battleshipplayer) players.get(1);

            nextplayer = (Battleshipplayer) players.get(0);
            while (true) {
                Move = currentplayer.getnextmove();
                if ("unknown".equals(currentplayer.get_state_sq(Move.getX(), Move.getY()))) {
                    break;
                }
            }
        }

        currentplayer.setCurrent_move(Move);
        Battleshipmoveres AttackResult = nextplayer.acceptplayermove(Move);
        ((currentplayer)).setResult(AttackResult.getResult());

        if (currentplayer instanceof Humanplayer) {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            information.addInfo_goals_human(new info_goal(Move, formatter.format(new Date()), AttackResult));
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            information.addInfo_goals_computer(new info_goal(Move, formatter.format(new Date()), AttackResult));
        }

        if ("destroyd all ship".equals(AttackResult.getResult())) {
            return true;
        }
        currentplayer.notify_player(AttackResult);

        if ("shoot".equals(AttackResult.getResult())) {

            int dx[] = {1, -1, 1, -1, 0, 0, 1, -1};
            int dy[] = {1, -1, -1, 1, 1, -1, 0, 0};
            for (int i = 0; i < 8; i++) {
                int nx = move.getX() + dx[i];
                int ny = move.getY() + dy[i];
                if ((nx >= 0 && nx < 10) && (ny >= 0 && ny < 10)) //         this.temp_Grid.setSquare(square);
                {
                    AttackResult = nextplayer.acceptplayermove(new Battleshipmove(nx, ny));
                    if ("destroyd all ship".equals(AttackResult.getResult())) {
                        stop();
                        return true;
                    }
                    currentplayer.notify_player(AttackResult);
                }

            }

        }
        return false;

    }

    public void result(Battleshipplayer p) {
        if (p instanceof Humanplayer)//if type current player is human player
        {
            System.out.println("you are win");
        } else {
            System.out.println("you are lose");
        }
    }

    @Override
    void stop() {
        Battleshipplayer first = ((Battleshipplayer) players.get(0));
        Battleshipplayer second = ((Battleshipplayer) players.get(1));
        first.leavegame();
        second.leavegame();
        FirstJavaProject.b_sh.leave(first);
        FirstJavaProject.b_sh.leave(second);
    }

    @Override
    void start() {
        if (players.size() == 2)//Game start condition
        {
            //use cast because we can't use abstract class :(
            Battleshipplayer first = ((Battleshipplayer) players.get(0));
            Battleshipplayer second = ((Battleshipplayer) players.get(1));
        }
    }

}
