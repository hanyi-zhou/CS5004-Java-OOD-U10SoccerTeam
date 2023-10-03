package soccerteam;

import java.time.LocalDate;

/**
 * A driver class testing the soccer team model.
 */
public class Main {

  /**
   * A main method testing the soccer team model.
   *
   * @param args The command-line arguments (not used in this application).
   */
  public static void main(String[] args) {
    try {
      Team team = new TeamImpl();

      // Adding players to the team
      team.addPlayer("Emma", "Brown", LocalDate.of(2016, 1, 5), Position.FORWARD,
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
          1);
      team.addPlayer("John", "Doe", LocalDate.of(2015, 5, 15), Position.GOALIE,
          4);
      team.addPlayer("Bob", "Johnson", LocalDate.of(2017, 8, 10), Position.MIDFIELDER,
          5);

      team.createTeam();

      team.setStartingLineup();

      System.out.println("List of all players in the team (sorted by last name):\n" + team.getAllPlayersList());

      System.out.println("Starting lineup (sorted by position and then last name):\n" + team.getStartingLineupList());

      // Adding more than 20 players to the team
      for (int i = 0; i < 11; i++) {
        team.addPlayer("Player " + (i + 1), "Last Name", LocalDate.of(2015, 1, 1),
            Position.MIDFIELDER, 3);
      }

      team.createTeam();

      team.setStartingLineup();

      System.out.println("List of all players in the team (sorted by last name):\n" + team.getAllPlayersList());

      System.out.println("Starting lineup (sorted by position and then last name):\n" + team.getStartingLineupList());

      // Removing a player from the team
      team.removePlayer(2);

      team.setStartingLineup();

      System.out.println("List of all players in the team (sorted by last name):\n" + team.getAllPlayersList());

      System.out.println("Starting lineup (sorted by position and then last name):\n" + team.getStartingLineupList());

    } catch (IllegalStateException e) {
      System.out.println(e.toString());
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }
  }
}

