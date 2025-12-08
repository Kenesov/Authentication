package vetro.uz.authentication.models;

public class UserData {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getLogin() { return login; }
    public String getPassword() { return password; }

    private UserData(UserDataBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.login = builder.login;
        this.password = builder.password;
    }

  public static class UserDataBuilder{
      private String firstName;
      private String lastName;
      private String login;
      private String password;

      public UserDataBuilder setFirstName(String firstName) {
          this.firstName = firstName;
          return this;
      }

      public UserDataBuilder setLastName(String lastName) {
          this.lastName = lastName;
          return this;
      }

      public UserDataBuilder setLogin(String login) {
          this.login = login;
          return this;
      }

      public UserDataBuilder setPassword(String password) {
          this.password = password;
          return this;
      }

      public UserData build() {
          return new UserData(this);
      }
  }
}
