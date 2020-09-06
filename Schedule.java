import java.io.*;
import java.util.*;

public class Schedule {
    
    // ArrayList that stores all the weeks of the regular season
    ArrayList<Week> weekList = new ArrayList<Week>();
    // ArrayList that stores all the teams in the league
    ArrayList<Team> teamList = new ArrayList<Team>();
    // File that needs to be read to get data for the season schedule
    String fileName;
    // Keeps track of current week of the season
    int currentWeek;
    // Tracks user team
    Team userTeam;
    
    // constructor

    public Schedule (String fileName, ArrayList<Team> teamList) throws FileNotFoundException {
        this.fileName = fileName;
        this.teamList = teamList;
        this.currentWeek = 1;
        readFile(fileName, teamList);
    }

    // methods

    public int getCurrentWeek() {
        return this.currentWeek;
    }

    public Week getWeek(int i) {
        return this.weekList.get(i);
    }
    
    public void readFile(String fileName, ArrayList<Team> teamList)
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

            createSchedule(temp, teamList);

            // close the Scanner object attached to the file
            inFile.close();
        }
    }
    
    public void createSchedule(ArrayList<String[]> temp, ArrayList<Team> teamList) {

        for (int i = 0; i < temp.size(); i++) {
            Week newWeek = new Week(temp.get(i), teamList);
            this.weekList.add(newWeek);

        }

    }

    public void advanceWeek() {
        this.currentWeek = this.currentWeek + 1;
    }

    public void simulateGame(int game) {
        Simulation simulation = new Simulation(this.weekList.get(this.currentWeek).getGame(game).getHomeTeam(), this.weekList.get(this.currentWeek).getGame(game).getAwayTeam(), false);
    }

    public void simulateWeek() {
        
        for (int i = 0; i < 3; i++) {
            if (userTeam.getTeamName().equals(this.weekList.get(this.currentWeek).getGame(i).getAwayTeamName())) {
                TextAreaLogProgram newTextAreaLogProgram = new TextAreaLogProgram(this.weekList.get(this.currentWeek).getGame(i).getHomeTeam(), userTeam);
            }
            else if (userTeam.getTeamName().equals(this.weekList.get(this.currentWeek).getGame(i).getHomeTeamName())) {
                TextAreaLogProgram newTextAreaLogProgram = new TextAreaLogProgram(userTeam, this.weekList.get(this.currentWeek).getGame(i).getAwayTeam());
            }
            else {
                simulateGame(i);
            }
        }
    }

    public String toString() {
        String printStatement = "";
        
        for (int i = 1; i < weekList.size(); i++) {
            printStatement += "Week " + i + ": ";
            for (int j = 0; j < 3; j++) {
                printStatement += weekList.get(i).gameList.get(j).getHomeTeamName() + " VS " + weekList.get(i).gameList.get(j).getAwayTeamName() + "  ";
            }
            printStatement += "\n";
        }

        return printStatement;
    }

}