package soccerteam;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class to test the functionality of the {@link TeamImpl} class.
 */
public class TestTeamImpl {

  private TeamImpl team;

  /**
   * Initialize the team and player instances.
   */
  @Before
  public void setUp() {
    team = new TeamImpl();

    team.addPlayer("Emma", "Brown", LocalDate.of(2015, 1, 5), Position.FORWARD,
        2);
    team.addPlayer("Sophia", "Miller", LocalDate.of(2014, 12, 7), Position.DEFENDER,
        4);
    team.addPlayer("Alice", "Smith", LocalDate.of(2016, 3, 20), Position.DEFENDER,
        3);
    team.addPlayer("William", "Taylor", LocalDate.of(2014, 11, 12), Position.DEFENDER,
        2);
    team.addPlayer("Robert", "Twist", LocalDate.of(2017, 10, 5), Position.MIDFIELDER,
        4);
    team.addPlayer("Michael", "Williams", LocalDate.of(2016, 9, 25), Position.GOALIE,
        3);
    team.addPlayer("Olivia", "Wilson", LocalDate.of(2015, 6, 18), Position.FORWARD,
        5);
    team.addPlayer("Ethan", "Davis", LocalDate.of(2016, 4, 30), Position.MIDFIELDER,
        2);
    team.addPlayer("John", "Doe", LocalDate.of(2015, 5, 15), Position.GOALIE,
        4);
    team.addPlayer("Bob", "Johnson", LocalDate.of(2017, 8, 10), Position.MIDFIELDER,
        5);
  }

  /**
   * Test adding a player and verifying the team size.
   */
  @Test
  public void testAddPlayer() {
    team.addPlayer("Key", "Johnson", LocalDate.of(2015, 7, 3), Position.MIDFIELDER,
        4);
    assertEquals(11, team.getSize());
  }

  /**
   * Test adding a player over 10 years old.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddPlayerOverTen() {
    team.addPlayer("Key", "Johnson", LocalDate.of(2013, 7, 3), Position.MIDFIELDER,
        4);
  }

  /**
   * Test adding a duplicate player.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddPlayerDuplicate() {
    team.addPlayer("Emma", "Brown", LocalDate.of(2015, 1, 5), Position.FORWARD,
        2);
  }

  /**
   * Test adding a player with too low skill level.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddPlayerTooLowSkillLevel() {
    for (int i = 1; i <= 10; i++) {
      team.addPlayer("Player", Integer.toString(i),
          LocalDate.of(2015, 1, 1), Position.GOALIE, 2);
    }
    team.addPlayer("Key", "Johnson", LocalDate.of(2015, 7, 3), Position.MIDFIELDER,
        1);
  }

  /**
   * Test adding players to a full team and verifying replacement.
   */
  @Test
  public void testAddPlayerWithFullTeam() {
    for (int i = 0; i < 10; i++) {
      team.addPlayer("Player " + (i + 1), "Last Name", LocalDate.of(2015, 1, 1),
          Position.MIDFIELDER, 1);
    }
    team.createTeam();
    assertEquals(team.getAllPlayersList(), """
        Emma Brown (#1)
        Ethan Davis (#8)
        John Doe (#9)
        Bob Johnson (#10)
        Player 1 Last Name (#11)
        Player 2 Last Name (#12)
        Player 3 Last Name (#13)
        Player 4 Last Name (#14)
        Player 5 Last Name (#15)
        Player 6 Last Name (#16)
        Player 7 Last Name (#17)
        Player 8 Last Name (#18)
        Player 9 Last Name (#19)
        Player 10 Last Name (#20)
        Sophia Miller (#2)
        Alice Smith (#3)
        William Taylor (#4)
        Robert Twist (#5)
        Michael Williams (#6)
        Olivia Wilson (#7)
        """);

    // Adding one more player with skill level 1 should replace the lowest skill level player
    team.addPlayer("Player 11", "Last Name",
        LocalDate.of(2015, 1, 1), Position.DEFENDER, 1);

    team.createTeam();
    assertEquals(team.getAllPlayersList(), """
        Emma Brown (#1)
        Ethan Davis (#8)
        John Doe (#9)
        Bob Johnson (#10)
        Player 2 Last Name (#12)
        Player 3 Last Name (#13)
        Player 4 Last Name (#14)
        Player 5 Last Name (#15)
        Player 6 Last Name (#16)
        Player 7 Last Name (#17)
        Player 8 Last Name (#18)
        Player 9 Last Name (#19)
        Player 10 Last Name (#20)
        Player 11 Last Name (#11)
        Sophia Miller (#2)
        Alice Smith (#3)
        William Taylor (#4)
        Robert Twist (#5)
        Michael Williams (#6)
        Olivia Wilson (#7)
        """);
  }

