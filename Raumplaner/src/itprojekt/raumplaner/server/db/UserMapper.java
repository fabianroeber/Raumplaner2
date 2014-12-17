package itprojekt.raumplaner.server.db;

import java.util.List;

import itprojekt.raumplaner.shared.bo.Einladung;
import itprojekt.raumplaner.shared.bo.User;

/**
 * /** Datenbank-Zugriffsklasse f&uuml;r {@link Einladung} Objekte.
 * 
 * @author Fabian
 * @author Thies
 *
 */

public class UserMapper implements DbMapperInterface<User> {

	private static UserMapper usermapper = null;

	private UserMapper() {
	}

	public static UserMapper getUserMapper() {
		if (usermapper == null) {
			usermapper = new UserMapper();
		}
		return usermapper;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User bo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(User bo) {
		// TODO Auto-generated method stub

	}

	@Override
	public User getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(User bo) {
		// TODO Auto-generated method stub

	}

}
