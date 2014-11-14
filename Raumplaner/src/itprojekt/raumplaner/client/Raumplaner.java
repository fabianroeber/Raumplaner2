package itprojekt.raumplaner.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Raumplaner implements EntryPoint {

	@Override
	public void onModuleLoad() {

		final Button sendButton = new Button(
				"Hallo Alex dein Test funktioniert jetzt");
		final TextBox nameField = new TextBox();
		nameField.setText("Textfeld");
		final Label errorLabel = new Label();

		RootPanel.get().add(errorLabel);
		RootPanel.get().add(sendButton);
		RootPanel.get().add(nameField);
	}
}
