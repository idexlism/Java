package main.java.example.diary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception { //запуске JavaFX-приложения
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/MainView.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Электронный ежедневник");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) { //точка входа
        launch(args);
    }
}
