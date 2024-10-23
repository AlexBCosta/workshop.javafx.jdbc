module workshop.javafx.jdbc {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;

	// add
	exports gui;

	// add
	opens gui;

	opens application to javafx.graphics, javafx.fxml;
}
