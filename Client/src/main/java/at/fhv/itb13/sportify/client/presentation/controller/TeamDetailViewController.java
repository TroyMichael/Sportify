package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by Michael on 15.11.2015.
 *
 */
public class TeamDetailViewController {

    @FXML
    private Label _teamNameLabel;

    @FXML
    private Label _sportLabel;

    @FXML
    private Label _trainerNameLabel;

    @FXML
    private TableView<PersonDTO> _membersTableView;

    @FXML
    private TableColumn<PersonDTO, String> _firstNameColumn;

    @FXML
    private TableColumn<PersonDTO, String> _lastNameColumn;

    @FXML
    private TableColumn<PersonDTO, String> _birthdateColumn;

    @FXML
    private TableColumn<PersonDTO, String> _emailColumn;

    @FXML
    private TableColumn<PersonDTO, String> _feeColumn;


    @FXML
    private void initialize() {

        //set values of each column
        _firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FName"));
        _lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LName"));
        _birthdateColumn.setCellValueFactory(new PropertyValueFactory<>("BirthDate"));
        _emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        _feeColumn.setCellValueFactory(new PropertyValueFactory<>("Payed"));

    }

    @FXML
    private void editTeam() {
        //open editTeamView
    }
}
