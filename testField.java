import java.io.FileNotFoundException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;

public class testField {
    public static void main(String[] args) throws FileNotFoundException {
        Roster roster = new Roster("roster.csv");

        Team toronto = new Team("Toronto", true);
        Team newYork = new Team("New York", false);
        Team detroit = new Team("Detroit", false);
        Team chicago = new Team("Chicago", false);
        Team montreal = new Team("Montreal", false);
        Team boston = new Team("Boston", false);

        Player[] torontoPlayers = new Player[10];
        Player[] newYorkPlayers = new Player[10];
        Player[] detroitPlayers = new Player[10];
        Player[] chicagoPlayers = new Player[10];
        Player[] montrealPlayers = new Player[10];
        Player[] bostonPlayers = new Player[10];

        ArrayList<Player> playerList = new ArrayList<Player>();
        ArrayList<Goalie> goalieList = new ArrayList<Goalie>();
        ArrayList<Coach> coachList = new ArrayList<Coach>();

        playerList = roster.getPlayers();
        goalieList = roster.getGoalies();
        coachList = roster.getCoaches();

        sortTeam("Toronto", toronto, playerList, goalieList, coachList, torontoPlayers);
        sortTeam("New York", newYork, playerList, goalieList, coachList, newYorkPlayers);
        sortTeam("Detroit", detroit, playerList, goalieList, coachList, detroitPlayers);
        sortTeam("Chicago", chicago, playerList, goalieList, coachList, chicagoPlayers);
        sortTeam("Montreal", montreal, playerList, goalieList, coachList, montrealPlayers);
        sortTeam("Boston", boston, playerList, goalieList, coachList, bostonPlayers);

        System.out.println("Toronto: \n" + toronto);
        System.out.println("\nNew York: \n" + newYork);
        System.out.println("\nDetroit: \n" + detroit);
        System.out.println("\nChicago: \n" + chicago);
        System.out.println("\nMontreal: \n" + montreal);
        System.out.println("\nBoston: \n" + boston);

        int time = 0;
        matchupCalculationOne(time, toronto, montreal);
    }

