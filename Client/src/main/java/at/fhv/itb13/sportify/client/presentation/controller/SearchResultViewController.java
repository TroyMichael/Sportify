package at.fhv.itb13.sportify.client.presentation.controller;


import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Caroline on 30.10.2015.
 *
 */
public class SearchResultViewController {
    private List<PersonDTO> _result;

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

    public void setResult(List<PersonDTO> result) {
        _result = new LinkedList<>(result);

        if (_result.size() > 0) {

            ObservableList<PersonDTO> obsRestults = FXCollections.observableArrayList();
            for (PersonDTO p : _result) {
                obsRestults.add(p);
            }

            _personTable.setItems(obsRestults);

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Member found");
            alert.setHeaderText("Sorry, the Member could not be found.");
            alert.setContentText("Please make sure you have entered the right data.");
            alert.showAndWait();
        }
    }

    public void setSearchInput(String searchInput) {
        _searchInput.setText(searchInput);
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the three columns.
        _firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FName"));
        _lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LName"));

        // Listen for selection changes and show the person details when changed.
        _personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> LoadMemberDataView(newValue, _borderPane));
    }

    private void LoadMemberDataView(PersonDTO person, BorderPane pane) {
        SportifyGUI.getSharedMainApp().loadMemberDataView(person, pane);
    }
}
