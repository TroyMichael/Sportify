package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.communication.ServiceLocator;
import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.DisplayTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleSportDTO;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.PersonRemote;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.SportRemote;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.TeamRemote;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Michael on 26.10.2015.
 * <p/>
 * Controls the view NewMemberForm. Checks if all required text fields contain values when trying to add a new member
 * and then creates a DTO.
 */
public class EditMemberDataController {

    @FXML
    private ComboBox _sportComboBox;

    @FXML
    private TableView<SimpleSportDTO> _sportsTableView;

    @FXML
    private TableColumn _sportTableColumn;

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
    private HBox _editButtonsHBox;

    @FXML
    private Label _memberViewTitle;

    @FXML
    private TableView<DisplayTeamDTO> _allTeamsTableView;

    @FXML
    private TableColumn<DisplayTeamDTO, String> _allTeamsNameColumn;

    @FXML
    private TableView<DisplayTeamDTO> _addedTeamsTableView;

    @FXML
    private TableColumn<DisplayTeamDTO, String> _addedTeamsNameColumn;

    private ObservableList<DisplayTeamDTO> _addedTeamsObservable = FXCollections.observableArrayList();

    //   private ObservableList<SimpleSportDTO> _sportObservable = FXCollections.observableArrayList();

    private ObservableList<SimpleSportDTO> _addedSportObservable = FXCollections.observableArrayList();

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
        setAllTeamsTableViewData();
        setSelectedTeamsTableViewData();
        setAllSportsTableViewData();
        _sportComboBox.getItems().setAll(setAllSports());
    }


    @FXML
    private void initialize() {

        //set values for allTeamsTableView's columns
        _allTeamsNameColumn.setCellValueFactory(new PropertyValueFactory<DisplayTeamDTO, String>("Name"));

        //set values for addedTeamsTableViews' columns
        _addedTeamsNameColumn.setCellValueFactory(new PropertyValueFactory<DisplayTeamDTO, String>("Name"));


        _addedTeamsTableView.setItems(_addedTeamsObservable);

        _sportTableColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        _sportsTableView.setItems(_addedSportObservable);

    }


    private void setAllTeamsTableViewData() {
        //retrieve list of all members and set the list to the _allMembersTableView
        List<DisplayTeamDTO> allTeams = ServiceLocator.getInstance().getRemote(TeamRemote.class).getAllDisplayTeams();

        if (allTeams != null) {
            //create an observableArrayList and fill it with all members
            ObservableList<DisplayTeamDTO> allTeamsObservable = FXCollections.observableArrayList();
            for (DisplayTeamDTO team : allTeams) {
                allTeamsObservable.add(team);
            }
            _allTeamsTableView.setItems(allTeamsObservable);
        }
    }

    private void setAllSportsTableViewData() {
        List<SimpleSportDTO> allSports = ServiceLocator.getInstance().getRemote(SportRemote.class).getAllSimpleSports();
        for (SimpleSportDTO simpleSportDTO : allSports) {
            if (_person.getSportIDs().contains(simpleSportDTO.getId())) {
                _addedSportObservable.add(simpleSportDTO);
            }
        }
    }

    private List<SimpleSportDTO> setAllSports() {
        return ServiceLocator.getInstance().getRemote(SportRemote.class).getAllSimpleSports();
    }

    private void setSelectedTeamsTableViewData() {

        Iterator<DisplayTeamDTO> iter = _allTeamsTableView.getItems().iterator();
        while (iter.hasNext()) {
            DisplayTeamDTO team = iter.next();
            if (_person.getTeamIds().contains(team.getId())) {
                _addedTeamsTableView.getItems().add(team);
                iter.remove();
            }
        }

    }


    @FXML
    private void addTeam() {
        switchTeam(_allTeamsTableView, _addedTeamsTableView);
    }

    @FXML
    private void removeTeam() {
        switchTeam(_addedTeamsTableView, _allTeamsTableView);
    }

    @FXML
    private void removeAllTeams() {
        while (_addedTeamsTableView.getItems().size() > 0) {
            DisplayTeamDTO teamToSwitch = _addedTeamsTableView.getItems().get(0);
            _addedTeamsTableView.getItems().remove(teamToSwitch);
            _allTeamsTableView.getItems().add(teamToSwitch);
        }
    }

    private void switchTeam(TableView<DisplayTeamDTO> viewToRemoveFrom, TableView<DisplayTeamDTO> viewToAddTo) {
        if (viewToRemoveFrom.getSelectionModel().getSelectedItem() != null) {
            DisplayTeamDTO teamToSwitch = viewToRemoveFrom.getSelectionModel().getSelectedItem();
            viewToRemoveFrom.getItems().remove(teamToSwitch);
            viewToAddTo.getItems().add(teamToSwitch);
        }
    }


    @FXML
    private void clickSaveButton() throws RemoteException, NotAuthorizedException {
        if (validateInput()) {
            _person.setFName(_fNameTextField.getText());
            _person.setLName(_lNameTextField.getText());
            _person.setStreet(_streetTextField.getText());
            _person.setHouseNumber(_streetNoTextField.getText());
            _person.setPostalCode(_postalCodeTextField.getText());
            _person.setCity(_cityTextField.getText());
            _person.setEmail(_eMailTextField.getText());
            _person.setBirthdate(_birthdayTextField.getText());

            _person.getTeamIds().clear();
            _person.getSportIDs().clear();
            for (DisplayTeamDTO team : _addedTeamsTableView.getItems()) {
                _person.addTeam(team.getId());
            }
            if (_sportsTableView.getItems() != null) {
                for (SimpleSportDTO sport : _sportsTableView.getItems()) {
                    _person.addSport(sport.getId());
                }
            }

            ServiceLocator.getInstance().getRemote(PersonRemote.class).editPerson(_person);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Saving successful!");
            alert.setTitle("Saving successful");
            alert.setContentText("The Member '" + _fNameTextField.getText() + " " + _lNameTextField.getText() + "' was successfuly edited!");
            alert.showAndWait();
            cancelEdit();
        }
    }

    @FXML
    private void cancelEdit() {
        SportifyGUI.getSharedMainApp().loadMemberDataView(_person);
    }

    private Boolean validateInput() {
        Boolean validation = true;

        if (_fNameTextField.getText().length() == 0) {
            _fNameTextField.setStyle("-fx-text-box-border: red;");
            validation = false;
        } else {
            _fNameTextField.setStyle("-fx-text-box-border:lightgrey;");
        }

        if (_lNameTextField.getText().length() == 0) {
            _lNameTextField.setStyle("-fx-text-box-border: red;");
            validation = false;
        } else {
            _lNameTextField.setStyle("-fx-text-box-border:lightgrey;");
        }

        if (_birthdayTextField.getText().length() == 0) {
            _birthdayTextField.setStyle("-fx-text-box-border: red;");
            validation = false;
        } else {
            _birthdayTextField.setStyle("-fx-text-box-border:lightgrey;");
        }

        return validation;
    }

    @FXML
    public void addSport(ActionEvent actionEvent) {
        SimpleSportDTO simpleSportDTO = (SimpleSportDTO) _sportComboBox.getSelectionModel().getSelectedItem();
        if (!_addedSportObservable.contains(simpleSportDTO)) {
            _addedSportObservable.add(simpleSportDTO);
        }
    }

    @FXML
    public void removeSport(ActionEvent actionEvent) {
        _addedSportObservable.remove(_sportsTableView.getSelectionModel().getSelectedItem());
    }
}