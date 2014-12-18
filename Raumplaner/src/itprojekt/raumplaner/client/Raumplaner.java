package itprojekt.raumplaner.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Raumplaner implements EntryPoint {

	@Override
	public void onModuleLoad() {

		RootLayout layout = new RootLayout();

		RootPanel.get().add(layout);
	}
}
