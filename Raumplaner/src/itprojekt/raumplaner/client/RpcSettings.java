package itprojekt.raumplaner.client;

import java.util.logging.Logger;

import com.google.gwt.core.shared.GWT;

import itprojekt.raumplaner.shared.LoginService;
import itprojekt.raumplaner.shared.LoginServiceAsync;
import itprojekt.raumplaner.shared.RaumplanerAdministration;
import itprojekt.raumplaner.shared.RaumplanerAdministrationAsync;
import itprojekt.raumplaner.shared.ReportGenerator;
import itprojekt.raumplaner.shared.ReportGeneratorAsync;

/**
 * Klasse mit Eigenschaften und Diensten, die f�r alle Client-seitigen Klassen
 * relevant sind. Hier werden alle RPC-Klassen als Singletons verwaltet.
 * 
 * @author Fabian
 * @author Thies
 *
 */
public class RpcSettings {

	private static RaumplanerAdministrationAsync raumplanerAdministration = null;

	private static LoginServiceAsync loginService = null;

	private static ReportGeneratorAsync reportGenerator;

	private static final Logger log = Logger.getLogger("Raumplaner Logger");

	/**
	 * Gibt den Logger zur�ck
	 * 
	 * @return eindeutige Instanz des Typs {@link Logger}
	 */
	public static Logger getLogger() {
		return log;
	}

	/**
	 * Gibt den LoginService zur�ck.
	 * 
	 * @return eindeutige Instanz vom Typ {@link LoginServiceAsync}
	 */
	public static LoginServiceAsync getLoginService() {
		if (loginService == null) {
			loginService = GWT.create(LoginService.class);
		}
		return loginService;
	}

	/**
	 * Gibt der Reportgenerator zusr�ck.
	 * 
	 * @return eindeutige Instanz vom Typ {@link ReportGeneratorAsync}
	 */
	public static ReportGeneratorAsync getReportGenerator() {
		if (reportGenerator == null) {
			reportGenerator = GWT.create(ReportGenerator.class);
		}
		return reportGenerator;
	}

	/**
	 * Gibt die RaumplanerAdministraion zur�ck und erstellt diese bei Bedarf.
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
