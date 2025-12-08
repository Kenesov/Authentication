package vetro.uz.authentication.screens.login;

import android.content.SharedPreferences;

import vetro.uz.authentication.data.PrefsManager;

public class LoginModel implements LoginContract.Model{
    private final PrefsManager prefs;

    public LoginModel(PrefsManager prefs){
        this.prefs = prefs;
    }
    @Override
    public boolean validateCredentials(String login, String password) {
        return prefs.validateCredentials(login,password);
    }

    @Override
    public boolean isUserRegistered() {
        return prefs.isUserRegistered();
    }

    @Override
    public void setLoggedIn(boolean isLoggedIn) {
        prefs.setLoggedIn(isLoggedIn);
    }
}
