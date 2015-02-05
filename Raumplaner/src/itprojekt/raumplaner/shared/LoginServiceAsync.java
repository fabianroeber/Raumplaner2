package itprojekt.raumplaner.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {

	void getUserInfo(String uri, AsyncCallback<LoginInfo> callback);

}
