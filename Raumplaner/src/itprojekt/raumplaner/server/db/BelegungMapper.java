package itprojekt.raumplaner.server.db;

import itprojekt.raumplaner.shared.bo.Belegung;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Datenbank-Zugriffsklasse f&uuml;r Objekte vom Typ {@link Belegung}.
 * 
 * @author Fabian
 * @author Thies
 *
 */
public class BelegungMapper implements DbMapperInterface<Belegung> {

	private static BelegungMapper belegungmapper = null;

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
					.executeQuery("SELECT idBelegung, thema, startzeit, endzeit, created, Raum_idRaum FROM Belegung "
							+ " ORDER BY idBelegung");
			while (resultSet.next()) {
				Belegung belegung = new Belegung();
				
				//TODO
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
	public void update(Belegung bo) {
	    Connection connection = DatabaseConnection.getConnection();

	    try {
	      Statement statement = connection.createStatement();

	      statement.executeUpdate("UPDATE Belegung" + "SET thema=\""
	          + bo.getThema() + "\", " + "startzeit=\"" + bo.getStartzeit() + "\", " 
	          + "endzeit=\"" + bo.getEndzeit() + "\", " + "created=\"" + bo.getCreated() + "\", " + "Raum_idRaum=\"" + bo.getRaum() + "\" "
	          + "WHERE id=" + bo.getId());

	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	}

	@Override
	public void insert(Belegung bo) {
	    Connection connection = DatabaseConnection.getConnection();

	    try {
	      Statement statement = connection.createStatement();

	      /*
	       * ZunÃ¤chst schauen wir nach, welches der momentan hÃ¶chste
	       * PrimÃ¤rschlÃ¼sselwert ist.
	       */
	      ResultSet resultSet = statement.executeQuery("SELECT MAX(idBelegung) AS maxID "
	          + "FROM Belegung ");

	      // Wenn wir etwas zurÃ¼ckerhalten, kann dies nur einzeilig sein
	      if (resultSet.next()) {
	        /*
	         * c erhÃ¤lt den bisher maximalen, nun um 1 inkrementierten
	         * PrimÃ¤rschlÃ¼ssel.
	         */
	        bo.setId(resultSet.getInt("maxID") + 1);

	        statement = connection.createStatement();

	        // Jetzt erst erfolgt die tatsÃ¤chliche EinfÃ¼geoperation
	        statement.executeUpdate("INSERT INTO Belegung (idBelegung, thema, startzeit, endzeit, created, Raum_idRaum) "
	            + "VALUES (" + bo.getId() + "','" + bo.getThema() + "','"
	            + bo.getStartzeit() + "','" + bo.getEndzeit() + "','" + bo.getCreated() + "','" + bo.getRaum() + "')");
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	  }

	@Override
	public Belegung getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Belegung bo) {
		// TODO Auto-generated method stub

	}

}
