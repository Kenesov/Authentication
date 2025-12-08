package vetro.uz.authentication.screens.login;

import vetro.uz.authentication.data.PrefsManager;

public class LoginPresenter implements LoginContract.Presenter {
    private final LoginContract.View view;
    private final LoginContract.Model model;

    public LoginPresenter(LoginContract.View view, LoginContract.Model model){
        this.view = view;
        this.model = model;
    }


    @Override
    public void onLoginClicked(String login, String password) {
        if (login.isEmpty()){
            view.showLoginError("Login yozilmagan");
            return;
        }
        if (password.isEmpty()){
            view.showPasswordError("Password yozilmagan");
            return;
        }
        if (!model.isUserRegistered()){
            view.showMessage("Account topilmadi, Iltimos qayta Registiratsiya qiling");
            return;
        }
        if (model.validateCredentials(login, password)){
            model.setLoggedIn(true);
            view.navigateToMain();
        }else {
            view.showMessage("Xatolik!");
        }
    }

    @Override
    public void onCreateAccountClicked() {
        view.navigateToRegister();
    }
}
