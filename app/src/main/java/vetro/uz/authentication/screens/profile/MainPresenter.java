package vetro.uz.authentication.screens.profile;

import vetro.uz.authentication.data.UserData;

public class MainPresenter implements MainContract.Presenter{
    private final MainContract.Model model;
    private final MainContract.View view;
    private int index ;

    public MainPresenter( MainContract.View view, int index ){
        this.view = view;
        this.model = new MainModel();
        this.index = index;
    }


    @Override
    public void onScreenLoaded() {
        if (!model.isUserActive(index)){
            view.navigateToLogin();
            return;
        }
        UserData user = model.getCurrentUser(index);

        if (user.getFirstName().isEmpty() || user.getLastName().isEmpty()){
            view.showEditProfileDialog(user.getFirstName(), user.getLastName());
        }else {
            view.showUserInfo(user.getFirstName(), user.getLastName(), user.getPassword());
        }
    }

    @Override
    public void onSaveProfileClicked(String firstName, String lastName) {
        model.updateUser(index, firstName,lastName);
        view.showSuccessDialog();
        view.showUserInfo(firstName, lastName, model.getCurrentUser(index).getPassword());
        view.showMessage("Ma'lumotlar saqlandi!");

    }

    @Override
    public void onLogoutClicked() {
        view.showLogoutConfirmDialog();
    }

    @Override
    public void onDeleteAccountClicked() {
        view.showDeleteConfirmDialog();
    }

    @Override
    public void confirmLogout() {
        model.logout(index);
        view.navigateToLogin();
    }

    @Override
    public void confirmDeleteAccount() {
        model.deleteUser(index);
        view.navigateToLogin();
    }
}
