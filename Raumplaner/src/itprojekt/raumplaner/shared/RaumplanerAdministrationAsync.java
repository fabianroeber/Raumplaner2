package itprojekt.raumplaner.shared;

import itprojekt.raumplaner.shared.bo.Belegung;
import itprojekt.raumplaner.shared.bo.Einladung;
import itprojekt.raumplaner.shared.bo.Raum;
import itprojekt.raumplaner.shared.bo.User;

import java.util.Date;
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

	void deleteRaum(Raum raum, AsyncCallback<Void> callback);

	void updateRaum(Raum raum, AsyncCallback<Void> callback);

	void saveNewBelegung(Belegung belegung, AsyncCallback<Void> callback);

	void updateBelegung(Belegung belegung, AsyncCallback<Void> callback);

	void deleteBelegung(Belegung belegung, AsyncCallback<Void> callback);

	void getEinladungenByBelegung(Belegung belegung,
			AsyncCallback<List<Einladung>> callback);

	void getEinladungenByUser(User user, AsyncCallback<List<Einladung>> callback);

	void isRaumBelegt(Raum raum, Date date, int start, int end,
			AsyncCallback<Boolean> callback);

	void getAllFreeUser(Belegung belegung, int start,
			AsyncCallback<List<User>> callback);

	void getAllUser(AsyncCallback<List<User>> callback);

	void getAllBelegung(AsyncCallback<List<Belegung>> callback);

	void deleteEinladung(Einladung einladung, AsyncCallback<Void> callback);

}
