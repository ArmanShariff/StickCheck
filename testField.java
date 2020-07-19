import java.lang.Math;
import java.util.Random;

public class testField {
    public static void main(String[] args) {
        int time = 1200;
        Team toronto = new Team("Toronto", true);
        Team montreal = new Team("Montreal", false);
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
            double chanceRetainPossession = 50 + (skatingOverall*2 + strengthOverall + awarenessOverall*1.5)*(randomMultiplier);

            //If % of off. team retaining possesssion > random value between 1-100
            // >Off. team will retain possession
            int successRate = getRandom(1,100);
            if(Math.round(chanceRetainPossession) >= successRate ) {
                System.out.println(offensiveTeam.getTeamName() + "retains possession!");
                return matchupCalculationOne(time + getRandom(5, 20), offensiveTeam, defensiveTeam);
            }
            else {
                System.out.println(defensiveTeam.getTeamName() + "steals the puck away from " + offensiveTeam.getTeamName() + "!");
                return matchupCalculationOne(time + getRandom(5, 20), defensiveTeam, offensiveTeam);
            }
        }
    }

    public static int getRandom(int min, int max) {

        int random_int = (int)(Math.random() * (max - min + 1) + min);

        return random_int;
    }
}
