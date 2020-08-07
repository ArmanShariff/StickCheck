import java.util.ArrayList;

public class Lines {
    // track time of last line change
    int lastTime;

    // team lines
    private Player[][] forwardLines = new Player[2][3];
    private Player[][] defenceLines = new Player[2][2];

    public Lines(ArrayList<Player> playerRoster) {

        sortPlayers(playerRoster);

    }

    /*
     * These get methods will return a player from a specific line
     * if you want to get the player currently on the ice, use the methods in OnIce
     */ 

    public Player getCenter(int line) {
        return forwardLines[line][1];
    }

    public Player getLeftWing(int line) {
        return forwardLines[line][0];
    }

    public Player getRightWing(int line) {
        return forwardLines[line][2];
    }

    public Player getLeftDefence(int line) {
        return defenceLines[line][0];
    }

    public Player getRightDefence(int line) {
        return defenceLines[line][1];
    }

    /*
     * These methods are used to control line changes
     */

    public int selectForwardLine() {
        int bestLine = 0;
        for (int i = 0; i < forwardLines.length; i++) {
            double averageStamina = getForwardAverageStamina(i);
            System.out.println(forwardLines[i][1] + " avg line Stamina (Line:" + (i+1) + ") :" + averageStamina);
            if (averageStamina > getForwardAverageStamina(bestLine)) {
                bestLine = i;
            }
        }
        return bestLine;
    }

    public int selectDefenceLine() {
        int bestLine = 0;
        for (int i = 0; i < defenceLines.length; i++) {
            double averageStamina = getDefenceAverageStamina(i);
            if (averageStamina > getDefenceAverageStamina(bestLine)) {
                bestLine = i;
            }
        }
        return bestLine;
    }

    public double getForwardAverageStamina(int line) {
        double averageStamina = 0;
        for (int i = 0; i < 3; i++) {
            averageStamina = averageStamina + forwardLines[line][i].getStaminaBar();
        }
        return (averageStamina/3);
    }

    public double getDefenceAverageStamina(int line) {
        double averageStamina = 0;
        for (int i = 0; i < 2; i++) {
            averageStamina = averageStamina + defenceLines[line][i].getStaminaBar();
        }
        return (averageStamina/2);
    }

    public void regenerateStamina(int currentForwardLine, int currentDefenceLine, int currentTime) {
        // loop through lines and regenerate stamina of all lines that are not currently on the ice
        for (int i = 0; i < forwardLines.length; i++) {
            if (i != currentForwardLine) {
                for (int j = 0; j < forwardLines[i].length; j++) {
                    //double playerStamina = forwardLines[i][j].getStamina();
                    forwardLines[i][j].regenerateStaminaBar(currentTime - lastTime);
                }
            }
        }

        for (int i = 0; i < defenceLines.length; i++) {
            if (i != currentDefenceLine) {
                for (int j = 0; j < defenceLines[i].length; j++) {
                    //double playerStamina = forwardLines[i][j].getStamina();
                    defenceLines[i][j].regenerateStaminaBar(currentTime - lastTime);
                }
            }
        }
        this.lastTime = currentTime;
    }

    public void setLastTime(int time) {
        this.lastTime = time;
    }

    /*
     * These methods are used to assign players to a specific line in the team
     */ 

    public void sortPlayers(ArrayList<Player> playerRoster) {

        for (int i = 0; i < playerRoster.size(); i++) {
            setPlayerPosition(playerRoster.get(i));
        }
    }

    //TO-DO: Change chain of if statements into a for loop
    //TO-DO: Make sure that the highest overall player is in the top line (can be done by sorting playerRoster by overall before settingPlayerPosition)
    public void setPlayerPosition(Player player) {

        if (player.getPosition().equals("Left Wing")) {

            if (forwardLines[0][0] == null) {
                forwardLines[0][0] = player;
            } else {
                forwardLines[1][0] = player;
            }
        }

        else if (player.getPosition().equals("Center")) {
            if (forwardLines[0][1] == null) {
                forwardLines[0][1] = player;
            } else {
                forwardLines[1][1] = player;
            }
        }

        else if (player.getPosition().equals("Right Wing")) {
            if (forwardLines[0][2] == null) {
                forwardLines[0][2] = player;
            } else {
                forwardLines[1][2] = player;
            }
        }

        else if (player.getPosition().equals("Left Defence")) {
            if (defenceLines[0][0] == null) {
                defenceLines[0][0] = player;
            } else {
                defenceLines[1][0] = player;
            }
        }

        else if (player.getPosition().equals("Right Defence")) {
            if (defenceLines[0][1] == null) {
                defenceLines[0][1] = player;
            } else {
                defenceLines[1][1] = player;
            }
        }
    }

    public void changeForwardLines(int line, int player, int newLine, int newPlayer) {
        Player selection1 = forwardLines[line][player];
        Player selection2 = forwardLines[newLine][newPlayer];

        //swap players
        forwardLines[line][player] = selection2;
        forwardLines[newLine][newPlayer] = selection1;
    }

    public void changeDefenceLines(int line, int player, int newLine, int newPlayer) {
        Player selection1 = defenceLines[line][player];
        Player selection2 = defenceLines[newLine][newPlayer];

        //swap players
        defenceLines[line][player] = selection2;
        defenceLines[newLine][newPlayer] = selection1;
    }

    public String toString() {

        String printStatement = "Lines: \n";

        for (int i = 0; i < forwardLines.length; i ++) {
            printStatement += "Line " + (i+1);
            for (int j = 0; j < forwardLines[i].length; j++) {
                printStatement += " " + forwardLines[i][j].getLastName() +  "(" + forwardLines[i][j].getGoals() + " / " + forwardLines[i][j].shootingPercentage() + ")";
            }
            printStatement += "\n";
        }

        for (int i = 0; i < defenceLines.length; i ++) {
            printStatement += "Line " + (i+1);
            for (int j = 0; j < defenceLines[i].length; j++) {
                printStatement += " " + defenceLines[i][j].getLastName() +  "(" + defenceLines[i][j].getGoals() + " / " + defenceLines[i][j].shootingPercentage() + ")";
            }
            printStatement += "\n";
        }
        return printStatement;
     }

}