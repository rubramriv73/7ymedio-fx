package juego;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Spanish BlackJack Game
 * 
 * Juego de las 7 y media
 * 
 * @author Rubén Ramírez Rivera
 *
 */
public class MainWindow extends Application {

  @Override
  public void start(Stage primaryStage) {
    FXMLLoader mainWindowFxml = new FXMLLoader(getClass().getResource("vistas/MainWindow.fxml"));
    try {
      var root = mainWindowFxml.<VBox>load();

      Scene scene = new Scene(root);

      primaryStage.setScene(scene);
      primaryStage.setTitle("Spanish BlackJack");
      primaryStage.show();
      
    } catch (IOException e) {
      System.out.println("Couldn't load the fxml file.");
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
