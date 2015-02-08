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
 * @author Alex
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
		Connection connection = DatabaseConnection.getConnection();
		List<User> resultlist = new ArrayList<User>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT idUser, vorname, nachname, email, created FROM User "
							+ " ORDER BY idUser");
			while (resultSet.next()) {
				User user = new User(resultSet.getInt("idUser"),
						resultSet.getDate("created"),
						resultSet.getString("vorname"),
						resultSet.getString("nachname"),
						resultSet.getString("email"));
				resultlist.add(user);
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
