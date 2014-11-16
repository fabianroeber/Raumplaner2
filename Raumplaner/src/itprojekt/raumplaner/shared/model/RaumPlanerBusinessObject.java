package itprojekt.raumplaner.shared.model;

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
	private Long id;
	/** Erstelldatum */
	private Date created;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
