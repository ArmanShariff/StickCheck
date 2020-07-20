import java.lang.Math;

class Simulation {

    // fields

    Team teamA;
    Team teamB;
    static int currentTime = 0;
    

    // constructor

    public Simulation(Team teamA, Team teamB) {

        this.teamA = teamA;
        this.teamB = teamB;

        for (int i = 0; i < 3; i++) {
            period(currentTime, teamA, teamB);
        }

        System.out.println("GAME OVER!!!!");
        System.out.println(teamA.getTeamName() + ": " + teamA.getScore());
        System.out.println(teamB.getTeamName() + ": " + teamB.getScore());
        System.out.println(teamA.getTeamName() + " shots: " + teamA.getShotCount());
        System.out.println(teamB.getTeamName() + " shots: " + teamB.getShotCount());
        
        teamA.reSetScore();
        teamB.reSetScore();
        teamA.reSetShotCount();
        teamB.reSetShotCount();

    }
    
    public static boolean period(int time, Team teamA, Team teamB) {

        // check if period is over
        if (time > 1200) {

            System.out.println("\n \n-- Period is over --\n \n" + "Shots: ");
            return false;

        }
        // otherwise move on to faceoff.
        else {

            return faceoffCalculation(currentTime, teamA, teamB);

        }

    }

    public static boolean faceoffCalculation(int time, Team teamA, Team teamB) {

        // check if period is over
        if (time > 1200) {
            System.out.println("\n \n-- Period is over --\n \n");
            
            return false;
        }

        else {
            System.out.println("Current Time: " + time);
            // get each teams centers faceoff stats
            int faceoffA = teamA.getsC().getFaceoff();
            int faceoffB = teamB.getsC().getFaceoff();

            // chance of team A winning the faceoff:
            int chance = 50 + (faceoffA - faceoffB);
            int random = getRandom(1,100);

            if (random <= chance) {
                // team A is on offence
                System.out.println(teamA.getsC().getFirstName() + " " + teamA.getsC().getLastName() + " won the faceoff!");
                return matchupCalculationOne(time + 2, teamA, teamB);
            }
            
            else {
                // team B is on offence
                System.out.println(teamB.getsC().getFirstName() + " " + teamB.getsC().getLastName() + " won the faceoff!");
                return matchupCalculationOne(time + 2, teamB, teamA);
            }

        }

    }

    public static boolean matchupCalculationOne(int time, Team offensiveTeam, Team defensiveTeam) {
        // check if period is over
        if (time > 1200) {

            System.out.println("\n \n-- Period is over --\n \n");
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
            double chanceRetainPossession = 30 + ((skatingOverall + strengthOverall/2 + awarenessOverall/1.5)*(randomMultiplier)/3);

            //If % of off. team retaining possesssion > random value between 1-100
            // >Off. team will retain possession
            int turnover = getRandom(1,100);
            if(Math.round(chanceRetainPossession) >= turnover) {
                System.out.println(offensiveTeam.getTeamName() + " retains possession!");
                return shotCalculation(time + getRandom(5, 20), offensiveTeam, defensiveTeam);
            }
            else {
                System.out.println(defensiveTeam.getTeamName() + " steals the puck away from " + offensiveTeam.getTeamName() + "!");
                return matchupCalculationOne(time + getRandom(5, 20), defensiveTeam, offensiveTeam);
            }
        }
    }

    public static boolean shotCalculation(int time, Team offensiveTeam, Team defensiveTeam) {
        
        offensiveTeam.setShotCount();
        Player shooter = determineShooter(offensiveTeam);
        Goalie goalie = defensiveTeam.getsG();

        // calculation to determine if its a goal
        boolean isGoal = isGoal(shooter, goalie);

        if (isGoal == true) {
            offensiveTeam.setScore();
            System.out.println("\n" + offensiveTeam.getTeamName() + "(" + offensiveTeam.getScore() + ")" + " - " + defensiveTeam.getTeamName() + "(" + defensiveTeam.getScore() + ")\n");
            return faceoffCalculation(time + 2, offensiveTeam, defensiveTeam);
        }
        else {
            // calculation to see if rebound or stopage in play
            boolean isRebound = isRebound(goalie);

            if (isRebound == false) {
                System.out.println("What a save!");
                return faceoffCalculation(time + 2, offensiveTeam, defensiveTeam);
            }
            else {
                System.out.println("Rebound opportunity!");
                return matchupCalculationTwo(time + 2, offensiveTeam, defensiveTeam);
            }

        }

    }

    public static Player determineShooter(Team offensiveTeam) {
        // calculation to determine shooter

        // sum of all shooting tendencies stats
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += offensiveTeam.getOnIce(i).getShootingTendency();
        }

