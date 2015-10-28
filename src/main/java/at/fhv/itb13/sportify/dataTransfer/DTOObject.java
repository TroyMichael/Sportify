package at.fhv.itb13.sportify.dataTransfer;

import at.fhv.itb13.sportify.database.PersistentObjectImpl;

import java.io.Serializable;

/**
 * Created by KYUSS on 27.10.2015.
 */
public abstract class DTOObject implements Serializable {

    public abstract DTOObject build(PersistentObjectImpl entity);

    //TODO Version Control bei UpdateObject
}
