package vetro.uz.authentication.screens.profile;

import vetro.uz.authentication.data.PrefsManager;
import vetro.uz.authentication.models.UserData;

public class MainModel implements MainContract.Model{
    private final PrefsManager prefs;

    public MainModel(PrefsManager prefs){
        this.prefs = prefs;
    }
    @Override
    public String getFirstName() {
        return prefs.getUser().getFirstName();
    }

    @Override
    public String getLastName() {
        return prefs.getUser().getLastName();
    }

    @Override
    public String getPassword() {
        return prefs.getUser().getPassword();
    }


    @Override
    public boolean isProfileCompleted() {
        UserData user = prefs.getUser();
        return user.getFirstName() != null && !user.getFirstName().isEmpty()
                && user.getLastName() != null && !user.getLastName().isEmpty();
    }

    @Override
    public void saveProfile(String firstName, String lastName) {
        UserData old = prefs.getUser();
        UserData update = new UserData.UserDataBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setLogin(old.getLogin())
                .setPassword(old.getPassword())
                .build();

        prefs.saveUser(update);
    }

    @Override
    public void loguot() {
        prefs.setLoggedIn(false);
    }

    @Override
    public void deletAcount() {
        prefs.deleteAccount();
    }
}
