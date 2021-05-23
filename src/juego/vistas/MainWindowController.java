package juego.vistas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;

/**
 * Main Window Controller
 * 
 * Here we control the main window of our game where we
 * choose to play the game or exit it.
 * 
 * @author Rubén Ramírez Rivera
 *
 */
public class MainWindowController {
  @FXML
  private ImageView cardsImg;
  @FXML
  private Button playBtn;
  @FXML
  private Button exitBtn;
  
  public void initialize() throws FileNotFoundException {
    FileInputStream input = new FileInputStream("src/juego/cartas.png");
    Image image = new Image(input);
    cardsImg.setImage(image);
  }

  // Event Listener on Button[#playBtn].onAction
  @FXML
  public void play(ActionEvent event) {
    FXMLLoader fxml = new FXMLLoader(getClass().getResource("PlayersWindow.fxml"));
    try {
      var root = fxml.<VBox>load();
      
      Scene scene = new Scene(root);
      
      Stage stage = (Stage) playBtn.getScene().getWindow();
      stage.setScene(scene);
      stage.show();
      
    } catch (IOException e) {
      System.out.println("Couldn't load the fxml file.");
      e.printStackTrace();
    }
  }
  
  // Event Listener on Button[#exitBtn].onAction
  @FXML
  public void exit(ActionEvent event) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    
    alert.setTitle("Exit Warning");
    alert.setHeaderText(null);
    alert.setContentText("Are you sure you want to exit the game?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
      Stage stage = (Stage) exitBtn.getScene().getWindow();
      stage.close();
    }
  }
}
