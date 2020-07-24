import java.lang.Math;

class Simulation {

    // fields

    Team teamA;
    Team teamB;
    boolean isPlayoffGame;
    static int periodLength;

    // constructor

    public Simulation(Team teamA, Team teamB, boolean isPlayoffGame) {

        this.teamA = teamA;
        this.teamB = teamB;
        this.isPlayoffGame = isPlayoffGame;
        Simulation.periodLength = 1200;

        for (int i = 0; i < 3; i++) {
            period(0, teamA, teamB);
        }

        if (teamA.getScore() == teamB.getScore()) {
            Simulation.periodLength = 300;
            System.out.println("Are you ready?");
            System.out.println("I SAID Are you READY?");
            System.out.println("ITS OOVVVVEERRRTIIIMMEEEE!!");
            period(0, teamA, teamB);
            System.out.println("overtime over!");

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
    // get methods

    public static int getPeriodLength() {
        return periodLength;
    }

    public static boolean period(int time, Team teamA, Team teamB) {

        // check if period is over
        if (time > periodLength) {

            System.out.println("\n \n-- Period is over --\n \n" + "Shots: ");
            return false;

        }
        // otherwise move on to faceoff.
        else {

            return faceoffCalculation(0, teamA, teamB);

        }

    }

    public static boolean faceoffCalculation(int time, Team teamA, Team teamB) {

        // check if period is over
        if (time > periodLength) {
            System.out.println("\n \n-- Period is over --\n \n");
            
            return false;
        }

        else {
            System.out.println("Current Time: " + time);
            // get each teams centers faceoff stats
            double faceoffA = teamA.getsC().getFaceoff();
            double faceoffB = teamB.getsC().getFaceoff();

            // chance of team A winning the faceoff:
            double chance = 50 + (faceoffA - faceoffB);
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
        if (time > periodLength) {

            System.out.println("\n \n-- Period is over --\n \n");
            return false;

        } else {

            //offensive team values
            double skatingOC = offensiveTeam.getsC().getSkating();
            double skatingORW = offensiveTeam.getsRW().getSkating();
            double skatingOLW = offensiveTeam.getsLW().getSkating();
            double skatingORD = offensiveTeam.getsRD().getSkating();
            double skatingOLD = offensiveTeam.getsLD().getSkating();
            double strengthOC = offensiveTeam.getsC().getStrength();
            double strengthORW = offensiveTeam.getsRW().getStrength();
            double strengthOLW = offensiveTeam.getsLW().getStrength();
            double strengthORD = offensiveTeam.getsRD().getStrength();
            double strengthOLD = offensiveTeam.getsLD().getStrength();
            double offAwarenessOC = offensiveTeam.getsC().getOffensiveAwareness();
            double offAwarenessORW = offensiveTeam.getsRW().getOffensiveAwareness();
            double offAwarenessOLW = offensiveTeam.getsLW().getOffensiveAwareness();
            double offAwarenessORD = offensiveTeam.getsRD().getOffensiveAwareness();
            double offAwarenessOLD = offensiveTeam.getsLD().getOffensiveAwareness();

            //defensive team values
            double skatingDC = defensiveTeam.getsC().getSkating();
            double skatingDRW = defensiveTeam.getsRW().getSkating();
            double skatingDLW = defensiveTeam.getsLW().getSkating();
            double skatingDRD = defensiveTeam.getsRD().getSkating();
            double skatingDLD = defensiveTeam.getsLD().getSkating();
            double strengthDC = defensiveTeam.getsC().getStrength();
            double strengthDRW = defensiveTeam.getsRW().getStrength();
            double strengthDLW = defensiveTeam.getsLW().getStrength();
            double strengthDRD = defensiveTeam.getsRD().getStrength();
            double strengthDLD = defensiveTeam.getsLD().getStrength();
            double defAwarenessDC = defensiveTeam.getsC().getDefensiveAwareness();
            double defAwarenessDRW = defensiveTeam.getsRW().getDefensiveAwareness();
            double defAwarenessDLW = defensiveTeam.getsLW().getDefensiveAwareness();
            double defAwarenessDRD = defensiveTeam.getsRD().getDefensiveAwareness();
            double defAwarenessDLD = defensiveTeam.getsLD().getDefensiveAwareness();

            //matchup calculations for offensive team
            double skatingOverallO = (skatingOC-skatingDC) + (skatingORW-skatingDLD) + (skatingOLW-skatingDRD) + (skatingORD-skatingDLW) + (skatingOLD-skatingDRW);
            double strengthOverallO = (strengthOC-strengthDC) + (strengthORW-strengthDLD) + (strengthOLW-strengthDRD) + (strengthORD-strengthDLW) + (strengthOLD-strengthDRW);
            double awarenessOverallO = (offAwarenessOC-defAwarenessDC) + (offAwarenessORW-defAwarenessDLD) + (offAwarenessOLW-defAwarenessDRD) + (offAwarenessORD-defAwarenessDLW) + (offAwarenessOLD-defAwarenessDRW);

            //changing overall int values into doubles
            double skatingOverall = Double.valueOf(skatingOverallO);
            double strengthOverall = Double.valueOf(strengthOverallO);
            double awarenessOverall = Double.valueOf(awarenessOverallO);
            
            //generate random multiplier for probability of retaining possession
            double max = 1.3;
            double min = 0.8;
            double randomMultiplier = Math.random() * (max - min + 0.1) + min;

            //probability of offensive team retaining possession of the puck
            double chanceRetainPossession = 30 + ((skatingOverall/1.5 + strengthOverall/3 + awarenessOverall/2)*(randomMultiplier)/3);

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
        double chance = 10 + (shooter.getShooting() - (goalie.getReflexes() + goalie.getAgility())/2);
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
        if (time > periodLength) {

            System.out.println("\n \n-- Period is over --\n \n");
            return false;

        } else {

            //offensive team values
            double skatingOC = offensiveTeam.getsC().getSkating();
            double skatingORW = offensiveTeam.getsRW().getSkating();
            double skatingOLW = offensiveTeam.getsLW().getSkating();
            double skatingORD = offensiveTeam.getsRD().getSkating();
            double skatingOLD = offensiveTeam.getsLD().getSkating();
            double strengthOC = offensiveTeam.getsC().getStrength();
            double strengthORW = offensiveTeam.getsRW().getStrength();
            double strengthOLW = offensiveTeam.getsLW().getStrength();
            double strengthORD = offensiveTeam.getsRD().getStrength();
            double strengthOLD = offensiveTeam.getsLD().getStrength();
            double offAwarenessOC = offensiveTeam.getsC().getOffensiveAwareness();
            double offAwarenessORW = offensiveTeam.getsRW().getOffensiveAwareness();
            double offAwarenessOLW = offensiveTeam.getsLW().getOffensiveAwareness();
            double offAwarenessORD = offensiveTeam.getsRD().getOffensiveAwareness();
            double offAwarenessOLD = offensiveTeam.getsLD().getOffensiveAwareness();

            //defensive team values
            double skatingDC = defensiveTeam.getsC().getSkating();
            double skatingDRW = defensiveTeam.getsRW().getSkating();
            double skatingDLW = defensiveTeam.getsLW().getSkating();
            double skatingDRD = defensiveTeam.getsRD().getSkating();
            double skatingDLD = defensiveTeam.getsLD().getSkating();
            double strengthDC = defensiveTeam.getsC().getStrength();
            double strengthDRW = defensiveTeam.getsRW().getStrength();
            double strengthDLW = defensiveTeam.getsLW().getStrength();
            double strengthDRD = defensiveTeam.getsRD().getStrength();
            double strengthDLD = defensiveTeam.getsLD().getStrength();
            double defAwarenessDC = defensiveTeam.getsC().getDefensiveAwareness();
            double defAwarenessDRW = defensiveTeam.getsRW().getDefensiveAwareness();
            double defAwarenessDLW = defensiveTeam.getsLW().getDefensiveAwareness();
            double defAwarenessDRD = defensiveTeam.getsRD().getDefensiveAwareness();
            double defAwarenessDLD = defensiveTeam.getsLD().getDefensiveAwareness();

            //individual matchup result calculations
            double[] matchupResults = new double[5];

            double skatingMatchup1 = (skatingOC-skatingDC);
            double strengthMatchup1 = (strengthOC-strengthDC)*2;
            double awarenessMatchup1 = (offAwarenessOC-defAwarenessDC);
            matchupResults[0] = skatingMatchup1 + strengthMatchup1 + awarenessMatchup1;

            double skatingMatchup2 = (skatingORW-skatingDLD);
            double strengthMatchup2 = (strengthORW-strengthDLD)*2;
            double awarenessMatchup2 = (offAwarenessORW-defAwarenessDLD);
            matchupResults[1] = skatingMatchup2 + strengthMatchup2 + awarenessMatchup2;

            double skatingMatchup3 = (skatingOLW-skatingDRD);
            double strengthMatchup3 = (strengthOLW-strengthDRD)*2;
            double awarenessMatchup3 = (offAwarenessOLW-defAwarenessDRD);
            matchupResults[2] = skatingMatchup3 + strengthMatchup3 + awarenessMatchup3;

            double skatingMatchup4 = (skatingORD-skatingDLW);
            double strengthMatchup4 = (strengthORD-strengthDLW)*2;
            double awarenessMatchup4 = (offAwarenessORD-defAwarenessDLW);
            matchupResults[3] = skatingMatchup4 + strengthMatchup4 + awarenessMatchup4;

            double skatingMatchup5 = (skatingOLD-skatingDRW);
            double strengthMatchup5 = (strengthOLD-strengthDRW)*2;
            double awarenessMatchup5 = (offAwarenessOLD-defAwarenessDRW);
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