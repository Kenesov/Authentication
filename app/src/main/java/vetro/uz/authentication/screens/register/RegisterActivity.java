package vetro.uz.authentication.screens.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vetro.uz.authentication.R;
import vetro.uz.authentication.data.PrefsManager;
import vetro.uz.authentication.screens.login.LoginActivity;
import vetro.uz.authentication.screens.profile.MainActivity;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View{
    private RegisterContract.Presenter presenter;
    private Button btnSubmit;
    private ImageView btnBack;
    private EditText etLogin;
    private EditText etPassword;
    private EditText etConPassword;
    private TextView txtLogin;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        PrefsManager prefs = new PrefsManager(this);
        presenter = new RegisterPresenter(this, new RegisterModel(prefs));
        findView();
        btnBack.setOnClickListener(view -> {
            navigateToLogin();
        });
        btnSubmit.setOnClickListener(view -> {
            String login = etLogin.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String cPassord = etConPassword.getText().toString().trim();
            presenter.onSubmitClicked(login,password,cPassord);
        });
        txtLogin.setOnClickListener(view -> {
            navigateToLogin();
        });
    }

    private void findView(){
        btnSubmit = findViewById(R.id.btn_submit);
        btnBack = findViewById(R.id.btn_back);
        etLogin = findViewById(R.id.et_login);
        etPassword = findViewById(R.id.et_password);
        etConPassword = findViewById(R.id.et_confirm_password);
        txtLogin = findViewById(R.id.tv_login);
    }

    @Override
    public void showLoginError(String message) {
        etLogin.setError(message);
    }

    @Override
    public void showPassword(String message) {
        etPassword.setError(message);
    }

    @Override
    public void showConfrimPasswordError(String message) {
        etConPassword.setError(message);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToMain() {
        this.startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void navigateToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
