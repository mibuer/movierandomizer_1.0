package at.miriam.movierandomizer.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import at.miriam.movierandomizer.model.Genre;
import at.miriam.movierandomizer.model.Movie;
import at.miriam.movierandomizer.model.StreamingService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.RadioButton;

public class TableViewMovieController extends BaseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Movie, String> directorColumn;

    @FXML
    private Label filterTableLabel;

    @FXML
    private TextField filterTableTextField;

    @FXML
    private TableColumn<Movie, Genre> genreColumn;

    @FXML
    private SplitPane splitPane;

    @FXML
    private TableColumn<Movie, StreamingService> streamingColumn;

    @FXML
    private TableView<Movie> tableView;

    @FXML
    private TableColumn<Movie, String> titleColumn;

    @FXML
    private Button updateMovieButton;

    @FXML
    private TableColumn<Movie, String> watchedColumn;

    @FXML
    private TableColumn<Movie, String> yearColumn;

	@FXML 
	private RadioButton radioButtonWatched;

	@FXML 
	private RadioButton radioButtonNotWatched;

	@FXML 
	private Button watchMovieFromTableButton;
    

    @FXML
    void onDeleteTableButtonClick(ActionEvent event) {

    	//moviesDBlist am gewählten Index löschen
    	Movie movie = model.getSelectedMovie();
    	if (movie != null) {
    		model.moviesDBList.remove(movie);
    		tableView.getSelectionModel().clearSelection();
    	}
    }

  
	@FXML public void onRadioButtonNotWatchedClick(ActionEvent event) {
		
		//zeige alle Filme moviesDBlist -> watched = false;
		List<Movie> moviesNotWatched = model.moviesDBList.stream()
											.filter(m -> m.isWatched() == false)
											.collect(Collectors.toList());
		
		ObservableList<Movie> moviesNotWatchedObservable = FXCollections.observableArrayList(moviesNotWatched);

		tableView.setItems(moviesNotWatchedObservable);
		
	
	}

	@FXML public void onWatchMovieFromTableButtonClick(ActionEvent event) {
		
		//set movie auf watched = true;
		Movie movie = model.getSelectedMovie();
    	if (movie != null) {
    		movie.setWatched(true);
    		int index = model.moviesDBList.indexOf(movie);
    		model.moviesDBList.set(index, movie);
    		System.out.println("****************Watch Movie From Table***********************");
    		System.out.println(model.moviesDBList.get(index));
    	}
		
		
	}

	@FXML 
	public void onRadioButtonWatchedClick(ActionEvent event) {
		
		//zeige alle Filme moviesDBlist -> watched = true;
		List<Movie> moviesWatched = model.moviesDBList.stream()
				.filter(m -> m.isWatched() == true)
				.collect(Collectors.toList());

		ObservableList<Movie> moviesWatchedObservable = FXCollections.observableArrayList(moviesWatched);

		tableView.setItems(moviesWatchedObservable);
		

	
	}
	
	
	
	

    @FXML
    void initialize() {
        assert directorColumn != null : "fx:id=\"directorColumn\" was not injected: check your FXML file 'movieTableView.fxml'.";
        assert filterTableLabel != null : "fx:id=\"filterTableLabel\" was not injected: check your FXML file 'movieTableView.fxml'.";
        assert filterTableTextField != null : "fx:id=\"filterTableTextField\" was not injected: check your FXML file 'movieTableView.fxml'.";
        assert genreColumn != null : "fx:id=\"genreColumn\" was not injected: check your FXML file 'movieTableView.fxml'.";
        assert splitPane != null : "fx:id=\"splitPane\" was not injected: check your FXML file 'movieTableView.fxml'.";
        assert streamingColumn != null : "fx:id=\"streamingColumn\" was not injected: check your FXML file 'movieTableView.fxml'.";
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'movieTableView.fxml'.";
        assert titleColumn != null : "fx:id=\"titleColumn\" was not injected: check your FXML file 'movieTableView.fxml'.";
        assert updateMovieButton != null : "fx:id=\"updateMovieButton\" was not injected: check your FXML file 'movieTableView.fxml'.";
        assert watchedColumn != null : "fx:id=\"watchedColumn\" was not injected: check your FXML file 'movieTableView.fxml'.";
        assert yearColumn != null : "fx:id=\"yearColumn\" was not injected: check your FXML file 'movieTableView.fxml'.";

        
        tableView.setItems(model.moviesDBList);
        
        genreColumn.setCellValueFactory(data -> new SimpleObjectProperty<Genre>(data.getValue().getGenre()));
        titleColumn.setCellValueFactory(data -> new SimpleObjectProperty<String>(data.getValue().getTitle()));
        yearColumn.setCellValueFactory(data -> new SimpleObjectProperty<String>(data.getValue().getYear()));
        directorColumn.setCellValueFactory(data -> new SimpleObjectProperty<String>(data.getValue().getDirector()));
        streamingColumn.setCellValueFactory(data -> new SimpleObjectProperty<StreamingService>(data.getValue().getStreamingService()));
        
        //Filter Tabelle
        
        FilteredList<Movie> filteredList = new FilteredList<>(model.moviesList, p -> true);
        
        
        filterTableTextField.textProperty().addListener((observable, oldValue, newValue) -> {

			filteredList.setPredicate(data -> { 

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase(); 
																	
																
				if (data.getGenre().toString().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (data.getTitle().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (data.getYear().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (data.getDirector().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (data.getStreamingService().getName().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} 
				
//				else if (data.isWatched().toLowerCase().contains(lowerCaseFilter)) {
//					return true;
//				}

				return false;

			});
		});	
        	
        SortedList<Movie> sortedData = new SortedList<>(filteredList);
		sortedData.comparatorProperty().bind(tableView.comparatorProperty());

		tableView.setItems(sortedData);			
        			
        			
        			
        
        	
        		
        		
        		
        		
        		
      
        
        
        
        
        
    }



}
