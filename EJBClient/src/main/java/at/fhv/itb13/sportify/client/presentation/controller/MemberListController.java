package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.communication.ServiceLocator;
import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.PersonRemote;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

import java.util.List;
import java.util.function.Predicate;

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
        _firstNameColumn.setCellValueFactory(new PropertyValueFactory<PersonDTO, String>("FName"));
        _lastNameColumn.setCellValueFactory(new PropertyValueFactory<PersonDTO, String>("LName"));
        _birthdateColumn.setCellValueFactory(new PropertyValueFactory<PersonDTO, String>("Birthdate"));
        _emailColumn.setCellValueFactory(new PropertyValueFactory<PersonDTO, String>("Email"));

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
                    if (_memberTableView.getSelectionModel().getSelectedItem() != null) {
                        SportifyGUI.getSharedMainApp().loadMemberDataView(_memberTableView.getSelectionModel().getSelectedItem());
                    }
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
        final FilteredList<PersonDTO> _filteredPersonList = new FilteredList<>(_personList, new Predicate() {
            @Override
            public boolean test(Object o) {
                return true;
            }
        });

        //set changeListener to textfield
        _filterTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, final String newValue) {
                _filteredPersonList.setPredicate(new Predicate<PersonDTO>() {
                    @Override
                    public boolean test(PersonDTO person) {
                        //define here all rules of filtering and what should be searched and filtered

                        //if textfield is empty/null show all persons
                        if (newValue == null || newValue.isEmpty()) {
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
                        } else if (person.getEmail().toLowerCase().contains(filterString)) {
                            return true;
                        }
                        //filter more attributes if wanted

                        //if nothing matches, return false, so that that person won't be shown in the list
                        return false;
                    }
                });
            }
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
        List<PersonDTO> tempPersonList = ServiceLocator.getInstance().getRemote(PersonRemote.class).getAllPersons();
        for (PersonDTO person : tempPersonList) {
            _personList.add(person);
        }
    }

    @FXML
    private void newMember() {
        SportifyGUI.getSharedMainApp().loadNewMemberForm();
    }
}