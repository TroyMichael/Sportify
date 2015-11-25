package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.application.SessionController;
import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTOImpl;
import at.fhv.itb13.sportify.shared.communication.dtos.DisplayTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * Created by Michael on 26.10.2015.
 * <p>
 * Controls the view NewMemberForm. Checks if all required text fields contain values when trying to add a new member
 * and then creates a DTO.
 */
public class NewMatchFormController {

    @FXML
    private TextField _durationTextField;

    @FXML
    private DatePicker _datePicker;

    @FXML
    private TextField _startTimeTextField;

    @FXML
    private TableView<DisplayTeamDTO> _allTeamsTableView;

    @FXML
    private TableColumn<DisplayTeamDTO, String> _allTeamsNameColumn;

    @FXML
    private TableView<DisplayTeamDTO> _allTeamsOponentTableView;

    @FXML
    private TableColumn<DisplayTeamDTO, String> _allTeamsOponentNameColumn;

    private LocalDate _localDate;

    private int _duration;

    private TournamentDTO _tournament;

    @FXML
    private void initialize(){
        //set values for allTeamsTableView's columns
        _allTeamsNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        //set values for addedTeamsTableViews' columns
        _allTeamsOponentNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
    }

    @FXML
    private void saveNewMatch() throws RemoteException {

        if (validateInput()) {

            MatchDTO newMatch = new MatchDTOImpl();
            newMatch.setDuration(_duration);
            newMatch.setStart(Date.from(_localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            newMatch.addMatchTeamId(_allTeamsTableView.getSelectionModel().getSelectedItem().getId());
            newMatch.addMatchTeamId(_allTeamsOponentTableView.getSelectionModel().getSelectedItem().getId());
            newMatch.setTorunamentId(_tournament.getId());
            newMatch.setMatchStatus("Planned");

            _tournament.addMatch(newMatch);

            initSuccessAlert();
            SportifyGUI.getSharedMainApp().loadNewTournamentView(_tournament);
        }
    }

    private void initSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Saving successful!");
        alert.setTitle("Saving successful");
        alert.setContentText("A new Match between " + _allTeamsTableView.getSelectionModel().getSelectedItem().getName() + " and " + _allTeamsOponentTableView.getSelectionModel().getSelectedItem().getName() + " was saved successfully!");
        alert.showAndWait();
    }

    private Boolean validateInput() {
        Boolean validation = true;

        //Check if inputs are null or empty
        if (_durationTextField.getText().length() == 0) {
            _durationTextField.setStyle("-fx-text-box-border: red;");
            validation = false;
        } else {
            _durationTextField.setStyle("-fx-text-box-border: lightgrey;");
        }

        if (_datePicker.getValue() == null){
            _datePicker.setStyle("-fx-date-picker-border: red;");
            validation = false;
        } else {
            _datePicker.setStyle("-fx-date-picker-border: lightgrey;");
        }

        if (_startTimeTextField.getText().length() == 0) {
            _startTimeTextField.setStyle("-fx-text-box-border: red;");
            validation = false;
        } else {
            _startTimeTextField.setStyle("-fx-text-box-border: lightgrey;");
        }

        //check if inputs are matching the given regex
        if (validation) {
            if (_durationTextField.getText().matches("(\\d*)")) {
                _duration = Integer.parseInt(_durationTextField.getText());
                _durationTextField.setStyle("-fx-text-box-border:  lightgrey;");
            } else {
                _durationTextField.setStyle("-fx-text-box-border: red;");
                validation = false;
            }

            if (_startTimeTextField.getText().matches("([01][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])")) {
                _localDate = _datePicker.getValue();
                String[] times = _startTimeTextField.getText().split(":");
                _localDate.atTime(Integer.parseInt(times[0]), Integer.parseInt(times[1]), Integer.parseInt(times[2]));
                _startTimeTextField.setStyle("-fx-text-box-border: lightgrey;");
            } else {
                _startTimeTextField.setStyle("-fx-text-box-border: red;");
                validation = false;
            }
        }

        if (_allTeamsTableView.getSelectionModel().getSelectedItem() == null ||_allTeamsOponentTableView.getSelectionModel().getSelectedItem() == null) {
            validation = false;
        }

        return validation;
    }

    @FXML
    private void cancelNewMatch() {
        SportifyGUI.getSharedMainApp().loadNewTournamentView(_tournament);
    }


    public void setTournament(TournamentDTO tournament) {
        _tournament = tournament;

        try {
            List<DisplayTeamDTO> teams = SessionController.getInstance().getSession().getTeamDetailRemote().getAllTeams();
            teams.forEach(team -> {
                if (_tournament.getTeamIDs().contains(team.getId())) {
                    _allTeamsTableView.getItems().add(team);
                    _allTeamsOponentTableView.getItems().add(team);
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}