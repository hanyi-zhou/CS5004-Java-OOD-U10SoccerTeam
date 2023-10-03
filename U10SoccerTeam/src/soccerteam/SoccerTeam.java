package soccerteam;

/**
 * The entry point for the SoccerTeam application. It initializes the team,
 * view, and controller, and starts the application.
 */
public class SoccerTeam {

  /**
   * The main method that serves as the entry point for the SoccerTeam application.
   *
   * @param args Command-line arguments (not used in this application).
   */
  public static void main(String[] args) {
    Team team = new TeamImpl();
    SoccerTeamView view = new SwingSoccerTeamView("Soccer Team");
    SoccerTeamController controller = new SoccerTeamControllerConsole(team, view);
    controller.go();
  }
}
