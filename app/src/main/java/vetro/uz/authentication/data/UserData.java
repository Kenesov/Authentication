package vetro.uz.authentication.data;

public class UserData {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private boolean isActive;
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getLogin() { return login; }



    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() { return password; }
    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean getisActive() {
        return isActive;
    }
    public UserData(String firstName, String lastName, String login, String password, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.isActive = isActive;
    }
}
