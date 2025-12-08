package vetro.uz.authentication.screens.profile;

import vetro.uz.authentication.data.PrefsManager;
import vetro.uz.authentication.models.UserData;

public class MainPresenter implements MainContract.Presenter{
    private final MainContract.Model model;
    private final MainContract.View view;

    public MainPresenter(MainContract.Model model, MainContract.View view){
        this.view = view;
        this.model = model;
    }

    @Override
    public void onScreenLoaded() {
        if (!model.isProfileCompleted()){
            view.showEditProfileDialog(model.getFirstName(), model.getLastName());
        }else {
            String firstName = model.getFirstName();
            String lastName = model.getLastName();
            String password = model.getPassword();
            view.showUserInfo(firstName,lastName,password);
        }

    }

    @Override
    public void onSaveProfileClicked(String firstName, String lastName) {
        if (firstName.isEmpty()){
            view.showMessage("Ism kiriting");
            return;
        }
        if (lastName.isEmpty()){
            view.showMessage("Ism kiritin");
            return;
        }
        if (firstName.equals(model.getFirstName()) && lastName.equals(model.getLastName())){
            view.showMessage("Avval kiritilgan");
            return;
        }
        model.saveProfile(firstName,lastName);
        view.showUserInfo(firstName, lastName, model.getPassword());

        view.showSuccessDialog();
    }

    @Override
    public void onLogoutClicked() {
        view.showLoguotConfirmDialog();
    }

    @Override
    public void onDeleteAccountClicked() {
        model.deletAcount();
        view.showDeleteConfirmDialog();
    }


    @Override
    public void confirmLogout() {
        model.loguot();
        view.navigateToLogin();
    }

    @Override
    public void confirmDeleteAccount() {
        model.deletAcount();
        view.navigateToLogin();
    }
}
