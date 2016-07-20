package FLOG_GUI;

/**
 *
 * @author Dushan Galappaththi
 */
public class ControllerWinners {

  PanelWinners panelWinners;
  GameScreen gameScreen;
  ControllerGamePlay controllerGamePlay;

  public ControllerWinners(
      PanelWinners panelWinners, GameScreen gameScreen, ControllerGamePlay controllerGamePlay) {
    this.panelWinners = panelWinners;
    this.gameScreen = gameScreen;
    this.controllerGamePlay = controllerGamePlay;
  }

  public void drawPlayers() {
    panelWinners.drawPlayers();
  }

  public void setWinner(int rank) {
    panelWinners.setWinnerLabel(rank);
  }
}
