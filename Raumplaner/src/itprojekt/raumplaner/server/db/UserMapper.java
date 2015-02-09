package itprojekt.raumplaner.server.db;

import itprojekt.raumplaner.client.RpcSettings;
import itprojekt.raumplaner.shared.bo.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * /** Datenbank-Zugriffsklasse f&uuml;r {@link User} Objekte.
 * 
 * @author Fabian
 * @author Thies
 * @author Alex
 */

public class UserMapper implements DbMapperInterface<User> {

	private static UserMapper usermapper = null;
	private Logger logger = RpcSettings.getLogger();

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
			logger.log(Level.WARNING,
					"User konnten nicht aus der Datenbank geladen werden", e);
			e.printStackTrace();
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
		Connection connection = DatabaseConnection.getConnection();

		try {
			Statement statement = connection.createStatement();

			ResultSet resultSet = statement
					.executeQuery("SELECT MAX(idUser) AS maxid " + "FROM User ");

			if (resultSet.next()) {
				// Max ID + 1
				bo.setId(resultSet.getInt("maxid") + 1);

				statement = connection.createStatement();

				// Erstelldatum setzen
				bo.setCreated(DbUtil.getTimeNow());

				statement
						.executeUpdate("INSERT INTO User (idUser, created, email) "
								+ "VALUES ("
								+ bo.getId()
								+ ", '"
								+ new Timestamp(bo.getCreated().getTime())
								+ "', '" + bo.getEmail() + "')");
			}
		} catch (SQLException e) {
			logger.log(Level.WARNING,
					"User mit der Email-Adresse: " + bo.getEmail()
							+ "konnte nicht gespeichert werden", e);
			e.printStackTrace();
		}

	}

	@Override
	public User getById(int id) {
		Connection connection = DatabaseConnection.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT idUser, vorname, nachname, email, created FROM User "
							+ "WHERE idUser=" + id);
			while (resultSet.next()) {
				User user = new User(resultSet.getInt("idUser"),
						resultSet.getDate("created"),
						resultSet.getString("vorname"),
						resultSet.getString("nachname"),
						resultSet.getString("email"));
				return user;
			}

		} catch (SQLException e) {
			logger.log(Level.WARNING, "User mit der id: " + id
					+ " konnte nicht aus der Datenbank geladen werden.", e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(User bo) {
		// TODO Auto-generated method stub

	}

/**
	 * Gibt ein {@link User) Objekt mit der übergebenen Email-Adresse zurück.
	 * @param email
	 * @return
	 */
	public User getUserByEmail(String email) {
		Connection connection = DatabaseConnection.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT idUser, vorname, nachname, email, created FROM User "
							+ "WHERE email LIKE '" + email + "'");
			while (resultSet.next()) {
				User user = new User(resultSet.getInt("idUser"),
						resultSet.getDate("created"),
						resultSet.getString("vorname"),
						resultSet.getString("nachname"),
						resultSet.getString("email"));
				return user;
			}

		} catch (SQLException e) {
			logger.log(Level.WARNING, "User mit der E-Mail-Adresse: " + email
					+ " konnte nicht aus der Datenbank geladen werden.", e);
			e.printStackTrace();
		}
		return null;
	}
}
