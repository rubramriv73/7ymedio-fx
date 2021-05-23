package juego.vistas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import juego.modelo.card.Card;
import juego.modelo.exceptions.ExceededScoreException;
import juego.modelo.people.Dealer;
import juego.modelo.people.Player;

/**
 * Playing Window Controller
 * 
 * Here we control the playing window of our game where each
 * player play within it's turn.
 * 
 * @author Rubén Ramírez Rivera
 *
 */
public class PlayingWindowController {
  private static final double DEFAULT_MAX_SCORE = 7.5;
  @FXML
  private Label giveCardLabel;
  @FXML
  private TextArea cardVisor;
  @FXML
  private TextArea pointsVisor;

  private Dealer dealer = new Dealer();
  private ObservableList<Player> players;

  private static int playerShown;
  private static double maxScore = -1;
  

  public PlayingWindowController(ObservableList<Player> players){
    this.players = players;
  }


  public void initialize() {
    dealer.resetDeck();
    playerShown = 0;
    modifyGiveCardLabel();
    try {
      this.players.get(playerShown).setCards(dealer.giveCard());
    } catch (ExceededScoreException e) {
      System.out.println("Max Score");
      e.printStackTrace();
    }
    showVisors();

  }


  /**
   * 
   */
  private void showVisors() {
    cardVisor.clear();
    for (Card card : players.get(playerShown).getCards()) {
      cardVisor.setText(card.getSuit() + "\n" + card.getValue());
    }
    pointsVisor.setText(players.get(playerShown).getScore()+"");
  }

  /**
   * 
   */
  private void modifyGiveCardLabel() {
    this.giveCardLabel.setText(dealer.getNick().toUpperCase() + " is giving a card to " + players.get(playerShown).getNick().toUpperCase());
  }

  // Event Listener on Button.onAction
  @FXML
  public void giveNextCard(ActionEvent event) throws ExceededScoreException {
    try {
      players.get(playerShown).setCards(dealer.giveCard());
      showVisors();
    } catch (ExceededScoreException e) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Exceeded Score");
      alert.setHeaderText(null);
      alert.setContentText("You exceeded the maximum score. Your score is reset: " + players.get(playerShown).getScore() + "pts");
      alert.showAndWait();
      continueRoundValidation();

    }
  }

  // Event Listener on Button.onAction
  @FXML
  public void stopRound(ActionEvent event) throws ExceededScoreException {
    continueRoundValidation();
  }
  
  /**
   * @throws ExceededScoreException
   */
  private void continueRoundValidation() throws ExceededScoreException {
    playerShown++;
    if (playerShown != players.size()) {
      modifyGiveCardLabel();
      players.get(playerShown).setCards(dealer.giveCard());
      showVisors();

    } else {
      getMaxScore();
      FXMLLoader fxml = new FXMLLoader(getClass().getResource("EndGameWindow.fxml"));
      try {

        EndGameWindowController controller = new EndGameWindowController(players,maxScore);
        fxml.setController(controller);;

        var root = fxml.<VBox>load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) cardVisor.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

      } catch (IOException e) {
        System.out.println("Couldn't load the fxml file.");
        e.printStackTrace();
      }
    }
  }
  
  private void getMaxScore() {
    for (Player player : players) {
      if (player.getScore() > maxScore && player.getScore() <= DEFAULT_MAX_SCORE) {
        maxScore = player.getScore();
      }
    }
  }
}
