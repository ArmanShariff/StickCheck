import java.math.*;

class Simulation {

    // fields

    Team teamA;
    Team teamB;

    // constructor

    public Simulation(Team teamA, Team teamB) {

        this.teamA = teamA;
        this.teamB = teamB;

    }

    public static Boolean faceoffCalculation(int time, Team teamA, Team teamB) {

        if (time > 1200) {

            return false;

        }

        else {

            int faceoffA = teamA.getsC().getFaceoff();
            int faceoffB = teamB.getsC().getFaceoff();

            // chance of team A winning the faceoff:
            int chance = 50 + (faceoffA - faceoffB);

            getRandom(1,100);

        }

        

        return matchupCalculationOne();

    }

    public static boolean matchupCalculationOne(int time, offensiveTeam, defensiveTeam) {

        if (time > 1200) {
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
            int strnegthOLD = offensiveTeam.getsLD().getStrength();
            int offensiveAwarenessOC = offensiveTeam.getsC().getOffensiveAwareness();
            int offensiveAwarenessORW = offensiveTeam.getsRW().getOffensiveAwareness();
            int offensiveAwarenessOLW = offensiveTeam.getsLW().getOffensiveAwareness();
            int offensiveAwarenessORD = offensiveTeam.getsRD().getOffensiveAwareness();
            int offensiveAwarenessOLD = offensiveTeam.getsLD().getOffensiveAwareness();

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
            int strnegthDLD = defensiveTeam.getsLD().getStrength();
            int defensiveAwarenessDC = defensiveTeam.getsC().getDefensiveAwareness());
            int defensiveAwarenessDRW = defensiveTeam.getsRW().getDefensiveAwareness();
            int defensiveAwarenessDLW = defensiveTeam.getsLW().getDefensiveAwareness();
            int defensiveAwarenessDRD = defensiveTeam.getsRD().getDefensiveAwareness();
            int defensiveAwarenessDLD = defensiveTeam.getsLD().getDefensiveAwareness();

            //matchup calculations for offensive team
            int skatingOverallO = (skatingOC-skatingDC)*1.7 + (skatingORW-skatingDLD)*1.7 + (skatingOLW-skatingDRD)*1.7 + (skatingORD-skatingDLW)*1.7  + (skatingOLD-skatingDRW)*1.7;
            int strengthOverallO = (strengthOC-strengthDC) + (strengthORW-strengthDLD) + (strengthOLW-strengthDRD) + (strengthORD-strengthDLW) + (strengthOLD-strengthDRW);
            int awarenessOverallO = (offensiveAwarenessOC-defensiveAwarenessDC)*1.5 + (offensiveAwarenessORW-defensiveAwarenessDLD)*1.5 + (offensiveAwarenessOLW-defensiveAwarenessDRD)*1.5 + (offensiveAwarenessORD-defensiveAwarenessDLW)*1.5 + (offensiveAwarenessOLD-defensiveAwarenessDRW)*1.5;

            //changing overall int values into doubles
            double skatingOverall = Double.parseDouble(skatingOverallO);
            double strengthOverall = Double.parseDouble(strengthOverallO);
            double awarenessOverallO = Double.parseDouble(awarenessOverallO);
            
            //generate random multiplier for probability of retaining possession
            double max = 1.3;
            double min = 0.8;
            double randomMultiplier = Math.random() * (max - min + 0.1) + min;

            //probability of offensive team retaining possession of the puck
            int chanceRetainPossession = (skatingOverall+strengthOverall+awarenessOverall)*(randomMultiplier);

        }

    }

    public static boolean matchupCalculationTwo(int time, offensiveTeam, defensiveTeam) {

        
    }


    public static int getRandom(int min, int max) {

        int random_int = (int)(Math.random() * (max - min + 1) + min);

        return random_int;
    }






}