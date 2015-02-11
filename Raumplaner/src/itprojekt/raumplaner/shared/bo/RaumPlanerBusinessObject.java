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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RaumPlanerBusinessObject other = (RaumPlanerBusinessObject) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
