package soccerteam;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

/**
 * Represents an implementation of a soccer team that manages players and their
 * roles within the team.
 */
public class TeamImpl implements Team {
  private List<Player> team;
  private List<Player> startingLineup;
  private static final int HIGHEST_SKILL_LEVEL = 5;
  private static final int MIN_TEAM_SIZE = 10;
  private static final int MAX_TEAM_SIZE = 20;
  private static final int STARTING_LINEUP_SIZE = 7;
  private static final int NUM_GOALIES = 1;
  private static final int NUM_DEFENDERS = 2;
  private static final int NUM_MIDFIELDERS = 3;
  private static final int NUM_FORWARDS = 1;

  /**
   * Constructs a new TeamImpl object.
   * Initializes the list for team players.
   */
  public TeamImpl() {
    this.team = new ArrayList<>();
  }

  @Override public void addPlayer(String firstName, String lastName, LocalDate dateOfBirth,
                                  Position preferredPosition, int skillLevel) throws IllegalArgumentException {
    Player player = new PlayerImpl(firstName, lastName, dateOfBirth, preferredPosition, skillLevel);
    if (team.contains(player)) {
      throw new IllegalArgumentException("The player is already on the team. Please try again.");
    }

    if (team.size() >= MAX_TEAM_SIZE) {
      if (player.getSkillLevel() < findLowestSkillLevel()) {
        throw new IllegalArgumentException(
            "Player's skill level is too low to be added to the team. Please try again.");
      }
      removeLowestSkillPlayer();
    }

    this.team.add(player);
  }

  /**
   * Finds and returns the lowest skill level among all players in the team.
   *
   * @return The lowest skill level of the players in the team, or the highest skill level if no players are present.
   */
  private int findLowestSkillLevel() {
    return team.stream().min(Comparator.comparing(Player::getSkillLevel))
        .map(Player::getSkillLevel).orElse(HIGHEST_SKILL_LEVEL);
  }

  /**
   * Removes the player with the lowest skill level from the team's list of players.
   * If multiple players have the same lowest skill level, only one of them will be removed.
   */
  private void removeLowestSkillPlayer() {
    team.stream().min(Comparator.comparing(Player::getSkillLevel)).ifPresent(team::remove);
  }

  @Override public void createTeam() {
    this.assignJerseyNumbers();
  }

  /**
   * Assigns jersey numbers to players in the team based on their current jersey numbers and availability.
   * Players with existing jersey numbers are added to a map for tracking, and then jersey numbers are assigned
   * to players without jersey numbers, ensuring that the assigned numbers are unique and within the valid range.
   */
  private void assignJerseyNumbers() {
    Map<Integer, Player> jerseyNumberMap = new HashMap<>();

    for (Player player : team) {
      int jerseyNumber = player.getJerseyNumber();
      if (jerseyNumber != 0) {
        jerseyNumberMap.put(jerseyNumber, player);
      }
    }

    int nextAvailableNumber = 1;

    for (Player player : team) {
      int jerseyNumber = player.getJerseyNumber();
      if (jerseyNumber == 0) {
        while (jerseyNumberMap.containsKey(nextAvailableNumber) || nextAvailableNumber > MAX_TEAM_SIZE) {
          nextAvailableNumber++;
        }
        player.setJerseyNumber(nextAvailableNumber);
        jerseyNumberMap.put(nextAvailableNumber, player);
        nextAvailableNumber++;
      }
    }
  }

  @Override public int getSize() {
    return team.size();
  }

  @Override public List<Player> getTeamPlayers() {
    return this.team;
  }

  @Override public List<Player> getStartingLineup() {
    return this.startingLineup;
  }

  @Override public void removePlayer(int jerseyNumber) throws IllegalArgumentException {
    Player playerToRemove = findPlayer(jerseyNumber);

    if (playerToRemove != null) {
      team.remove(playerToRemove);
      if (startingLineup != null) {
        startingLineup.remove(playerToRemove);
      }
    } else {
      throw new IllegalArgumentException("The player is not on the team.");
    }
  }

  /**
   * Finds a player by their jersey number in the team.
   *
   * @param jerseyNumber The jersey number of the player to find.
   *
   * @return The found player, or null if not found.
   */
  private Player findPlayer(int jerseyNumber) {
    return Stream.concat(team.stream(), team.stream())
        .filter(player -> player.getJerseyNumber() == jerseyNumber)
        .findFirst()
        .orElse(null);
  }

