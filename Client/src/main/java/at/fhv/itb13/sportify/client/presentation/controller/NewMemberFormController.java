package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.application.SessionController;
import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTOImpl;
import at.fhv.itb13.sportify.shared.communication.dtos.DisplayTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleSportDTO;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    public ComboBox _sportComboBox;

    @FXML
    public TableView<SimpleSportDTO> _sportTableView;

    @FXML
    public TableColumn<SimpleSportDTO, String> _sportTableColumn;

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
    private TableView<DisplayTeamDTO> _allTeamsTableView;

    @FXML
    private TableColumn<DisplayTeamDTO, String> _allTeamsNameColumn;

    @FXML
    private TableView<DisplayTeamDTO> _addedTeamsTableView;

    @FXML
    private TableColumn<DisplayTeamDTO, String> _addedTeamsNameColumn;

    private ObservableList<DisplayTeamDTO> _addedTeamsObservable = FXCollections.observableArrayList();
    private ObservableList<SimpleSportDTO> _sportObservable = FXCollections.observableArrayList();


    @FXML
    private void initialize(){
        //set values for allTeamsTableView's columns
        _allTeamsNameColumn.setCellValueFactory(new PropertyValueFactory<DisplayTeamDTO, String>("Name"));

        //set values for addedTeamsTableViews' columns
        _addedTeamsNameColumn.setCellValueFactory(new PropertyValueFactory<DisplayTeamDTO, String>("Name"));

        _sportTableColumn.setCellValueFactory(new PropertyValueFactory<SimpleSportDTO, String>("Name"));

        _addedTeamsTableView.setItems(_addedTeamsObservable);

        _sportTableView.setItems(_sportObservable);

        //_sportTableView.setItems(_sportObservable);

        _sportComboBox.getItems().setAll(setSports());

        setAllTeamsTableViewData();
    }

    private List<SimpleSportDTO> setSports() {
        List<SimpleSportDTO> simpleSportDTOs = null;
        try {
            simpleSportDTOs = SessionController.getInstance().getSession().getSportRemote().getAllSimpleSports();
            //_sportObservable.setAll(simpleSportDTOs);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return simpleSportDTOs;

    }

    private void setAllTeamsTableViewData() {
        //retrieve list of all members and set the list to the _allMembersTableView
        try {
            List<DisplayTeamDTO> allTeams = SessionController.getInstance().getSession().getTeamRemote().getAllDisplayTeams();

            if (allTeams != null) {
                //create an observableArrayList and fill it with all members
                ObservableList<DisplayTeamDTO> allTeamsObservable = FXCollections.observableArrayList();
                allTeamsObservable.addAll(allTeams);
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
            DisplayTeamDTO teamToSwitch = _addedTeamsTableView.getItems().get(0);
            _addedTeamsTableView.getItems().remove(teamToSwitch);
            _allTeamsTableView.getItems().add(teamToSwitch);
        }
    }

    private void switchTeam (TableView<DisplayTeamDTO> viewToRemoveFrom, TableView<DisplayTeamDTO> viewToAddTo) {
        if (viewToRemoveFrom.getSelectionModel().getSelectedItem() != null) {
            DisplayTeamDTO teamToSwitch = viewToRemoveFrom.getSelectionModel().getSelectedItem();
            viewToRemoveFrom.getItems().remove(teamToSwitch);
            viewToAddTo.getItems().add(teamToSwitch);
        }
    }

    /**
     *
     * @throws RemoteException
     */
    @FXML
    private void saveNewMember() throws RemoteException, NotAuthorizedException {

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
                for (DisplayTeamDTO team : _addedTeamsTableView.getItems()) {
                    newMember.addTeam(team.getId());
                }
            }
            if(_sportTableView.getItems() != null){
                for(SimpleSportDTO sport : _sportTableView.getItems()){
                    newMember.addSport(sport.getId());
                }
            }

            SessionController.getInstance().getSession().getPersonRemote().create(newMember);
            initSuccessAlert();
            SportifyGUI.getSharedMainApp().loadMemberDataView(newMember);
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
    private void cancelNewMember() {
        SportifyGUI.getSharedMainApp().loadMemberList();
    }

    @FXML
    public void addSport(ActionEvent actionEvent) {
        SimpleSportDTO simpleSportDTO = (SimpleSportDTO) _sportComboBox.getSelectionModel().getSelectedItem();
        if(!_sportObservable.contains(simpleSportDTO)) {
            _sportObservable.add(simpleSportDTO);
        }
    }

    @FXML
    public void removeSport(ActionEvent actionEvent) {
        _sportObservable.remove(_sportTableView.getSelectionModel().getSelectedItem());
    }
}