package vetro.uz.authentication.screens.register;

public interface RegisterContract {

    interface View {
      void showLoginError(String message);
      void showPassword(String message);
      void showConfirmPasswordError(String message);
      void showMessage(String message);
      void alreadyExistLogin();
      void showInvalidCharacterError();
      void navigateToMain(int index);
      void navigateToLogin();
      void submitButtonState(boolean bool);
    }

    interface Presenter {
        void setLogin(String s);
        void setPassword(String s);
        void setRepeatPassword(String s);
        void backLogin();
        void clickSubmit();
    }
    interface Model{
        int register(String login, String password);
    }
}
