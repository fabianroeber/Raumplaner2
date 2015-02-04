package itprojekt.raumplaner.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import itprojekt.raumplaner.shared.bo.Belegung;
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
		//Wird noch überarbeitet -> Alex
		Connection connection = DatabaseConnection.getConnection();
		List<User> resultlist = new ArrayList<User>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT idBelegung, thema, startzeit, endzeit, created, Raum_idRaum FROM Belegung "
							+ " ORDER BY idBelegung");
			while (resultSet.next()) {
				Belegung belegung = new Belegung(resultSet.getInt("idBelegung"),
						resultSet.getDate("created"),
						resultSet.getDate("startzeit"),
						resultSet.getDate("endzeit"),
						resultSet.getString("thema"),
						resultSet.getInt("Raum_idRaum"));
				resultlist.add(belegung);
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultlist;
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
