package at.miriam.movierandomizer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
    	System.out.println("App started");
        scene = new Scene(loadFXML(""));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
    	//hier TabPane später einfügen
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(Constants.PATH_TO_START_VIEW));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}