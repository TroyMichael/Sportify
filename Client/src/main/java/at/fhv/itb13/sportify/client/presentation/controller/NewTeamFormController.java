package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.application.SessionController;
import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Michael on 10.11.2015.
 *
 */
public class NewTeamFormController {

    @FXML
    private TextField _nameTextField;

    @FXML
    private ComboBox<SportDTO> _sportComboBox;

    @FXML
    private TextField _trainerTextField;

    @FXML
    private TableView<PersonDTO> _allMembersTableView;

    @FXML
    private TableColumn<PersonDTO, String> _allMembersFirstNameColumn;

    @FXML
    private TableColumn<PersonDTO, String> _allMembersLastNameColumn;

    @FXML
    private TableView<PersonDTO> _addedMembersTableView;

    @FXML
    private TableColumn<PersonDTO, String> _addedMembersFirstNameColumn;

    @FXML
    private TableColumn<PersonDTO, String> _addedMembersLastNameColumn;

    private ObservableList<PersonDTO> _addedMembersObservable = FXCollections.observableArrayList();

    private PersonDTO _trainer;

    @FXML
    private void initialize(){

        //set values for allMembersTableView's columns
        _allMembersFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FName"));
        _allMembersLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LName"));

        //set values for addedMembersTableViews' columns
        _addedMembersFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FName"));
        _addedMembersLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LName"));

        _addedMembersTableView.setItems(_addedMembersObservable);

        setAllMembersTableViewData();
        setSportComboBoxData();
    }

    private void setAllMembersTableViewData() {
        //retrieve list of all members and set the list to the _allMembersTableView
        try {
            List<PersonDTO> allMembers = SessionController.getInstance().getSession().getPersonRemote().getAllPersons();

            if (allMembers != null) {
                //create an observableArrayList and fill it with all members
                ObservableList<PersonDTO> allMembersObservable = FXCollections.observableArrayList();
                allMembers.forEach(person -> allMembersObservable.add(person));
                _allMembersTableView.setItems(allMembersObservable);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    private void setSportComboBoxData() {
        try {
            List<SportDTO> sportList;
            sportList = SessionController.getInstance().getSession().getSportRemote().getSports();

            if (sportList != null) {
                ObservableList<SportDTO> sportObservable = FXCollections.observableArrayList();
                sportList.forEach(sport -> sportObservable.add(sport));
                _sportComboBox.getItems().addAll((sportObservable));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addMember () {
        switchMember(_allMembersTableView, _addedMembersTableView);
    }

    @FXML
    private void removeMember () {
        switchMember(_addedMembersTableView, _allMembersTableView);
    }

    @FXML
    private void removeAllMembers() {
        while (_addedMembersTableView.getItems().size() > 0) {
            PersonDTO personToSwitch = _addedMembersTableView.getItems().get(0);
                _addedMembersTableView.getItems().remove(personToSwitch);
                _allMembersTableView.getItems().add(personToSwitch);
        }
    }

    private void switchMember (TableView<PersonDTO> viewToRemoveFrom, TableView<PersonDTO> viewToAddTo) {
        if (viewToRemoveFrom.getSelectionModel().getSelectedItem() != null) {
            PersonDTO personToSwitch = viewToRemoveFrom.getSelectionModel().getSelectedItem();
            viewToRemoveFrom.getItems().remove(personToSwitch);
            viewToAddTo.getItems().add(personToSwitch);
        }
    }

    @FXML
    private void setTrainer() {
        //add old trainer back to the allMemberList
        if (_trainer != null) {
            _allMembersTableView.getItems().add(_trainer);
        }

        //set new Trainer and remove respective personDTO from the tableView
        if (_allMembersTableView.getSelectionModel().getSelectedItem() != null) {
            _trainer = _allMembersTableView.getSelectionModel().getSelectedItem();
            _allMembersTableView.getItems().remove(_trainer);
            _trainerTextField.setText(_trainer.getFName() + " " + _trainer.getLName());
        }
    }

    @FXML
    private void saveTeam() {

        if (validate()) {
            //gather all information of the new team
            String teamName = _nameTextField.getText();
            SportDTO selectedSport = _sportComboBox.getValue();

            //read all person IDs and save them into a hashset
            HashSet<String> addedMembersIDs = new HashSet<>();
            _addedMembersObservable.forEach(person -> addedMembersIDs.add(person.getId()));


            //create new TeamDTO
            TeamDTO newTeam = new TeamDTOImpl(teamName, _trainer.getId(), addedMembersIDs, selectedSport.getId());

            //call createFunction
            try {
                SessionController.getInstance().getSession().getTeamRemote().createTeam(newTeam);
                initSuccessAlert();
                //TODO switch to team detail view
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Components missing!");
            alert.setTitle("Components missing!");
            alert.setContentText("A new Team could not be created due to missing fields!");
            alert.showAndWait();
        }
    }

    private boolean validate() {
        boolean validation = true;

        if (_nameTextField.getText().length() == 0) {
            _nameTextField.setStyle("-fx-text-box-border:red;");
            validation = false;
        } else {
            _nameTextField.setStyle("-fx-text-box-border:lightgrey;");
        }

        if (_sportComboBox.getValue() == null) {
            _sportComboBox.setStyle("-fx-text-box-border:red;");
            validation = false;
        } else {
            _sportComboBox.setStyle("-fx-text-box-border:lightgrey;");
        }

        //TODO fix error message with style
        if (_trainerTextField.getText().length() == 0){
            _trainerTextField.setStyle("-fx-text-box-border:red;");
            validation = false;
        } else {
            _trainerTextField.setStyle("-fx-text-box-border:lightgrey;");
        }

        return validation;
    }

    private void initSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Saving successful!");
        alert.setTitle("Saving successful");
        alert.setContentText("A new Team with the name: " + _nameTextField.getText() + " and the Sport: " + _sportComboBox.getValue().getName() + "' was successfully created!");
        alert.showAndWait();
    }

    @FXML
    private void cancelNewTeam() {
        SportifyGUI.getSharedMainApp().loadHelloView();
    }
}