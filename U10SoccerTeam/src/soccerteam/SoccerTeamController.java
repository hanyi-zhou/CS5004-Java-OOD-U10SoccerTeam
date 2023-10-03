package soccerteam;

/**
 * The SoccerTeamController interface defines the contract for controllers in the Soccer Team application.
 * Controllers are responsible for orchestrating the interactions between the user interface (View) and the underlying
 * data and logic (Model) of the application.
 */
public interface SoccerTeamController {

  /**
   * Initiates the execution of the controller, allowing it to perform necessary actions to manage the application flow.
   * This method typically involves setting up event listeners, displaying initial views, and responding to user interactions.
   */
  void go();

  /**
   * Handles the action of adding a player to the team.
   */
  void addPlayer();

  /**
   * Handles the action of removing a player from the team.
   */
  void removePlayer();

  /**
   * Handles the action of generating the starting lineup for the team.
   */
  void generateStartingLineup();
}
