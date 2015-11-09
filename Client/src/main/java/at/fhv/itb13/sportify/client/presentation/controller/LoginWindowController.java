package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import javafx.fxml.FXML;

import java.awt.*;

/**
 * This controller controls the MainFrame.fxml. It implements the respective functions for each item in the menu bar.
 */
public class LoginWindowController {


    private void loadMainFrame() {
        SportifyGUI.getSharedMainApp().loadMainFrame();
    }

    @FXML
    private void loginButtonClicked(){

        loadMainFrame();
        System.out.println("Button Clicked");

    }
}
