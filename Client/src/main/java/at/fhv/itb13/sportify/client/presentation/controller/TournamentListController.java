package at.fhv.itb13.sportify.client.presentation.controller;

import at.fhv.itb13.sportify.client.application.SessionController;
import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;
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
 * Created by KYUSS on hütt.
 *
 */
public class TournamentListController {

    @FXML
    private TextField _filterTextField;

    @FXML
    private TableView<TournamentDTO> _tournamentTableView;

    @FXML
    private TableColumn<TournamentDTO, String> _descriptionColumn;

    @FXML
    private TableColumn<TournamentDTO, String> _sportColumn;

    @FXML
    private TableColumn<TournamentDTO, String> _startDateColumn;

    @FXML
    private TableColumn<TournamentDTO, String> _locationColumn;

    private ObservableList<TournamentDTO> _tournamentList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        //set column values
        _descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("FName"));
        _sportColumn.setCellValueFactory(new PropertyValueFactory<>("LName"));
        _startDateColumn.setCellValueFactory(new PropertyValueFactory<>("Birthdate"));
        _locationColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));

        getAndAddDataToTournamentList();
        setDoubleClickOnMemberTableView();
        setFilterProcess();
    }

    /*
    defines that when a doubleclick occurs on a row in memberTableView the given TournamentDTO is loaded in detail
     */
    private void setDoubleClickOnMemberTableView() {
        //set doubleclick handling of tableViewRow
        _tournamentTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    if (_tournamentTableView.getSelectionModel().getSelectedItem() != null) {
                        SportifyGUI.getSharedMainApp().loadTournamentDataView(_tournamentTableView.getSelectionModel().getSelectedItem());
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
        //tournamentDTO -> true shows all tournaments
        FilteredList<TournamentDTO> _filteredTournamentDTOList = new FilteredList<>(_tournamentList, tournamentDTO -> true);

        //set changeListener to textfield
        _filterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            _filteredTournamentDTOList.setPredicate(tournamentDTO -> {

                //define here all rules of filtering and what should be searched and filtered

                //if textfield is empty/null show all persons
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                //else compare the filter string to the different columns
                String filterString = _filterTextField.getText().toLowerCase();

                if (tournamentDTO.getDescription().toLowerCase().contains(filterString)) {
                    return true;
                } else if (String.valueOf(tournamentDTO.getStartDate()).toLowerCase().contains(filterString)) {
                    return true;
                } else if (tournamentDTO.getLocation().toLowerCase().contains(filterString)) {
                    return true;
                }
                //filter more attributes if wanted

                //if nothing matches, return false, so that the searched tournament won't be shown in the list
                return false;
            });
        });

        //FilteredList cannot be modified -> not sortable
        //wrap filteredList in sortedList
        SortedList<TournamentDTO> sortedMemberList = new SortedList<>(_filteredTournamentDTOList);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedMemberList.comparatorProperty().bind(_tournamentTableView.comparatorProperty());

        //set sortedList as items to memberTableView
        _tournamentTableView.setItems(sortedMemberList);
    }

    /*
    Gets all tournaments from the database and adds them to _tournamentList
     */
    private void getAndAddDataToTournamentList() {
        try {
            List<TournamentDTO> tempTournamentList = SessionController.getInstance().getSession().getTournamentRemote().getAllTournaments();
            tempTournamentList.forEach(tournamentDTO -> _tournamentList.add(tournamentDTO));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void newTournament() {
        SportifyGUI.getSharedMainApp().loadNewTournamentView();
    }
}