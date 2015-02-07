package itprojekt.raumplaner.client;

import itprojekt.raumplaner.shared.RaumplanerAdministration;
import itprojekt.raumplaner.shared.RaumplanerAdministrationAsync;
import itprojekt.raumplaner.shared.bo.Raum;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

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

		HorizontalPanel raumPanel = new HorizontalPanel();
		this.add(raumPanel);
		Button button = new Button("Neuen Raum erstellen");
		raumPanel.add(button);

		raumplanerAdministration.getAllRaums(new GetRaumsCallBack());

		CellTable<Raum> raumtable = new CellTable<Raum>();
		raumtable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		TextColumn<Raum> bezeichnungColumn = new TextColumn<Raum>() {

			@Override
			public String getValue(Raum object) {
				return object.getBezeichnung();
			}

		};
		raumtable.addColumn(bezeichnungColumn, "Bezeichnung");

		TextColumn<Raum> kapaColumn = new TextColumn<Raum>() {

			@Override
			public String getValue(Raum object) {

				return Integer.toString(object.getFassungsvermoegen());
			}
		};
		raumtable.addColumn(kapaColumn, "Maximale Teilnehmerzahl");

		final SingleSelectionModel<Raum> selectionModel = new SingleSelectionModel<Raum>();
		raumtable.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						selectedRaum = selectionModel.getSelectedObject();
						if (selectedRaum != null) {
							Window.alert("selcted:"
									+ selectedRaum.getBezeichnung());
						}
					}
				});
		raumtable.setRowCount(raums.size());
		raumtable.setRowData(0, raums);

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
