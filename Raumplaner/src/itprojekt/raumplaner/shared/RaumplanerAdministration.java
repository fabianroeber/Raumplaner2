package itprojekt.raumplaner.shared;

import itprojekt.raumplaner.shared.bo.Belegung;
import itprojekt.raumplaner.shared.bo.Einladung;
import itprojekt.raumplaner.shared.bo.Raum;
import itprojekt.raumplaner.shared.bo.User;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * /** Synchrone Schnittstelle, die alle notwendigen Methoden f&uuml;r die
 * Klasse {@link RaumplanerAdministrationImpl} bereitstellt.
 * 
 * @author Fabian
 *
 */
/**
 * @author Fabian
 *
 */
@RemoteServiceRelativePath("raumplanerAdministration")
public interface RaumplanerAdministration extends RemoteService {

	/**
	 * Gibt alle R�ume zur�ck.
	 * 
	 * @return List<Raum>
	 */
	public List<Raum> getAllRaums();

	/**
	 * Gibt alle Belegungen eines Raums zur�ck.
	 * 
	 * @return List<Belegung>
	 */
	public List<Belegung> getAllBelegungByRaum(Raum raum);

	/**
	 * Gibt eine {@link User} Objekt zu einer Email-Adresse zur�ck.
	 * 
	 * @param E
	 *            -Mail Adresse als String
	 * @return {@link User}
	 */
	public User getUserByEmail(String user);

	/**
	 * Speichert einen neuen Raum
	 */
	public void saveNewRaum(Raum raum);

	/**
	 * Löscht einen Raum
	 * 
	 * @param raum
	 */
	public void deleteRaum(Raum raum);

	/**
	 * Speichert Änderungen an einem Raum
	 * 
	 * @param raum
	 */
	public void updateRaum(Raum raum);

	/**
	 * Speichert eine neue Belegung
	 */
	public void saveNewBelegung(Belegung belegung);

	/**
	 * Speichert Änderungen an einer Belegung
	 * 
	 * @param belegung
	 */
	public void updateBelegung(Belegung belegung);

	/**
	 * Löscht eine Belegung
	 * 
	 * @param belegung
	 */
	public void deleteBelegung(Belegung belegung);

	/**
	 * Lädt alle Einladungen nach der zugehörigen Belegung
	 * 
	 * @return Liste von {@link Einladung}
	 */
	public List<Einladung> getEinladungenByBelegung();

	/**
	 * Lädt alle Einladungen für einen User.
	 * 
	 * @return
	 */
	public List<Einladung> getEinladungenByUser();
}
