package soccerteam;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Test class for the {@link PlayerImpl} class.
 */
public class TestPlayerImpl {
  private PlayerImpl validPlayer;

  /**
   * Sets up a valid player instance for testing.
   */
  @Before
  public void setUp() {
    validPlayer = new PlayerImpl("John", "Doe", LocalDate.of(2015, 5, 15),
        Position.GOALIE, 4);
  }

  /**
   * Tests the attributes of a valid player.
   */
  @Test
  public void testValidPlayer() {
    assertEquals("John", validPlayer.getFirstName());
    assertEquals("Doe", validPlayer.getLastName());
    assertEquals(LocalDate.of(2015, 5, 15), validPlayer.getDateOfBirth());
    assertEquals(Position.GOALIE, validPlayer.getPreferredPosition());
    assertEquals(4, validPlayer.getSkillLevel());
  }

  /**
   * Tests creating a player instance with invalid first name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFirstName() {
    new PlayerImpl("", "Doe", LocalDate.of(2015, 5, 15),
        Position.GOALIE, 4);
  }

  /**
   * Tests creating a player instance with invalid last name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLastName() {
    new PlayerImpl("John", "", LocalDate.of(2015, 5, 15),
        Position.GOALIE, 4);
  }

  /**
   * Tests creating a player instance with invalid age.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAge() {
    new PlayerImpl("Alice", "Smith", LocalDate.of(2013, 3, 20),
        Position.DEFENDER, 3);
  }

  /**
   * Tests creating a player instance with invalid preferred position.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPreferredPosition() {
    new PlayerImpl("Bob", "Johnson", LocalDate.of(2017, 8, 10),
        null, 5);
  }

  /**
   * Tests creating a player instance with skill level below the range 1-5.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSkillLevelLow() {
    new PlayerImpl("Emma", "Brown", LocalDate.of(2015, 1, 5),
        Position.FORWARD, 0);
  }

  /**
   * Tests creating a player instance with skill level beyond the range 1-5.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSkillLevelHigh() {
    new PlayerImpl("Michael", "Williams", LocalDate.of(2016, 9, 25),
        Position.GOALIE, 6);
  }

  /**
   * Tests setting the starting position for a player.
   */
  @Test
  public void testSetStartingPosition() {
    validPlayer.setStartingPosition(Position.DEFENDER);
    assertEquals(Position.DEFENDER, validPlayer.getStartingPosition());
  }

  /**
   * Tests getting the starting position for a player.
   */
  @Test
  public void testGetStartingPosition() {
    validPlayer.setStartingPosition(Position.GOALIE);
    assertEquals(Position.GOALIE, validPlayer.getStartingPosition());
  }

  /**
   * Tests setting the jersey number for a player.
   */
  @Test
  public void testSetJerseyNumber() {
    validPlayer.setJerseyNumber(10);
    assertEquals(10, validPlayer.getJerseyNumber());
  }

  /**
   * Tests the string representation of a player.
   */
  @Test
  public void testToString() {
    validPlayer.setJerseyNumber(10);
    assertEquals("John Doe(#10) - Preferred Position: GOALIE - Skill Level: 4\n", validPlayer.toString());
  }

  /**
   * Tests equal players.
   */
  @Test
  public void testEqualPlayers() {
    Player player1 = new PlayerImpl("John", "Doe",
        LocalDate.of(2015, 5, 10), Position.MIDFIELDER, 4);
    Player player2 = new PlayerImpl("John", "Doe",
        LocalDate.of(2015, 5, 10), Position.MIDFIELDER, 4);
    assertTrue(player1.equals(player2));
  }

  /**
   * Tests equals() with null.
   */
  @Test
  public void testEqualPlayersNull() {
    Player player1 = new PlayerImpl("John", "Doe",
        LocalDate.of(2015, 5, 10), Position.MIDFIELDER, 4);
    assertFalse(player1.equals(null));
  }

  /**
   * Tests equals() with different class instance.
   */
  @Test
  public void testEqualPlayersDifferentClass() {
    Player player1 = new PlayerImpl("John", "Doe",
        LocalDate.of(2015, 5, 10), Position.MIDFIELDER, 4);
    Team team = new TeamImpl();
    assertFalse(player1.equals(team));
  }

  /**
   * Tests equals() with different first name.
   */
  @Test
  public void testEqualPlayersDifferentFirstName() {
    Player player1 = new PlayerImpl("John", "Doe",
        LocalDate.of(2015, 5, 10), Position.MIDFIELDER, 4);
    Player player2 = new PlayerImpl("Joe", "Doe",
        LocalDate.of(2015, 5, 10), Position.MIDFIELDER, 4);
    assertFalse(player1.equals(player2));
  }

  /**
   * Tests equals() with different last name.
   */
  @Test
  public void testEqualPlayersDifferentLastName() {
    Player player1 = new PlayerImpl("John", "Doe",
        LocalDate.of(2015, 5, 10), Position.MIDFIELDER, 4);
    Player player2 = new PlayerImpl("John", "Davis",
        LocalDate.of(2015, 5, 10), Position.MIDFIELDER, 4);
    assertFalse(player1.equals(player2));
  }

  /**
   * Tests equals() with different date of birth.
   */
  @Test
  public void testEqualPlayersDifferentDateOfBirth() {
    Player player1 = new PlayerImpl("John", "Doe",
        LocalDate.of(2015, 5, 10), Position.MIDFIELDER, 4);
    Player player2 = new PlayerImpl("John", "Doe",
        LocalDate.of(2015, 5, 11), Position.MIDFIELDER, 4);
    assertFalse(player1.equals(player2));
  }

  /**
   * Tests equals() with different preferred position.
   */
  @Test
  public void testEqualPlayersDifferentPreferredPosition() {
    Player player1 = new PlayerImpl("John", "Doe",
        LocalDate.of(2015, 5, 10), Position.MIDFIELDER, 4);
    Player player2 = new PlayerImpl("John", "Doe",
        LocalDate.of(2015, 5, 11), Position.GOALIE, 4);
    assertFalse(player1.equals(player2));
  }

  /**
   * Tests equals() with different skill level.
   */
  @Test
  public void testEqualPlayersDifferentSkillLevel() {
    Player player1 = new PlayerImpl("John", "Doe",
        LocalDate.of(2015, 5, 10), Position.MIDFIELDER, 4);
    Player player2 = new PlayerImpl("John", "Doe",
        LocalDate.of(2015, 5, 11), Position.MIDFIELDER, 1);
    assertFalse(player1.equals(player2));
  }
}
