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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
			// Setzen der Zeitzone (App Engine setzt diese per Default auf UTC)
			startzeit.setTimeZone(TimeZone.getTimeZone("CET"));
			actDatum.setTimeZone(TimeZone.getTimeZone("CET"));
			endZeit.setTimeZone(TimeZone.getTimeZone("CET"));

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
		List<User> usersToFilter = new ArrayList<>();
		List<Belegung> belegungen = getAllBelegung();
		List<Einladung> einladungen = getEinladungenByBelegung(selectedBelegung);
		// Alle User und Belegungen durchgehen, Datum vergleichen, Uhrzeit
		// vergleihen
		for (User user : users) {
			for (Belegung belegung : belegungen) {
				Calendar startzeit = Calendar.getInstance();
				Calendar actDatum = Calendar.getInstance();
				startzeit.setTime(belegung.getStartzeit());
				actDatum.setTime(selectedBelegung.getStartzeit());
				// Setzen der Zeitzone (App Engine setzt diese per Default auf
				// UTC)
				startzeit.setTimeZone(TimeZone.getTimeZone("CET"));
				actDatum.setTimeZone(TimeZone.getTimeZone("CET"));
				if (startzeit.get(Calendar.YEAR) == actDatum.get(Calendar.YEAR)
						&& startzeit.get(Calendar.DAY_OF_YEAR) == actDatum
								.get(Calendar.DAY_OF_YEAR)) {

					if (startzeit.get(Calendar.HOUR_OF_DAY) == start) {
						for (Einladung einladung : belegung.getEinladungen()) {
							if (einladung.getUser().equals(user)) {
								usersToFilter.add(user);
								break;
							}
						}
					}
				}

			}
		}		
		users.removeAll(usersToFilter);
		return users;

	}

	@Override
	public List<Belegung> getAllBelegung() {
		List<Belegung> belegungen = belegungMapper.getAll();

		// Die Belegungen werden nach Datum und Uhrzeit sortiert
		Collections.sort(belegungen, new Comparator<Belegung>() {

			@Override
			public int compare(Belegung o1, Belegung o2) {
				return o1.getStartzeit().compareTo(o2.getStartzeit());
			}
		});
		return belegungen;
	}

	@Override
	public void deleteEinladung(List<Einladung> einladungen) {
		for (Einladung einladung : einladungen) {
			einladungMapper.delete(einladung);
		}
	}

	@Override
	public void SendInvitationMails(List<User> users, Belegung belegung) {
		for (User user : users) {
			MailSender.sendInvitationMail(user, belegung);
		}

	}

	@Override
	public void SendDeclineMails(List<User> users, Belegung belegung) {
		for (User user : users) {
			MailSender.sendDeclineMail(user, belegung);
		}

	}

	@Override
	public void updateEinladung(Einladung einladung) {
		einladungMapper.update(einladung);
	}
}
