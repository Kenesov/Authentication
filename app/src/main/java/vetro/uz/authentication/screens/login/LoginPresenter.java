package vetro.uz.authentication.screens.login;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private LoginContract.Model model;
    private String password = "";
    private String login = "";

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        this.model = new LoginModel();
    }

    @Override
    public void checkActiveUser() {
        int activeIndex = model.getCurrentActiveUserIndex();
        if (activeIndex != -1) {
            view.navigateToMain(activeIndex);
        }
    }

    @Override
    public void setPassword(String st) {
        this.password = st;
        view.setLoginButtonState(this.password.trim().length() >= 5 && this.login.trim().length() >= 5);
    }

    @Override
    public void setLogin(String st) {
        this.login = st;
        view.setLoginButtonState(this.password.trim().length() >= 5 && this.login.trim().length() >= 5);
    }

    @Override
    public void login() {
        if (login.trim().length() < 5) {
            view.showMessage("Login kamida 5 ta belgidan iborat bo'lishi kerak");
            return;
        }

        if (password.trim().length() < 5) {
            view.showMessage("Parol kamida 5 ta belgidan iborat bo'lishi kerak");
            return;
        }

        int i = model.checkUser(login.trim(), password.trim());
        if (i == -1) {
            view.showMessage("Bu login va parol bo'yicha foydalanuvchi topilmadi");
        } else {
            view.navigateToMain(i);
        }
    }

    @Override
    public void register() {
        view.navigateToRegister();
    }
}
