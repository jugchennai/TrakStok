package in.jugchennai.javamoney.trakstok.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

public class Main extends Application {
  public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
    
        StackPane root = new StackPane();
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
