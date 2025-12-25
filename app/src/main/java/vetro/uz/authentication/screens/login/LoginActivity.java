package vetro.uz.authentication.screens.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import vetro.uz.authentication.R;
import vetro.uz.authentication.screens.profile.MainActivity;
import vetro.uz.authentication.screens.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    
    private EditText etLogin;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvCreateAccount;

    private LoginContract.Presenter presenter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewFind();
        presenter = new LoginPresenter(this);
        presenter.checkActiveUser();
        btnLogin.setOnClickListener(view -> {
          presenter.login();
        });
        tvCreateAccount.setOnClickListener(view -> {
            presenter.register();
        });
        etLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                presenter.setLogin(editable.toString());
                if (editable.toString().trim().length() > 0 && editable.toString().trim().length() < 5) {
                    etLogin.setError("Kamida 5 ta belgi kiriting");
                } else {
                    etLogin.setError(null);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
        });
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                presenter.setPassword(editable.toString());
                if (editable.toString().trim().length() > 0 && editable.toString().trim().length() < 5) {
                    etPassword.setError("Kamida 5 ta belgi kiriting");
                } else {
                    etPassword.setError(null);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
        });

    }

    private void viewFind(){
        etLogin = findViewById(R.id.et_login);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvCreateAccount = findViewById(R.id.tv_create_account);

    }

    @Override
    public void setLoginButtonState(boolean bool) {
        btnLogin.setEnabled(bool);
    }

    @Override
    public void showMessage(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("Ok", (dialog, which) -> dialog.dismiss())
                .show();
    }

    @Override
    public void navigateToRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void navigateToMain(int index) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("INDEX",index);
        startActivity(intent);
        finish();
    }


}
