package itprojekt.raumplaner.server.db;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Klasse mit Util-Methoden f�r die Mapper-Klassen.
 * 
 * @author Fabian
 *
 */
public class DbUtil {

	/**
	 * Gibt die aktuelle Serverzeit als {@link Timestamp}-Objekt zur�ck
	 * 
	 * @return {@link Timestamp}
	 */
	public static Timestamp getTimeNow() {
		Date created = new Date(System.currentTimeMillis());
		return new Timestamp(created.getTime());
	}

}
