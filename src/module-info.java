module workshop.javafx.jdbc {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;

	// add
	exports gui;
	exports model.entities;
	exports model.services;

	// add
	opens gui;
	opens model.entities;
	opens model.services;

	opens application to javafx.graphics, javafx.fxml;
}
