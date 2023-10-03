package soccerteam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The SwingSoccerTeamView class represents the graphical user interface (GUI) view of the Soccer Team application
 * using Swing components. It provides methods to display player information, handle user inputs, and interact
 * with the user interface.
 */
public class SwingSoccerTeamView extends JFrame implements SoccerTeamView {
  private JButton addPlayerButton;
  private JTextField addFirstNameInput;
  private JTextField addLastNameInput;
  private JTextField yearInput;
  private JTextField monthInput;
  private JTextField dayInput;
  private JComboBox preferredPositionInput;
  private JComboBox skillLevelInput;
  private JTextField jerseyNumberInput;
  private JButton removePlayerButton;
  private JLabel currentTeamRoster;
  private JList teamList;
  private JButton generateStartingLineupButton;
  private JLabel startingLineup;
  private JList startingLineupList;
  private JLabel message;

  /**
   * Constructs a SwingSoccerTeamView object with the specified title.
   *
   * @param title The title of the GUI window.
   */
  public SwingSoccerTeamView(String title) {
    // set the JFrame
    this.setTitle(title);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1200, 1000);
    this.setLocation(300, 200);
    this.setLayout(new BorderLayout());

    // set the panel of adding a player
    JPanel addPlayerPanel = new JPanel();
    addPlayerPanel.setLayout(new GridLayout(6, 2, 10, 10));

    addPlayerPanel.add(new JLabel("First Name"));
    addFirstNameInput = new JTextField(10);
    addPlayerPanel.add(addFirstNameInput);


    addPlayerPanel.add(new JLabel("Last Name"));
    addLastNameInput = new JTextField(10);
    addPlayerPanel.add(addLastNameInput);


    addPlayerPanel.add(new JLabel("Date Of Birth"));
    JPanel dobPanel = new JPanel();
    yearInput = new JTextField(4);
    monthInput = new JTextField(2);
    dayInput = new JTextField(2);
    dobPanel.add(new JLabel("Year:"));
    dobPanel.add(yearInput);
    dobPanel.add(new JLabel("Month"));
    dobPanel.add(monthInput);
    dobPanel.add(new JLabel("Day:"));
    dobPanel.add(dayInput);
    addPlayerPanel.add(dobPanel);

    addPlayerPanel.add(new JLabel("Preferred Position"));
    preferredPositionInput = new JComboBox<>(Position.values());
    preferredPositionInput.setSelectedIndex(-1);
    addPlayerPanel.add(preferredPositionInput);

    addPlayerPanel.add(new JLabel("Skill Level"));
    this.skillLevelInput = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
    skillLevelInput.setSelectedIndex(-1);
    addPlayerPanel.add(skillLevelInput);

    this.addPlayerButton = new JButton("Add Player");
    addPlayerPanel.add(addPlayerButton);

    // set the panel of removing a player by jersey number
    JPanel removePlayerPanel = new JPanel();
    removePlayerPanel.setLayout(new GridLayout(6, 2, 10, 10));

    removePlayerPanel.add(new JLabel("Enter the jersey number to remove a player:"));

    removePlayerPanel.add(new JLabel("# Jersey Number"));
    jerseyNumberInput = new JTextField(3);
    removePlayerPanel.add(jerseyNumberInput);

    removePlayerButton = new JButton("Remove Player");
    removePlayerPanel.add(removePlayerButton);

    // put addPlayer and removePlayer panels in the frame
    JPanel playerPanelsContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
    playerPanelsContainer.add(addPlayerPanel);
    playerPanelsContainer.add(removePlayerPanel);
    this.add(playerPanelsContainer, BorderLayout.NORTH);

    // set the panel of presenting team rosters
    JPanel teamPanel = new JPanel();
    teamPanel.setLayout(new BorderLayout());

    currentTeamRoster = new JLabel("Current Team Roster:");
    teamPanel.add(currentTeamRoster, BorderLayout.NORTH);

    DefaultListModel<String> listModel = new DefaultListModel<>();
    teamList = new JList<>(listModel);
    teamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JScrollPane teamScrollPane = new JScrollPane(teamList);
    teamScrollPane.setPreferredSize(new Dimension(500, 300));
    teamPanel.add(teamScrollPane, BorderLayout.CENTER);

    // set the generate starting lineup button
    this.generateStartingLineupButton = new JButton("Generate Starting Lineup");
    teamPanel.add(generateStartingLineupButton, BorderLayout.SOUTH);
    this.add(teamPanel, BorderLayout.WEST);

    // set the panel of presenting starting lineup
    JPanel startingLineupPanel = new JPanel();
    startingLineupPanel.setLayout(new BorderLayout());
    startingLineup = new JLabel("Staring Lineup:");
    startingLineupPanel.add(startingLineup, BorderLayout.NORTH);

    DefaultListModel<String> listModel1 = new DefaultListModel<>();
    startingLineupList = new JList<>(listModel1);
    startingLineupList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JScrollPane startingLineupScrollPane = new JScrollPane(startingLineupList);
    startingLineupScrollPane.setPreferredSize(new Dimension(500, 300));
    startingLineupPanel.add(startingLineupScrollPane, BorderLayout.CENTER);
    this.add(startingLineupPanel, BorderLayout.EAST);

