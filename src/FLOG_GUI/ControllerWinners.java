/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
