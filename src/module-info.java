module spanishBlackJack {
  requires transitive javafx.graphics;
  requires javafx.controls;
  requires javafx.fxml;
  
  opens juego to javafx.fxml;
  opens juego.vistas to javafx.fxml;
  opens juego.modelo.card to javafx.fxml;
  opens juego.modelo.deck to javafx.fxml;
  opens juego.modelo.exceptions to javafx.fxml;
  opens juego.modelo.people to javafx.fxml;
  
  exports juego;
  exports juego.vistas;
  exports juego.modelo.card;
  exports juego.modelo.deck;
  exports juego.modelo.exceptions;
  exports juego.modelo.people;
}
