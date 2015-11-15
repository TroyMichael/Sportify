package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.application.SessionController;
import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Michael on 15.11.2015.
 *
 */
public class MemberListController {

    @FXML
    private TextField _filterTextField;

    @FXML
    private TableView<PersonDTO> _memberTableView;

    @FXML
    private TableColumn<PersonDTO, String> _firstNameColumn;

    @FXML
    private TableColumn<PersonDTO, String> _lastNameColumn;

    @FXML
    private TableColumn<PersonDTO, String> _birthdateColumn;

    @FXML
    private TableColumn<PersonDTO, String> _emailColumn;

    private ObservableList<PersonDTO> _personList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        //set column values
        _firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FName"));
        _lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LName"));
        _birthdateColumn.setCellValueFactory(new PropertyValueFactory<>("Birthdate"));
        _emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));

        getAndAddDataToPersonList();
        setDoubleClickOnMemberTableView();
        setFilterProcess();
    }

    /*
    defines that when a doubleclick occurs on a row in memberTableView the given PersonDTO is loaded in detail
     */
    private void setDoubleClickOnMemberTableView() {
        //set doubleclick handling of tableViewRow
        _memberTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    System.out.println(_memberTableView.getSelectionModel().getSelectedItem().getFName());
                    SportifyGUI.getSharedMainApp().loadMemberDataView(_memberTableView.getSelectionModel().getSelectedItem());
                }
            }
        });
    }

    /*
    defines the filter process, when the user enters data in the filter text field
    additionally, data is set to the tableview
     */
    private void setFilterProcess() {
        //filtering-process taken from: http://code.makery.ch/blog/javafx-8-tableview-sorting-filtering/

        //wrap observableList into filter list
        //p -> true shows all persons
        FilteredList<PersonDTO> _filteredPersonList = new FilteredList<>(_personList, p -> true);

        //set changeListener to textfield
        _filterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            _filteredPersonList.setPredicate(person -> {

                //define here all rules of filtering and what should be searched and filtered

                //if textfield is empty/null show all persons
                if (newValue == null ||newValue.isEmpty()) {
                    return true;
                }

                //else compare the filter string to the different columns
                String filterString = _filterTextField.getText().toLowerCase();

                if (person.getFName().toLowerCase().contains(filterString)) {
                    return true;
                } else if (person.getLName().toLowerCase().contains(filterString)) {
                    return true;
                } else if (person.getBirthdate().toLowerCase().contains(filterString)) {
                    return true;
                }
                //filter more attributes if wanted

                //if nothing matches, return false, so that that person won't be shown in the list
                return false;
            });
        });

        //FilteredList cannot be modified -> not sortable
        //wrap filteredList in sortedList
        SortedList<PersonDTO> sortedMemberList = new SortedList<>(_filteredPersonList);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedMemberList.comparatorProperty().bind(_memberTableView.comparatorProperty());

        //set sortedList as items to memberTableView
        _memberTableView.setItems(sortedMemberList);
    }

    /*
    Gets all Persons from the database and adds them to _personList
     */
    private void getAndAddDataToPersonList() {

        try {
            List<PersonDTO> tempPersonList = SessionController.getInstance().getSession().getPersonRemote().getAllPersons();
            tempPersonList.forEach(person -> _personList.add(person));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void newMember() {
        SportifyGUI.getSharedMainApp().loadNewMemberForm();
    }
}