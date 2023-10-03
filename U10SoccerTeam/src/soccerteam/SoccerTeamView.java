package soccerteam;

import java.awt.event.ActionListener;

/**
 * The SoccerTeamView interface defines the contract for a user interface view in the SoccerTeam application.
 * It specifies the methods that a view implementation must provide to interact with the user and display information.
 */
public interface SoccerTeamView {

  /**
   * Displays the user interface to the user.
   */
  void display();

  /**
   * Adds an ActionListener to the view's interactive components to handle user actions.
   *
   * @param listener The ActionListener to be added.
   */
  void addActionListener(ActionListener listener);

  /**
   * Retrieves the first name input provided by the user for adding a player.
   *
   * @return The user-provided first name.
   * @throws IllegalArgumentException If the input is invalid or empty.
   */
  String getAddFirstName() throws IllegalArgumentException;

  /**
   * Retrieves the last name input provided by the user for adding a player.
   *
   * @return The user-provided last name.
   * @throws IllegalArgumentException If the input is invalid or empty.
   */
  String getAddLastName() throws IllegalArgumentException;

  /**
   * Retrieves the birth year input provided by the user.
   *
   * @return The user-provided birth year.
   * @throws IllegalArgumentException If the input is invalid or empty.
   */
  int getBirthYear() throws IllegalArgumentException;

  /**
   * Retrieves the birth month input provided by the user.
   *
   * @return The user-provided birth month.
   * @throws IllegalArgumentException If the input is invalid or empty.
   */
  int getBirthMonth() throws IllegalArgumentException;

  /**
   * Retrieves the birth day input provided by the user.
   *
   * @return The user-provided birth day.
   * @throws IllegalArgumentException If the input is invalid or empty.
   */
  int getBirthDay() throws IllegalArgumentException;

  /**
   * Retrieves the preferred playing position selected by the user.
   *
   * @return The user-selected preferred playing position.
   * @throws IllegalArgumentException If the selection is invalid or empty.
   */
  Position getPreferredPosition() throws IllegalArgumentException;

  /**
   * Retrieves the skill level input selected by the user.
   *
   * @return The user-selected skill level.
   * @throws IllegalArgumentException If the selection is invalid or empty.
   */
  int getSkillLevel() throws IllegalArgumentException;

  /**
   * Retrieves the jersey number input provided by the user.
   *
   * @return The user-provided jersey number.
   * @throws IllegalArgumentException If the input is invalid or empty.
   */
  int getJerseyNumber() throws IllegalArgumentException;

  /**
   * Displays a message to the user on the interface.
   *
   * @param s The message to be displayed.
   */
  void displayMessage(String s);

  /**
   * Displays a list of player information to the user on the interface.
   *
   * @param s The player information to be displayed.
   */
  void displayPlayers(String s);


  /**
   * Displays a list of starting lineup information to the user on the interface.
   *
   * @param s The starting lineup information to be displayed.
   */
  void displayStartingLineup(String s);
}
