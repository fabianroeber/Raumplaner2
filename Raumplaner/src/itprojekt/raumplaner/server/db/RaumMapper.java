package itprojekt.raumplaner.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import itprojekt.raumplaner.shared.bo.Raum;

/**
 * Zugriffsklasse f&uuml;r {@link Raum} Objekte.
 * 
 * @author Fabian
 * @author Alex
 * @author Simon
 * @author Thies
 *
 */
public class RaumMapper implements DbMapperInterface<Raum> {

	private static RaumMapper raummapper = null;

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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultlist;
	}

	@Override
	public void insertBo(Raum bo) {
		// TODO Auto-generated method stub

	}

	@Override
	public Raum getBoById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBo(Raum bo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBo(Raum bo) {
		// TODO Auto-generated method stub

	}

}
