package itprojekt.raumplaner.client;

import itprojekt.raumplaner.shared.RaumplanerAdministrationAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Raumplaner implements EntryPoint {

	@Override
	public void onModuleLoad() {

		// Administration holen
		RaumplanerAdministrationAsync raumplanerAdministration = RpcSettings
				.getRaumplanerAdministration();

		// Neue Navigation erstellen
		VerticalPanel navigationPanel = new VerticalPanel();
		// Navigation dem Rootpanel hinzufügen
		RootPanel.get("navigation").add(navigationPanel);

		// erster Menupunkt
		final Button raums = new Button("Räume");
		navigationPanel.add(raums);

		// Clickhandler Räume

		raums.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
			}
		});

		// zweiter Menupunkt

		final Button raumbelegungsplan = new Button("Raumbelegunsplan");
		navigationPanel.add(raumbelegungsplan);
		raumbelegungsplan.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
			}
		});
	}
}
