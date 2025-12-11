package vetro.uz.authentication.screens.login;

import vetro.uz.authentication.domain.AppRepository;
import vetro.uz.authentication.domain.AppRepositoryImpl;

public class LoginModel implements LoginContract.Model{
    private AppRepository repository = AppRepositoryImpl.getInstance();
    @Override
    public int checkUser(String login, String password) {
        return repository.checkUser(login,password);
    }

    @Override
    public int getCurrentActiveUserIndex() {
        return repository.getCurrentUserIndex();
    }
}
