package itprojekt.raumplaner.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.google.appengine.api.rdbms.AppEngineDriver;

/**
 * Diese Klasse stellt die Verbindung zur Datenbank her.
 * 
 * @author Fabian
 * @author Thies
 */
public class DatabaseConnection {

	private static Connection connection = null;

	private static String url = "jdbc:google:rdbms://lucid-copilot-788/raumplanerdb?user=root";
	public static Connection getConnection() {

		if (connection == null) {
			try {
				DriverManager.registerDriver(new AppEngineDriver());
				connection = DriverManager.getConnection(url);
			} catch (SQLException e1) {
				connection = null;
				System.out.println("Database could not bet loaded");
				e1.printStackTrace();
			}
		}
		return connection;
	}
}
