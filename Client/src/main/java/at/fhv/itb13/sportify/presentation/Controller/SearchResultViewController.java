package at.fhv.itb13.sportify.presentation.Controller;

import at.fhv.itb13.sportify.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.communication.dtos.PersonDTOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Caroline on 30.10.2015.
 */
public class SearchResultViewController {
    private List<PersonDTO> _result;


    @FXML
    private TableView<PersonDTO> _personTable;
    @FXML
    private TableColumn<PersonDTO, String> _firstNameColumn;
    @FXML
    private TableColumn<PersonDTO, String> _lastNameColumn;

    public void setResult(List<PersonDTO> result) {
      //  _result = result;

        _result = new LinkedList<>();

        PersonDTO p1 = new PersonDTOImpl();
        p1.setFName("Caroline");
        p1.setLName("Meusburger");
        p1.setBirthdate("22.07.1994");

        PersonDTO p2 = new PersonDTOImpl();
        p2.setFName("Patrick Kai");
        p2.setLName("Poiger");
        p2.setBirthdate("02.06.1992");

        _result.add(p1);
        _result.add(p2);


    }

    @FXML
    private void initialize() {

        setResult(null);
        // Initialize the person table with the three columns.
        _firstNameColumn.setCellValueFactory(new PropertyValueFactory<PersonDTO, String>("Firstname"));
        _lastNameColumn.setCellValueFactory(new PropertyValueFactory<PersonDTO, String>("Lastname"));

        if(_result.size() > 0){

            ObservableList<PersonDTO> obsRestults = FXCollections.observableArrayList();
            for(PersonDTO p : _result){
                obsRestults.add(p);
            }

            _personTable.setItems(obsRestults);

        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Patient found");
            alert.setHeaderText("Sorry, the Patient could not be found.");
            alert.setContentText("Please make sure you have entered the right name or Social Insurance Number.");
            alert.showAndWait();
        }

    }


}
