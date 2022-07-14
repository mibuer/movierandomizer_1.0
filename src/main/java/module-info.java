module at.miriam.movierandomizer {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    opens at.miriam.movierandomizer to javafx.fxml;
    exports at.miriam.movierandomizer;
    
    opens at.miriam.movierandomizer.controller to javafx.fxml;
    exports at.miriam.movierandomizer.controller;
}
