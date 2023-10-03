package soccerteam;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a soccer team that consists of players.
 * The team should have at least 10 players and at most 20 players.
 * The team has a 7 players starting lineup with positions
 * (1 goalie, 2 defenders, 3 midfielders, and 1 forward).
 * Players not in the starting lineup are benched players, with no positions.
 */
public interface Team {

  /**
   * Adds a player to the team with the provided information.
   *
   * @param firstName The first name of the player.
   * @param lastName The last name of the player.
   * @param dateOfBirth The date of birth of the player.
   * @param preferredPosition The preferred playing position of the player.
   * @param skillLevel The skill level of the player.
   * @throws IllegalArgumentException If the player is already in the team or
   *    the provided skill level is invalid.
   */
  void addPlayer(String firstName, String lastName, LocalDate dateOfBirth,
                Position preferredPosition, int skillLevel) throws IllegalArgumentException;

  /**
   * Creates a team by finalizing the player roster and assigning jersey numbers to players.
   *
   */
  void createTeam();

  /**
   * Gets the current size of the team.
   *
   * @return The number of players in the team.
   */
  int getSize();

  /**
   * Retrieves a list of all players in the team.
   *
   * @return A list containing all players in the team.
   */
  List<Player> getTeamPlayers();

  /**
   * Retrieves a list of players in the starting lineup.
   *
   * @return A list containing players in the starting lineup.
   */
  List<Player> getStartingLineup();

  /**
   * Removes a player from the team based on their jersey number.
   * The player is removed only if they are found in the team.
   * The player is removed if they are found in the staring lineup.
   *
   * @param jerseyNumber The jersey number of the player to be removed.
   *
   * @throws IllegalArgumentException If the player to be moved is not on the team.
   */
  void removePlayer(int jerseyNumber) throws IllegalArgumentException;

  /**
   * Sets the starting lineup for the team, ensuring that the team has been
   * created and all players have been assigned
   * jersey numbers. Throws an {@link IllegalStateException} if the team size is
   * below the minimum required size or if
   * there are players without assigned jersey numbers.
   *
   * @throws IllegalStateException If the team size is below the minimum required
   *    size or if there are players without assigned jersey numbers.
   */
  void setStartingLineup() throws IllegalStateException;

  /**
   * Generates a formatted list of all players in the team, sorted by last name and
   * including their jersey numbers.
   *
   * @return A formatted string containing the list of all players in the team.
   * @throws IllegalStateException If the current team size is below the minimum
   *    required or if there are players without jersey numbers.
   */
  String getAllPlayersList() throws IllegalStateException;

  /**
   * Generates a formatted list of the starting lineup players, including their jersey
   * numbers and starting positions.
   *
   * @return A formatted string containing the list of players in the starting lineup.
   * @throws IllegalStateException If the current team size is below the minimum required,
   *    if there are players without jersey numbers, or if the current starting lineup size is below the required size.
   */
  String getStartingLineupList() throws IllegalArgumentException;

  /**
   * Represents a string representation of the team.
   *
   * @return A string representation of the team.
   */
  String toString();
}