        // calculates the chance that a player will take the shot
        // stores the chance in the array 'chance'
        double[] chance = new double[5];
        double percent = 10000/sum;
        for (int i = 0; i < 5; i++) {
            chance[i] = (percent * offensiveTeam.getOnIce(i).getShootingTendency()/100);
        }

        Player shooter = offensiveTeam.getOnIce(0);                 // stores the player taking the shot
        double random = getRandomDouble(1.0,100.0);                 // gets a random number
        double temp = 0;

        for (int i = 0; i < 5; i++) {
            temp = temp + chance[i];
            if (random <= temp) {
                shooter = offensiveTeam.getOnIce(i);
                break;
            }
        }
        return shooter;
    }

    public static boolean isGoal(Player shooter, Goalie goalie) {

        // % chance of a goal = 15 + (Shooting - (Reflexes + Agility)/2)
        int chance = 10 + (shooter.getShooting() - (goalie.getReflexes() + goalie.getAgility())/2);
        int random_int = getRandom(1, 100);

        if (chance <= 0) {
            // no chance of a goal
            return false;
        }
        else if (random_int <= chance) {
            System.out.println(shooter.getLastName() + " shoots!.. He scores!");
            
            return true;
        }
        else {
            // no goal
            System.out.println(shooter.getLastName() + " shoots!");
            
            return false;
        }

    }

    public static boolean isRebound(Goalie goalie) {

        // %chance of rebound = 120-(Flexibility+Rebound control)/2

        int chance = 120 - (goalie.getFlexibility() + goalie.getReboundControl())/2;
        int random_int = getRandom(1, 100);

        if (chance <= random_int) {
            // it is a rebound
            return true;
        }
        else {
            // no rebound
            return false;
        }

    }

    public static boolean matchupCalculationTwo(int time, Team offensiveTeam, Team defensiveTeam) {

        // check if period is over
        if (time > 1200) {

            System.out.println("\n \n-- Period is over --\n \n");
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

            //individual matchup result calculations
            int[] matchupResults = new int[5];

            int skatingMatchup1 = (skatingOC-skatingDC)/2;
            int strengthMatchup1 = (strengthOC-strengthDC);
            int awarenessMatchup1 = (offAwarenessOC-defAwarenessDC)/2;
            matchupResults[0] = skatingMatchup1 + strengthMatchup1 + awarenessMatchup1;

            int skatingMatchup2 = (skatingORW-skatingDLD)/2;
            int strengthMatchup2 = (strengthORW-strengthDLD);
            int awarenessMatchup2 = (offAwarenessORW-defAwarenessDLD)/2;
            matchupResults[1] = skatingMatchup2 + strengthMatchup2 + awarenessMatchup2;

            int skatingMatchup3 = (skatingOLW-skatingDRD)/2;
            int strengthMatchup3 = (strengthOLW-strengthDRD);
            int awarenessMatchup3 = (offAwarenessOLW-defAwarenessDRD)/2;
            matchupResults[2] = skatingMatchup3 + strengthMatchup3 + awarenessMatchup3;

            int skatingMatchup4 = (skatingORD-skatingDLW)/2;
            int strengthMatchup4 = (strengthORD-strengthDLW);
            int awarenessMatchup4 = (offAwarenessORD-defAwarenessDLW)/2;
            matchupResults[3] = skatingMatchup4 + strengthMatchup4 + awarenessMatchup4;

            int skatingMatchup5 = (skatingOLD-skatingDRW)/2;
            int strengthMatchup5 = (strengthOLD-strengthDRW);
            int awarenessMatchup5 = (offAwarenessOLD-defAwarenessDRW)/2;
            matchupResults[4] = skatingMatchup5 + strengthMatchup5 + awarenessMatchup5;

            //determining overall team results
            int offTeamScore = 0;
            int defTeamScore = 0;
            
            for (int i = 0; i < 5; i++) {
                if (matchupResults[i] > 1) {
                    offTeamScore++;
                } else if (matchupResults[i] < 1) {
                    defTeamScore++;
                }
            }

            //determining outcome of the calculation
            if(offTeamScore > defTeamScore) {
                return shotCalculation(time + 30, offensiveTeam, defensiveTeam);
            } else {
                return matchupCalculationOne(time + 30, defensiveTeam, offensiveTeam);
            }

        }
        
    }

    public static int getRandom(int min, int max) {

        int random_int = (int)(Math.random() * (max - min + 1) + min);

        return random_int;
    }

    public static double getRandomDouble(double min, double max) {

        double random_double = (Math.random() * ((max - min) +1)) + min;

        return random_double;
    }

}