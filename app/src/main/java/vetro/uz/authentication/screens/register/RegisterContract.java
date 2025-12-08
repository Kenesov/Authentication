package vetro.uz.authentication.screens.register;

public interface RegisterContract {

    interface View {
      void showLoginError(String message);
      void showPassword(String message);
      void showConfrimPasswordError(String message);
      void showMessage(String message);
      void navigateToMain();
      void navigateToLogin();
    }

    interface Presenter {
        void onSubmitClicked(String login, String password, String confirmPassword);
    }
    interface Model{
        boolean isUserRegistered();
        void saveUser(String login, String password);
        void setLoggedIn(boolean loggedIn);
    }
}
