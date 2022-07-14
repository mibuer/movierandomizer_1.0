package at.miriam.movierandomizer.controller;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import at.miriam.movierandomizer.model.Movie;
import at.miriam.movierandomizer.model.RandomMovie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Popup;

public class RandomizeMovieController extends BaseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button okButton;

    @FXML
    private Button secondTryButton;

    @FXML
    private Label selectedMovieLabel;

    @FXML
    private Label showResultLabel;
   

    @FXML
    void onOKButtonClick(ActionEvent event) {
    	//möchte ich den Film anschauen, click auf OK
    	//Movie watched = true
    	
    	//PopUp "Movie saved, Have fun with the movie"
    	RandomMovie randomSingeltonMovie = RandomMovie.getInstance();
        
        Movie randomMovie = randomSingeltonMovie.getRandMovie();
    	
    	randomMovie.setWatched(true);
    	//update moviesList am gleichen index 
    	int index = model.moviesList.indexOf(randomMovie);
    	model.moviesList.set(index, randomMovie);
    	
    	System.out.println("*****************Updated Movie******************************");
    	System.out.println(model.moviesList.get(index));
    	
    	okButton.disabledProperty();
    	
    	
    	   	
    }

    @FXML
    void onSecondTryButtonClick(ActionEvent event) {
    	
    	//aus den beiden übrigen Movies einen zufälligen auswählen
    	//wird im Label angezeigt
    	Random random = new Random();
    	
    	int randomIndex = random.nextInt(model.moviesList.size());
    	Movie randomMovie = model.moviesList.get(randomIndex);
    	
    	RandomMovie randomSingeltonMovie = RandomMovie.getInstance();
        
        Movie newRandomMovie = randomSingeltonMovie.getRandMovie();
    	
    	if (randomMovie.equals(newRandomMovie)) {
    		model.moviesList.remove(randomIndex);
    	}
    	//aus den letzten beiden Filmen wird einer ausgewählt
    	int randIndex = random.nextInt(model.moviesList.size());
    	Movie finalMovie = model.moviesList.get(randIndex);
    	
    	selectedMovieLabel.setText(finalMovie.toString());
    	
    	
    	//Update finalMovie auf watched=true
    	finalMovie.setWatched(true);
    	//update moviesList am gleichen index 
    	int index = model.moviesList.indexOf(finalMovie);
    	model.moviesList.set(index, finalMovie);
    	
    	System.out.println("*****************Updated finalMovie**********************");
    	System.out.println(model.moviesList.get(index));
    	
    	showResultLabel.setText("Have fun with your movie!");
    	
    	okButton.setDisable(true);
    	secondTryButton.setDisable(true);
    	
    	
    	
    }

    @FXML
    void initialize() {
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'randomizeMovieView.fxml'.";
        assert secondTryButton != null : "fx:id=\"secondTryButton\" was not injected: check your FXML file 'randomizeMovieView.fxml'.";
        assert selectedMovieLabel != null : "fx:id=\"selectedMovieLabel\" was not injected: check your FXML file 'randomizeMovieView.fxml'.";
        assert showResultLabel != null : "fx:id=\"showResultLabel\" was not injected: check your FXML file 'randomizeMovieView.fxml'.";

        
        RandomMovie randMovie = RandomMovie.getInstance();
        Movie m = randMovie.getRandMovie();
        //Anzeige des Films im Label
        selectedMovieLabel.setText(m.toString());
        
        
    }

	

}
