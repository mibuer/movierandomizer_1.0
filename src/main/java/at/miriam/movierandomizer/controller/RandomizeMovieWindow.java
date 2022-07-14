package at.miriam.movierandomizer.controller;

import java.io.IOException;

import at.miriam.movierandomizer.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RandomizeMovieWindow extends BaseController {

void show() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(Constants.PATH_TO_RANDOMIZE_MOVIE_VIEW));
		
		Parent root = loader.load();
		Scene scene = new Scene(root);
		
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	
	}

}
