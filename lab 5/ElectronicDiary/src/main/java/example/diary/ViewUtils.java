package main.java.example.diary;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ViewUtils {
    public static Parent loadFXML(String fxmlPath) {
        try {
            return FXMLLoader.load(ViewUtils.class.getResource(fxmlPath));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}