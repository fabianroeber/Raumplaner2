package itprojekt.raumplaner.server;

import itprojekt.raumplaner.server.db.BelegungMapper;
import itprojekt.raumplaner.server.db.EinladungMapper;
import itprojekt.raumplaner.server.db.RaumMapper;
import itprojekt.raumplaner.server.db.UserMapper;
import itprojekt.raumplaner.shared.RaumplanerAdministration;
import itprojekt.raumplaner.shared.bo.Belegung;
import itprojekt.raumplaner.shared.bo.Einladung;
import itprojekt.raumplaner.shared.bo.Raum;
import itprojekt.raumplaner.shared.bo.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Verwaltungsklasse f&uuml;r die Raumplaner Benutzeroberfl�che. Hier werden
 * alle notwendigen Daten bereigestellt, jedoch keine neuen Daten erstellt.
 * 
 * @author Fabian
 *
 */

@RemoteServiceRelativePath("RaumplanerAdministrationImpl")
public class RaumplanerAdministrationImpl extends RemoteServiceServlet
		implements RaumplanerAdministration {

	private static final long serialVersionUID = 4785387129676251677L;

	public RaumplanerAdministrationImpl() {

	}

	private RaumMapper raumMapper = null;
	private BelegungMapper belegungMapper = null;
	private UserMapper userMapper = null;
	private EinladungMapper einladungMapper = null;

	@Override
	public void init() {
		raumMapper = RaumMapper.getRaumMapper();
		belegungMapper = BelegungMapper.getBelegungMapper();
		userMapper = UserMapper.getUserMapper();
		einladungMapper = EinladungMapper.getEinladungMapper();
	}

	@Override
	public List<Raum> getAllRaums() {
		List<Raum> raums = raumMapper.getAll();
		return raums;
	}

	@Override
	public List<Belegung> getAllBelegungByRaum(Raum raum) {
		List<Belegung> belegungen = belegungMapper.getAllbyRaum(raum);
		return belegungen;
	}

	@Override
	public User getUserByEmail(String email) {
		User user = userMapper.getUserByEmail(email);
		return user;
	}

	@Override
	public void saveNewRaum(Raum raum) {
		raumMapper.insert(raum);
	}

	@Override
	public void deleteRaum(Raum raum) {
		raumMapper.delete(raum);

	}

	@Override
	public void updateRaum(Raum raum) {
		raumMapper.update(raum);

	}

	@Override
	public void saveNewBelegung(Belegung belegung) {
		belegungMapper.insert(belegung);
	}

	@Override
	public void updateBelegung(Belegung belegung) {
		belegungMapper.update(belegung);

	}

	@Override
	public void deleteBelegung(Belegung belegung) {
		belegungMapper.delete(belegung);

	}

	@Override
	public List<Einladung> getEinladungenByBelegung(Belegung belegung) {
		return einladungMapper.getAllByBelegung(belegung);
	}

	@Override
	public boolean isRaumBelegt(Raum raum, Date date, int start, int end) {
		List<Belegung> belegungen = getAllBelegungByRaum(raum);
		for (Belegung belegung : belegungen) {

			Calendar startzeit = Calendar.getInstance();
			Calendar actDatum = Calendar.getInstance();
			Calendar endZeit = Calendar.getInstance();
			startzeit.setTime(belegung.getStartzeit());
			actDatum.setTime(date);
			endZeit.setTime(belegung.getEndzeit());

			// Zunächst nur Datum vergleichen
			if (startzeit.get(Calendar.YEAR) == actDatum.get(Calendar.YEAR)
					&& startzeit.get(Calendar.DAY_OF_YEAR) == actDatum
							.get(Calendar.DAY_OF_YEAR)) {
				// Bei gleichem Datum --> Zeitslot vergleichen
				if (startzeit.get(Calendar.HOUR_OF_DAY) == start
						&& endZeit.get(Calendar.HOUR_OF_DAY) == end) {
					return true;
				}
			}
		}
		return false;

	}

	@Override
	public List<Einladung> getEinladungenByUser(User user) {
		return einladungMapper.getAllByUser(user);
	}

	@Override
	public List<User> getAllUser() {
		return userMapper.getAll();
	}

	@Override
	public List<User> getAllFreeUser(Belegung selectedBelegung, int start) {
		// Laden aller notwendigen Daten
		List<User> users = getAllUser();
		List<User> freeUsers = new ArrayList<User>();
		List<Belegung> belegungen = getAllBelegung();
		List<Einladung> einladungen = getEinladungenByBelegung(selectedBelegung);

		for (User user : users) {
			for (Belegung belegung : belegungen) {
				Calendar startzeit = Calendar.getInstance();
				Calendar actDatum = Calendar.getInstance();
				startzeit.setTime(belegung.getStartzeit());
				actDatum.setTime(selectedBelegung.getStartzeit());
				if (startzeit.get(Calendar.YEAR) == actDatum.get(Calendar.YEAR)
						&& startzeit.get(Calendar.DAY_OF_YEAR) == actDatum
								.get(Calendar.DAY_OF_YEAR)) {

					if (startzeit.get(Calendar.HOUR_OF_DAY) == start) {
						for (Einladung einladung : einladungen) {
							if (!einladung.getUser().equals(user)) {
								freeUsers.add(user);
								break;
							} else {
								break;
							}
						}
					}
				} else {
					freeUsers.add(user);
					break;
				}

			}
		}
		return freeUsers;

	}

	@Override
	public List<Belegung> getAllBelegung() {
		return belegungMapper.getAll();
	}
}
