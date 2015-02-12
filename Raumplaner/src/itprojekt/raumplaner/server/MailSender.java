package itprojekt.raumplaner.server;

import itprojekt.raumplaner.client.RpcSettings;
import itprojekt.raumplaner.shared.bo.Belegung;
import itprojekt.raumplaner.shared.bo.User;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Klasse, die Mails versendet.
 * 
 * @author Fabian
 *
 */
public class MailSender {

	private static final Properties props = new Properties();
	private static final Session session = Session.getDefaultInstance(props,
			null);

	/**
	 * Mail für die Einladung zu einer Buchung
	 * 
	 * @param user
	 * @param belegung
	 */
	public static void sendInvitationMail(User user, Belegung belegung) {
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("fabianroeber@gmail.com",
					"Raumreservierungssystem"));
			msg.addRecipient(Message.RecipientType.TO,
					new InternetAddress(user.getEmail()));
			msg.setSubject("Einladung zur Veranstaltung");
			msg.setText("Hallo, Sie wurde zur Veranstaltung mit dem Thema: "
					+ belegung.getThema()
					+ " eingeladen! Bitte melden Sie sich am Raumreservierungssystem unter http://lucid-copilot-788.appspot.com an, um die Einladung zu bestätigen.");
			Transport.send(msg);
		} catch (MessagingException | UnsupportedEncodingException e) {
			RpcSettings.getLogger().log(Level.WARNING,
					"E-Mail wurde nicht versendet", e);
			e.printStackTrace();
		}

	}

	/**
	 * Mail für die Ausladung von einer Buchung
	 * 
	 * @param user
	 * @param belegung
	 */
	public static void sendDeclineMail(User user, Belegung belegung) {
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(
					"fabianroeber@gmail.com",
					"Raumreservierungssystem"));
			msg.addRecipient(Message.RecipientType.TO,
					new InternetAddress(user.getEmail()));
			msg.setSubject("Ausladung von einer Veranstaltung");
			msg.setText("Hallo, Sie wurden von der Veranstaltung mit dem Thema: "
					+ belegung.getThema() + " ausgeladen");
			Transport.send(msg);
		} catch (MessagingException | UnsupportedEncodingException e) {
			RpcSettings.getLogger().log(Level.WARNING,
					"E-Mail wurde nicht versendet", e);
			e.printStackTrace();
		}
	}

}
