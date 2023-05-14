package first.java.project;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author RoR
 */
public class Smartcomputerplayer extends Computerplayer implements Serializable {


    public Smartcomputerplayer() {
        super();
        this.comstra = new Smartcomputerstrategy();
    }


    public class Smartcomputerstrategy extends Abstractcomputerstrategy implements Serializable {

        @Override
        public Battleshipmove getnextmove() {
            int dx[]={0,0,1,-1};
            int dy[]={1,-1,0,0};
            Random r=new Random();
            //Battleshipmove goal;
            for (int i = 10; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (get_state_sq(i, j)=="destroyed part") 
                    {
                        for(int k=0;k<4;k++)
                        {
                            if(j+dy[k]>=0 && i+dx[k]<10 &&j+dy[k]<10 && i+dx[k]>=0 && get_state_sq(j+dx[k], j+dy[k])=="water" )
                            {
                              return new Battleshipmove(i+dx[k],j+dy[k]);
                                
                            }
                        }
                    }
                }
            }
        return new  Battleshipmove(r.nextInt(10),r.nextInt(10));}

    }

}