  /**
   * Test creating a team.
   */
  @Test
  public void testCreateTeam() {
    team.createTeam();
    assertEquals(team.getAllPlayersList(), """
        Emma Brown (#1)
        Ethan Davis (#8)
        John Doe (#9)
        Bob Johnson (#10)
        Sophia Miller (#2)
        Alice Smith (#3)
        William Taylor (#4)
        Robert Twist (#5)
        Michael Williams (#6)
        Olivia Wilson (#7)
        """);
  }


  /**
   * Test getting the team size.
   */
  @Test
  public void testGetSize() {
    assertEquals(10, team.getSize());
    for (int i = 1; i <= 20; i++) {
      team.addPlayer("Player", Integer.toString(i),
                LocalDate.of(2015, 1, 1), Position.GOALIE, 2);
    }
    assertEquals(20, team.getSize());
  }

  /**
   * Test getting the list of team players.
   */
  @Test
  public void testGetTeamPlayers() {
    List<Player> players = team.getTeamPlayers();
    assertEquals(10, players.size());
  }

  /**
   * Test getting the starting lineup.
   */
  @Test
  public void testGetStartingLineup() {
    team.createTeam();
    team.setStartingLineup();
    List<Player> startingLineup = team.getStartingLineup();
    assertEquals(7, startingLineup.size());
  }

  /**
   * Test removing players from the team.
   */
  @Test
  public void testRemovePlayer() {
    team.addPlayer("Key", "Johnson", LocalDate.of(2015, 7, 3), Position.MIDFIELDER,
        4);
    team.createTeam();
    team.setStartingLineup();
    assertEquals(team.getAllPlayersList(), """
        Emma Brown (#1)
        Ethan Davis (#8)
        John Doe (#9)
        Bob Johnson (#10)
        Key Johnson (#11)
        Sophia Miller (#2)
        Alice Smith (#3)
        William Taylor (#4)
        Robert Twist (#5)
        Michael Williams (#6)
        Olivia Wilson (#7)
        """);
    assertEquals(team.getStartingLineupList(), """
        John Doe (#9) - Starting Position: GOALIE
        Sophia Miller (#2) - Starting Position: DEFENDER
        Alice Smith (#3) - Starting Position: DEFENDER
        Bob Johnson (#10) - Starting Position: MIDFIELDER
        Key Johnson (#11) - Starting Position: MIDFIELDER
        Robert Twist (#5) - Starting Position: MIDFIELDER
        Olivia Wilson (#7) - Starting Position: FORWARD
        """);

    team.removePlayer(11);
    assertEquals(team.getAllPlayersList(), """
        Emma Brown (#1)
        Ethan Davis (#8)
        John Doe (#9)
        Bob Johnson (#10)
        Sophia Miller (#2)
        Alice Smith (#3)
        William Taylor (#4)
        Robert Twist (#5)
        Michael Williams (#6)
        Olivia Wilson (#7)
        """);
    team.setStartingLineup();
    assertEquals(team.getStartingLineupList(), """
        John Doe (#9) - Starting Position: GOALIE
        Sophia Miller (#2) - Starting Position: DEFENDER
        Alice Smith (#3) - Starting Position: DEFENDER
        Ethan Davis (#8) - Starting Position: MIDFIELDER
        Bob Johnson (#10) - Starting Position: MIDFIELDER
        Robert Twist (#5) - Starting Position: MIDFIELDER
        Olivia Wilson (#7) - Starting Position: FORWARD
        """);
  }

  /**
   * Test removing players that are not on the team.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemovePlayerNotExist() {
    team.removePlayer(11);
  }

  /**
   * Test setting the starting lineup.
   */
  @Test
  public void testSetStartingLineup() {
    team.createTeam();
    team.setStartingLineup();
    assertEquals(team.getStartingLineupList(), """
        John Doe (#9) - Starting Position: GOALIE
        Sophia Miller (#2) - Starting Position: DEFENDER
        Alice Smith (#3) - Starting Position: DEFENDER
        Ethan Davis (#8) - Starting Position: MIDFIELDER
        Bob Johnson (#10) - Starting Position: MIDFIELDER
        Robert Twist (#5) - Starting Position: MIDFIELDER
        Olivia Wilson (#7) - Starting Position: FORWARD
        """);
  }

