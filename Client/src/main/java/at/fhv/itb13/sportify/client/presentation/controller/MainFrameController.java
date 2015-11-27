package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;

/**
 * This controller controls the MainFrame.fxml. It implements the respective functions for each item in the menu bar.
 */
public class MainFrameController {

    @FXML
    private MenuBar _menuBar;

    public void setMenuBarDisable(boolean bool) {
        _menuBar.setDisable(bool);
    }

    @FXML
    private void loadAddNewMemberView() {
        SportifyGUI.getSharedMainApp().loadNewMemberForm();
    }

    @FXML
    private void loadMemberlistView() {
        SportifyGUI.getSharedMainApp().loadMemberList();
    }

    @FXML
    private void loadSearchMemberView() {
        SportifyGUI.getSharedMainApp().loadSearchMemberForm();
    }

    @FXML
    private void loadNewTeamView() {
        SportifyGUI.getSharedMainApp().loadNewTeamForm();
    }

    @FXML
    private void loadTeamList() {
        SportifyGUI.getSharedMainApp().loadTeamList();
    }

    @FXML
    private void loadNewTournamentView() {
        SportifyGUI.getSharedMainApp().loadNewTournamentView();
    }

    @FXML
    private void loadTournamentListView(){
        SportifyGUI.getSharedMainApp().loadTournamentListView();
    }

    @FXML
    public void close(ActionEvent actionEvent) {
        Platform.exit();
    }
}
