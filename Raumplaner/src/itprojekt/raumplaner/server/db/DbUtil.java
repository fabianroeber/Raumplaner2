package itprojekt.raumplaner.server.db;

import java.util.Date;

/**
 * Klasse mit Util-Methoden für die Mapper-Klassen.
 * 
 * @author Fabian
 *
 */
public class DbUtil {

	/**
	 * Gibt die aktuelle Serverzeit als Date-Objekt zurück
	 * 
	 * @return {@link Date}
	 */
	public static Date getTimeNow() {
		Date created = new Date(System.currentTimeMillis());
		return created;
	}

}
