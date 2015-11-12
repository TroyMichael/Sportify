package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.application.SessionController;
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
                //create a observableArrayList and fill it with all members
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
    private void saveTeam() {
        //gather all information of the new team
        String teamName = _nameTextField.getText();
        SportDTO selectedSport = _sportComboBox.getValue();
        System.out.println(selectedSport.getName());
        System.out.println(selectedSport.getId());

        //read all person IDs and save them into a hashset
        HashSet<String> addedMembersIDs = new HashSet<>();
        _addedMembersObservable.forEach(person -> addedMembersIDs.add(person.getId()));


        //create new TeamDTO
        TeamDTO newTeam = new TeamDTOImpl(teamName, addedMembersIDs, selectedSport.getId());

        //call createFunction
        try {
            SessionController.getInstance().getSession().getTeamRemote().createTeam(newTeam);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}