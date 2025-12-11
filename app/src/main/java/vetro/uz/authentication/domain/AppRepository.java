package vetro.uz.authentication.domain;

import vetro.uz.authentication.data.UserData;

public interface AppRepository {
    int register(String login, String password);
    int checkUser(String login, String password);
    UserData getUserDataByIndex(int index);
    int getCurrentUserIndex();
    void update(int index, String newFirstName, String newLastName);
    void deleteAccount(int index);
    boolean isActive(int index);
    void setActive(int index, boolean bool);
    void logout(int index);

}
