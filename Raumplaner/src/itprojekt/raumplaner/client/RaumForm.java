package itprojekt.raumplaner.client;

import itprojekt.raumplaner.shared.RaumplanerAdministration;
import itprojekt.raumplaner.shared.RaumplanerAdministrationAsync;
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
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 * Hier werden alle verfügbaren Räume zur Auswahl angezeigt.
 * 
 * @author Fabian, Alex
 * @author Rathke, Thies
 *
 */
public class RaumForm extends VerticalPanel {

	Raum selectedRaum = null;
	CellTable<Raum> raumTable = new CellTable<Raum>();
	HorizontalPanel basePanel = new HorizontalPanel();
	VerticalPanel raumPanel = new VerticalPanel();
	VerticalPanel buchungsPanel = new VerticalPanel();
	Button button = new Button("Neuen Raum erstellen");

	Logger logger = RpcSettings.getLogger();

	RaumplanerAdministrationAsync raumplanerAdministration = RpcSettings
			.getRaumplanerAdministration();

	public RaumForm() {
		this.add(basePanel);
		basePanel.add(raumPanel);
		basePanel.add(buchungsPanel);

		raumTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		TextColumn<Raum> bezeichnungColumn = new TextColumn<Raum>() {

			@Override
			public String getValue(Raum object) {
				return object.getBezeichnung();
			}

		};
		raumTable.addColumn(bezeichnungColumn, "Bezeichnung");

		TextColumn<Raum> kapaColumn = new TextColumn<Raum>() {

			@Override
			public String getValue(Raum object) {

				return Integer.toString(object.getFassungsvermoegen());
			}
		};
		raumTable.addColumn(kapaColumn, "Maximale Teilnehmerzahl");

		final SingleSelectionModel<Raum> selectionModel = new SingleSelectionModel<Raum>();
		raumTable.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						selectedRaum = selectionModel.getSelectedObject();
						if (selectedRaum != null) {
							buchungsPanel.clear();
							buchungsPanel.add(new BelegungForm(selectedRaum));
							logger.log(Level.INFO,
									"Raum:" + selectedRaum.getBezeichnung()
											+ "wurde ausgewählt");
						}
					}
				});

		raumplanerAdministration.getAllRaums(new GetRaumsCallBack());
		raumPanel.add(raumTable);
		raumPanel.add(button);
	}

	class GetRaumsCallBack implements AsyncCallback<List<Raum>> {

		@Override
		public void onFailure(Throwable caught) {

			logger.log(Level.WARNING, "Räume konnten nicht geladen werden");
		}

		@Override
		public void onSuccess(List<Raum> result) {
			raumTable.setRowCount(result.size());
			raumTable.setRowData(0, result);
		}

	}

}
