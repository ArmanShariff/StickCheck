import java.lang.Math;
import java.lang.management.ThreadMXBean;

class Simulation {

    /*  
        _______ _______ ______ __     ______ _____
       /  ____/__   __/  ____/  /    / ___  \  ___/
      /  /__    /  / /  __/ /  /    / /  / /\__ \
     /  ___/ __/  /_/  /___/  /____/ /__/ /___/ /
    /__/   /_______/______/_______/______/_____/   

    */
    
    Team teamA;
    Team teamB;
    boolean isPlayoffGame;
    boolean isShootout = false;

    static int periodLength;
    static boolean isOvertime;
    static int playerDrops = 1;
    static int goalieDrops = 1;

    /*
        ______ ______ _   __ _____ ______ _______ _   __ ______ ______ ______ ______
       /  ___/ ___  / \  / /  ___/__  __/  __   / /  / /  ____/__  __/ ___  /  __  /
      /  /  / /  / /   \/ /\__ \   / / /  /_/__/ /  / /  /      / / / /  / /  /_/_/
     /  /__/ /__/ / /\   /___/ /  / / /  /\ \ / /__/ /  /____  / / / /__/ /  /\ \
    /_____/______/_/  \_/_____/  /_/ /__/  \_\ _____/_______/ /_/ /______/__/  \_\
    
    */

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
        // resets stamina/stats
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
                // resets stamina/stats
                if (teamA.getScore() == teamB.getScore()) {
                    teamResetStamina(teamA, teamB);
                    teamResetStats(teamA, teamB);
                    shootout(teamA, teamB);
                    isShootout = true;
                }
            }
        }

        //prints end-of-game message (exception: shootout)
        if (isShootout == false) {
            System.out.println("GAME OVER!!!!");
            System.out.println(teamA.getTeamName() + ": " + teamA.getScore());
            System.out.println(teamB.getTeamName() + ": " + teamB.getScore());
        }

        //prints team shot totals
        System.out.println(teamA.getTeamName() + " shots: " + teamA.getShotCount());
        System.out.println(teamB.getTeamName() + " shots: " + teamB.getShotCount());
        
        teamA.reSetScore();
        teamB.reSetScore();
        teamA.reSetShotCount();
        teamB.reSetShotCount();
    }

    /*  
        __  _________________  ______  ____ _____
       /  |/  / ____/_  __/ / / / __ \/ __ / ___/
      / /|_/ / __/   / / / /_/ / / / / / / \__ \ 
     / /  / / /___  / / / __  / /_/ / /_/ /___// 
    /_/  /_/_____/ /_/ /_/ /_/\____/_____/____/  
    
    */

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
            // drops players' stamina/stats (Faceoff)
            dropPlayerStamina(teamA, teamB);
            dropPlayerStats(teamA, teamB);
            System.out.println(teamA.getOnIce(1).getFirstName() + "'s stamina: " + teamA.getOnIce(1).getStaminaBar());
            System.out.println(teamA.getOnIce(1).getFirstName() + "'s skating: " + teamA.getOnIce(1).getSkating());
            System.out.println("Current Time: " + time);
            // get each teams centers faceoff stats
            double faceoffA = teamA.getOnIce(1).getFaceoff();
            double faceoffB = teamB.getOnIce(1).getFaceoff();

            // chance of team A winning the faceoff:
            double chance = 50 + (faceoffA - faceoffB);
            int random = getRandom(1,100);

            if (random <= chance) {
                // team A is on offence
                System.out.println(teamA.getOnIce(1).getFirstName() + " " + teamA.getOnIce(1).getLastName() + " won the faceoff!");
                
                return matchupCalculationOne(time + 2, teamA, teamB);

            } else {
                // team B is on offence
                System.out.println(teamB.getOnIce(1).getFirstName() + " " + teamB.getOnIce(1).getLastName() + " won the faceoff!");
                
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
            double skatingOLW = offensiveTeam.getOnIce(0).getSkating();
            double skatingOC = offensiveTeam.getOnIce(1).getSkating();
            double skatingORW = offensiveTeam.getOnIce(2).getSkating();
            double skatingOLD = offensiveTeam.getOnIce(3).getSkating();
            double skatingORD = offensiveTeam.getOnIce(4).getSkating();
            double strengthOLW = offensiveTeam.getOnIce(0).getStrength();
            double strengthOC = offensiveTeam.getOnIce(1).getStrength();
            double strengthORW = offensiveTeam.getOnIce(2).getStrength();
            double strengthOLD = offensiveTeam.getOnIce(3).getStrength();
            double strengthORD = offensiveTeam.getOnIce(4).getStrength();
            double offAwarenessOLW = offensiveTeam.getOnIce(0).getOffensiveAwareness();
            double offAwarenessOC = offensiveTeam.getOnIce(1).getOffensiveAwareness();
            double offAwarenessORW = offensiveTeam.getOnIce(2).getOffensiveAwareness();
            double offAwarenessOLD = offensiveTeam.getOnIce(3).getOffensiveAwareness();
            double offAwarenessORD = offensiveTeam.getOnIce(4).getOffensiveAwareness();
            

            //defensive team values
            double skatingDLW = defensiveTeam.getOnIce(0).getSkating();
            double skatingDC = defensiveTeam.getOnIce(1).getSkating();
            double skatingDRW = defensiveTeam.getOnIce(2).getSkating();
            double skatingDLD = defensiveTeam.getOnIce(3).getSkating();
            double skatingDRD = defensiveTeam.getOnIce(4).getSkating();
            double strengthDLW = defensiveTeam.getOnIce(0).getStrength();
            double strengthDC = defensiveTeam.getOnIce(1).getStrength();
            double strengthDRW = defensiveTeam.getOnIce(2).getStrength();
            double strengthDLD = defensiveTeam.getOnIce(3).getStrength();
            double strengthDRD = defensiveTeam.getOnIce(4).getStrength();
            double defAwarenessDLW = defensiveTeam.getOnIce(0).getDefensiveAwareness();
            double defAwarenessDC = defensiveTeam.getOnIce(1).getDefensiveAwareness();
            double defAwarenessDRW = defensiveTeam.getOnIce(2).getDefensiveAwareness();
            double defAwarenessDLD = defensiveTeam.getOnIce(3).getDefensiveAwareness();
            double defAwarenessDRD = defensiveTeam.getOnIce(4).getDefensiveAwareness();

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
                // drops players' stamina/stats (Maintain puck possession)
                dropPlayerStamina(offensiveTeam, defensiveTeam);
                dropPlayerStats(offensiveTeam, defensiveTeam);
                System.out.println(offensiveTeam.getTeamName() + " retains possession!");

                return shotCalculation(time + getRandom(5, 20), offensiveTeam, defensiveTeam);

            } else {
                // drops players' stamina/stats (Puck stolen away)
                dropPlayerStamina(offensiveTeam, defensiveTeam);
                dropPlayerStats(offensiveTeam, defensiveTeam);
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

        // drops goalie stamina/stats (Every shot)
        dropGoalieStamina(offensiveTeam, defensiveTeam);
        dropGoalieStats(offensiveTeam, defensiveTeam);
        System.out.println(offensiveTeam.getsG().getFirstName() + "'s stamina: " + offensiveTeam.getsG().getStaminaBar());
        System.out.println(offensiveTeam.getsG().getFirstName() + "'s agility: " + offensiveTeam.getsG().getAgility());

        if (isGoal == true) {
            offensiveTeam.setScore();
            System.out.println("\n" + offensiveTeam.getTeamName() + "(" + offensiveTeam.getScore() + ")" + " - " + defensiveTeam.getTeamName() + "(" + defensiveTeam.getScore() + ")\n");
            
            if (isOvertime == true) {
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
                // drops players' stamina/stats (Rebound)
                dropPlayerStamina(offensiveTeam, defensiveTeam);
                dropPlayerStats(offensiveTeam, defensiveTeam);
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

        double chance = 120 - (goalie.getFlexibility() + goalie.getReboundControl())/2;
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
            double skatingOLW = offensiveTeam.getOnIce(0).getSkating();
            double skatingOC = offensiveTeam.getOnIce(1).getSkating();
            double skatingORW = offensiveTeam.getOnIce(2).getSkating();
            double skatingOLD = offensiveTeam.getOnIce(3).getSkating();
            double skatingORD = offensiveTeam.getOnIce(4).getSkating();
            double strengthOLW = offensiveTeam.getOnIce(0).getStrength();
            double strengthOC = offensiveTeam.getOnIce(1).getStrength();
            double strengthORW = offensiveTeam.getOnIce(2).getStrength();
            double strengthOLD = offensiveTeam.getOnIce(3).getStrength();
            double strengthORD = offensiveTeam.getOnIce(4).getStrength();
            double offAwarenessOLW = offensiveTeam.getOnIce(0).getOffensiveAwareness();
            double offAwarenessOC = offensiveTeam.getOnIce(1).getOffensiveAwareness();
            double offAwarenessORW = offensiveTeam.getOnIce(2).getOffensiveAwareness();
            double offAwarenessOLD = offensiveTeam.getOnIce(3).getOffensiveAwareness();
            double offAwarenessORD = offensiveTeam.getOnIce(4).getOffensiveAwareness();
            

            //defensive team values
            double skatingDLW = defensiveTeam.getOnIce(0).getSkating();
            double skatingDC = defensiveTeam.getOnIce(1).getSkating();
            double skatingDRW = defensiveTeam.getOnIce(2).getSkating();
            double skatingDLD = defensiveTeam.getOnIce(3).getSkating();
            double skatingDRD = defensiveTeam.getOnIce(4).getSkating();
            double strengthDLW = defensiveTeam.getOnIce(0).getStrength();
            double strengthDC = defensiveTeam.getOnIce(1).getStrength();
            double strengthDRW = defensiveTeam.getOnIce(2).getStrength();
            double strengthDLD = defensiveTeam.getOnIce(3).getStrength();
            double strengthDRD = defensiveTeam.getOnIce(4).getStrength();
            double defAwarenessDLW = defensiveTeam.getOnIce(0).getDefensiveAwareness();
            double defAwarenessDC = defensiveTeam.getOnIce(1).getDefensiveAwareness();
            double defAwarenessDRW = defensiveTeam.getOnIce(2).getDefensiveAwareness();
            double defAwarenessDLD = defensiveTeam.getOnIce(3).getDefensiveAwareness();
            double defAwarenessDRD = defensiveTeam.getOnIce(4).getDefensiveAwareness();

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

    // Method that drops every players' stamina
    // After: Faceoffs, fights for possession, rebounds 
    public static void dropPlayerStamina(Team teamA, Team teamB) {
        playerDrops = playerDrops++;

        for(int i = 0; i < 5; i++) {
            teamA.getOnIce(i).dropPlayerStamina(playerDrops);
        }

        for(int i = 0; i < 5; i++) {
            teamB.getOnIce(i).dropPlayerStamina(playerDrops);
        }
    }

    // Method that drops every goalies' stamina
    // After: Each shot
    public static void dropGoalieStamina(Team teamA, Team teamB) {
        goalieDrops = goalieDrops++;
        
        teamA.getsG().dropGoalieStamina(goalieDrops);
        teamB.getsG().dropGoalieStamina(goalieDrops);
    }
    
    //Resets every players' and goalies' stamina after the period ends
    public static void teamResetStamina(Team teamA, Team teamB) {
        playerDrops = 1;
        goalieDrops = 1;

        for(int i = 0; i < 5; i++) {
            teamA.getOnIce(i).setStaminaBar(1);
        }
        
        teamA.getsG().setStaminaBar(1);

        for(int i = 0; i < 5; i++) {
            teamB.getOnIce(i).setStaminaBar(1);
        }
    }

    //Drop stats of every player depending on their current stamina
    // >Drops everytime stamina drops
    public static void dropPlayerStats(Team teamA, Team teamB) {

        for(int i = 0; i < 5; i++) {
            teamA.getOnIce(i).dropPlayerStats(teamA.getOnIce(i).getStaminaBar());
        }

        for(int i = 0; i < 5; i++) {
            teamB.getOnIce(i).dropPlayerStats(teamA.getOnIce(i).getStaminaBar());
        }
    }

    //Drop stats of every goalie depending on their stamina
    // >Drops everytime stamina drops
    public static void dropGoalieStats(Team teamA, Team teamB) {
        teamA.getsG().dropGoalieStats(teamA.getsG().getStaminaBar());
        teamB.getsG().dropGoalieStats(teamB.getsG().getStaminaBar());
    }

    //Reset stats of every player after each period
    public static void teamResetStats(Team teamA, Team teamB) {

        for(int i = 0; i < 5; i++) {
            teamA.getOnIce(i).resetPlayerStats();
        }

        teamA.getsG().resetGoalieStats();

        for(int i = 0; i < 5; i++) {
            teamB.getOnIce(i).resetPlayerStats();
        }

        teamB.getsG().resetGoalieStats();
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

        for (int i = 0; i < 10; i++) {
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
        int teamScoreA = 0;
        int teamScoreB = 0;
        int loopTracker = 0;
        double[] probabilityA = new double[10];
        double[] probabilityB = new double[10];

        shootoutLoop:
        for (int i = 1; i < 11; i++) {

            System.out.println(" ");
            System.out.println("Round " + (loopTracker*10 + i));

            //shootout attempt for Team A
            probabilityA[i-1] = (50 + shootoutA[i-1] - overallB);
            if (probabilityA[i-1] > getRandom(0, 100)) {
                teamScoreA++;
                System.out.println(teamA.getRoster(i-1).getLastName() + " scored!");
            } else {
                System.out.println(teamA.getRoster(i-1).getLastName() + " missed!");
            }

            if ((teamScoreA < teamScoreB) && (i == 3) && (loopTracker == 0)) {
                endShootoutB(teamA, teamB);
                break shootoutLoop;
            } else if ((teamScoreA - 1 > teamScoreB) && (i == 3) && (loopTracker == 0)) {
                endShootoutA(teamA, teamB);
                break shootoutLoop;
            }

            //shootout attempt for Team B
            probabilityB[i-1] = (50 + shootoutB[i-1] - overallA);
            if (probabilityB[i-1] > getRandom(0,100)) {
                teamScoreB++;
                System.out.println(teamB.getRoster(i-1).getLastName() + " scored!");
            } else {
                System.out.println(teamB.getRoster(i-1).getLastName() + " missed!");
            }

            //checking if a team has won 
            if ((teamScoreA == 2) && (teamScoreB == 0)) {
                endShootoutA(teamA, teamB);
                break shootoutLoop;
            } else if ((teamScoreA == 0) && (teamScoreB == 2)) {
                endShootoutB(teamA, teamB);
                break shootoutLoop;
            } else if (((teamScoreA > teamScoreB) && (i >= 3)) || ((teamScoreA > teamScoreB) && (loopTracker > 0))) {
                endShootoutA(teamA, teamB);
                break shootoutLoop;
            } else if (((teamScoreA < teamScoreB) && (i >= 3)) || ((teamScoreA < teamScoreB) && (loopTracker > 0))) {
                endShootoutB(teamA, teamB);
                break shootoutLoop;
            } else if ((teamScoreA == teamScoreB) && (i == 10)) {
                i = 0;
                loopTracker++;
            }
        }   
    }

    //method called if Team A wins the shootout
    public static void endShootoutA(Team teamA, Team teamB) {
        System.out.println(" ");
        System.out.println(teamA.getTeamName() + " has won the game!");
        System.out.println("Final Score: " + (teamA.getScore() + 1) + "-" + (teamB.getScore()) + " (SO)");
    }

    //method called if Team B wins the shootout
    public static void endShootoutB(Team teamA, Team teamB) {
        System.out.println(" ");
        System.out.println(teamB.getTeamName() + " has won the game!");
        System.out.println("Final Score: " + (teamA.getScore() + 1) + "-" + (teamB.getScore()) + " (SO)");
    }

}
