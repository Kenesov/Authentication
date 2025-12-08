package vetro.uz.authentication.screens.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vetro.uz.authentication.R;
import vetro.uz.authentication.data.PrefsManager;
import vetro.uz.authentication.screens.profile.MainActivity;
import vetro.uz.authentication.screens.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    
    private EditText etLogin;
    private EditText    etPassword;
    private Button btnLogin;
    private TextView tvCreateAccount;

    private LoginContract.Presenter presenter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewFind();
        PrefsManager prefs = new PrefsManager(this);
        presenter = new LoginPresenter(this, new LoginModel(prefs));
        btnLogin.setOnClickListener(view -> {
            String login = etLogin.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            presenter.onLoginClicked(login, password);
        });
        tvCreateAccount.setOnClickListener(view -> {
            presenter.onCreateAccountClicked();
        });

    }

    private void viewFind(){
        etLogin = findViewById(R.id.et_login);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvCreateAccount = findViewById(R.id.tv_create_account);

    }


    @Override
    public void showLoginError(String message) {
        etLogin.setError(message);
    }

    @Override
    public void showPasswordError(String message) {
        etPassword.setError(message);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void navigateToMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
