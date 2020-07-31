public class OnIce {
    
    //team that is being tracked
    Team team;

    //Left Wing: 0
    //Center: 1
    //Right Wing: 2
    //Left Defense: 3
    //Right Defense: 4
    private Player[] onIce = new Player[5];

    //current line on the ice
    int forwardLine;
    int defenceLine;

    public OnIce(Team team, int forwardStartingLine, int defenceStartingLine) {
        this.team = team;
        this.forwardLine = forwardStartingLine;
        this.defenceLine = defenceStartingLine;

        onIce[0] = team.getTeamLines().getLeftWing(forwardLine);
        onIce[1] = team.getTeamLines().getCenter(forwardLine);
        onIce[2] = team.getTeamLines().getRightWing(forwardLine);
        onIce[3] = team.getTeamLines().getLeftDefence(defenceLine);
        onIce[4] = team.getTeamLines().getRightDefence(defenceLine);

    }

    public OnIce(Team team) {
        this.team = team;
    }

    public void lineChange(int time) {
        team.getTeamLines().regenerateStamina(forwardLine, defenceLine, time);
        if (team.getTeamLines().getForwardAverageStamina(forwardLine) < 0.75) {
            changeForwards(team.getTeamLines().selectForwardLine());
        }
        if (team.getTeamLines().getDefenceAverageStamina(defenceLine) < 0.75) {
            changeDefenders(team.getTeamLines().selectDefenceLine());
        }
    }
    

    public void changeForwards(int forwardLine) {
        //use this method if you only want to change the foward line
        onIce[0] = team.getTeamLines().getLeftWing(forwardLine);
        onIce[1] = team.getTeamLines().getCenter(forwardLine);
        onIce[2] = team.getTeamLines().getRightWing(forwardLine);

        this.forwardLine = forwardLine;
    }

    public void changeDefenders(int defenceLine) {
        //use this method if you only want to change the defence line
        onIce[3] = team.getTeamLines().getLeftDefence(defenceLine);
        onIce[4] = team.getTeamLines().getRightDefence(defenceLine);

        this.defenceLine = defenceLine;
    }

    //these methods will return a player that is currently on the ice
    public Player getCenter(){
        return onIce[1];
    }

    public Player getLeftWing() {
        return onIce[0];
    }

    public Player getRightWing() {
        return onIce[2];
    }

    public Player getLeftDefence() {
        return onIce[3];
    }

    public Player getRightDefence() {
        return onIce[4];
    }

    public Player getOnIce(int i) {
        return onIce[i];
    }

    public double getForwardAverageStamina() {
        double averageStamina = 0;
        for (int i = 0; i < 3; i++) {
            averageStamina = averageStamina + onIce[i].getStaminaBar();
        }
        return (averageStamina/3);
    }

    public double getDefenceAverageStamina() {
        double averageStamina = 0;
        for (int i = 3; i < 5; i++) {
            averageStamina = averageStamina + onIce[i].getStaminaBar();
        }
        return (averageStamina/2);
    }


}