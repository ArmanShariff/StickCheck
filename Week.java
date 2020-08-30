import java.util.*;

public class Week {
    
    // ArrayList that stores the games being played this week
    ArrayList<Game> gameList = new ArrayList<Game>();

    public Week(String[] gamesInWeek, ArrayList<Team> teamList) {
        createWeek(gamesInWeek, teamList);
    }

	public void createWeek(String[] gamesInWeek, ArrayList<Team> teamList) {
        // TO:DO use a for loop or something instead, so we can have weeks with less or more than 3 games
        Game newGame1 = new Game(gamesInWeek[1], gamesInWeek[2], teamList);
        Game newGame2 = new Game(gamesInWeek[3], gamesInWeek[4], teamList);
        Game newGame3 = new Game(gamesInWeek[5], gamesInWeek[6], teamList);
        gameList.add(newGame1);
        gameList.add(newGame2);
        gameList.add(newGame3);
    }

}