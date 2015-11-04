package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Caroline on 30.10.2015.
 */
public class SearchResultViewController {

    @FXML
    private TableView<PersonDTO> _personTable;

    @FXML
    private TableColumn<PersonDTO, String> _firstNameColumn;

    @FXML
    private TableColumn<PersonDTO, String> _lastNameColumn;

    @FXML
    private Label _searchInput;

    @FXML
    private BorderPane _borderPane;

    public void setResult(List<PersonDTO> argResult) {
        List<PersonDTO> result = new LinkedList<>(argResult);

        if (result.size() > 0) {
            ObservableList<PersonDTO> obsResults = FXCollections.observableArrayList();
            for (PersonDTO p : result) {
                obsResults.add(p);
            }
            _personTable.setItems(obsResults);
        }
    }

    public void setSearchInput(String searchInput) {
        _searchInput.setText(searchInput);
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns
        _firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FName"));
        _lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LName"));

        // Listen for selection changes and show the person details when changed
        _personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> loadMemberDataView(newValue, _borderPane));
    }

    private void loadMemberDataView(PersonDTO person, BorderPane pane) {
        SportifyGUI.getSharedMainApp().loadMemberDataView(person, pane);
    }

    private void loadSearchMemberForm() {
        SportifyGUI.getSharedMainApp().loadSearchMemberForm();
    }

    @FXML
    private void clickBackToSearch() {
        loadSearchMemberForm();
    }
}
