package soccerteam;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 * Represents a soccer player with various attributes and methods related to
 * the player's information.
 */
public class PlayerImpl implements Player {
  private String firstName;
  private String lastName;
  private LocalDate dateOfBirth;
  private Position preferredPosition;
  private Position startingPosition;
  private int skillLevel;
  private int jerseyNumber;
  private static final int LOWEST_SKILL_LEVEL = 1;
  private static final int HIGHEST_SKILL_LEVEL = 5;
  private static final int MAX_AGE_UNDER_TEN = 10;

  /**
   * Constructs a new player with the specified attributes.
   *
   * @param firstName        The first name of the player.
   * @param lastName         The last name of the player.
   * @param dateOfBirth      The date of birth of the player.
   * @param preferredPosition The preferred playing position of the player.
   * @param skillLevel       The skill level of the player.
   * @throws IllegalArgumentException If any of the input values are invalid.
   */
  public PlayerImpl(String firstName, String lastName, LocalDate dateOfBirth,
      Position preferredPosition, int skillLevel) throws IllegalArgumentException {

    if (firstName.isEmpty() || lastName.isEmpty()) {
      throw new IllegalArgumentException("First name and last name cannot be empty.");
    }

    LocalDate currentDate = LocalDate.now();
    int age = Period.between(dateOfBirth, currentDate).getYears();
    if (age >= MAX_AGE_UNDER_TEN) {
      throw new IllegalArgumentException(String.format(
          "The player must be under %d years old.", MAX_AGE_UNDER_TEN));
    } else if (age < 0) {
      throw new IllegalArgumentException("Invalid birth date.");
    }

    if (preferredPosition == null) {
      throw new IllegalArgumentException("Preferred position cannot be empty.");
    }

    if (skillLevel < LOWEST_SKILL_LEVEL || skillLevel > HIGHEST_SKILL_LEVEL) {
      throw new IllegalArgumentException(String.format(
          "Skill level must be between %d and %d.", LOWEST_SKILL_LEVEL, HIGHEST_SKILL_LEVEL));
    }

    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.preferredPosition = preferredPosition;
    this.skillLevel = skillLevel;
  }

  @Override public String getFirstName() {
    return this.firstName;
  }

  @Override public String getLastName() {
    return this.lastName;
  }

  @Override public LocalDate getDateOfBirth() {
    return this.dateOfBirth;
  }

  @Override public Position getPreferredPosition() {
    return this.preferredPosition;
  }

  @Override public Position getStartingPosition() {
    return this.startingPosition;
  }

  @Override public int getSkillLevel() {
    return this.skillLevel;
  }

  @Override public int getJerseyNumber() {
    return this.jerseyNumber;
  }

  @Override public void setStartingPosition(Position startingPosition) {
    this.startingPosition = startingPosition;
  }

  @Override public void setJerseyNumber(int jerseyNumber) {
    this.jerseyNumber = jerseyNumber;
  }

  @Override
  public String toString() {
    return String.format("%s %s(#%d) - Preferred Position: %s - Skill Level: %s\n",
        firstName, lastName, jerseyNumber, preferredPosition, skillLevel);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlayerImpl player = (PlayerImpl) o;
    return Objects.equals(firstName, player.firstName)
        && Objects.equals(lastName, player.lastName)
        && Objects.equals(dateOfBirth, player.dateOfBirth)
        && preferredPosition == player.preferredPosition
        && skillLevel == player.skillLevel;
  }
}
