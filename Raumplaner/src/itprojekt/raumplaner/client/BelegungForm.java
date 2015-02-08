package itprojekt.raumplaner.client;

import itprojekt.raumplaner.shared.RaumplanerAdministration;
import itprojekt.raumplaner.shared.RaumplanerAdministrationAsync;
import itprojekt.raumplaner.shared.bo.Belegung;
import itprojekt.raumplaner.shared.bo.Raum;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Fabian
 * @author Alex
 *
 */
public class BelegungForm extends VerticalPanel {

	Raum actualRaum = null;

	HorizontalPanel basePanel = new HorizontalPanel();
	Button button = new Button("Neue Belegung erstellen");

	CellTable<Belegung> belegungTable = new CellTable<Belegung>();
	Logger logger = RpcSettings.getLogger();

	RaumplanerAdministrationAsync raumplanerAdministration = GWT
			.create(RaumplanerAdministration.class);

	public BelegungForm(Raum selectedRaum) {
		actualRaum = selectedRaum;
		this.add(basePanel);

		belegungTable
				.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		TextColumn<Belegung> themaColumn = new TextColumn<Belegung>() {

			@Override
			public String getValue(Belegung object) {
				return object.getThema();
			}

		};
		belegungTable.addColumn(themaColumn, "Thema");

		basePanel.add(belegungTable);
		basePanel.add(button);
	}

	class GetBelegungCallback implements AsyncCallback<List<Belegung>> {

		@Override
		public void onFailure(Throwable caught) {
			logger.log(Level.WARNING,
					"Belegungen konnten nicht geladen werden.");

		}

		@Override
		public void onSuccess(List<Belegung> result) {
			belegungTable.setRowData(result);
			logger.log(Level.INFO, "Buchen für Raum" + actualRaum
					+ "wurden erfolgreich geladen");
		}

	}

}
