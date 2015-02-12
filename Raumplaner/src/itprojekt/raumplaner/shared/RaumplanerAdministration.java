package itprojekt.raumplaner.shared;

import itprojekt.raumplaner.shared.bo.Belegung;
import itprojekt.raumplaner.shared.bo.Einladung;
import itprojekt.raumplaner.shared.bo.Raum;
import itprojekt.raumplaner.shared.bo.User;

import java.util.Date;
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
	public List<Einladung> getEinladungenByBelegung(Belegung belegung);

	/**
	 * Lädt alle Einladungen für einen User.
	 * 
	 * @return
	 */
	public List<Einladung> getEinladungenByUser(User user);

	/**
	 * Abfrage, ob Raum zum Zeitpunkt belegt ist
	 * 
	 * @return boolean
	 */
	public boolean isRaumBelegt(Raum raum, Date date, int start, int end);

	/**
	 * Gibt alle User zurück
	 * 
	 * @return Liste von {@link User}
	 */
	public List<User> getAllUser();

	/**
	 * Gibt alle verfügbaren User zurück
	 * 
	 * @return Liste von {@link User}
	 */
	public List<User> getAllFreeUser(Belegung belegung, int start);

	/**
	 * Gibt alle Belegungen zurück
	 * 
	 * @return
	 */
	public List<Belegung> getAllBelegung();

	/**
	 * Diese Methode löscht eine Einladung
	 * 
	 * @param einladung
	 */
	public void deleteEinladung(Einladung einladung);

	/**
	 * Versendet alle EinladungsMails für eine Liste von Usern zu einer Belegung
	 * 
	 * @param Liste
	 *            von {@link User}
	 * @param belegung
	 */
	public void SendInvitationMails(List<User> users, Belegung belegung);

	/**
	 * Versendet alle Ausladungsmails für eine Liste von Usern zu einer Belegung
	 * 
	 * @param Liste
	 *            von {@link User}
	 * @param belegung
	 */
	public void SendDeclineMails(List<User> users, Belegung belegung);
}
