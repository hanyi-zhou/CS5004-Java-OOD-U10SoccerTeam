package soccerteam;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * The SoccerTeamControllerConsole class implements the SoccerTeamController interface and represents the controller
 * responsible for managing user interactions and orchestrating the application logic in a console-based environment.
 */
public class SoccerTeamControllerConsole implements SoccerTeamController, ActionListener {
  private Team team;
  private SoccerTeamView view;

  /**
   * Constructs a new instance of SoccerTeamControllerConsole with the given team and view.
   *
   * @param team The Team instance representing the soccer team data.
   * @param view The SoccerTeamView instance representing the user interface view.
   */
  public SoccerTeamControllerConsole(Team team, SoccerTeamView view) {
    this.team = team;
    this.view = view;
    view.addActionListener(this);
  }

  @Override
  public void go() {
    view.display();
  }

  @Override
  public void addPlayer() {
    // add the player to the team
    view.displayMessage("");
    view.displayStartingLineup("");
    String firstName = view.getAddFirstName();
    String lastName = view.getAddLastName();
    int birthYear = view.getBirthYear();
    int birthMonth = view.getBirthMonth();
    int birthDay = view.getBirthDay();
    Position preferredPosition = view.getPreferredPosition();
    int skillLevel = view.getSkillLevel();
    LocalDate dateOfBirth = LocalDate.of(birthYear, birthMonth, birthDay);
    team.addPlayer(firstName, lastName, dateOfBirth, preferredPosition, skillLevel);

    // display the current and up-to-date team roster to the user
    if (team.getSize() < 10) {
      team.createTeam();
      view.displayPlayers("Warning: The team cannot be created under 10 players.\nCurrent team size: "
          + team.getSize() +
          "\nList of all players added in the team so far (sorted by adding order):\n"
          + team.toString());
    } else {
      team.createTeam();
      view.displayPlayers("The team has be created.\nCurrent team size (max 20): "
          + team.getSize() +
          "\nList of all players in the team (sorted by last name):\n"
          + team.getAllPlayersList());
    }
  }

  @Override
  public void removePlayer() {
    view.displayMessage("");
    view.displayStartingLineup("");
    int jerseyNumber = view.getJerseyNumber();
    team.removePlayer(jerseyNumber);
    view.displayPlayers(team.toString());
    if (team.getSize() < 10) {
      team.createTeam();
      view.displayPlayers("Warning: The team cannot be created under 10 players.\nCurrent team size: "
          + team.getSize()
          + "\nList of all players added to the team so far (sorted by adding order):\n" +
          team.toString());
    } else {
      team.createTeam();
      view.displayPlayers("The team has be created.\nCurrent team size: "
          + team.getSize() +
          "\nList of all players in the team (sorted by last name):\n"
          + team.getAllPlayersList());
    }
  }

  @Override
  public void generateStartingLineup() {
    view.displayMessage("");
    team.createTeam();
    team.setStartingLineup();
    view.displayStartingLineup("Starting lineup (sorted by position and then last name):\n" +
        team.getStartingLineupList());
  }

  /**
   * Handles user actions performed through the view's interface elements by implementing the ActionListener interface.
   *
   * @param e The ActionEvent triggered by the user's action.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Add Player":
        try {
          addPlayer();
        } catch (IllegalArgumentException ex) {
            view.displayMessage("Error: " + ex.getMessage());
        } catch (DateTimeException ex) {
            view.displayMessage("Error: Invalid date, please try again.");
        }
        break;

      case "Remove Player":
        try {
          removePlayer();
        } catch (IllegalArgumentException ex) {
          view.displayMessage("Error: " + ex.getMessage());
        }
        break;

      case "Generate Starting Lineup":
        try {
          generateStartingLineup();
        } catch (IllegalStateException ex) {
          view.displayMessage("Error: " + ex.getMessage());
        }
        break;
    }
  }
}

