package vetro.uz.authentication.screens.profile;

import vetro.uz.authentication.data.UserData;

public interface MainContract {
    
    interface View {
        void showEditProfileDialog(String firstName, String lastName);
        void showMessage(String message);
        void showUserInfo(String firstName, String lastName, String password);
        void showLogoutConfirmDialog();
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
        boolean isUserActive(int index);
        UserData getCurrentUser(int index);
        void updateUser(int index, String firstName, String lastName);
        void logout(int index);
        void deleteUser(int index);
        int getCurrentUserIndex();
    }
}
