package at.fhv.itb13.sportify.presentation.Controller;

import at.fhv.itb13.sportify.presentation.SportifyGUI;
import javafx.fxml.FXML;

/**
 * Created by Patrick on 22.10.2015.
 * <p>
 * This controller controls the MainFrame.fxml. It implements the respective functions for each item in the menu bar.
 */
public class ControllerMainFrame {


    @FXML
    private void LoadAddNewMemberView() {
        SportifyGUI.getSharedMainApp().loadNewMemberForm();
    }

    @FXML
    private void LoadMemberlistView() {

    }

    @FXML
    private void LoadSearchMemberView() {

    }

}
