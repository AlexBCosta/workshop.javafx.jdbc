package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

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
		JOptionPane.showMessageDialog(null, "About = Sobre");
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {

	}

}
