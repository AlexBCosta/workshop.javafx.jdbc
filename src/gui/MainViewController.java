package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;
	@FXML
	private MenuItem menuItemDepartment;
	@FXML
	private MenuItem menuItemAbout;

	@FXML
	public void onMenuItemSeller() {
		JOptionPane.showMessageDialog(null, "Seller = Vendedores");
	}

	@FXML
	public void onMenuItemDepartment() {
		JOptionPane.showMessageDialog(null, "Department = Departamentos");
	}

	@FXML
	public void onMenuItemAbout() {
		loadView("/gui/About.fxml");
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {

	}

	private synchronized void loadView(String absoluteName) { // evita problemas synchronized
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newBox = loader.load();

			// para mostrar essa janela dentro da principal
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();

			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newBox.getChildren());
			// termina aq

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error Loading View", e.getMessage(), AlertType.ERROR);
		}
	}

}