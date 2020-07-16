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

    public static boolean matchupCalculationOne(int time, Team offensiveTeam, Team defensiveTeam) {

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
            int strnegthDLD = defensiveTeam.getsLD().getStrength();
            int defensiveAwarenessDC = defensiveTeam.getsC().getDefensiveAwareness();
            int defAwarenessDRW = defensiveTeam.getsRW().getDefensiveAwareness();
            int defAwarenessDLW = defensiveTeam.getsLW().getDefensiveAwareness();
            int defAwarenessDRD = defensiveTeam.getsRD().getDefensiveAwareness();
            int defAwarenessDLD = defensiveTeam.getsLD().getDefensiveAwareness();

            //matchup calculations for offensive team
            int skatingOverallO = (skatingOC-skatingDC) + (skatingORW-skatingDLD) + (skatingOLW-skatingDRD) + (skatingORD-skatingDLW) + (skatingOLD-skatingDRW);
            int strengthOverallO = (strengthOC-strengthDC) + (strengthORW-strengthDLD) + (strengthOLW-strengthDRD) + (strengthORD-strengthDLW) + (strengthOLD-strengthDRW);
            int awarenessOverallO = (offAwarenessOC-defAwarenessDC) + (offAwarenessORW-defAwarenessDLD) + (offAwarenessOLW-defAwarenessDRD) + (offAwarenessORD-defAwarenessDLW) + (offAwarenessOLD-defAwarenessDRW);

            //changing overall int values into doubles
            double skatingOverall = Double.parseDouble(skatingOverallO);
            double strengthOverall = Double.parseDouble(strengthOverallO);
            double awarenessOverallO = Double.parseDouble(awarenessOverallO);
            
            //generate random multiplier for probability of retaining possession
            double max = 1.3;
            double min = 0.8;
            double randomMultiplier = Math.random() * (max - min + 0.1) + min;

            //probability of offensive team retaining possession of the puck
            int chanceRetainPossession = (skatingOverall*1.7 + strengthOverall + awarenessOverall*1.5)*(randomMultiplier);

        }

    }

    public static boolean matchupCalculationTwo(int time, offensiveTeam, defensiveTeam) {

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
            int strnegthDLD = defensiveTeam.getsLD().getStrength();
            int defAwarenessDC = defensiveTeam.getsC().getDefensiveAwareness();
            int defAwarenessDRW = defensiveTeam.getsRW().getDefensiveAwareness();
            int defAwarenessDLW = defensiveTeam.getsLW().getDefensiveAwareness();
            int defAwarenessDRD = defensiveTeam.getsRD().getDefensiveAwareness();
            int defAwarenessDLD = defensiveTeam.getsLD().getDefensiveAwareness();

            //individual matchup result calculations
            int[] matchupResults = new int[4];

            int skatingMatchup1 = (skatingOC-skatingDC);
            int strengthMatchup1 = (strengthOC-strengthDC)*2;
            int awarenessMatchup1 = (offAwarenessOC-defAwarenessDC);
            matchupResults[0] = skatingMatchup1 + strengthMatchup1 + awarenessMatchup1;

            int skatingMatchup2 = (skatingORW-skatingDLD);
            int strengthMatchup2 = (strengthORW-strengthDLD)*2;
            int awarenessMatchup2 = (offAwarenessORW-defAwarenessDLD);
            matchupResults[1] = skatingMatchup2 + strengthMatchup2 + awarenessMatchup2;

            int skatingMatchup3 = (skatingOLW-skatingDRD);
            int strengthMatchup3 = (strengthOLW-strengthDRD)*2;
            int awarenessMatchup3 = (offAwarenessOLW-defAwarenessDRD);
            matchupResults[2] = skatingMatchup3 + strengthMatchup3 + awarenessMatchup3;

            int skatingMatchup4 = (skatingORD-skatingDLW);
            int strengthMatchup4 = (strengthORD-strengthDLW)*2;
            int awarenessMatchup4 = (offAwarenessORD-defAwarenessDLW);
            matchupResults[3] = skatingMatchup4 + strengthMatchup4 + awarenessMatchup4;

            int skatingMatchup5 = (skatingOLD-skatingDRW);
            int strengthMatchup5 = (strengthOLD-strengthDRW)*2;
            int awarenessMatchup5 = (offAwarenessOLD-defAwarenessDRW);
            matchupResults[4] = skatingMatchup5 + strengthMatchup5 + awarenessMatchup5;

            //determining overall team results
            int offTeamScore;
            int defTeamScore;
            
            for (int i = 0; i < 5; i++) {
                if (matchupResults[i] > 1) {
                    offTeamScore++;
                } else if (matchupResults[i] < 1) {
                    defTeamScore++;
                }
            }

            //determining outcome of the calculation
            if(offTeamScore > defTeamScore) {
                //goes to the shot method
            } else {
                //goes to the matchupCalculationOne method
            }

        }
        
    }


    public static int getRandom(int min, int max) {

        int random_int = (int)(Math.random() * (max - min + 1) + min);

        return random_int;
    }






}