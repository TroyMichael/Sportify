package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.communication.ServiceLocator;
import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.*;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.SessionRemote;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Michael on 26.10.2015.
 * <p/>
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
    private TableView<DisplayTeamDTO> _allTeamsOpponentTableView;

    @FXML
    private TableColumn<DisplayTeamDTO, String> _allTeamsOpponentNameColumn;

    private LocalDate _localDate;

    private LocalTime _localTime;

    private int _duration;

    private TournamentDTO _tournament;

    private HashSet<ExternalDisplayTeamDTO> _externalDisplayTeamDTOs;
    private Boolean _newTournament;

    @FXML
    private void initialize() {
        //set values for allTeamsTableView's columns
        _allTeamsNameColumn.setCellValueFactory(new PropertyValueFactory<DisplayTeamDTO, String>("Name"));

        //set values for addedTeamsTableViews' columns
        _allTeamsOpponentNameColumn.setCellValueFactory(new PropertyValueFactory<DisplayTeamDTO, String>("Name"));
    }

    @FXML
    private void saveNewMatch() throws RemoteException {

        if (validateInput()) {

            MatchDTO newMatch = new MatchDTOImpl();
            newMatch.setDuration(_duration);
            Calendar cal = new GregorianCalendar();
            cal.set(_localDate.getYear(), _localDate.getMonthValue(), _localDate.getDayOfMonth(), _localTime.getHour(), _localTime.getMinute(), _localTime.getSecond());
            newMatch.setStart(cal.getTime());

            MatchDTOImpl.SimpleMatchTeamDTO team1 = new MatchDTOImpl.SimpleMatchTeamDTO(_allTeamsTableView.getSelectionModel().getSelectedItem().getId());
            team1.setId(_allTeamsOpponentTableView.getSelectionModel().getSelectedItem().getId());
            team1.setName(_allTeamsTableView.getSelectionModel().getSelectedItem().getName());
            team1.setVersion(_allTeamsTableView.getSelectionModel().getSelectedItem().getVersion());

            MatchDTOImpl.SimpleMatchTeamDTO team2 = new MatchDTOImpl.SimpleMatchTeamDTO(_allTeamsOpponentTableView.getSelectionModel().getSelectedItem().getId());
            team2.setId(_allTeamsOpponentTableView.getSelectionModel().getSelectedItem().getId());
            team2.setName(_allTeamsOpponentTableView.getSelectionModel().getSelectedItem().getName());
            team2.setVersion(_allTeamsOpponentTableView.getSelectionModel().getSelectedItem().getVersion());

            newMatch.setTeam1(team1);
            newMatch.setTeam2(team2);
            newMatch.setTournamentId(_tournament.getId());
            newMatch.setMatchStatus("Planned");

            _tournament.addMatch(newMatch);

            initSuccessAlert();
            //todo fix correct weiterleitung
            if (_newTournament) {
                SportifyGUI.getSharedMainApp().loadNewTournamentView(_tournament, _externalDisplayTeamDTOs);
            } else {
                SportifyGUI.getSharedMainApp().loadEditTournamentForm(_tournament);
            }
        }
    }

    private void initSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Saving successful!");
        alert.setTitle("Saving successful");
        alert.setContentText("A new Match between " + _allTeamsTableView.getSelectionModel().getSelectedItem().getName() + " and " + _allTeamsOpponentTableView.getSelectionModel().getSelectedItem().getName() + " was saved successfully!");
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

        if (_datePicker.getValue() == null) {
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

            //define Regex: 0-24h, 0-59min, 0-69sek
            if (_startTimeTextField.getText().matches("([01][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])")) {
                _localDate = _datePicker.getValue();
                String[] times = _startTimeTextField.getText().split(":");
                _localTime = LocalTime.of(Integer.parseInt(times[0]), Integer.parseInt(times[1]), Integer.parseInt(times[2]));
                _startTimeTextField.setStyle("-fx-text-box-border: lightgrey;");
            } else {
                _startTimeTextField.setStyle("-fx-text-box-border: red;");
                validation = false;
            }
        }

        if (_allTeamsTableView.getSelectionModel().getSelectedItem() == null || _allTeamsOpponentTableView.getSelectionModel().getSelectedItem() == null) {
            validation = false;
        }

        return validation;
    }

    @FXML
    private void cancelNewMatch() {
        SportifyGUI.getSharedMainApp().loadNewTournamentView(_tournament, _externalDisplayTeamDTOs);
    }


    public void setTournament(TournamentDTO tournament, HashSet<ExternalDisplayTeamDTO> externalDisplayTeamDTOs, Boolean newTournament) {
        _tournament = tournament;
        _newTournament = newTournament;
        _externalDisplayTeamDTOs = externalDisplayTeamDTOs;

        List<DisplayTeamDTO> teams = ServiceLocator.getInstance().getRemote(SessionRemote.class).getTeamRemote().getAllDisplayTeams();
        for (DisplayTeamDTO team : teams) {
            if (_tournament.getTeamIDs().contains(team.getId())) {
                _allTeamsTableView.getItems().add(team);
                _allTeamsOpponentTableView.getItems().add(team);
            }
        }
        _allTeamsTableView.getItems().addAll(_externalDisplayTeamDTOs);
        _allTeamsOpponentTableView.getItems().addAll(_externalDisplayTeamDTOs);
    }
}