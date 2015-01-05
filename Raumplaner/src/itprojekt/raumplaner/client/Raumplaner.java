package itprojekt.raumplaner.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Raumplaner implements EntryPoint {

	@Override
	public void onModuleLoad() {

		// Hier wird noch bearbeitet. Bitte nichts ändern

		LoginPage anmelden = new LoginPage();

		RootPanel.get().add(anmelden);

		// HauptLayout hl = new HauptLayout();

		// RootPanel.get().add(hl);
	}
}