    public static boolean matchupCalculationOne(int time, Team offensiveTeam, Team defensiveTeam) {

        // check if period is over
        if (time > 1200) {

            System.out.println("-- Period is over --");
            return false;

        } else {

            //offensive team values
            int skatingOC = offensiveTeam.getsC().getSkating();
            int skatingORW = offensiveTeam.getsRW().getSkating();
            int skatingOLW = offensiveTeam.getsLW().getSkating();
            int skatingORD = offensiveTeam.getsRD().getSkating();
            int skatingOLD = offensiveTeam.getsLD().getSkating();
            int strengthOC = offensiveTeam.getsC().getStrength();
            int strengthORW = offensiveTeam.getsRW().getStrength();
            int strengthOLW = offensiveTeam.getsLW().getStrength();
            int strengthORD = offensiveTeam.getsRD().getStrength();
            int strengthOLD = offensiveTeam.getsLD().getStrength();
            int offAwarenessOC = offensiveTeam.getsC().getOffensiveAwareness();
            int offAwarenessORW = offensiveTeam.getsRW().getOffensiveAwareness();
            int offAwarenessOLW = offensiveTeam.getsLW().getOffensiveAwareness();
            int offAwarenessORD = offensiveTeam.getsRD().getOffensiveAwareness();
            int offAwarenessOLD = offensiveTeam.getsLD().getOffensiveAwareness();

            //defensive team values
            int skatingDC = defensiveTeam.getsC().getSkating();
            int skatingDRW = defensiveTeam.getsRW().getSkating();
            int skatingDLW = defensiveTeam.getsLW().getSkating();
            int skatingDRD = defensiveTeam.getsRD().getSkating();
            int skatingDLD = defensiveTeam.getsLD().getSkating();
            int strengthDC = defensiveTeam.getsC().getStrength();
            int strengthDRW = defensiveTeam.getsRW().getStrength();
            int strengthDLW = defensiveTeam.getsLW().getStrength();
            int strengthDRD = defensiveTeam.getsRD().getStrength();
            int strengthDLD = defensiveTeam.getsLD().getStrength();
            int defAwarenessDC = defensiveTeam.getsC().getDefensiveAwareness();
            int defAwarenessDRW = defensiveTeam.getsRW().getDefensiveAwareness();
            int defAwarenessDLW = defensiveTeam.getsLW().getDefensiveAwareness();
            int defAwarenessDRD = defensiveTeam.getsRD().getDefensiveAwareness();
            int defAwarenessDLD = defensiveTeam.getsLD().getDefensiveAwareness();

            //matchup calculations for offensive team
            int skatingOverallO = (skatingOC-skatingDC) + (skatingORW-skatingDLD) + (skatingOLW-skatingDRD) + (skatingORD-skatingDLW) + (skatingOLD-skatingDRW);
            int strengthOverallO = (strengthOC-strengthDC) + (strengthORW-strengthDLD) + (strengthOLW-strengthDRD) + (strengthORD-strengthDLW) + (strengthOLD-strengthDRW);
            int awarenessOverallO = (offAwarenessOC-defAwarenessDC) + (offAwarenessORW-defAwarenessDLD) + (offAwarenessOLW-defAwarenessDRD) + (offAwarenessORD-defAwarenessDLW) + (offAwarenessOLD-defAwarenessDRW);

            //changing overall int values into doubles
            double skatingOverall = Double.valueOf(skatingOverallO);
            double strengthOverall = Double.valueOf(strengthOverallO);
            double awarenessOverall = Double.valueOf(awarenessOverallO);
            
            //generate random multiplier for probability of retaining possession
            double max = 1.3;
            double min = 0.8;
            double randomMultiplier = Math.random() * (max - min + 0.1) + min;

            //probability of offensive team retaining possession of the puck
            double chanceRetainPossession = 30 + ((skatingOverall*2 + strengthOverall + awarenessOverall*1.5)*(randomMultiplier)/3);

            //If % of off. team retaining possesssion > random value between 1-100
            // >Off. team will retain possession
            int turnover = getRandom(1,100);
            // System.out.println("% retain: " + chanceRetainPossession);;
            // System.out.println("Turnover %: " + turnover);
            if(Math.round(chanceRetainPossession) >= turnover) {
                System.out.println(offensiveTeam.getTeamName() + " retains possession!");
                System.out.println("Current time: " + time);
                return matchupCalculationOne(time + getRandom(5, 20), offensiveTeam, defensiveTeam);
            }
            else {
                System.out.println(defensiveTeam.getTeamName() + " steals the puck away from " + offensiveTeam.getTeamName() + "!");
                System.out.println("Current time: " + time);
                return matchupCalculationOne(time + getRandom(5, 20), defensiveTeam, offensiveTeam);
            }
        }
    }

    public static int getRandom(int min, int max) {

        int random_int = (int)(Math.random() * (max - min + 1) + min);

        return random_int;
    }

    public static void sortTeam(String teamName,Team team, ArrayList<Player> playerRoster, ArrayList<Goalie> goalieRoster, ArrayList<Coach> coachRoster, Player[] arrPlayers) {
        for(int i = 0; i < playerRoster.size(); i++){
            if(playerRoster.get(i).getCurrentTeam().equals(teamName)) {
                team.addPlayer(playerRoster.get(i));
                team.setPlayerPosition(playerRoster.get(i), arrPlayers);
            }
        }

        for(int i = 0; i < goalieRoster.size(); i++){
            if(goalieRoster.get(i).getCurrentTeam().equals(teamName)) {
                team.addGoalie(goalieRoster.get(i));
                team.setGoaliePosition(goalieRoster.get(i));
            }
        }

        for(int i = 0; i < coachRoster.size(); i++){
            if(coachRoster.get(i).getCurrentTeam().equals(teamName)) {
                team.addCoach(coachRoster.get(i));
                team.setCoach(coachRoster.get(i));
            }
        }
    }
}
