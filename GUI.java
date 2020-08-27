import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class GUI {

    // frame
    JFrame frame;
    // keeping track of which screen the player is on
    String progress;
    // keeping track of what team the player selects
    ArrayList<Team> teamList;
    Team teamPlayer;
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
    JPanel teamRosterPanel, goalsScoredPanel, plusMinusPanel, shotsPanel, shootingPercentPanel, savePercentPanel;
    JTextArea teamRoster, goalsScored, plusMinus, shots, shootingPercent, savePercent;
    String teamRosterString = "";
    // edit lines screen components
    JPanel editLinesPanel;
    
    public GUI(ArrayList<Team> teamList) throws IOException {

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

    public void titleScreen() throws IOException { // creates title screen

        progress = "Title Screen";
        // creating title name
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(200, 50, 900, 500);
        titleNamePanel.setBackground(Color.black);

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
        startButton.setFont(normalFont); // sets the font of the button's text to match normalFont
        startButton.addActionListener(sBH); // adds an action listener to the start button
        startButton.setFocusPainted(false); // removes additional "box" around the text (so only the outline of the
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

    public void calendarScreen() {

        progress = "Calendar Screen";
        // disabling previous panels
        optionsPanel.setVisible(false);
        startSimPanel.setVisible(false);

    }

    public void statisticsScreen() {

        progress = "Statistics Screen";
        // disabling previous panels
        optionsPanel.setVisible(false);
        startSimPanel.setVisible(false);

        // creating team roster display
        teamRosterPanel = new JPanel();
        teamRosterPanel.setBounds(100, 200, 400, 400);
        teamRosterPanel.setBackground(Color.blue);

        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                teamRosterString += (teamPlayer.getRoster(i).getFirstName() + " "
                        + teamPlayer.getRoster(i).getLastName());
            } else {
                teamRosterString += ("\n" + teamPlayer.getRoster(i).getFirstName() + " "
                        + teamPlayer.getRoster(i).getLastName());
            }
        }

        teamRoster = new JTextArea(teamRosterString);
        teamRoster.setBounds(100, 200, 400, 400);
        teamRoster.setBackground(Color.blue);
        teamRoster.setForeground(Color.white);
        teamRoster.setFont(normalFont);
        teamRosterPanel.add(teamRoster);

        // adding components to the frame
        frame.add(teamRosterPanel);

    }

    public void editLinesScreen() { // creates title screen

        progress = "Edit Lines";
        // creating title name
        editLinesPanel = new JPanel();
        editLinesPanel.setBounds(200, 50, 900, 500);
        editLinesPanel.setBackground(Color.black);

        // creating start button
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(490, 570, 300, 75);
        startButtonPanel.setBackground(Color.black);

        startButton = new JButton("Start Game");
        startButton.setBackground(Color.white); // sets the colour of the button itself
        startButton.setForeground(Color.black); // sets the colour of the button's text
        startButton.setFont(normalFont); // sets the font of the button's text to match normalFont
        startButton.addActionListener(sBH); // adds an action listener to the start button
        startButton.setFocusPainted(false); // removes additional "box" around the text (so only the outline of the
                                            // button itself shows)
        startButtonPanel.add(startButton);
        // adding components to the frame
        frame.add(titleNamePanel);
        frame.add(startButtonPanel);
        frame.setVisible(true);
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
 
                optionSelectScreen();
            } else if (progress.equals("Edit Lines Screen")) {
 
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
                calendarScreen();
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

