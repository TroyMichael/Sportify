package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.communication.ServiceLocator;
import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.DisplayTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleSportDTO;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.SportRemote;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.TeamRemote;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Michael on 26.10.2015.
 * <p/>
 * Controls the view NewMemberForm. Checks if all required text fields contain values when trying to add a new member
 * and then creates a DTO.
 */
public class MemberDataController {

    @FXML
    private TextField _fNameTextField;

    @FXML
    private TextField _lNameTextField;

    @FXML
    private TextField _streetTextField;

    @FXML
    private TextField _streetNoTextField;

    @FXML
    private TextField _postalCodeTextField;

    @FXML
    private TextField _cityTextField;

    @FXML
    private TextField _eMailTextField;

    @FXML
    private TextField _birthdayTextField;

    @FXML
    private Button _editButton;

    @FXML
    private Label _memberViewTitle;

    @FXML
    private javafx.scene.control.TableView<DisplayTeamDTO> _teamTableView;

    @FXML
    private TableView<SimpleSportDTO> _sportsTableView;

    @FXML
    private TableColumn<DisplayTeamDTO, String> _teamNameColumn;

    @FXML
    private TableColumn<SimpleSportDTO, String> _sportNameColumn;

    private ObservableList<DisplayTeamDTO> _teamsObservable = FXCollections.observableArrayList();

    private ObservableList<SimpleSportDTO> _sportsObservable = FXCollections.observableArrayList();


    private PersonDTO _person;


    public void setPerson(PersonDTO person) {
        _person = person;

        if (_person != null) {
            if (_person.getFName() != null) {
                _fNameTextField.setText(_person.getFName());
            }
            if (_person.getLName() != null) {
                _lNameTextField.setText(_person.getLName());
            }
            if (_person.getBirthdate() != null) {
                _birthdayTextField.setText(_person.getBirthdate());
            }
            if (_person.getCity() != null) {
                _cityTextField.setText(_person.getCity());
            }
            if (_person.getEmail() != null) {
                _eMailTextField.setText(_person.getEmail());
            }
            if (_person.getPostalCode() != null) {
                _postalCodeTextField.setText(_person.getPostalCode());
            }
            if (_person.getHouseNumber() != null) {
                _streetNoTextField.setText(_person.getHouseNumber());
            }
            if (_person.getStreet() != null) {
                _streetTextField.setText(_person.getStreet());
            }
        }
        setTeamsTableViewData();
        setSportTableViewData();
    }

    private void setSportTableViewData() {
        List<SimpleSportDTO> allSports = ServiceLocator.getInstance().getRemote(SportRemote.class).getAllSimpleSports();
        HashSet<String> sports = new HashSet<>();
        sports = _person.getSportIDs();
        if (sports != null) {
            for (SimpleSportDTO simpleSportDTO : allSports) {
                if (sports.contains(simpleSportDTO.getId())) {
                    _sportsObservable.add(simpleSportDTO);
                }
            }

            _sportsTableView.setItems(_sportsObservable);
        }
    }

    @FXML
    private void initialize() {

        //set values for allTeamsTableView's columns
        _teamNameColumn.setCellValueFactory(new PropertyValueFactory<DisplayTeamDTO, String>("Name"));

        _teamTableView.setItems(_teamsObservable);

        _sportNameColumn.setCellValueFactory(new PropertyValueFactory<SimpleSportDTO, String>("Name"));

        _sportsTableView.setItems(_sportsObservable);
    }

    private void setTeamsTableViewData() {
        List<DisplayTeamDTO> allTeams = ServiceLocator.getInstance().getRemote(TeamRemote.class).getAllDisplayTeams();
        HashSet<String> teams = new HashSet<>();
        teams = _person.getTeamIds();

        if (teams != null) {

            for (DisplayTeamDTO teamDto : allTeams) {
                if (teams.contains(teamDto.getId())) {
                    _teamsObservable.add(teamDto);

                }
            }

            _teamTableView.setItems(_teamsObservable);
        }
    }

    @FXML
    private void clickEditButton() {
        SportifyGUI.getSharedMainApp().loadEditMemberData(_person);
    }
}