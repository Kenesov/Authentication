package vetro.uz.authentication.screens.login;

public interface LoginContract {
    
    interface View {
        void showLoginError(String message);
        void showPasswordError(String message);
        void showMessage(String message);
        void navigateToRegister();
        void navigateToMain();
    }
    
    interface Presenter {
        void onLoginClicked(String login, String password);
        void onCreateAccountClicked();
    }
    interface Model{
        boolean validateCredentials(String login, String password);
        boolean isUserRegistered();
        void setLoggedIn(boolean isLoggedIn);
    }
}
