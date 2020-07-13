package Teams;

import Players.Player;
import Rosters.Roster;
import Goalies.Goalie;

public class Team {

    public Player sRW;
    public Player sLW;
    public Player sC;
    public Player sLD;
    public Player sRD;
    public Goalie sG;
    public Player bRW;
    public Player bLW;
    public Player bC;
    public Player bLD;
    public Player bRD;
    public Goalie bG;

    public Team(Roster roster) {
        sRW = roster.getPlayerRoster().get(0);
        sLW = roster.getPlayerRoster().get(1);
        sG = roster.getGoalieRoster().get(0);
    }

}