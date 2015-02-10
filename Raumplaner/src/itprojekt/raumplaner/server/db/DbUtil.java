package itprojekt.raumplaner.server.db;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Klasse mit Util-Methoden für die Mapper-Klassen.
 * 
 * @author Fabian
 *
 */
public class DbUtil {

	/**
	 * Gibt die aktuelle Serverzeit als {@link Timestamp}-Objekt zurück
	 * 
	 * @return {@link Timestamp}
	 */
	public static Timestamp getTimeNow() {
		Date created = new Date(System.currentTimeMillis());
		return new Timestamp(created.getTime());
	}

}
