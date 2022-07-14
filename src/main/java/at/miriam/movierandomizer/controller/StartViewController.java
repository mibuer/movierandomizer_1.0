package at.miriam.movierandomizer.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import at.miriam.movierandomizer.model.Genre;
import at.miriam.movierandomizer.model.Movie;
import at.miriam.movierandomizer.model.RandomMovie;
import at.miriam.movierandomizer.model.StreamingService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

public class StartViewController extends BaseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField directorTextField;

    @FXML
    private Button randomizeButton;

    @FXML
    private Button resetButton;

    @FXML
    private Pane startPane;

    @FXML
    private Label startViewHeader;

    @FXML
    private Label startViewMovieLabelOne;

    @FXML
    private Label startViewMovieLabelThree;

    @FXML
    private Label startViewMovieLabelTwo;


    @FXML
    private Button submitButton;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField yearTextField;

	@FXML 
	private ChoiceBox<StreamingService> streamingServiceChoiceBox;

	@FXML 
	private ChoiceBox<Genre> genreChoiceBox;

	@FXML 
	private ListView<Movie> listView;
	
	

    @FXML
    void onRandomizeButtonClick(ActionEvent event) {
    	//neues Fenster öffnet sich und der zufällig ausgewählte Film wird angezeigt
    		//Methode getLastThreeMovies, nur aus diesen auswählen
    	//Klick auf OK Button --> Movie watched = true 
    		//am alten Index der Liste Wert ändern 
    	//Klick auf 2ndTry --> Auswahl aus den beiden übrigen Filmen -> Anzeige des neu ausgewählten Films 
    
    	Random rand = new Random();
    	
    	int randomIndex = rand.nextInt(model.moviesList.size());
    	
    	
    	Movie randomMovie = model.moviesList.get(randomIndex);
    	
    	//zufälltig gewählter Film wird in der SingeltonKlasse RandomMovie gespeichert 
    	//damit können andere Controller darauf zugreifen
    	RandomMovie.getInstance().setRandMovie(randomMovie);
    	
    	System.out.println("*************************RandomMovie************************************");
    	System.out.println(randomMovie);
    
    	
    	try {
			new RandomizeMovieWindow().show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }


	@FXML
    void onResetFormButtonClick(ActionEvent event) {
    	
		clearForm();
    }

    @FXML
    void onSubmitMovieButtonClick(ActionEvent event) { //----------------------------add new Movie to ArrayList
    	//Movie wird erstellt und in der moviesList im MovieModel hinzugefügt
    	String title = titleTextField.getText();
    	String director = directorTextField.getText();
    	Genre genre = genreChoiceBox.getValue();
    	String year = yearTextField.getText();
    	StreamingService streamingService = streamingServiceChoiceBox.getValue();
    	
    	if (isValidFormInput(title, director, genre, year, streamingService)) {
    		
    		Movie movie = new Movie(title, director, genre, year, streamingService, false);
    		
    		System.out.println(movie);
    		
    		model.moviesList.add(movie);
    		
    	}
    	
    	System.out.println("***************************OnSubmitButtonClick******************************");
    	System.out.println(model.moviesList);
    	
    	clearForm();
    	
    	listView.setItems(model.moviesList);
    	
    	if (model.moviesList.size() == 3) {
    		submitButton.disableProperty();
    	}
    	
    	
    }


    private boolean isValidFormInput(String title, String director, Genre genre, 
    								String year, StreamingService streamingService) {
		
    	
    	return !title.isEmpty()
    			&& !director.isEmpty()
    			&& genre != null
    			&& !year.isEmpty()
    			&& streamingService != null;
    	
	}
    
    
    private void clearForm() {
		
    	titleTextField.setText("");
    	directorTextField.setText("");
    	genreChoiceBox.setValue(null);
    	yearTextField.setText("");
    	streamingServiceChoiceBox.setValue(null);

	}

	@FXML
    void initialize() {
        assert directorTextField != null : "fx:id=\"directorTextField\" was not injected: check your FXML file 'startView.fxml'.";
        assert randomizeButton != null : "fx:id=\"randomizeButton\" was not injected: check your FXML file 'startView.fxml'.";
        assert resetButton != null : "fx:id=\"resetButton\" was not injected: check your FXML file 'startView.fxml'.";
        assert startPane != null : "fx:id=\"startPane\" was not injected: check your FXML file 'startView.fxml'.";
        assert startViewHeader != null : "fx:id=\"startViewHeader\" was not injected: check your FXML file 'startView.fxml'.";
        assert startViewMovieLabelOne != null : "fx:id=\"startViewMovieLabelOne\" was not injected: check your FXML file 'startView.fxml'.";
        assert startViewMovieLabelThree != null : "fx:id=\"startViewMovieLabelThree\" was not injected: check your FXML file 'startView.fxml'.";
        assert startViewMovieLabelTwo != null : "fx:id=\"startViewMovieLabelTwo\" was not injected: check your FXML file 'startView.fxml'.";
        assert submitButton != null : "fx:id=\"submitButton\" was not injected: check your FXML file 'startView.fxml'.";
        assert titleTextField != null : "fx:id=\"titleTextField\" was not injected: check your FXML file 'startView.fxml'.";
        assert yearTextField != null : "fx:id=\"yearTextField\" was not injected: check your FXML file 'startView.fxml'.";
        
        
        genreChoiceBox.setItems(model.genreList);
        streamingServiceChoiceBox.setItems(model.streamingServiceList);
        
        submitButton.disableProperty().bind(titleTextField.textProperty().isEmpty()
        								.or(genreChoiceBox.valueProperty().isNull())
        								.or(streamingServiceChoiceBox.valueProperty().isNull()));
        
        titleTextField.setPromptText("Herr der Ringe, Die Gefährten");
        directorTextField.setPromptText("Peter Jackson");
        genreChoiceBox.setValue(Genre.FANTASY);
        yearTextField.setPromptText("2001");
        streamingServiceChoiceBox.setValue(new StreamingService("Netflix"));
        
        
        
        
        
        

    }

}
