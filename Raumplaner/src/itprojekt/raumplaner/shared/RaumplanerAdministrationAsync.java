package itprojekt.raumplaner.shared;

import itprojekt.raumplaner.shared.bo.Belegung;
import itprojekt.raumplaner.shared.bo.Raum;
import itprojekt.raumplaner.shared.bo.User;

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

	void getAllBelegungByRaum(Raum raum, AsyncCallback<List<Belegung>> callback);

	void getUserByEmail(String user, AsyncCallback<User> callback);

	void saveNewRaum(Raum raum, AsyncCallback<Void> callback);

}
