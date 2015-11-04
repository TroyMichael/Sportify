package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import javafx.fxml.FXML;

/**
 * This controller controls the MainFrame.fxml. It implements the respective functions for each item in the menu bar.
 */
public class ControllerMainFrame {

    @FXML
    private void loadAddNewMemberView() {
        SportifyGUI.getSharedMainApp().loadNewMemberForm();
    }

    @FXML
    private void loadMemberlistView() {
    }

    @FXML
    private void loadSearchMemberView() {
        SportifyGUI.getSharedMainApp().loadSearchMemberForm();
    }
}
