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

		VerticalPanel vPanel = new VerticalPanel();
		
		final Button sendButton = new Button("Hallo Alex dein Test funktioniert jetzt");
		vPanel.add(sendButton);
		
		final TextBox nameField = new TextBox();
		vPanel.add(nameField);
		nameField.setText("Textfeld");
		
		final Label errorLabel = new Label();
		vPanel.add(errorLabel);

		RootPanel.get().add(vPanel);
	}
}
