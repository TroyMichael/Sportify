package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * This controller controls the MainFrame.fxml. It implements the respective functions for each item in the menu bar.
 */
public class HelloUserViewController {


    @FXML
    private Label _usernameLabel;


    @FXML
    private void initialize() {

    }

    public void setUsername(String username){
        _usernameLabel.setText(username);
    }
}
