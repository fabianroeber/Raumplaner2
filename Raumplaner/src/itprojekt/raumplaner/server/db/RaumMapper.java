package itprojekt.raumplaner.server.db;

import itprojekt.raumplaner.client.RpcSettings;
import itprojekt.raumplaner.shared.bo.Belegung;
import itprojekt.raumplaner.shared.bo.Raum;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Datenbank-Zugriffsklasse f&uuml;r {@link Raum} Objekte.
 * 
 * @author Fabian
 * @author Alex
 * @author Simon
 * @author Thies
 *
 */
public class RaumMapper implements DbMapperInterface<Raum> {

	private static RaumMapper raummapper = null;
	Logger logger = RpcSettings.getLogger();

	private RaumMapper() {
	}

	public static RaumMapper getRaumMapper() {
		if (raummapper == null) {
			raummapper = new RaumMapper();
		}
		return raummapper;
	}

	@Override
	public List<Raum> getAll() {
		Connection connection = DatabaseConnection.getConnection();
		List<Raum> resultlist = new ArrayList<Raum>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT idRaum, bezeichnung, fassungsvermoegen, created FROM Raum "
							+ " ORDER BY idRaum");
			while (resultSet.next()) {
				Raum raum = new Raum(resultSet.getInt("idRaum"),
						resultSet.getDate("created"),
						resultSet.getString("bezeichnung"),
						resultSet.getInt("fassungsvermoegen"));
				resultlist.add(raum);
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultlist;
	}

	@Override
	public void update(Raum bo) {
		Connection connection = DatabaseConnection.getConnection();

		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("UPDATE Raum" + "SET bezeichnung=\""
					+ bo.getBezeichnung() + "\", " + "fassungsvermoegen=\""
					+ bo.getFassungsvermoegen() + "\", " + "created=\""
					+ bo.getCreated() + "\" " + "WHERE id=" + bo.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void insert(Raum bo) {
		Connection connection = DatabaseConnection.getConnection();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT MAX(idRaum) AS maxID " + "FROM Raum ");

			if (resultSet.next()) {
				bo.setId(resultSet.getInt("maxID") + 1);
				statement = connection.createStatement();
				statement
						.executeUpdate("INSERT INTO Raum (idRaum, bezeichnung, fassungsvermoegen, created) "
								+ "VALUES ("
								+ bo.getId()
								+ ", '"
								+ bo.getBezeichnung()
								+ "', '"
								+ bo.getFassungsvermoegen()
								+ "', '"
								+ DbUtil.getTimeNow() + "')");
			}
		} catch (SQLException e) {
			logger.log(Level.WARNING,
					"Raum mit der Bezeichnung: " + bo.getBezeichnung()
							+ " konnte nicht gespeichert werden", e);
		}

	}

	@Override
	public Raum getById(int id) {
		Connection connection = DatabaseConnection.getConnection();

		try {
			Statement statement = connection.createStatement();

			ResultSet resultSet = statement
					.executeQuery("SELECT idRaum, bezeichnung, fassungsvermoegen, created FROM Raum "
							+ "WHERE idRaum=" + id + " ORDER BY idRaum");

			if (resultSet.next()) {
				Raum raum = new Raum(resultSet.getInt("idRaum"),
						resultSet.getDate("created"),
						resultSet.getString("bezeichnung"),
						resultSet.getInt("fassungsvermoegen"));
				return raum;
			}

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Raum mit der ID: " + id
					+ " konnte nicht geladen werden", e);
		}
		return null;

	}

	@Override
	public void delete(Raum bo) {
		Connection connection = DatabaseConnection.getConnection();

		// Zunächst müssen alle Belegungen des Raums gelöscht werden.
		BelegungMapper belegungMapper = BelegungMapper.getBelegungMapper();
		try {
			List<Belegung> belegungen = belegungMapper.getAllbyRaum(bo);
			if (belegungen != null) {
				for (Belegung belegung : belegungen) {
					belegungMapper.delete(belegung);
				}
			}
		} catch (Exception e) {
			logger.log(Level.WARNING, "Belegungen für den Raum mit der ID: "
					+ bo.getId() + " konnten nicht gelöscht werden", e);
		}
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("DELETE FROM Raum " + "WHERE idRaum="
					+ bo.getId());

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Raum mit der ID: " + bo.getId()
					+ " konnte nicht gelöscht werden", e);
		}

	}
}
