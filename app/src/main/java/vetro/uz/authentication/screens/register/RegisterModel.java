package vetro.uz.authentication.screens.register;

import vetro.uz.authentication.domain.AppRepository;
import vetro.uz.authentication.domain.AppRepositoryImpl;

public class RegisterModel implements RegisterContract.Model{
    private AppRepository repository = AppRepositoryImpl.getInstance();



    @Override
    public int register(String login, String password) {

        return repository.register(login,password);
    }
}
