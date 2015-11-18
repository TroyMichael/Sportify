package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.application.SessionController;
import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTOImpl;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDetailDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Michael on 26.10.2015.
 * <p>
 * Controls the view NewMemberForm. Checks if all required text fields contain values when trying to add a new member
 * and then creates a DTO.
 */
public class NewMemberFormController {

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
    private TableView<TeamDetailDTO> _allTeamsTableView;

    @FXML
    private TableColumn<TeamDetailDTO, String> _allTeamsNameColumn;

    @FXML
    private TableView<TeamDetailDTO> _addedTeamsTableView;

    @FXML
    private TableColumn<TeamDetailDTO, String> _addedTeamsNameColumn;

    private ObservableList<TeamDetailDTO> _addedTeamsObservable = FXCollections.observableArrayList();


    @FXML
    private void initialize(){
        //set values for allTeamsTableView's columns
        _allTeamsNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        //set values for addedTeamsTableViews' columns
        _addedTeamsNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));


        _addedTeamsTableView.setItems(_addedTeamsObservable);

        setAllTeamsTableViewData();
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

    @FXML
    private void addTeam () {
        switchTeam(_allTeamsTableView, _addedTeamsTableView);
    }

    @FXML
    private void removeTeam () {
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

    private void switchTeam (TableView<TeamDetailDTO> viewToRemoveFrom, TableView<TeamDetailDTO> viewToAddTo) {
        if (viewToRemoveFrom.getSelectionModel().getSelectedItem() != null) {
            TeamDetailDTO teamToSwitch = viewToRemoveFrom.getSelectionModel().getSelectedItem();
            viewToRemoveFrom.getItems().remove(teamToSwitch);
            viewToAddTo.getItems().add(teamToSwitch);
        }
    }

    /**
     * TODO: get PAYMENTSTATUS
     *
     * @throws RemoteException
     */
    @FXML
    private void saveNewMember() throws RemoteException {

        if (validateInput()) {
            PersonDTOImpl newMember = new PersonDTOImpl(
                    _fNameTextField.getText(),
                    _lNameTextField.getText(),
                    _streetTextField.getText(),
                    _streetNoTextField.getText(),
                    _postalCodeTextField.getText(),
                    _cityTextField.getText(),
                    _eMailTextField.getText(),
                    _birthdayTextField.getText(),
                    true
            );

            if(_addedTeamsTableView.getItems() != null) {
                for (TeamDetailDTO team : _addedTeamsTableView.getItems()) {
                    newMember.addTeam(team.getId());
                }
            }

            SessionController.getInstance().getSession().getPersonRemote().create(newMember);
            initSuccessAlert();
            SportifyGUI.getSharedMainApp().loadMemberDataView(newMember); //TODO check if this conflicts with database if this member is edited and changes are saved
        }
    }

    private void initSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Saving successful!");
        alert.setTitle("Saving successful");
        alert.setContentText("The member '" + _fNameTextField.getText() + " " + _lNameTextField.getText() + "' was saved successfully!");
        alert.showAndWait();
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

    @FXML
    private void cancelNewMember() {
        SportifyGUI.getSharedMainApp().loadMemberList();
    }
}