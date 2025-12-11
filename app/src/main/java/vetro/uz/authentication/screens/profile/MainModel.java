package vetro.uz.authentication.screens.profile;

import vetro.uz.authentication.data.UserData;
import vetro.uz.authentication.domain.AppRepository;
import vetro.uz.authentication.domain.AppRepositoryImpl;

public class MainModel implements MainContract.Model{
    private  AppRepository repository = AppRepositoryImpl.getInstance();


    @Override
    public boolean isUserActive(int index) {
        return repository.isActive(index);
    }

    @Override
    public UserData getCurrentUser(int index) {
        return repository.getUserDataByIndex(index);
    }

    @Override
    public void updateUser(int index, String firstName, String lastName) {
        repository.update(index, firstName, lastName);
    }

    @Override
    public void logout(int index) {
        repository.logout(index);
    }

    @Override
    public void deleteUser(int index) {
        repository.deleteAccount(index);
    }

    @Override
    public int getCurrentUserIndex() {
        return repository.getCurrentUserIndex();
    }
}
