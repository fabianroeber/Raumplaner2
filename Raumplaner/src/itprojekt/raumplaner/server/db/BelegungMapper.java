package itprojekt.raumplaner.server.db;

import itprojekt.raumplaner.client.RpcSettings;
import itprojekt.raumplaner.shared.bo.Belegung;
import itprojekt.raumplaner.shared.bo.Einladung;
import itprojekt.raumplaner.shared.bo.Raum;

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
 * Datenbank-Zugriffsklasse f&uuml;r Objekte vom Typ {@link Belegung}.
 * 
 * @author Fabian
 * @author Thies
 *
 */
public class BelegungMapper implements DbMapperInterface<Belegung> {

	private static BelegungMapper belegungmapper = null;

	private Logger logger = RpcSettings.getLogger();

	private BelegungMapper() {

	}

	public static BelegungMapper getBelegungMapper() {
		if (belegungmapper == null) {
			belegungmapper = new BelegungMapper();
		}
		return belegungmapper;
	}

	@Override
	public List<Belegung> getAll() {
		Connection connection = DatabaseConnection.getConnection();
		List<Belegung> resultlist = new ArrayList<Belegung>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT idBelegung, thema, startzeit, endzeit, created, User_idUser, Raum_idRaum FROM Belegung "
							+ " ORDER BY idBelegung");
			while (resultSet.next()) {

				Belegung belegung = new Belegung(resultSet.getString("thema"),
						resultSet.getTimestamp("startzeit"),
						resultSet.getTimestamp("endzeit"),
						resultSet.getTimestamp("created"));
				belegung.setId(resultSet.getInt("idBelegung"));
				belegung.setRaum(RaumMapper.getRaumMapper().getById(
						resultSet.getInt("Raum_idRaum")));
				belegung.setErsteller(UserMapper.getUserMapper().getById(
						resultSet.getInt("User_idUser")));
				resultlist.add(belegung);
			}
			resultSet.close();
		} catch (SQLException e) {
			logger.log(Level.WARNING,
					"Fehler beim Datenbankzugriff auf alle Buchungen", e);
		}
		return resultlist;
	}

	@Override
	public void update(Belegung bo) {
		Connection connection = DatabaseConnection.getConnection();

		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("UPDATE Belegung" + " SET thema='"
					+ bo.getThema() + "', " + "startzeit='"
					+ new Timestamp(bo.getStartzeit().getTime()) + "', "
					+ "endzeit='" + new Timestamp(bo.getEndzeit().getTime())
					+ "', " + "created='" + DbUtil.getTimeNow() + "', "
					+ "Raum_idRaum=" + bo.getRaum().getId() + ", "
					+ " User_idUser=" + bo.getErsteller().getId()
					+ " WHERE idBelegung=" + bo.getId());

			if (bo.getEinladungen() != null) {

				for (Einladung einladung : bo.getEinladungen()) {
					if (einladung.isNew()) {
						EinladungMapper.getEinladungMapper().insert(einladung);
					} else {
						EinladungMapper.getEinladungMapper().update(einladung);
					}

				}
			}

		} catch (SQLException e) {
			logger.log(
					Level.WARNING,
					"Fehler beim Schreiben der Belegung mit der Id:"
							+ bo.getId(), e);
		}

	}

	@Override
	public void insert(Belegung bo) {
		Connection connection = DatabaseConnection.getConnection();

		try {
			Statement statement = connection.createStatement();
			// Höchste ID auslesen
			ResultSet resultSet = statement
					.executeQuery("SELECT MAX(idBelegung) AS maxid "
							+ "FROM Belegung ");

			if (resultSet.next()) {
				// Höchste ID + 1 ist die ID für unser neues Projekt
				bo.setId(resultSet.getInt("maxid") + 1);

				statement = connection.createStatement();

				statement
						.executeUpdate("INSERT INTO Belegung (idBelegung, thema, startzeit, endzeit, created, Raum_idRaum, User_idUser) "
								+ "VALUES ("
								+ bo.getId()
								+ ", '"
								+ bo.getThema()
								+ "', '"
								+ new Timestamp(bo.getStartzeit().getTime())
								+ "', '"
								+ new Timestamp(bo.getEndzeit().getTime())
								+ "', '"
								+ DbUtil.getTimeNow()
								+ "', "
								+ bo.getRaum().getId()
								+ ", "
								+ bo.getErsteller().getId() + ")");

				if (bo.getEinladungen() != null) {

					for (Einladung einladung : bo.getEinladungen()) {
						EinladungMapper.getEinladungMapper().insert(einladung);
					}
				}
			}
		} catch (SQLException e) {
			logger.log(Level.WARNING,
					"Fehler beim Schreiben der neuen Belegung mit dem Thema:"
							+ bo.getThema(), e);
		}

	}

	@Override
	public Belegung getById(int id) {
		Connection connection = DatabaseConnection.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT idBelegung, thema, startzeit, endzeit, created, User_idUser, Raum_idRaum FROM Belegung "
							+ " WHERE idBelegung=" + id);
			while (resultSet.next()) {

				Belegung belegung = new Belegung(resultSet.getString("thema"),
						resultSet.getTimestamp("startzeit"),
						resultSet.getTimestamp("endzeit"),
						resultSet.getTimestamp("created"));
				belegung.setId(resultSet.getInt("idBelegung"));
				belegung.setRaum(RaumMapper.getRaumMapper().getById(
						resultSet.getInt("Raum_idRaum")));
				belegung.setErsteller(UserMapper.getUserMapper().getById(
						resultSet.getInt("User_idUser")));
				return belegung;
			}
			resultSet.close();
		} catch (SQLException e) {
			logger.log(Level.WARNING,
					"Fehler beim Datenbankzugriff auf die Belegung mit der ID: "
							+ id, e);
		}
		return null;

	}

	@Override
	public void delete(Belegung bo) {
		Connection connection = DatabaseConnection.getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("DELETE FROM Belegung "
					+ "WHERE idBelegung=" + bo.getId());

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Belegung mit der ID: " + bo.getId()
					+ " konnte nicht gelöscht werden", e);
		}
	}

	/**
	 * Diese Methode gibt eine Liste von Belegungen für einen Raum aus.
	 * 
	 * @return List<Belegung>
	 */
	public List<Belegung> getAllbyRaum(Raum raum) {
		Connection connection = DatabaseConnection.getConnection();
		List<Belegung> resultlist = new ArrayList<Belegung>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt
					.executeQuery("SELECT Belegung.idBelegung, Belegung.thema, Belegung.startzeit, Belegung.endzeit, Belegung.created, Belegung.Raum_idRaum, Belegung.User_idUser FROM Belegung"
							+ " WHERE Raum_idRaum=" + raum.getId());
			while (resultSet.next()) {
				Belegung belegung = new Belegung(resultSet.getString("thema"),
						resultSet.getTimestamp("startzeit"),
						resultSet.getTimestamp("endzeit"),
						resultSet.getTimestamp("created"));
				belegung.setId(resultSet.getInt("idBelegung"));
				belegung.setRaum(RaumMapper.getRaumMapper().getById(
						raum.getId()));
				belegung.setErsteller(UserMapper.getUserMapper().getById(
						resultSet.getInt("User_idUser")));
				resultlist.add(belegung);
				belegung.setEinladungen(EinladungMapper.getEinladungMapper()
						.getAllByBelegung(belegung));
			}
		} catch (SQLException e) {
			logger.log(
					Level.WARNING,
					"Fehler beim Laden aller Buchungen für den Raum: "
							+ raum.getBezeichnung(), e);
		}

		return resultlist;
	}
}
