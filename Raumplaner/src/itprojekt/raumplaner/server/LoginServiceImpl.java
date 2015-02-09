package itprojekt.raumplaner.server;

import java.sql.SQLException;

import itprojekt.raumplaner.server.db.UserMapper;
import itprojekt.raumplaner.shared.LoginInfo;
import itprojekt.raumplaner.shared.LoginService;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Meldet den User über den Google Accounts Service an.
 * 
 * @author Fabian
 */
public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {

	private static final long serialVersionUID = -1682217411238580183L;

	@Override
	public LoginInfo getUserInfo(String uri) {
		// Nutzer von Google holen
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		LoginInfo loginInfo = new LoginInfo();

		if (user != null) {

			UserMapper userMapper = UserMapper.getUserMapper();

			loginInfo.setLoggedIn(true);
			loginInfo.setEmailAddress(user.getEmail());
			loginInfo.setLogoutUrl(userService.createLogoutURL(uri));

			// In der Datenbank nachsehen, ob User schon registiert ist.
			itprojekt.raumplaner.shared.bo.User raumplanerUser = (userMapper
					.getUserByEmail(loginInfo.getEmailAddress()));
			if (raumplanerUser != null) {
				loginInfo.setUser(raumplanerUser);
			}
			// User 'registrieren'
			else {
				raumplanerUser = new itprojekt.raumplaner.shared.bo.User();
				raumplanerUser.setEmail(loginInfo.getEmailAddress());
				userMapper.insert(raumplanerUser);
			}

		} else {
			loginInfo.setLoggedIn(false);
			loginInfo.setLoginUrl(userService.createLoginURL(uri));
		}
		return loginInfo;

	}
}
