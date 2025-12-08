package vetro.uz.authentication.screens.profile;

import vetro.uz.authentication.models.UserData;

public interface MainContract {
    
    interface View {
        void showEditProfileDialog(String firstName, String lastName);
        void showMessage(String message);
        void showUserInfo(String firstName, String lastName, String password);
        void showLoguotConfirmDialog();
        void showDeleteConfirmDialog();
        void showSuccessDialog();

        void navigateToLogin();
    }
    
    interface Presenter {
        void onScreenLoaded();
        void onSaveProfileClicked(String firstName, String lastName);
        void onLogoutClicked();
        void onDeleteAccountClicked();
        void confirmLogout();
        void confirmDeleteAccount();

    }
    interface Model{
        String getFirstName();
        String getLastName();
        String getPassword();
        boolean isProfileCompleted();
        void saveProfile(String firstName, String lastName);
        void loguot();
        void deletAcount();
    }
}
