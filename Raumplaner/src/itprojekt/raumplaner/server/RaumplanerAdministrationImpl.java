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
		// TODO Auto-generated method stub

	}

	@Override
	public void saveNewBelegung(Belegung belegung) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBelegung(Belegung belegung) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteBelegung(Belegung belegung) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Einladung> getEinladungenByBelegung() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Einladung> getEinladungenByUser() {
		// TODO Auto-generated method stub
		return null;
	}

}
