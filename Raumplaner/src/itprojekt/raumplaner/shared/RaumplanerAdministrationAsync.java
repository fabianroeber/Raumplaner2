package itprojekt.raumplaner.shared;

import itprojekt.raumplaner.shared.bo.Belegung;
import itprojekt.raumplaner.shared.bo.Raum;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Automatisch generierte asynchrone Schnittstelle
 * 
 * @author Fabian
 *
 */
public interface RaumplanerAdministrationAsync {

	void getAllRaums(AsyncCallback<List<Raum>> callback);

	void getAllBelegung(AsyncCallback<List<Belegung>> callback);

}