  @Override public void setStartingLineup() throws IllegalStateException {
    if (team.size() < MIN_TEAM_SIZE) {
      throw new IllegalStateException(
          String.format("The current team size is under %d.", MIN_TEAM_SIZE));
    }
    for (Player player : team) {
      if (player.getJerseyNumber() == 0) {
        throw new IllegalStateException(
            "The team has not been created yet. There are players with no jersey numbers.");
      }
    }

    List<Player> startingLineup = new ArrayList<>();

    // sorting players by the skill level in descending order
    List<Player> sortedPlayers = new ArrayList<>(this.team);
    sortedPlayers.sort(Comparator.comparing(Player::getSkillLevel).reversed());

    // finding player lists of each position by their preferred positions
    List<Player> goalies = findPlayersByPreferredPosition(sortedPlayers, Position.GOALIE);
    List<Player> defenders = findPlayersByPreferredPosition(sortedPlayers, Position.DEFENDER);
    List<Player> midfielders = findPlayersByPreferredPosition(sortedPlayers, Position.MIDFIELDER);
    List<Player> forwards = findPlayersByPreferredPosition(sortedPlayers, Position.FORWARD);

    // setting starting lineup positions to each player
    List<Player> startingGoalie = setStartingPositions(goalies, Position.GOALIE, NUM_GOALIES);
    startingLineup.addAll(startingGoalie);
    sortedPlayers.removeAll(startingGoalie);

    List<Player> startingDefenders = setStartingPositions(defenders, Position.DEFENDER, NUM_DEFENDERS);
    startingLineup.addAll(startingDefenders);
    sortedPlayers.removeAll(startingDefenders);

    List<Player> startingMidfielders = setStartingPositions(midfielders, Position.MIDFIELDER, NUM_MIDFIELDERS);
    startingLineup.addAll(startingMidfielders);
    sortedPlayers.removeAll(startingMidfielders);

    List<Player> startingForward = setStartingPositions(forwards, Position.FORWARD, NUM_FORWARDS);
    startingLineup.addAll(startingForward);
    sortedPlayers.removeAll(startingForward);

    // checking if there are remaining positions in the starting lineup
    int remainingPositions = STARTING_LINEUP_SIZE - startingLineup.size();

    if (remainingPositions > 0) {
      // counting remaining numbers for each position
      int goalieCount = 0;
      int defenderCount = 0;
      int midfielderCount = 0;
      int forwardCount = 0;
      for (Player startingPlayer : startingLineup) {
        if (startingPlayer.getStartingPosition() == Position.GOALIE) {
          goalieCount++;
        } else if (startingPlayer.getStartingPosition() == Position.DEFENDER) {
          defenderCount++;
        } else if (startingPlayer.getStartingPosition() == Position.MIDFIELDER) {
          midfielderCount++;
        } else {
          forwardCount++;
        }
      }

      // filling remaining positions
      if (forwardCount < NUM_FORWARDS) {
        List<Player> suppliedStartingForward = setRemainingPositions(sortedPlayers, Position.FORWARD, NUM_FORWARDS, forwardCount);
        startingLineup.addAll(suppliedStartingForward);
        startingForward.addAll(suppliedStartingForward);
        sortedPlayers.removeAll(suppliedStartingForward);
      }

      if (defenderCount < NUM_DEFENDERS) {
        List<Player> suppliedStartingDefenders = setRemainingPositions(sortedPlayers, Position.DEFENDER, NUM_DEFENDERS, defenderCount);
        startingLineup.addAll(suppliedStartingDefenders);
        startingDefenders.addAll(suppliedStartingDefenders);
        sortedPlayers.removeAll(suppliedStartingDefenders);
      }

      if (midfielderCount < NUM_MIDFIELDERS) {
        List<Player> suppliedStartingMidfielders = setRemainingPositions(sortedPlayers, Position.MIDFIELDER, NUM_MIDFIELDERS, midfielderCount);
        startingLineup.addAll(suppliedStartingMidfielders);
        startingMidfielders.addAll(suppliedStartingMidfielders);
        sortedPlayers.removeAll(suppliedStartingMidfielders);
      }

      if (goalieCount < NUM_GOALIES) {
        List<Player> suppliedStartingGoalie = setRemainingPositions(sortedPlayers, Position.GOALIE, NUM_GOALIES, goalieCount);
        startingLineup.addAll(suppliedStartingGoalie);
        startingGoalie.addAll(suppliedStartingGoalie);
        sortedPlayers.removeAll(suppliedStartingGoalie);
      }
    }
    this.startingLineup = startingLineup;
  }

