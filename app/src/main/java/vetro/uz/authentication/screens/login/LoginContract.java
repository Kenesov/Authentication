package vetro.uz.authentication.screens.login;

public interface LoginContract {
    
    interface View {
        void setLoginButtonState(boolean bool);

        void showMessage(String message);

        void navigateToRegister();
        void navigateToMain(int index);
    }
    
    interface Presenter {
        void checkActiveUser();
        void setPassword(String st);
        void setLogin(String st);
        void login();
        void register();
    }
    interface Model{
        int checkUser(String login, String password);
        int getCurrentActiveUserIndex();
    }
}
