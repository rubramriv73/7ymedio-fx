package juego.vistas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.Collections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import juego.modelo.people.Player;

/**
 * End Game Window Controller
 * 
 * Here we control the end game window of our game where show
 * the winners and the leaderboard.
 * 
 * @author Rubén Ramírez Rivera
 *
 */
public class EndGameWindowController {
  @FXML
  private TextArea winnerVisor;
  @FXML
  private TextArea leaderBoardVisor;
  
  private ObservableList<Player> players;
  
  private double maxScore;
  
  public EndGameWindowController(ObservableList<Player> players, double maxScore) {
    this.players = players;
    this.maxScore = maxScore;
  }
  
  public void initialize() {
    getWinners();
    getLeaderBoard();
  }

  // Event Listener on Button.onAction
  @FXML
  public void backToMainMenu(ActionEvent event) {
    FXMLLoader fxml = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
    try {
      var root = fxml.<VBox>load();

      Scene scene = new Scene(root);
      
      Stage stage = (Stage) winnerVisor.getScene().getWindow();
      
      stage.setScene(scene);
      
      stage.show();
      
    } catch (IOException e) {
      System.out.println("Couldn't load the fxml file.");
      e.printStackTrace();
    }
  }
  
  private void getWinners() {
    for (Player player : players) {
      if (player.getScore() == this.maxScore) {
        winnerVisor.appendText(player.getNick() + "\n");
      }
    }
  }
  
  private void getLeaderBoard() {
    Collections.sort(players);
    for (Player player : players) {
      leaderBoardVisor.appendText(player.getNick() + " ---> " + player.getScore() + " pts\n");
    }
  }
}
