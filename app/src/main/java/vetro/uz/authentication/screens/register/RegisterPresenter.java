package vetro.uz.authentication.screens.register;

public class RegisterPresenter implements RegisterContract.Presenter{
    private final RegisterContract.View view;
    private final RegisterContract.Model model;
    private String login = "";
    private String password = "";
    private String repeatPassword = "";

    public RegisterPresenter(RegisterContract.View view){
        this.view = view;
        this.model = new RegisterModel();
    }




    @Override
    public void setLogin(String s) {
        this.login = s;
        view.submitButtonState(login.length() >=5 && password.length() >= 5 &&
                password.equals(repeatPassword));
    }

    @Override
    public void setPassword(String s) {
        this.password = s;
        view.submitButtonState(login.length() >=5 && password.length() >= 5 &&
                password.equals(repeatPassword));
    }

    @Override
    public void setRepeatPassword(String s) {
        this.repeatPassword = s;
        view.submitButtonState(login.length() >=5 && password.length() >= 5 && password.equals(repeatPassword));
    }

    @Override
    public void backLogin() {
        view.navigateToLogin();
    }

    private boolean containsInvalidCharacters(String text) {
        return text.contains("#") || text.contains("&");
    }

    @Override
    public void clickSubmit() {
        if (login.trim().length() < 5) {
            view.showLoginError("Login kamida 5 ta belgidan iborat bo'lishi kerak");
            return;
        }
        if (password.trim().length() < 5) {
            view.showPassword("Parol kamida 5 ta belgidan iborat bo'lishi kerak");
            return;
        }
        if (!password.equals(repeatPassword)) {
            view.showConfirmPasswordError("Parollar bir xil emas");
            return;
        }
        if (containsInvalidCharacters(login) || containsInvalidCharacters(password)) {
            view.showInvalidCharacterError();
            return;
        }
        int i = model.register(login, password);
        if (i == -1) view.alreadyExistLogin();
        else view.navigateToMain(i);
    }
}
