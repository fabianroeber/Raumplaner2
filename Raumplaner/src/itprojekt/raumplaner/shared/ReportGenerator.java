package itprojekt.raumplaner.shared;

import itprojekt.raumplaner.server.report.ReportGeneratorImpl;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Synchrone Schnittstelle, die alle notwendigen Methoden f&uuml;r die Klasse
 * {@link ReportGeneratorImpl} bereitstellt.
 * 
 * @author Fabian
 *
 */
@RemoteServiceRelativePath("reportGenerator")
public interface ReportGenerator extends RemoteService {

}
