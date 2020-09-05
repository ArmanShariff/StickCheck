import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.*;
import java.net.URL;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GUI {

    // frame
    JFrame frame;
    // keeping track of which screen the player is on
    String progress;
    // keeping track of what team the player selects
    ArrayList<Team> teamList;
    Team teamPlayer;
    Team team1, team2;
    // title screen components
    JPanel titleNamePanel, startButtonPanel;
    JLabel titleNameLabel;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 78);
    JButton startButton;
    startButtonHandler sBH = new startButtonHandler();
    Font normalFont = new Font("Calibri", Font.PLAIN, 30);
    // header components
    JPanel undoButtonPanel;
    JButton undoButton;
    undoButtonHandler uBH = new undoButtonHandler();
    // team selection screen components
    JPanel textAreaPanel, teamSelectionPanel;
    JTextArea textArea;
    JButton torSelect, monSelect, bosSelect, nyrSelect, detSelect, chiSelect;
    String teamSelected;
    teamSelectHandler tSH = new teamSelectHandler();
    // team summary screen components
    JPanel cityNamePanel, proceedButtonPanel, teamStatsPanel;
    JTextArea cityName, teamStats;
    JButton proceedButton;
    String teamName, teamOverall, teamSalaryCap, teamMarketSize, teamStatus;
    proceedButtonHandler pBH = new proceedButtonHandler();
    // option selection screen components
    JPanel optionsPanel, startSimPanel;
    JButton calendarButton, statisticsButton, editLinesButton, startSimButton;
    String optionSelected;
    optionsButtonHandler oBH = new optionsButtonHandler();
    // calendar screen components
    // statistics screen components
    JPanel teamRosterPanel, goalsScoredPanel, plusMinusPanel, shotsPanel, shootingPercentPanel, savePercentPanel,
            goaliePanel, shotsAgainstPanel, savesPanel;
    JTextArea teamRoster, goalsScored, plusMinus, shots, shootingPercent, savePercent, goalie, shotsAgainst, saves;
    String teamRosterString = "Player\n", goalsScoredString = "Goals\n", plusMinusString = "Plus-Minus\n",
            shotsString = "Shots\n", shootingPercentString = "Shooting Percentage\n",
            savePercentString = "Save Percentage\n", goalieString = "\n", shotsAgainstString = "Shots Against\n",
            savesString = "Saves\n";
    // edit lines screen components
    JPanel line1OffPanel, line2OffPanel, line1DefPanel, line2DefPanel, goalie1Panel, goalie2Panel;
    JButton p1Button, p2Button, p3Button, p4Button, p5Button, p6Button, p7Button, p8Button, p9Button, p10Button;
    JTextArea line1, line2;
    String line1String = "Line 1", line2String = "Line 2";
    // begin simulation screen components

    public GUI(ArrayList<Team> teamList, Team team1, Team team2) throws IOException, LineUnavailableException,
            UnsupportedAudioFileException {

        this.team1 = team1;
        this.team2 = team2;
        this.teamList = teamList;
        // creating frame
        frame = new JFrame();
        frame.setSize(1275, 685);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        // frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // frame.setUndecorated(true);
        // frame.setState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(Color.black);
        frame.setLayout(null);
        // adding background image to frame
        titleScreen();
    }

    public void titleScreen() throws IOException, LineUnavailableException, UnsupportedAudioFileException { // creates
                                                                                                            // title
                                                                                                            // screen

        URL url = new URL(
                          "https://file-examples-com.github.io/uploads/2017/11/file_example_WAV_1MG.wav");
        Clip clip = AudioSystem.getClip();
        // getAudioInputStream() also accepts a File or InputStream
        AudioInputStream ais = AudioSystem.getAudioInputStream( url );
        clip.open(ais);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // A GUI element to prevent the Clip's daemon Thread 
                // from terminating at the end of the main()
                JOptionPane.showMessageDialog(null, "Close to exit!");
            }
        });

        progress = "Title Screen";
        // creating title name
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(180, 50, 900, 500);
        titleNamePanel.setBackground(Color.black);
        
        // add a picture (stick check logo)
        BufferedImage myPicture = ImageIO.read(new File("StickCheckLogoFinal.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        titleNamePanel.add(picLabel);

        // creating start button
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(490, 570, 300, 75);
        startButtonPanel.setBackground(Color.black);

        startButton = new JButton("Start Game");
        startButton.setBackground(Color.white); // sets the colour of the button itself
        startButton.setForeground(Color.black); // sets the colour of the button's text
        startButton.setFont(normalFont);        // sets the font of the button's text to match normalFont
        startButton.addActionListener(sBH);     // adds an action listener to the start button
        startButton.setFocusPainted(false);     // removes additional "box" around the text (so only the outline of the
                                                // button itself shows)
        startButtonPanel.add(startButton);
        // adding components to the frame
        frame.add(titleNamePanel);
        frame.add(startButtonPanel);
        frame.setVisible(true);
    }

    public void teamSelectScreen() throws IOException { // creates team selection screen

        progress = "Team Selection Screen";
        // disabling previous screen panels
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        // creating header
        undoButtonPanel = new JPanel();
        undoButtonPanel.setBounds(0, 0, 75, 75);
        undoButtonPanel.setBackground(Color.black);
        Icon undoIcon = new ImageIcon("ArrowLeft.png");
        undoButton = new JButton(undoIcon);
        undoButton.setBackground(Color.black);
        undoButton.setForeground(Color.white);
        undoButton.addActionListener(uBH);
        undoButton.setFocusPainted(false);
        undoButtonPanel.add(undoButton);

        // creating text area
        textAreaPanel = new JPanel();
        textAreaPanel.setBounds(150, 40, 1000, 100);
        textAreaPanel.setBackground(Color.blue);

        textArea = new JTextArea("Team Select");
        textArea.setBounds(150, 40, 1000, 100);
        textArea.setBackground(Color.blue); // sets the colour of the text area itself
        textArea.setForeground(Color.white); // sets the colour of the text area's text
        textArea.setFont(normalFont); // sets the font of the text area's text to match normalFont
        textArea.setLineWrap(true); // if the text is too long for one line, it will automatically continue on a new
                                    // line
        textAreaPanel.add(textArea);

        // creating team selection buttons
        teamSelectionPanel = new JPanel();
        teamSelectionPanel.setBounds(150, 200, 1000, 400);
        teamSelectionPanel.setBackground(Color.red);
        teamSelectionPanel.setLayout(new GridLayout(2, 3));

        Icon torIcon = new ImageIcon("LeafsLogo.png");
        torSelect = new JButton(torIcon); // creating Toronto button
        torSelect.setBackground(Color.blue);
        torSelect.setForeground(Color.white);
        torSelect.setFont(normalFont);
        torSelect.setFocusPainted(false);
        torSelect.addActionListener(tSH); // adds an action listener to the team select button (in this case, Toronto)
        torSelect.setActionCommand("Toronto"); // indicates which team has been selected
        teamSelectionPanel.add(torSelect);

        Icon monIcon = new ImageIcon("CanadiensLogo.png");
        monSelect = new JButton(monIcon); // creating Montreal button
        monSelect.setBackground(Color.red);
        monSelect.setForeground(Color.white);
        monSelect.setFont(normalFont);
        monSelect.setFocusPainted(false);
        monSelect.addActionListener(tSH);
        monSelect.setActionCommand("Montreal");
        teamSelectionPanel.add(monSelect);

        Icon bosIcon = new ImageIcon("BruinsLogo.png");
        bosSelect = new JButton(bosIcon); // creating Boston button
        bosSelect.setBackground(Color.yellow);
        bosSelect.setForeground(Color.black);
        bosSelect.setFont(normalFont);
        bosSelect.setFocusPainted(false);
        bosSelect.addActionListener(tSH);
        bosSelect.setActionCommand("Boston");
        teamSelectionPanel.add(bosSelect);

        Icon nyrIcon = new ImageIcon("RangersLogo.png");
        nyrSelect = new JButton(nyrIcon); // creating New York button
        nyrSelect.setBackground(Color.white);
        nyrSelect.setForeground(Color.blue);
        nyrSelect.setFont(normalFont);
        nyrSelect.setFocusPainted(false);
        nyrSelect.addActionListener(tSH);
        nyrSelect.setActionCommand("New York");
        teamSelectionPanel.add(nyrSelect);

        Icon detIcon = new ImageIcon("WingsLogo.png");
        detSelect = new JButton(detIcon); // creating Detroit button
        detSelect.setBackground(Color.white);
        detSelect.setForeground(Color.red);
        detSelect.setFont(normalFont);
        detSelect.setFocusPainted(false);
        detSelect.addActionListener(tSH);
        detSelect.setActionCommand("Detroit");
        teamSelectionPanel.add(detSelect);

        Icon chiIcon = new ImageIcon("BlackHawksLogo.png");
        chiSelect = new JButton(chiIcon); // creating Chicago button
        chiSelect.setBackground(Color.red);
        chiSelect.setForeground(Color.yellow);
        chiSelect.setFont(normalFont);
        chiSelect.setFocusPainted(false);
        chiSelect.addActionListener(tSH);
        chiSelect.setActionCommand("Chicago");
        teamSelectionPanel.add(chiSelect);

        // adding components to the frame
        frame.add(textAreaPanel);
        frame.add(teamSelectionPanel);
        frame.add(undoButtonPanel);

    }

    public void teamSummaryScreen() {

        progress = "Team Summary Screen";
        // disabling previous screen panels
        textAreaPanel.setVisible(false);
        teamSelectionPanel.setVisible(false);

        // creating proceed button
        proceedButtonPanel = new JPanel();
        proceedButtonPanel.setBounds(600, 275, 100, 50);
        proceedButtonPanel.setBackground(Color.gray);

        proceedButton = new JButton("Proceed");
        proceedButton.setBackground(Color.gray);
        proceedButton.setForeground(Color.white);
        proceedButton.setFont(normalFont);
        proceedButton.setFocusPainted(false);
        proceedButton.addActionListener(pBH);
        proceedButtonPanel.add(proceedButton);

        // creating team name area
        cityNamePanel = new JPanel();
        cityNamePanel.setBounds(150, 200, 1000, 100);
        cityNamePanel.setBackground(Color.blue);

        cityName = new JTextArea(teamSelected);
        cityName.setBounds(150, 100, 1000, 100);
        cityName.setBackground(Color.blue); // sets the colour of the text area itself
        cityName.setForeground(Color.white); // sets the colour of the text area's text
        cityName.setFont(normalFont); // sets the font of the text area's text to match normalFont
        cityNamePanel.add(cityName);

        // creating team stats area
        teamStatsPanel = new JPanel();
        teamStatsPanel.setBounds(150, 450, 1000, 200);
        teamStatsPanel.setBackground(Color.blue);

        teamStats = new JTextArea(teamOverall + "\n" + teamSalaryCap + "\n" + teamMarketSize + "\n" + teamStatus);
        teamStats.setBounds(150, 250, 1000, 100);
        teamStats.setBackground(Color.blue);
        teamStats.setForeground(Color.white);
        teamStats.setFont(normalFont);
        teamStatsPanel.add(teamStats);

        // adding components to the frame
        frame.add(proceedButtonPanel);
        frame.add(cityNamePanel);
        frame.add(teamStatsPanel);

    }

    public void optionSelectScreen() {

        progress = "Options Select Screen";
        // disabling previous screen panels
        proceedButtonPanel.setVisible(false);
        cityNamePanel.setVisible(false);
        teamStatsPanel.setVisible(false);

        // creating panel for the options
        optionsPanel = new JPanel();
        optionsPanel.setBounds(150, 100, 1000, 200);
        optionsPanel.setBackground(Color.blue);
        optionsPanel.setLayout(new GridLayout(1, 3));

        // creating option buttons
        calendarButton = new JButton("Calendar");
        calendarButton.setBackground(Color.blue);
        calendarButton.setForeground(Color.white);
        calendarButton.setFont(normalFont);
        calendarButton.setFocusPainted(false);
        calendarButton.addActionListener(oBH);
        calendarButton.setActionCommand("Calendar");
        optionsPanel.add(calendarButton);

        statisticsButton = new JButton("Statistics");
        statisticsButton.setBackground(Color.blue);
        statisticsButton.setForeground(Color.white);
        statisticsButton.setFont(normalFont);
        statisticsButton.setFocusPainted(false);
        statisticsButton.addActionListener(oBH);
        statisticsButton.setActionCommand("Statistics");
        optionsPanel.add(statisticsButton);

        editLinesButton = new JButton("Edit Lines");
        editLinesButton.setBackground(Color.blue);
        editLinesButton.setForeground(Color.white);
        editLinesButton.setFont(normalFont);
        editLinesButton.setFocusPainted(false);
        editLinesButton.addActionListener(oBH);
        editLinesButton.setActionCommand("Edit Lines");
        optionsPanel.add(editLinesButton);

        // creating the button to start the simulation
        startSimPanel = new JPanel();
        startSimPanel.setBounds(150, 350, 1000, 200);
        startSimPanel.setBackground(Color.blue);

        startSimButton = new JButton("Begin Simulation");
        startSimButton.setBackground(Color.blue);
        startSimButton.setForeground(Color.white);
        startSimButton.setFont(normalFont);
        startSimButton.setFocusPainted(false);
        startSimPanel.add(startSimButton);

        // adding components to the frame
        frame.add(optionsPanel);
        frame.add(startSimPanel);
    }

    public void calendarScreen(Team team1, Team team2) {
        progress = "Calendar Screen";
        // disabling previous panels
        optionsPanel.setVisible(false);
        startSimPanel.setVisible(false);

        new TextAreaLogProgram(team1, team2).setVisible(true);
        //TextAreaLogProgram printGame = new TextAreaLogProgram(team1, team2);
    }

    public void statisticsScreen() {

        progress = "Statistics Screen";
        // disabling previous panels
        optionsPanel.setVisible(false);
        startSimPanel.setVisible(false);

        //gathering team stats from simulation
        for (int i = 0; i < 10; i++) {
            teamRosterString += ("\n" + teamPlayer.getRoster(i).getFirstName() + " " + teamPlayer.getRoster(i).getLastName());
            goalsScoredString += ("\n" + teamPlayer.getRoster(i).getGoals());
            plusMinusString += ("\n" + teamPlayer.getRoster(i).getPlusMinus());
            shotsString += ("\n" + teamPlayer.getRoster(i).getShots());
            shootingPercentString += ("\n" + teamPlayer.getRoster(i).getShootingPercentage());
            if (i < 2) {
                goalieString += ("\n" + teamPlayer.getGoalieRoster(i).getFirstName() + " " + teamPlayer.getGoalieRoster(i).getLastName());
                shotsAgainstString += ("\n" + teamPlayer.getGoalieRoster(i).getShotsAgainst());
                savesString += ("\n" + teamPlayer.getGoalieRoster(i).getSaves());
                savePercentString += ("\n" + teamPlayer.getGoalieRoster(i).getSavePercentage());
            }
        }   

        // creating team roster display
        teamRosterPanel = new JPanel();
        teamRosterPanel.setBounds(100, 20, 300, 470);
        teamRosterPanel.setBackground(Color.blue);

        teamRoster = new JTextArea(teamRosterString);
        teamRoster.setBounds(100, 20, 300, 470);
        teamRoster.setBackground(Color.blue);
        teamRoster.setForeground(Color.white);
        teamRoster.setFont(normalFont);
        teamRosterPanel.add(teamRoster);

        //creating goals scored column
        goalsScoredPanel = new JPanel();
        goalsScoredPanel.setBounds(400, 20, 100, 470);

        goalsScored = new JTextArea(goalsScoredString);
        goalsScored.setBounds(400, 20, 100, 470);
        goalsScored.setForeground(Color.black);
        goalsScored.setFont(normalFont);
        goalsScoredPanel.add(goalsScored);

        //creating +/- column
        plusMinusPanel = new JPanel();
        plusMinusPanel.setBounds(500, 20, 150, 470);

        plusMinus = new JTextArea(plusMinusString);
        plusMinus.setBounds(500, 20, 150, 470);
        plusMinus.setForeground(Color.black);
        plusMinus.setFont(normalFont);
        plusMinusPanel.add(plusMinus);

        //creating shots column
        shotsPanel = new JPanel();
        shotsPanel.setBounds(650, 20, 100, 470);

        shots = new JTextArea(shotsString);
        shots.setBounds(650, 20, 100, 470);
        shots.setForeground(Color.black);
        shots.setFont(normalFont);
        shotsPanel.add(shots);

        //creating shooting % column
        shootingPercentPanel = new JPanel();
        shootingPercentPanel.setBounds(750, 20, 300, 470);

        shootingPercent = new JTextArea(shootingPercentString);
        shootingPercent.setBounds(750, 20, 300, 470);
        shootingPercent.setForeground(Color.black);
        shootingPercent.setFont(normalFont);
        shootingPercentPanel.add(shootingPercent);

        //creating goalie name column
        goaliePanel = new JPanel();
        goaliePanel.setBounds(100, 490, 300, 150);

        goalie = new JTextArea(goalieString);
        goalie.setBounds(100, 490, 300, 150);
        goalie.setForeground(Color.black);
        goalie.setFont(normalFont);
        goaliePanel.add(goalie);

        //creating shots against column
        shotsAgainstPanel = new JPanel();
        shotsAgainstPanel.setBounds(400, 490, 200, 150);

        shotsAgainst = new JTextArea(shotsAgainstString);
        shotsAgainst.setForeground(Color.black);
        shotsAgainst.setFont(normalFont);
        shotsAgainstPanel.add(shotsAgainst);

        //creating saves column
        savesPanel = new JPanel();
        savesPanel.setBounds(600, 490, 100, 150);

        saves = new JTextArea(savesString);
        saves.setForeground(Color.black);
        saves.setFont(normalFont);
        savesPanel.add(saves);

        //creating save % column
        savePercentPanel = new JPanel();
        savePercentPanel.setBounds(700, 490, 300, 150);

        savePercent = new JTextArea(savePercentString);
        savePercent.setForeground(Color.black);
        savePercent.setFont(normalFont);
        savePercentPanel.add(savePercent);

        // adding components to the frame
        frame.add(teamRosterPanel);
        frame.add(goalsScoredPanel);
        frame.add(plusMinusPanel);
        frame.add(shotsPanel);
        frame.add(shootingPercentPanel);
        frame.add(goaliePanel);
        frame.add(shotsAgainstPanel);
        frame.add(savesPanel);
        frame.add(savePercentPanel);
    }

    public void editLinesScreen() {
        progress = "Edit Lines Screen";
        // disabling previous panels
        optionsPanel.setVisible(false);
        startSimPanel.setVisible(false);

        line1OffPanel = new JPanel();
        line1OffPanel.setBounds(140, 80, 1000, 70);
        line1OffPanel.setBackground(Color.blue);
        line1OffPanel.setLayout(new FlowLayout());

        line2OffPanel = new JPanel();
        line2OffPanel.setBounds(140, 150, 1000, 70);
        line2OffPanel.setBackground(Color.blue);
        line2OffPanel.setLayout(new FlowLayout());

        line1DefPanel = new JPanel();
        line1DefPanel.setBounds(250, 220, 755, 70);
        line1DefPanel.setBackground(Color.blue);
        line1DefPanel.setLayout(new FlowLayout());

        line2DefPanel = new JPanel();
        line2DefPanel.setBounds(250, 290, 755, 70);
        line2DefPanel.setBackground(Color.blue);
        line2DefPanel.setLayout(new FlowLayout());

        goalie1Panel = new JPanel();
        goalie1Panel.setBounds(300, 360, 600, 70);
        goalie1Panel.setBackground(Color.blue);
        goalie1Panel.setLayout(new FlowLayout());

        goalie2Panel = new JPanel();
        goalie2Panel.setBounds(300, 430, 600, 70);
        goalie2Panel.setBackground(Color.blue);
        goalie2Panel.setLayout(new FlowLayout());

        JButton[] buttonArray = new JButton[10];
        JButton goalie1 = new JButton(teamPlayer.getGoalieRoster(0).getFirstName() + " " + teamPlayer.getGoalieRoster(0).getLastName());
        goalie1.setPreferredSize(new Dimension (200, 70));
        JButton goalie2 = new JButton(teamPlayer.getGoalieRoster(1).getFirstName() + " " + teamPlayer.getGoalieRoster(1).getLastName());
        goalie2.setPreferredSize(new Dimension (200, 70));
        
        int x = 0;
            // add fowards to the array of buttons
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    buttonArray[x] = new JButton(teamPlayer.getTeamLines().getForwardLine(i, j).getFirstName() + " " + teamPlayer.getTeamLines().getForwardLine(i, j).getLastName());
                    buttonArray[x].setPreferredSize(new Dimension (200, 70));
                    x ++;
                }
            }

            // add defence to the array
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    buttonArray[x] = new JButton(teamPlayer.getTeamLines().getDefenceLine(i, j).getFirstName() + " " + teamPlayer.getTeamLines().getDefenceLine(i, j).getLastName());
                    buttonArray[x].setPreferredSize(new Dimension (200, 70));
                    x ++;
                }
            }

        line1OffPanel.add(buttonArray[0]);
        line1OffPanel.add(buttonArray[1]);
        line1OffPanel.add(buttonArray[2]);

        line2OffPanel.add(buttonArray[3]);
        line2OffPanel.add(buttonArray[4]);
        line2OffPanel.add(buttonArray[5]);

        line1DefPanel.add(buttonArray[6]);
        line1DefPanel.add(buttonArray[7]);

        line2DefPanel.add(buttonArray[8]);
        line2DefPanel.add(buttonArray[9]);
        
        goalie1Panel.add(goalie1);
        goalie2Panel.add(goalie2);

        frame.add(line1OffPanel);
        frame.add(line2OffPanel);
        frame.add(line1DefPanel);
        frame.add(line2DefPanel);
        frame.add(goalie1Panel);
        frame.add(goalie2Panel);
    }

    public class startButtonHandler implements ActionListener { // dictates the action that happens when start button is
                                                                // pressed
        public void actionPerformed(ActionEvent e) {
            try {
                teamSelectScreen();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    public class undoButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (progress.equals("Team Selection Screen")) {
                undoButtonPanel.setVisible(false);
                textAreaPanel.setVisible(false);
                teamSelectionPanel.setVisible(false);
                try {
                    titleScreen();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (LineUnavailableException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (UnsupportedAudioFileException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else if (progress.equals("Team Summary Screen")) {
                proceedButtonPanel.setVisible(false);
                cityNamePanel.setVisible(false);
                teamStatsPanel.setVisible(false);
                try {
                    teamSelectScreen();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else if (progress.equals("Options Select Screen")) {
                optionsPanel.setVisible(false);
                startSimPanel.setVisible(false);
                teamSummaryScreen();
            } else if (progress.equals("Calendar Screen")) {
                
                optionSelectScreen();
            } else if (progress.equals("Statistics Screen")) {
                teamRosterPanel.setVisible(false);
                goalsScoredPanel.setVisible(false);
                plusMinusPanel.setVisible(false);
                shotsPanel.setVisible(false);
                shootingPercentPanel.setVisible(false);
                goaliePanel.setVisible(false);
                savesPanel.setVisible(false);
                savePercentPanel.setVisible(false);
                shotsAgainstPanel.setVisible(false);
                optionSelectScreen();
            } else if (progress.equals("Edit Lines Screen")) {
                line1OffPanel.setVisible(false);
                line2OffPanel.setVisible(false);
                line1DefPanel.setVisible(false);
                line2DefPanel.setVisible(false);
                goalie1Panel.setVisible(false);
                goalie2Panel.setVisible(false);
                optionSelectScreen();
            }
        }
    }
 
    public class teamSelectHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            teamSelected = e.getActionCommand(); //will place the selected action command into the teamSelected string 
            if (teamSelected.equals("Toronto")) {
                teamOverall = "Overall: 89";
                teamSalaryCap = "Salary Cap: $95,178,332";
                teamMarketSize = "Market Size: Large";
                teamStatus = "Status: Contender";
                teamPlayer = teamList.get(0);
            } else if (teamSelected.equals("Montreal")) {
                teamOverall = "Overall: 87";
                teamSalaryCap = "Salary Cap: $76,960,206";
                teamMarketSize = "Market Size: Large";
                teamStatus = "Status: Hopeful";
                teamPlayer = teamList.get(4);
            } else if (teamSelected.equals("Boston")) {
                teamOverall = "Overall: 89";
                teamSalaryCap = "Salary Cap: $81,653,080";
                teamMarketSize = "Market Size: Medium";
                teamStatus = "Status: Contender";
                teamPlayer = teamList.get(5);
            } else if(teamSelected.equals("New York")) {
                teamOverall = "Overall: 86";
                teamSalaryCap = "Salary Cap: $77,613,415";
                teamMarketSize = "Market Size: Large";
                teamStatus = "Status: Hopeful";
                teamPlayer = teamList.get(1);
            } else if (teamSelected.equals("Detroit")) {
                teamOverall = "Overall: 82";
                teamSalaryCap = "Salary Cap: $79,968,736";
                teamMarketSize = "Market Size: Medium";
                teamStatus = "Status: Rebuilder";
                teamPlayer = teamList.get(2);
            } else {
                teamOverall = "Overall: 85";
                teamSalaryCap = "Salary Cap: $82,524,710";
                teamMarketSize = "Market Size: Medium";
                teamStatus = "Status: Hopeful";
                teamPlayer = teamList.get(3);
            }
            teamSummaryScreen();
        }
    }
 
    public class proceedButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            optionSelectScreen();
        }
    }
 
    public class optionsButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            optionSelected = e.getActionCommand();
            if (optionSelected.equals("Calendar")) {
                calendarScreen(team1, team2);
            } else if (optionSelected.equals("Statistics")) {
                statisticsScreen();
            } else {
                editLinesScreen();
            }
        }
    }

    
 
 
}
 
/*
public class GUI {  
    public static void main(String[] args) {  
 
        //creating frame
        JFrame frame = new JFrame("Stick Check");  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1650, 685);
        frame.setLayout(null);
 
        //creating textfield
        final TextField tf = new TextField();  
        tf.setBounds(50,50, 150,20);  
 
        //creating button
        JButton startButton = new JButton("Start Game");
        startButton.setBounds(490, 450, 300, 75);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tf.setText("clicked");
            }
        });
 
        frame.add(startButton);frame.add(tf);    
        frame.setVisible(true);   
    }  
    }   
 
    public static void main(String args[]){
        GUI test = new GUI();
        
       //music();
    }
 
    MediaPlayer mediaPlayer;
    public void music() {
        String s = "action.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();
    }   
}
*/