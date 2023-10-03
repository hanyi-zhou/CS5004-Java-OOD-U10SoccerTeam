package soccerteam;

import java.time.LocalDate;

/**
 * Represents a soccer player with various attributes and methods related to
 * the player's information.
 */
public interface Player {

  /**
   * Gets the first name of the player.
   *
   * @return The first name of the player.
   */
  String getFirstName();

  /**
   * Gets the last name of the player.
   *
   * @return The last name of the player.
   */
  String getLastName();

  /**
   * Gets the date of birth of the player.
   *
   * @return The date of birth of the player.
   */
  LocalDate getDateOfBirth();

  /**
   * Gets the preferred playing position of the player.
   *
   * @return The preferred playing position of the player.
   */
  Position getPreferredPosition();

  /**
   * Gets the starting playing position of the player in the team's lineup.
   *
   * @return The starting playing position of the player.
   */
  Position getStartingPosition();

  /**
   * Gets the skill level of the player.
   *
   * @return The skill level of the player.
   */
  int getSkillLevel();

  /**
   * Gets the jersey number of the player.
   *
   * @return The jersey number of the player.
   */
  int getJerseyNumber();

  /**
   * Sets the starting playing position of the player in the team's lineup.
   *
   * @param startingPosition The starting playing position to be set for the player.
   */
  void setStartingPosition(Position startingPosition);

  /**
   * Sets the jersey number of the player.
   *
   * @param jerseyNumber The jersey number to be assigned to the player.
   */
  void setJerseyNumber(int jerseyNumber);

  /**
   * Returns a string representation of the player.
   *
   * @return A string representation of the player.
   */
  String toString();

  /**
   * Compares this player to the specified object for equality. Returns true if and only if the specified
   * object is also a PlayerImpl and has the same attributes as this player.
   *
   * @param o The object to compare with this player for equality.
   * @return {@code true} if the objects are equal, {@code false} otherwise.
   */
  boolean equals(Object o);
}
