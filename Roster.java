import java.io.*;
import java.util.*;

public class Roster {
    
    // fields

    ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<Goalie> goalies = new ArrayList<Goalie>();
    ArrayList<Coach> coaches = new ArrayList<Coach>();
    String fileName;
    
    // constructor

    public Roster (String fileName) throws FileNotFoundException {
        
        this.fileName = fileName;
        readFile(fileName, players, goalies, coaches);

    }

    // methods

    public static void readFile(String fileName, ArrayList<Player> players, ArrayList<Goalie> goalies,ArrayList<Coach> coaches)
            throws FileNotFoundException {

        ArrayList<String[]> temp = new ArrayList<String[]>();

        File file = new File(fileName);
    
        // check that the file exists
        
        if ( file.exists() ) {

            // before trying to create a
            // Create a Scanner from the file. 
            // This statement can cause a FileNotFoundException.
                  
            Scanner inFile = new Scanner( file );
                  
            // For each line in the file, read the line and add it to the array list
            // Use the results of calling the hasNext method to 
            // determine if you are at the end of the file before 
            // reading the next line of the file.
                  
            while ( inFile.hasNext()) {

                // read the next line
                String line = inFile.nextLine();
                // Split line by "," and add it to the array list

                temp.add(line.split(","));
            }

            assignPerson(temp, players, goalies, coaches);

            // close the Scanner object attached to the file
            inFile.close();

        }

    }

    public static void assignPerson(ArrayList<String[]> temp, ArrayList<Player> players, ArrayList<Goalie> goalies, ArrayList<Coach> coaches) {

        String[][]temp2 = new String[temp.size()][23];
        temp.toArray(temp2);

        for (int x = 1; x <= 78; x++) {

            if (temp2[x][3].equals("Player")) {

                String firstName = temp2[x][0];
                String lastName = temp2[x][1];
                String currentTeam = temp2[x][2];
                String playerType = temp2[x][3];
                String position = temp2[x][4];
                int number = Integer.parseInt(temp2[x][5]);
                int age = Integer.parseInt(temp2[x][6]);
                String country = temp2[x][7];
                int faceoff = Integer.parseInt(temp2[x][8]);
                int shooting = Integer.parseInt(temp2[x][9]);
                int stamina = Integer.parseInt(temp2[x][10]);
                int skating = Integer.parseInt(temp2[x][11]);
                int strength = Integer.parseInt(temp2[x][12]);
                int offensiveAwareness = Integer.parseInt(temp2[x][13]);
                int defensiveAwareness = Integer.parseInt(temp2[x][14]);
                int shotTend = Integer.parseInt(temp2[x][24]);
                Player player = new Player(faceoff, shooting, stamina, skating, strength, offensiveAwareness, defensiveAwareness, shotTend, firstName, lastName, position, country, age, number, currentTeam, playerType);
                players.add(player); 

            } else if (temp2[x][3].equals("Goalie")) {

                String firstName = temp2[x][0];
                String lastName = temp2[x][1];
                String currentTeam = temp2[x][2];
                String playerType = temp2[x][3];
                String position = temp2[x][4];
                int number = Integer.parseInt(temp2[x][5]);
                int age = Integer.parseInt(temp2[x][6]);
                String country = temp2[x][7];
                int stamina = Integer.parseInt(temp2[x][10]);
                int reflexes = Integer.parseInt(temp2[x][15]);
                int agility = Integer.parseInt(temp2[x][16]);
                int flexibility = Integer.parseInt(temp2[x][17]);
                int reboundControl = Integer.parseInt(temp2[x][18]);
                int puckControl = Integer.parseInt(temp2[x][19]);
                Goalie goalie = new Goalie(stamina, reflexes, agility, flexibility, reboundControl, puckControl, firstName, lastName, position, country, age, number, currentTeam, playerType);
                goalies.add(goalie);
                
            } else {

                String firstName = temp2[x][0];
                String lastName = temp2[x][1];
                String currentTeam = temp2[x][2];
                String playerType = temp2[x][3];
                String position = temp2[x][4];
                int age = Integer.parseInt(temp2[x][6]);
                String country = temp2[x][7];
                String aptitude = temp2[x][21];
                String specialty = temp2[x][22];
                int rating = Integer.parseInt(temp2[x][23]);
                Coach coach = new Coach(aptitude, specialty, rating, firstName, lastName, position, country, age, currentTeam, playerType);
                coaches.add(coach);
                
            }
       

        } 

    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Goalie> getGoalies() {
        return goalies;
    }

    public ArrayList<Coach> getCoaches() {
        return coaches;
    }

}