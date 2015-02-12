package itprojekt.raumplaner.server.db;

import itprojekt.raumplaner.client.RpcSettings;
import itprojekt.raumplaner.shared.bo.Belegung;
import itprojekt.raumplaner.shared.bo.Einladung;
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
 * Datenbank-Zugriffsklasse f&uuml;r {@link Einladung} Objekte.
 * 
 * @author Fabian, Alex
 * @author Thies
 *
 */
public class EinladungMapper implements DbMapperInterface<Einladung> {

	private static EinladungMapper einladungmapper = null;

	private Logger logger = RpcSettings.getLogger();

	private EinladungMapper() {
	}

	public static EinladungMapper getEinladungMapper() {
		if (einladungmapper == null) {
			einladungmapper = new EinladungMapper();
		}
		return einladungmapper;
	}

	@Override
	public List<Einladung> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Einladung bo) {
		Connection connection = DatabaseConnection.getConnection();

		try {
			Statement statement = connection.createStatement();

			String akzeptiert;

			if (bo.getAkzeptiert()) {
				akzeptiert = "y";
			} else {
				akzeptiert = "n";
			}

			statement.executeUpdate("UPDATE Einladung" + " SET User_idUser="
					+ bo.getUser() + ", " + "Belegung_idBelegung='"
					+ bo.getBelegung() + "', " + "created='"
					+ DbUtil.getTimeNow() + "', " + "akzeptiert='" + akzeptiert
					+ "' " + "WHERE idEinladung=" + bo.getId());

		} catch (SQLException e) {
			logger.log(
					Level.WARNING,
					"Fehler beim Schreiben der Einladung mit der Id:"
							+ bo.getId(), e);
		}

	}

	@Override
	public void insert(Einladung bo) {
		Connection connection = DatabaseConnection.getConnection();

		try {
			Statement statement = connection.createStatement();
			// Höchste ID auslesen
			ResultSet resultSet = statement
					.executeQuery("SELECT MAX(idEinladung) AS maxid "
							+ "FROM Einladung ");

			if (resultSet.next()) {
				// Höchste ID + 1 ist die ID für unsere neue Einladung
				bo.setId(resultSet.getInt("maxid") + 1);

				String akzeptiert;

				if (bo.getAkzeptiert()) {
					akzeptiert = "y";
				} else {
					akzeptiert = "n";
				}

				statement = connection.createStatement();

				statement
						.executeUpdate("INSERT INTO Einladung (idEinladung, akzeptiert, User_idUser, Belegung_idBelegung, created) "
								+ "VALUES ("
								+ bo.getId()
								+ ", '"
								+ akzeptiert
								+ "', "
								+ bo.getUser().getId()
								+ ", "
								+ bo.getBelegung().getId()
								+ "', '"
								+ DbUtil.getTimeNow() + "')");
			}
		} catch (SQLException e) {
			logger.log(Level.WARNING,
					"Fehler beim Schreiben der neuen Einladung mit der ID:"
							+ bo.getId(), e);
		}

	}

	@Override
	public Einladung getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Einladung bo) {
		Connection connection = DatabaseConnection.getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("DELETE FROM Einladung "
					+ "WHERE idEinladung=" + bo.getId());

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Einladung mit der ID: " + bo.getId()
					+ " konnte nicht gelöscht werden", e);
		}

	}

	public List<Einladung> getAllByUser(User user) {

		Connection connection = DatabaseConnection.getConnection();
		List<Einladung> resultlist = new ArrayList<Einladung>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT idEinladung, akzeptiert, User_idUser, created, Belegung_idBelegung FROM Einladung"
							+ " WHERE User_idUser=" + user.getId());
			while (resultSet.next()) {
				Einladung einladung = new Einladung();
				einladung.setId(resultSet.getInt("idEinladung"));
				einladung.setCreated(resultSet.getDate("created"));
				if (resultSet.getString("akzeptiert").equalsIgnoreCase("y")) {
					einladung.setAkzeptiert(true);
				} else {
					einladung.setAkzeptiert(false);
				}
				einladung.setUser(UserMapper.getUserMapper().getById(
						user.getId()));
				einladung.setBelegung(BelegungMapper.getBelegungMapper()
						.getById(resultSet.getInt("Belegung_idBelegung")));
				resultlist.add(einladung);
			}
		} catch (SQLException e) {
			logger.log(
					Level.WARNING,
					"Fehler beim Laden aller Einladungen für den User: "
							+ user.getId(), e);
		}

		return resultlist;
	}

	public List<Einladung> getAllByBelegung(Belegung belegung) {
		Connection connection = DatabaseConnection.getConnection();
		List<Einladung> resultlist = new ArrayList<Einladung>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT idEinladung, akzeptiert, User_idUser, created, Belegung_idBelegung FROM Einladung"
							+ " WHERE Belegung_idBelegung=" + belegung.getId());
			while (resultSet.next()) {
				Einladung einladung = new Einladung();
				einladung.setId(resultSet.getInt("idEinladung"));
				einladung.setCreated(resultSet.getDate("created"));
				if (resultSet.getString("akzeptiert").equalsIgnoreCase("y")) {
					einladung.setAkzeptiert(true);
				} else {
					einladung.setAkzeptiert(false);
				}
				einladung.setUser(UserMapper.getUserMapper().getById(
						resultSet.getInt("User_idUser")));
				einladung.setBelegung(BelegungMapper.getBelegungMapper()
						.getById(resultSet.getInt("Belegung_idBelegung")));
				resultlist.add(einladung);
			}
		} catch (SQLException e) {
			logger.log(
					Level.WARNING,
					"Fehler beim Laden aller Einladungen für den User: "
							+ belegung.getId(), e);
		}
		return resultlist;
	}
	
}
