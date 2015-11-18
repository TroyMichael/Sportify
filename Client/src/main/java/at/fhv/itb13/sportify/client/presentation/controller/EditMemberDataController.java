package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.application.SessionController;
import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDetailDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Michael on 26.10.2015.
 * <p>
 * Controls the view NewMemberForm. Checks if all required text fields contain values when trying to add a new member
 * and then creates a DTO.
 */
public class EditMemberDataController {

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
    private TableView<TeamDetailDTO> _allTeamsTableView;

    @FXML
    private TableColumn<TeamDetailDTO, String> _allTeamsNameColumn;

    @FXML
    private TableView<TeamDetailDTO> _addedTeamsTableView;

    @FXML
    private TableColumn<TeamDetailDTO, String> _addedTeamsNameColumn;

    private ObservableList<TeamDetailDTO> _addedTeamsObservable = FXCollections.observableArrayList();


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
    }


    @FXML
    private void initialize() {

        //set values for allTeamsTableView's columns
        _allTeamsNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        //set values for addedTeamsTableViews' columns
        _addedTeamsNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));


        _addedTeamsTableView.setItems(_addedTeamsObservable);


    }


    private void setAllTeamsTableViewData() {
        //retrieve list of all members and set the list to the _allMembersTableView
        try {
            List<TeamDetailDTO> allTeams = SessionController.getInstance().getSession().getTeamDetailRemote().getAllTeams();

            if (allTeams != null) {
                //create an observableArrayList and fill it with all members
                ObservableList<TeamDetailDTO> allTeamsObservable = FXCollections.observableArrayList();
                allTeams.forEach(team -> allTeamsObservable.add(team));
                _allTeamsTableView.setItems(allTeamsObservable);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void setSelectedTeamsTableViewData() {

        Iterator<TeamDetailDTO> iter = _allTeamsTableView.getItems().iterator();
        while (iter.hasNext()) {
            TeamDetailDTO team = iter.next();
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
            TeamDetailDTO teamToSwitch = _addedTeamsTableView.getItems().get(0);
            _addedTeamsTableView.getItems().remove(teamToSwitch);
            _allTeamsTableView.getItems().add(teamToSwitch);
        }
    }

    private void switchTeam(TableView<TeamDetailDTO> viewToRemoveFrom, TableView<TeamDetailDTO> viewToAddTo) {
        if (viewToRemoveFrom.getSelectionModel().getSelectedItem() != null) {
            TeamDetailDTO teamToSwitch = viewToRemoveFrom.getSelectionModel().getSelectedItem();
            viewToRemoveFrom.getItems().remove(teamToSwitch);
            viewToAddTo.getItems().add(teamToSwitch);
        }
    }


    @FXML
    private void clickSaveButton() throws RemoteException {
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

            for (TeamDetailDTO team : _addedTeamsTableView.getItems()) {
                _person.addTeam(team.getId());
            }


            SessionController.getInstance().getSession().getPersonRemote().editPerson(_person);
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
            _fNameTextField.setStyle("-fx-text-box-border: black;");
        }

        if (_lNameTextField.getText().length() == 0) {
            _lNameTextField.setStyle("-fx-text-box-border: red;");
            validation = false;
        } else {
            _lNameTextField.setStyle("-fx-text-box-border: black;");
        }

        if (_birthdayTextField.getText().length() == 0) {
            _birthdayTextField.setStyle("-fx-text-box-border: red;");
            validation = false;
        } else {
            _birthdayTextField.setStyle("-fx-text-box-border: black;");
        }

        return validation;
    }
}