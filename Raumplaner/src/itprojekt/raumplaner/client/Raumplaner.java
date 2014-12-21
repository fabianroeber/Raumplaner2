package itprojekt.raumplaner.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Raumplaner implements EntryPoint {

	@Override
	public void onModuleLoad() {

		HauptLayout hl = new HauptLayout();
		
		
		RootPanel.get().add(hl);
	}
}
