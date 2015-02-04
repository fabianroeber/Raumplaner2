package itprojekt.raumplaner.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import itprojekt.raumplaner.shared.bo.Raum;

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
	          + bo.getBezeichnung() + "\", " + "fassungsvermoegen=\"" + bo.getFassungsvermoegen() + "\", " 
	          + "created=\"" + bo.getCreated() + "\" "
	          + "WHERE id=" + bo.getId());

	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }
	    
	}

	@Override
	public void insert(Raum bo) {
	    Connection connection = DatabaseConnection.getConnection();

	    try {
	      Statement statement = connection.createStatement();

	      /*
	       * ZunÃ¤chst schauen wir nach, welches der momentan hÃ¶chste
	       * PrimÃ¤rschlÃ¼sselwert ist.
	       */
	      ResultSet resultSet = statement.executeQuery("SELECT MAX(idRaum) AS maxID "
	          + "FROM Raum ");

	      // Wenn wir etwas zurÃ¼ckerhalten, kann dies nur einzeilig sein
	      if (resultSet.next()) {
	        /*
	         * c erhÃ¤lt den bisher maximalen, nun um 1 inkrementierten
	         * PrimÃ¤rschlÃ¼ssel.
	         */
	        bo.setId(resultSet.getInt("maxID") + 1);

	        statement = connection.createStatement();

	        // Jetzt erst erfolgt die tatsÃ¤chliche EinfÃ¼geoperation
	        statement.executeUpdate("INSERT INTO Raum (idRaum, bezeichnung, fassungsvermoegen, created) "
	            + "VALUES (" + bo.getId() + ",'" + bo.getBezeichnung() + "','"
	            + bo.getFassungsvermoegen() + ",'" + bo.getCreated() + "')");
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	  }


	@Override
	public Raum getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Raum bo) {
		// TODO Auto-generated method stub

	}

}
