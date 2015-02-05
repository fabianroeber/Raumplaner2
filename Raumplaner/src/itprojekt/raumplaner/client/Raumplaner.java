package itprojekt.raumplaner.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Raumplaner implements EntryPoint {

	@Override
	public void onModuleLoad() {

		Label raumplanerHeader = new Label("Raumreservierungssystem");

		raumplanerHeader.getElement().setAttribute("id", "header");

		RootPanel.get().add(raumplanerHeader);

		Navigation navigation = new Navigation();

		RootPanel.get().add(navigation);

	}
}
