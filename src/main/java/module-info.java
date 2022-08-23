module at.miriam.movierandomizer {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	requires jakarta.persistence;
	requires org.hibernate.orm.core;
	
	opens at.miriam.movierandomizer.model;
	
	opens at.miriam.movierandomizer to javafx.fxml;
    exports at.miriam.movierandomizer;
    
    opens at.miriam.movierandomizer.controller to javafx.fxml;
    exports at.miriam.movierandomizer.controller;
}
