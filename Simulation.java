import java.lang.Math;
import java.lang.management.ThreadMXBean;

class Simulation {

    // fields

    Team teamA;
    Team teamB;
    boolean isPlayoffGame;

    static int periodLength;
    static boolean isOvertime;
    static int drops = 1;

    // constructor

    public Simulation(Team teamA, Team teamB, boolean isPlayoffGame) {

        this.teamA = teamA;
        this.teamB = teamB;
        this.isPlayoffGame = isPlayoffGame;

        Simulation.periodLength = 1200;
        Simulation.isOvertime = false;

        
        // run three periods
        // resets stamina/stats after each period
        for (int i = 0; i < 3; i++) {
            period(0, teamA, teamB);
            teamResetStamina(teamA, teamB);
            teamResetStats(teamA, teamB);
        }

        // if the game is tied start overtime

        if (teamA.getScore() == teamB.getScore()) {
            Simulation.isOvertime = true;
            teamResetStamina(teamA, teamB);
            teamResetStats(teamA, teamB);

            // shootout only occurs during the regular season
            if (isPlayoffGame == true) {
                while (teamA.getScore() == teamB.getScore()) {
                    overtime(teamA, teamB, 1200);
                }

            } else {
                overtime(teamA, teamB, 300);

                // if its still tied go to shootout
                //if (teamA.getScore() == teamB.getScore()) {
                //    shootout(teamA, teamB);
                //}
            }
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

    public static boolean getIsOvertime() {
        return isOvertime;
    }

    //One period
    public static boolean period(int time, Team teamA, Team teamB) {

        // check if period is over, otherwise move on to faceoff.
        if (time > periodLength) {
            System.out.println("\n \n-- Period is over --\n \n" + "Shots: ");

            return false;

        } else {
            return faceoffCalculation(0, teamA, teamB);
        }
    }

    //Faceoff Calculation
    public static boolean faceoffCalculation(int time, Team teamA, Team teamB) {
        // check if period is over
        if (time > periodLength) {
            System.out.println("\n \n-- Period is over --\n \n");
            
            return false;

        } else {
            teamDropStamina(teamA, teamB);
            teamDropStats(teamA, teamB);
            System.out.println(teamA.getsC().getFirstName() + "'s stamina: " + teamA.getsC().getStaminaBar());
            System.out.println(teamA.getsC().getFirstName() + "'s skating: " + teamA.getsC().getSkating());
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

            } else {
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
            double skatingOC = offensiveTeam.getOnIce(1).getSkating();
            double skatingORW = offensiveTeam.getOnIce(2).getSkating();
            double skatingOLW = offensiveTeam.getOnIce(0).getSkating();
            double skatingORD = offensiveTeam.getOnIce(3).getSkating();
            double skatingOLD = offensiveTeam.getOnIce(4).getSkating();
            double strengthOC = offensiveTeam.getOnIce(1).getStrength();
            double strengthORW = offensiveTeam.getOnIce(2).getStrength();
            double strengthOLW = offensiveTeam.getOnIce(0).getStrength();
            double strengthORD = offensiveTeam.getOnIce(3).getStrength();
            double strengthOLD = offensiveTeam.getOnIce(4).getStrength();
            double offAwarenessOC = offensiveTeam.getOnIce(0).getOffensiveAwareness();
            double offAwarenessORW = offensiveTeam.getOnIce(1).getOffensiveAwareness();
            double offAwarenessOLW = offensiveTeam.getOnIce(2).getOffensiveAwareness();
            double offAwarenessORD = offensiveTeam.getOnIce(3).getOffensiveAwareness();
            double offAwarenessOLD = offensiveTeam.getOnIce(4).getOffensiveAwareness();

            //defensive team values
            double skatingDC = defensiveTeam.getOnIce(1).getSkating();
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
                teamDropStamina(offensiveTeam, defensiveTeam);
                teamDropStats(offensiveTeam, defensiveTeam);
                System.out.println(offensiveTeam.getTeamName() + " retains possession!");

                return shotCalculation(time + getRandom(5, 20), offensiveTeam, defensiveTeam);

            } else {
                teamDropStamina(offensiveTeam, defensiveTeam);
                teamDropStats(offensiveTeam, defensiveTeam);
                System.out.println(defensiveTeam.getTeamName() + " steals the puck away from " + offensiveTeam.getTeamName() + "!");
                
                return matchupCalculationOne(time + getRandom(5, 20), defensiveTeam, offensiveTeam);
            }
        }
    }

    //Shot calculation
    public static boolean shotCalculation(int time, Team offensiveTeam, Team defensiveTeam) {
        
        offensiveTeam.setShotCount();
        Player shooter = determineShooter(offensiveTeam);
        Goalie goalie = defensiveTeam.getsG();

        // calculation to determine if its a goal
        boolean isGoal = isGoal(shooter, goalie);

        if (isGoal == true) {
            offensiveTeam.setScore();
            System.out.println("\n" + offensiveTeam.getTeamName() + "(" + offensiveTeam.getScore() + ")" + " - " + defensiveTeam.getTeamName() + "(" + defensiveTeam.getScore() + ")\n");
            
            if (isOvertime == true) {
                offensiveTeam.setScore();

                return true;

            } else {
                return faceoffCalculation(time + 2, offensiveTeam, defensiveTeam);
            }

        }
        else {
            // calculation to see if rebound or stopage in play
            boolean isRebound = isRebound(goalie);

            if (isRebound == false) {
                System.out.println("What a save!");
                return faceoffCalculation(time + 2, offensiveTeam, defensiveTeam);

            } else {
                teamDropStamina(offensiveTeam, defensiveTeam);
                teamDropStats(offensiveTeam, defensiveTeam);
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
            System.out.println(shooter.getLastName() + " -YOU SUCK AT HOCKEY BUD");
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

    // Method that drops every player's stamina
    // After: Faceoffs, fights for possession, rebounds 
    public static void teamDropStamina(Team teamA, Team teamB) {
        drops = drops++;
        teamA.getsC().dropStamina(drops);
        teamA.getsLW().dropStamina(drops);
        teamA.getsRW().dropStamina(drops);
        teamA.getsLD().dropStamina(drops);
        teamA.getsRD().dropStamina(drops);
        teamB.getsC().dropStamina(drops);
        teamB.getsLW().dropStamina(drops);
        teamB.getsRW().dropStamina(drops);
        teamB.getsLD().dropStamina(drops);
        teamB.getsRD().dropStamina(drops);
    }
    
    //Resets every player's stamina after the period ends
    public static void teamResetStamina(Team teamA, Team teamB) {
        drops = 1;
        teamA.getsC().setStaminaBar(1);
        teamA.getsLW().setStaminaBar(1);
        teamA.getsRW().setStaminaBar(1);
        teamA.getsLD().setStaminaBar(1);
        teamA.getsRD().setStaminaBar(1);
        teamB.getsC().setStaminaBar(1);
        teamB.getsLW().setStaminaBar(1);
        teamB.getsRW().setStaminaBar(1);
        teamB.getsLD().setStaminaBar(1);
        teamB.getsRD().setStaminaBar(1);
    }

    //Drop stats of every player depending on their current stamina
    // >Drops everytime stamina drops
    public static void teamDropStats(Team teamA, Team teamB) {
        teamA.getsC().dropStats(teamA.getsC().getStaminaBar());
        teamA.getsLW().dropStats(teamA.getsLW().getStaminaBar());
        teamA.getsRW().dropStats(teamA.getsRW().getStaminaBar());
        teamA.getsLD().dropStats(teamA.getsLD().getStaminaBar());
        teamA.getsRD().dropStats(teamA.getsRW().getStaminaBar());
        teamB.getsC().dropStats(teamB.getsC().getStaminaBar());
        teamB.getsLW().dropStats(teamB.getsLW().getStaminaBar());
        teamB.getsRW().dropStats(teamB.getsRW().getStaminaBar());
        teamB.getsLD().dropStats(teamB.getsLD().getStaminaBar());
        teamB.getsRD().dropStats(teamB.getsRW().getStaminaBar());
    }

    //Reset stats of every player after each period
    public static void teamResetStats(Team teamA, Team teamB) {
        teamA.getsC().ResetStats();
        teamA.getsLW().ResetStats();
        teamA.getsRW().ResetStats();
        teamA.getsLD().ResetStats();
        teamA.getsRD().ResetStats();
        teamB.getsC().ResetStats();
        teamB.getsLW().ResetStats();
        teamB.getsRW().ResetStats();
        teamB.getsLD().ResetStats();
        teamB.getsRD().ResetStats();
        
    }
    
    public static boolean overtime(Team teamA, Team teamB, int overtimePeriodLength) {

        Simulation.periodLength = overtimePeriodLength;
        
        System.out.println("Are you ready?");
        System.out.println("I SAID Are you READY?");
        System.out.println("ITS OOVVVVEERRRTIIIMMEEEE!!");

        return period(0, teamA, teamB);
    }

    public static void shootout(Team teamA, Team teamB) {

        System.out.println("BOYS AND GIRLS,");
        System.out.println("LADIES AND GENTLEMEN,");
        System.out.println("WELCOME TO THE SHOOTOUT!");
        
        //shooting stats of Team A and Team B
        double[] shootoutA = new double[10];
        double[] shootoutB = new double[10];

        for (int i = 1; i < 11; i++) {
            shootoutA[i] = teamA.getRoster(i).getShooting();
            shootoutB[i] = teamB.getRoster(i).getShooting();
        }

        //stats of Team A goalie
        double reflexesA = teamA.getsG().getReflexes();
        double agilityA = teamA.getsG().getAgility();
        double flexibilityA = teamA.getsG().getFlexibility();
        double overallA = (reflexesA + agilityA + flexibilityA)/3;

        //stats of Team B goalie
        double reflexesB = teamB.getsG().getReflexes();
        double agilityB = teamB.getsG().getAgility();
        double flexibilityB = teamB.getsG().getFlexibility();
        double overallB = (reflexesB + agilityB + flexibilityB)/3;

        //calculating the outcome of the shootout
        int teamScoreA;
        int teamScoreB;
        int loopTracker;
        double[] probabilityA = new double[9];
        double[] probabilityB = new double[9];

        for (int i = 1; i < 11; i++) {
            probabilityA[i] = (50 + shootoutA[i] - overallB);
            if (probabilityA[i] > getRandom(0, 100)) {
                teamScoreA++;
                System.out.println(teamA.getRoster(i).getLastName() + " has scored!");
            } else {
                System.out.println(teamA.getRoster(i).getLastName() + " missed!");
            }

            probabilityB[i] = (50 + shootoutB[i] - overallA);
            if (probabilityB[i] > getRandom(0, 100)) {
                teamScoreB++;
                System.out.println(teamB.getRoster(i).getLastName() + " has scored!");
            } else {
                System.out.println(teamB.getRoster(i).getLastName() + " missed!");
            }

            //checking if a team has won 
            if ((teamScoreA == 2) && (teamScoreB == 0)) {
                endShootoutA(teamA, teamB);
                i = 10;
            } else if ((teamScoreA == 0) && (teamScoreB == 2)) {
                endShootoutB(teamA, teamB);
                i = 10;
            } else if (((teamScoreA > teamScoreB) && (i >= 3)) || ((teamScoreA > teamScoreB) && (loopTracker > 0))) {
                endShootoutA(teamA, teamB);
            } else if (((teamScoreA < teamScoreB) && (i >= 3)) || ((teamScoreA < teamScoreB) && (loopTracker > 0))) {
                endShootoutB(teamA, teamB);
            } else if ((teamScoreA == teamScoreB) && (i == 10)) {
                i = 0;
                loopTracker++;
            }
        }   
    }

    //method called if Team A wins the shootout
    public static void endShootoutA(Team teamA, Team teamB) {
        System.out.println(teamA.getTeamName() + " has won the game!");
        System.out.println("Final Score: " + (teamA.getScore() + 1) + "-" + (teamB.getScore()) + " (SO)");
    }

    //method called if Team B wins the shootout
    public static void endShootoutB(Team teamA, Team teamB) {
        System.out.println(teamB.getTeamName() + " has won the game!");
        System.out.println("Final Score: " + (teamB.getScore() + 1) + "-" + (teamA.getScore()) + " (SO)");
    }

}
/*
     __  _________________  ______  ____ _____
   /  |/  / ____/_  __/ / / / __ \/ __ / ___/
  / /|_/ / __/   / / / /_/ / / / / / / \__ \ 
 / /  / / /___  / / / __  / /_/ / /_/ ___/ / 
/_/  /_/_____/ /_/ /_/ /_/\____/_____/____/ 

*/