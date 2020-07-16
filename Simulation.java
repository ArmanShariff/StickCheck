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

        

        return matchupA();

    }

    public static int getRandom(int min, int max) {


        return 
    }






}