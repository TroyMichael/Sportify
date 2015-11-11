package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;

/**
 * This controller controls the MainFrame.fxml. It implements the respective functions for each item in the menu bar.
 */
public class MainFrameController {

    @FXML
    private Menu _memberMenu;

    public void setMemberMenuDisable(boolean bool){
        _memberMenu.setDisable(bool);
    }

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

    @FXML
    private void loadNewTeamView() {
        SportifyGUI.getSharedMainApp().loadNewTeamForm();
    }
}
