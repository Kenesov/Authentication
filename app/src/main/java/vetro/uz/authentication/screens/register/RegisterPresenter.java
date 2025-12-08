package vetro.uz.authentication.screens.register;

import vetro.uz.authentication.data.PrefsManager;

public class RegisterPresenter implements RegisterContract.Presenter{
    private final RegisterContract.View view;
    private final RegisterContract.Model model;

    public RegisterPresenter(RegisterContract.View view, RegisterContract.Model model){
        this.view = view;
        this.model = model;
    }


    @Override
    public void onSubmitClicked(String login, String password, String confirmPassword) {
        if (login.isEmpty()){
            view.showLoginError("Login kiriting");
            return;
        }
        if (password.isEmpty()){
            view.showPassword("Password kiriting");
            return;
        }
        if (confirmPassword.isEmpty()){
            view.showConfrimPasswordError("Password qayta kiriting");
            return;
        }
        if (!password.equals(confirmPassword)){
            view.showConfrimPasswordError("Password qayta kiriting");
            return;
        }
        if (!model.isUserRegistered()){
            view.showMessage("Avval royhatdan otgan");
            return;
        }
        model.saveUser(login, password);
        model.setLoggedIn(true);

        view.navigateToMain();
    }
}
