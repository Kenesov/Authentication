package vetro.uz.authentication.data;

import android.content.Context;
import android.content.SharedPreferences;

import vetro.uz.authentication.models.UserData;

public class PrefsManager {
    private static final String PREF_NAME = "app_prefs";
    private static final String KEY_LOGIN = "user_login";
    private static final String KEY_PASSWORD = "user_password";
    private static final String KEY_FIRST_NAME = "first_name";
    private static final String KEY_LAST_NAME = "last_name";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";
    private static final String KEY_IS_PROFILE_COMPLETE = "is_profile_complete";

    private SharedPreferences prefs;

    public PrefsManager(Context context){
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveUser(UserData userData){
        boolean profileCom = userData.getFirstName() != null && !userData.getFirstName().isEmpty()
                && userData.getLastName() != null && !userData.getLastName().isEmpty();

        prefs.edit().putString(KEY_LOGIN, userData.getLogin())
                .putString(KEY_PASSWORD, userData.getPassword())
                .putString(KEY_FIRST_NAME, userData.getFirstName())
                .putString(KEY_LAST_NAME, userData.getLastName())
                .putBoolean(KEY_IS_PROFILE_COMPLETE, profileCom)
                .apply();
    }

    public UserData getUser(){
        String login = prefs.getString(KEY_LOGIN, "");
        String password = prefs.getString(KEY_PASSWORD, "");
        String firstName = prefs.getString(KEY_FIRST_NAME, "");
        String lastName = prefs.getString(KEY_LAST_NAME, "");

        return new UserData.UserDataBuilder()
                .setLogin(login)
                .setPassword(password)
                .setFirstName(firstName)
                .setLastName(lastName)
                .build();
    }

    public void setLoggedIn(boolean isLoggedIn){
        prefs.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply();
    }
    public boolean isLoggedIn(){
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void logout(){
        setLoggedIn(false);
    }

    public void deleteAccount(){
        prefs.edit().clear().apply();
    }

    public boolean isUserRegistered(){
        return !prefs.getString(KEY_LOGIN, "").isEmpty() && !prefs.getString(KEY_PASSWORD, "").isEmpty();
    }

    public boolean validateCredentials(String login, String password){
        return login.equals(prefs.getString(KEY_LOGIN, ""))
                && password.equals(prefs.getString(KEY_PASSWORD, ""));
    }

    public boolean isProfileComplete(){
        return prefs.getBoolean(KEY_IS_PROFILE_COMPLETE, false);
    }
}