    // set the JLabel of presenting error message
    message = new JLabel();
    message.setForeground(Color.RED);
    message.setFont(new Font("Arial", Font.BOLD, 15));
    this.add(message, BorderLayout.PAGE_END);

    this.pack();
  }

  @Override
  public void display() {
    this.setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    this.addPlayerButton.addActionListener(listener);
    this.addPlayerButton.setActionCommand("Add Player");

    this.removePlayerButton.addActionListener(listener);
    this.removePlayerButton.setActionCommand("Remove Player");

    this.generateStartingLineupButton.addActionListener(listener);
    this.generateStartingLineupButton.setActionCommand("Generate Starting Lineup");
  }

  @Override
  public String getAddFirstName() throws IllegalArgumentException {
    if (addFirstNameInput.getText().isEmpty()) {
      throw new IllegalArgumentException("The player's name cannot be empty. Please try again.");
    } else if (!addFirstNameInput.getText().matches("[a-zA-Z]+")) {
      throw new IllegalArgumentException("Invalid name input. Please try again.");
    }
    return addFirstNameInput.getText();
  }

  @Override
  public String getAddLastName() throws IllegalArgumentException {
    if (addLastNameInput.getText().isEmpty()) {
      throw new IllegalArgumentException("The player's name cannot be empty. Please try again.");
    } else if (!addLastNameInput.getText().matches("[a-zA-Z]+")) {
      throw new IllegalArgumentException("Invalid name input. Please try again.");
    }
    return addLastNameInput.getText();
  }

  @Override
  public int getBirthYear() throws IllegalArgumentException {
    if (yearInput.getText().isEmpty()) {
      throw new IllegalArgumentException("The birth date cannot be empty. Please try again.");
    } else if (!yearInput.getText().matches("\\d+")) {
      throw new IllegalArgumentException("Invalid birth date input. Please try again.");
    }
    return Integer.parseInt(yearInput.getText());
  }

  @Override
  public int getBirthMonth() throws IllegalArgumentException {
    if (monthInput.getText().isEmpty()) {
      throw new IllegalArgumentException("The birth date cannot be empty. Please try again.");
    } else if (!monthInput.getText().matches("\\d+")) {
      throw new IllegalArgumentException("Invalid birth date input. Please try again.");
    }
    return Integer.parseInt(monthInput.getText());
  }

  @Override
  public int getBirthDay() throws IllegalArgumentException {
    if (dayInput.getText().isEmpty()) {
      throw new IllegalArgumentException("The birth date cannot be empty. Please try again.");
    } else if (!dayInput.getText().matches("\\d+")) {
      throw new IllegalArgumentException("Invalid birth date input. Please try again.");
    }
    return Integer.parseInt(dayInput.getText());
  }

  @Override
  public Position getPreferredPosition() throws IllegalArgumentException {
    Object selectedItem = preferredPositionInput.getSelectedItem();
    if (selectedItem == null) {
      throw new IllegalArgumentException("The preferred position cannot be empty. Please try again.");
    }
    Position result = null;
    if (selectedItem.equals(Position.GOALIE)) {
      result = Position.GOALIE;
    } else if (selectedItem.equals(Position.DEFENDER)) {
      result = Position.DEFENDER;
    } else if (selectedItem.equals(Position.MIDFIELDER)) {
      result = Position.MIDFIELDER;
    } else if (selectedItem.equals(Position.FORWARD)) {
      result = Position.FORWARD;
    }

    return result;
  }

  @Override
  public int getSkillLevel() throws IllegalArgumentException {
    Object skillLevel = skillLevelInput.getSelectedItem();
    if (skillLevel == null) {
      throw new IllegalArgumentException("The skill level cannot be empty. Please try again.");
    }
    int result = 0;
    if (skillLevel.equals(1)) {
      result = 1;
    } else if (skillLevel.equals(2)) {
      result = 2;
    } else if (skillLevel.equals(3)) {
      result = 3;
    } else if (skillLevel.equals(4)) {
      result = 4;
    } else if (skillLevel.equals(5)) {
      result = 5;
    }

    return result;
  }

  @Override
  public int getJerseyNumber() throws IllegalArgumentException {
    if (jerseyNumberInput.getText().isEmpty()) {
      throw new IllegalArgumentException("The jersey number cannot be empty. Please try again.");
    } else if (!jerseyNumberInput.getText().matches("\\d+")
        || Integer.parseInt(jerseyNumberInput.getText()) < 1) {
      throw new IllegalArgumentException("Invalid jersey number input. Please try again.");
    }
    return Integer.parseInt(jerseyNumberInput.getText());
  }

  @Override
  public void displayMessage(String s) {
    message.setText(s);
  }

  @Override
  public void displayPlayers(String s) {
    String[] playerInfoArray = s.split("\n");
    DefaultListModel<String> listModel = new DefaultListModel<>();
    for (String playerInfo : playerInfoArray) {
      listModel.addElement(playerInfo);
    }
    teamList.setModel(listModel);
  }

  @Override
  public void displayStartingLineup(String s) {
    String[] playerInfoArray = s.split("\n");
    DefaultListModel<String> listModel = new DefaultListModel<>();
    for (String playerInfo : playerInfoArray) {
      listModel.addElement(playerInfo);
    }
    startingLineupList.setModel(listModel);
  }
}
