package itprojekt.raumplaner.client;

import itprojekt.raumplaner.shared.LoginInfo;
import itprojekt.raumplaner.shared.LoginService;
import itprojekt.raumplaner.shared.LoginServiceAsync;
import itprojekt.raumplaner.shared.RaumplanerAdministrationAsync;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Raumplaner implements EntryPoint {

	private LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label(
			"Bitte melden Sie sich mit ihrem Google Account an, um Zugang zur Applikation zu bekommen");
	private Anchor signInLink = new Anchor("Anmelden");
	private Anchor signOutLink = new Anchor("Abmelden");
	Logger logger;

	@Override
	public void onModuleLoad() {

		// Login wird versucht
		logger = RpcSettings.getLogger();
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.getUserInfo(
				com.google.gwt.core.client.GWT.getHostPageBaseURL(),
				new AsyncCallback<LoginInfo>() {

					@Override
					public void onSuccess(LoginInfo result) {
						loginInfo = result;
						if (loginInfo.isLoggedIn()) {
							logger.log(Level.INFO, "Login erfolgreich");
							loadRaumplaner();
						} else {
							loadLogin();
						}

					}

					@Override
					public void onFailure(Throwable caught) {
						logger.log(Level.WARNING, "Login not available");
					}
				});

	}

	/**
	 * Aufbauen der Anwendung
	 */
	public void loadRaumplaner() {

		// Anzeige des angemeldeten Benutzers
		Label loggedInlabel = new Label("Angemeldet als: "
				+ loginInfo.getEmailAddress());

		// Logout Url holen
		signOutLink.setHref(loginInfo.getLogoutUrl());
		RootPanel.get("logout").add(loggedInlabel);
		RootPanel.get("logout").add(signOutLink);

		RaumplanerAdministrationAsync raumplanerAdministration = RpcSettings
				.getRaumplanerAdministration();

		// Neue Navigation erstellen
		VerticalPanel navigationPanel = new VerticalPanel();
		RootPanel.get("navigation").add(navigationPanel);

		// Menupunkte
		final Button raumbuchungen = new Button("Raumbuchungen");
		navigationPanel.add(raumbuchungen);
		raumbuchungen.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("content").clear();
				RaumForm raumForm = new RaumForm();
				VerticalPanel raumPanel = new VerticalPanel();
				raumPanel.add(raumForm);
				RootPanel.get("content").add(raumPanel);
			}
		});

		final Button raumbelegungsplan = new Button("Raumbelegunsplan");
		navigationPanel.add(raumbelegungsplan);
		raumbelegungsplan.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("content").clear();
			}
		});

		final Button userBelegungen = new Button("Meine Buchungen");
		navigationPanel.add(userBelegungen);
		userBelegungen.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("content").clear();

			}
		});

		final Button userEinladungen = new Button("Meine Einladungen");
		navigationPanel.add(userEinladungen);
		userEinladungen.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("content").clear();

			}
		});

	}

	/**
	 * Diese Methode dient zum Weiterleiten auf den Google Accounts Service
	 */
	public void loadLogin() {
		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		RootPanel.get("content").add(loginPanel);
	}
}
