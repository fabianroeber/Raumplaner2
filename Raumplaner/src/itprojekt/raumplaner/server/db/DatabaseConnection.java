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

	public static boolean isUnitTesting = false;

	private static String url = "jdbc:google:rdbms://lucid-copilot-788:gcsql/mydb?user=root";
	private static String urlUnitTest = "jdbc:mysql://173.194.225.11:3306/mydb";

	public static Connection getConnection() {

		if (connection == null) {
			try {
				if (isUnitTesting) {

					connection = DriverManager.getConnection(urlUnitTest,
							"root", "1234");
				} else {
					DriverManager.registerDriver(new AppEngineDriver());
					connection = DriverManager.getConnection(url);
				}

			} catch (SQLException e1) {
				connection = null;
				System.out.println("Database could not bet loaded");
				e1.printStackTrace();
			}
			isUnitTesting = false;
		}
		return connection;
	}
}
