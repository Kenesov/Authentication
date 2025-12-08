package vetro.uz.authentication.screens.register;

import vetro.uz.authentication.data.PrefsManager;
import vetro.uz.authentication.models.UserData;

public class RegisterModel implements RegisterContract.Model{
    private final PrefsManager prefs;

    public RegisterModel(PrefsManager prefs){
        this.prefs = prefs;
    }
    @Override
    public boolean isUserRegistered() {
        return prefs.isUserRegistered();
    }

    @Override
    public void saveUser(String login, String password) {
        UserData userData = new UserData.UserDataBuilder().setLogin(login).setPassword(password).build();
        prefs.saveUser(userData);
    }

    @Override
    public void setLoggedIn(boolean loggedIn) {
        prefs.setLoggedIn(loggedIn);
    }
}
