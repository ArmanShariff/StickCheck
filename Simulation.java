class Simulation {

    // fields

    Team teamA;
    Team teamB;

    // constructor

    public Simulation(Team teamA, Team teamB) {

        this.teamA = teamA;
        this.teamB = teamB;

    }

    public static boolean faceoffCalculation(int time, Team teamA, Team teamB) {

        // check if period is over

        if (time > 1200) {

            return false;

        }

        else {

            // get each teams centers faceoff stats
            int faceoffA = teamA.getsC().getFaceoff();
            int faceoffB = teamB.getsC().getFaceoff();

            // chance of team A winning the faceoff:
            int chance = 50 + (faceoffA - faceoffB);
            int random = getRandom(1,100);

            if (random <= chance) {

                // team A is on offence
                return matchupCalculation(time + 2, teamA, teamB);

            }
            else {

                // team B is on offence
                return matchupCalculation(time + 2, teamB, teamA);

            }

        }

    }

    public static boolean shotCalculation(int time, Team offensiveTeam, Team defencisveTeam) {
        // this is still a WIP
    }

    public static boolean rebound(int time, Team offensiveTeam, Team defencisveTeam) {
        
        int Oteam = 0;
        int Dteam = 0;



        

    }

    public static int reboundCalculation (int Oteam, int Dteam, Player oPlayer, Player dPlayer) {
        
        if (oPlayer.getStrength() > dPlayer.getStrength()) {
            Oteam = Oteam + 1;
        }
        else {
            Dteam = Dteam + 1;
        }

        if (oPlayer.getSkating() > dPlayer.getSkating()) {
            Oteam = Oteam + 1;
        }
        else {
            Dteam = Dteam + 1;
        }

        if (oPlayer.getOffensiveAwareness() > dPlayer.getOffensiveAwareness()) {
            Oteam = Oteam + 1;
        }
        else {
            Dteam = Dteam + 1;
        }

        if (oPlayer.getDefensiveAwareness() > dPlayer.getDefensiveAwareness()) {
            Oteam = Oteam + 1;
        }
        else {
            Dteam = Dteam + 1;
        }

        
    }


    public static int getRandom(int min, int max) {

        int random_int = (int)(Math.random() * (max - min + 1) + min);

        return random_int;
    }






}