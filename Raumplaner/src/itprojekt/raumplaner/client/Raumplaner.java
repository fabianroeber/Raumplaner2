package itprojekt.raumplaner.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Raumplaner implements EntryPoint {

	@Override
	public void onModuleLoad() {
		
//		neues GUI:
		
		Navigation navigation = new Navigation();
		
		RootPanel.get().add(navigation);

//		Für das alte GUI hier ausklammern:
		
//		LoginPage anmelden = new LoginPage();
//
//		RootPanel.get().add(anmelden);

	}
}