  /**
   * Finds and returns a list of players from the provided list who have the specified preferred position.
   *
   * @param players The list of players to search through.
   * @param position The preferred position to filter players by.
   * @return A list of players with the specified preferred position.
   */
  private List<Player> findPlayersByPreferredPosition(List<Player> players, Position position) {
    List<Player> playersByPosition = new ArrayList<>();
    for (Player player : players) {
      if (player.getPreferredPosition() == position) {
        playersByPosition.add(player);
      }
    }
    return playersByPosition;
  }

  /**
   * Retrieves a sublist of players from the provided list, up to the specified count.
   * If the list has fewer players than the given count, the entire list is returned.
   *
   * @param players The list of players to extract a sublist from.
   * @param count The maximum number of players to include in the sublist.
   * @return A sublist of players with a maximum size of 'count'.
   */
  private List<Player> getPlayersForStartingLineup(List<Player> players, int count) {
    if (players.size() >= count) {
      return players.subList(0, count);
    } else {
      return players;
    }
  }

  /**
   * Sets the starting positions for a specified number of players with the given position.
   *
   * @param players The list of players to select from.
   * @param position The desired starting position for the players.
   * @param numPositions The number of players to set to the starting position.
   * @return A list of players who have been set to the starting position.
   */
  private List<Player> setStartingPositions(List<Player> players, Position position, int numPositions) {
    List<Player> startingPlayers = getPlayersForStartingLineup(players, numPositions);
    for (Player player : startingPlayers) {
      player.setStartingPosition(position);
    }

    return startingPlayers;
  }

  /**
   * Sets the remaining starting positions for a specified number of players with the given position,
   * based on the count of players already set to that position.
   *
   * @param sortedPlayers The list of players to select from.
   * @param position The desired starting position for the players.
   * @param numPositions The total number of players to set to the starting position.
   * @param count The current count of players already set to the starting position.
   * @return A list of players who have been set to the starting position.
   */
  private List<Player> setRemainingPositions(List<Player> sortedPlayers, Position position, int numPositions, int count) {
    int remainingCount = numPositions - count;
    List<Player> suppliedStartingPlayers = sortedPlayers.subList(0, remainingCount);
    for (Player player : suppliedStartingPlayers) {
      player.setStartingPosition(position);
    }

    return suppliedStartingPlayers;
  }

  @Override public String getAllPlayersList() throws IllegalStateException {
    if (team.size() < MIN_TEAM_SIZE) {
      throw new IllegalStateException(String.format("The current team size is under %d.", MIN_TEAM_SIZE));
    }
    for (Player player : team) {
      if (player.getJerseyNumber() == 0) {
        throw new IllegalStateException("A team has not been created yet. There are players with no jersey numbers.");
      }
    }

    List<Player> allPlayers = new ArrayList<>(team);
    allPlayers.sort(Comparator.comparing(Player::getLastName));

    StringBuilder listBuilder = new StringBuilder();
    for (Player player : allPlayers) {
      listBuilder.append(player.getFirstName()).append(" ").append(player.getLastName()).append(" (#").append(player.getJerseyNumber()).append(")\n");
    }
    return listBuilder.toString();
  }

  @Override public String getStartingLineupList() throws IllegalStateException {
    if (team.size() < MIN_TEAM_SIZE) {
      throw new IllegalStateException(String.format("The current team size is under %d.", MIN_TEAM_SIZE));
    }
    for (Player player : team) {
      if (player.getJerseyNumber() == 0) {
        throw new IllegalStateException("A team has not been created yet. There are players with no jersey numbers.");
      }
    }
    if (startingLineup.size() < STARTING_LINEUP_SIZE) {
      throw new IllegalStateException(String.format("The current starting lineup size is under %d.", STARTING_LINEUP_SIZE));
    }

    startingLineup.sort(
        Comparator.comparing(Player::getStartingPosition).thenComparing(Player::getLastName));

    StringBuilder listBuilder = new StringBuilder();
    for (Player player : startingLineup) {
      listBuilder.append(player.getFirstName()).append(" ").append(player.getLastName()).append(" (#").append(player.getJerseyNumber()).append(") - Starting Position: ")
          .append(player.getStartingPosition()).append("\n");
    }
    return listBuilder.toString();
  }

  @Override public String toString() {
    StringBuilder result = new StringBuilder();
    for (Player player : team) {
      result.append(player.toString());
    }
    return result.toString();
  }
}
