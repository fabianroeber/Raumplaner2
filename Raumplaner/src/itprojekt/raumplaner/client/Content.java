package itprojekt.raumplaner.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class Content extends VerticalPanel {

	public void onload() {
		super.onLoad();
	}

	public HTML createHeadline(String text) {
		HTML content = new HTML(text);

		return content;
	}

	public void append(String text) {
		HTML content = new HTML(text);
		content.setStylePrimaryName("bankproject-simpletext");
		this.add(content);
	}

	public abstract String getHeadlineText();

	public abstract void run();

}
