package itprojekt.raumplaner.client;

import java.util.logging.Logger;

import com.google.gwt.core.shared.GWT;

import itprojekt.raumplaner.shared.RaumplanerAdministration;
import itprojekt.raumplaner.shared.RaumplanerAdministrationAsync;

/**
 * Klasse mit Eigenschaften und Diensten, die für alle Client-seitigen Klassen
 * relevant sind.
 * 
 * @author Fabian
 * @author Thies
 *
 */
public class RpcSettings {

	private static RaumplanerAdministrationAsync raumplanerAdministration = null;

	private static final Logger log = Logger.getLogger("Raumplaner Logger");

	/**
	 * Gibt den Logger zurück
	 * 
	 * @return eindeutige Instanz des Typs {@link Logger}
	 */
	public static Logger getLogger() {
		return log;
	}

	/**
	 * Gibt die RaumplanerAdministraion zurück und erstellt diese bei Bedarf.
	 * 
	 * @return eindeutige Instanz vom Typ {@link RaumplanerAdministrationAsync}
	 */
	public static RaumplanerAdministrationAsync getRaumplanerAdministration() {
		if (raumplanerAdministration == null) {
			raumplanerAdministration = GWT
					.create(RaumplanerAdministration.class);
		}
		return raumplanerAdministration;
	}

}