  /**
   * Test setting starting lineup when the team size is under 10.
   */
  @Test(expected = IllegalStateException.class)
  public void testSetStartingLineupWithTeamUnderTen() {
    Team exampleTeam = new TeamImpl();
    exampleTeam.setStartingLineup();
  }

  /**
   * Test setting starting lineup when there are players have not been assigned jersey numbers.
   */
  @Test(expected = IllegalStateException.class)
  public void testSetStartingLineupWithPlayersHaveNoJerseyNumbers() {
    team.addPlayer("Key", "Johnson", LocalDate.of(2015, 7, 3), Position.MIDFIELDER,
        4);
    team.setStartingLineup();
  }

  /**
   * Test getting the list of all players.
   */
  @Test
  public void testGetAllPlayersList() {
    team.createTeam();
    assertEquals(team.getAllPlayersList(), """
        Emma Brown (#1)
        Ethan Davis (#8)
        John Doe (#9)
        Bob Johnson (#10)
        Sophia Miller (#2)
        Alice Smith (#3)
        William Taylor (#4)
        Robert Twist (#5)
        Michael Williams (#6)
        Olivia Wilson (#7)
        """);
  }

  /**
   * Test getting the list of all players when the team size is invalid.
   */
  @Test(expected = IllegalStateException.class)
  public void testGetAllPlayersList_WithInvalidTeamSize() {
    Team newTeam = new TeamImpl();
    newTeam.getAllPlayersList();
  }

  /**
   * Test getting the list of all players when there are players have not been assigned jersey numbers.
   */
  @Test(expected = IllegalStateException.class)
  public void testGetAllPlayersList_WithPlayersHaveNoJerseyNumbers() {
    team.addPlayer("Key", "Johnson", LocalDate.of(2015, 7, 3), Position.MIDFIELDER,
        4);
    team.getAllPlayersList();
  }

  /**
   * Test getting the list of starting lineup.
   */
  @Test
  public void testGetStartingLineupList() {
    team.createTeam();
    team.setStartingLineup();
    assertEquals(team.getStartingLineupList(), """
        John Doe (#9) - Starting Position: GOALIE
        Sophia Miller (#2) - Starting Position: DEFENDER
        Alice Smith (#3) - Starting Position: DEFENDER
        Ethan Davis (#8) - Starting Position: MIDFIELDER
        Bob Johnson (#10) - Starting Position: MIDFIELDER
        Robert Twist (#5) - Starting Position: MIDFIELDER
        Olivia Wilson (#7) - Starting Position: FORWARD
        """);
  }

  /**
   * Test getting the starting lineup list when the team size is invalid.
   */
  @Test(expected = IllegalStateException.class)
  public void testGetStartingLineupList_WithInvalidTeamSize() {
    Team newTeam = new TeamImpl();
    newTeam.getStartingLineupList();
  }

  /**
   * Test getting the starting lineup list when there are players have not been assigned jersey numbers.
   */
  @Test(expected = IllegalStateException.class)
  public void testGetStartingLineupList_WithPlayersHaveNoJerseyNumbers() {
    team.addPlayer("Key", "Johnson", LocalDate.of(2015, 7, 3), Position.MIDFIELDER,
        4);
    team.getStartingLineupList();
  }

  /**
   * Test getting the staring lineup list when the starting lineup size is invalid.
   */
  @Test(expected = IllegalStateException.class)
  public void testGetStartingLineupList_WithInvalidStartingLineupSize() {
    team.createTeam();
    team.setStartingLineup();
    team.removePlayer(2);
    team.getStartingLineupList();
  }

  /**
   * Test the string representation of the team.
   */
  @Test
  public void testToString() {
    team.createTeam();
    assertEquals(team.toString(), """
        Emma Brown(#1) - Preferred Position: FORWARD - Skill Level: 2
        Sophia Miller(#2) - Preferred Position: DEFENDER - Skill Level: 4
        Alice Smith(#3) - Preferred Position: DEFENDER - Skill Level: 3
        William Taylor(#4) - Preferred Position: DEFENDER - Skill Level: 2
        Robert Twist(#5) - Preferred Position: MIDFIELDER - Skill Level: 4
        Michael Williams(#6) - Preferred Position: GOALIE - Skill Level: 3
        Olivia Wilson(#7) - Preferred Position: FORWARD - Skill Level: 5
        Ethan Davis(#8) - Preferred Position: MIDFIELDER - Skill Level: 2
        John Doe(#9) - Preferred Position: GOALIE - Skill Level: 4
        Bob Johnson(#10) - Preferred Position: MIDFIELDER - Skill Level: 5
        """);
  }
}