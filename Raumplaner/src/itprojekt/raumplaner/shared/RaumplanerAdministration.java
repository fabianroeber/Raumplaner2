package itprojekt.raumplaner.shared;

import java.util.List;

import itprojekt.raumplaner.server.RaumplanerAdministrationImpl;
import itprojekt.raumplaner.shared.bo.Belegung;
import itprojekt.raumplaner.shared.bo.Raum;
import itprojekt.raumplaner.shared.bo.User;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * /** Synchrone Schnittstelle, die alle notwendigen Methoden f&uuml;r die
 * Klasse {@link RaumplanerAdministrationImpl} bereitstellt.
 * 
 * @author Fabian
 *
 */
@RemoteServiceRelativePath("raumplanerAdministration")
public interface RaumplanerAdministration extends RemoteService {

	/**
	 * Gibt alle Räume zurück.
	 * 
	 * @return List<Raum>
	 */
	public List<Raum> getAllRaums();

	/**
	 * Gibt alle Belegungen eines Raums zurück.
	 * 
	 * @return List<Belegung>
	 */
	public List<Belegung> getAllBelegungByRaum(Raum raum);

	/**
	 * Gibt eine {@link User} Objekt zu einer Email-Adresse zurück.
	 * 
	 * @param user
	 * @return User
	 */
	public User getUserByEmail(String user);

}
