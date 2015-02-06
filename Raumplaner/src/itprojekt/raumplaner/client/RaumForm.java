package itprojekt.raumplaner.client;

import itprojekt.raumplaner.shared.RaumplanerAdministration;
import itprojekt.raumplaner.shared.RaumplanerAdministrationAsync;
import itprojekt.raumplaner.shared.bo.Raum;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Hier werden alle verfügbaren Räume zur Auswahl angezeigt.
 * 
 * @author Fabian
 * @author Rathke, Thies
 *
 */
public class RaumForm extends VerticalPanel {

	List<Raum> raums = new ArrayList<>();
	Raum selectedRaum = null;

	Logger logger = RpcSettings.getLogger();

	RaumplanerAdministrationAsync raumplanerAdministration = GWT
			.create(RaumplanerAdministration.class);

	public RaumForm() {

		Button button = new Button("Neuen Raum erstellen");
		this.add(button);
		raumplanerAdministration.getAllRaums(new GetRaumsCallBack());
		Grid raumgrid = new Grid(raums.size(), 2);
		this.add(raumgrid);
		for (int i = 0; i < raums.size(); i++) {
			raumgrid.setText(i, 0, raums.get(i).getBezeichnung());
			raumgrid.setText(i, 1,
					Integer.toString(raums.get(i).getFassungsvermoegen()));
		}
		this.add(raumgrid);
	}

	class GetRaumsCallBack implements AsyncCallback<List<Raum>> {

		@Override
		public void onFailure(Throwable caught) {

			logger.log(Level.WARNING, "Räume konnten nicht geladen werden");
		}

		@Override
		public void onSuccess(List<Raum> result) {
			raums = result;
		}

	}

}
