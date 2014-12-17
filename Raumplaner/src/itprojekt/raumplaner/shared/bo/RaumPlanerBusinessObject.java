package itprojekt.raumplaner.shared.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * Eine Basisklasse f&uuml;r alle Modelle des Raumplaners.
 * 
 * @author Fabian
 *
 */
public class RaumPlanerBusinessObject implements Serializable {

	private static final long serialVersionUID = -7768670689158205737L;
	/** Id */
	private int id;
	/** Erstelldatum */
	private Date created;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
