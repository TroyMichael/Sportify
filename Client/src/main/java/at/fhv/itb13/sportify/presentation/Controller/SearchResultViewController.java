package at.fhv.itb13.sportify.presentation.Controller;

import at.fhv.itb13.sportify.communication.dtos.PersonDTO;

import java.util.List;

/**
 * Created by Caroline on 30.10.2015.
 */
public class SearchResultViewController {
    private List<PersonDTO> _result;

    public void setResult(List<PersonDTO> result) {
        _result = result;
    }
}
