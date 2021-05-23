package juego.vistas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import juego.modelo.people.Player;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

/**
 * Players Window Controller
 * 
 * Here we control the players window of our game where we
 * add or delete the players that are going to play in it.
 * 
 * @author Rubén Ramírez Rivera
 *
 */
public class PlayersWindowController {
  @FXML
  private TextField playerNameField;
  @FXML
  private Button addBtn;
  @FXML
  private TableView<Player> playersTable;
  @FXML
  private TableColumn<?, ?> colPlayers;
  @FXML
  private Button deleteBtn;
  @FXML
  private Button startBtn;

  private ObservableList<Player> players;

  public void initialize() {
    players = FXCollections.observableArrayList();
    this.playersTable.setItems(players);

    this.colPlayers.setCellValueFactory(new PropertyValueFactory<>("nick"));
  }

  // Event Listener on Button[#addBtn].onAction
  @FXML
  public void addPlayer(ActionEvent event) {
    String nick = playerNameField.getText();

    Player player = new Player(nick);

    if (!this.players.contains(player)) {
      this.players.add(player);
      this.playersTable.refresh();

    } else {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.setContentText("Player already exist");
      alert.showAndWait();
    }
    
    playerNameField.clear();
  }

  // Event Listener on Button[#deleteBtn].onAction
  @FXML
  public void deletePlayer(ActionEvent event) {
    Player player = this.playersTable.getSelectionModel().getSelectedItem();

    if (player == null) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.setContentText("You have to select a Player");
      alert.showAndWait();

    } else {
      
      this.players.remove(player);
      this.playersTable.refresh();
    }
  }
  // Event Listener on Button[#startBtn].onAction
  @FXML
  public void startGame(ActionEvent event) {
    if (this.players.isEmpty()) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.setContentText("You have to create at least 1 Player in order to start playing");
      alert.showAndWait();
    } else {
      FXMLLoader fxml = new FXMLLoader(getClass().getResource("PlayingWindow.fxml"));
      try {
        
        
        PlayingWindowController controller = new PlayingWindowController(players);
        fxml.setController(controller);;
        
        var root = fxml.<VBox>load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) startBtn.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

      } catch (IOException e) {
        System.out.println("Couldn't load the fxml file.");
        e.printStackTrace();
      }
    }
    
  }

  /**
   * @return the players
   */
  public ObservableList<Player> getPlayers() {
    return players;
  }
}
