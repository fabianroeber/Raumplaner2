package itprojekt.raumplaner.shared.bo;

import java.sql.Date;

/**
 * Dieses Model enth&auml;t alle relevanten Informationen zu einem User.
 * 
 * @author Fabian
 *
 */
public class User extends RaumPlanerBusinessObject {

	private static final long serialVersionUID = 1917108383838779183L;

	/** Vorname */
	private String vorname;
	/** Nachname */
	private String nachname;
	/** Email-Adresse */
	private String email;

	public User() {
		super();
	}

	public User(int id, Date created, String vorname, String nachname,
			String email) {
		super();
		setId(id);
		setCreated(created);
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
