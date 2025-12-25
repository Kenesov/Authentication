package vetro.uz.authentication.screens.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import vetro.uz.authentication.R;
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
        presenter = new RegisterPresenter(this);
        findView();
        btnBack.setOnClickListener(view -> {
            navigateToLogin();
        });
        btnSubmit.setOnClickListener(view -> {
            presenter.clickSubmit();
        });
        txtLogin.setOnClickListener(view -> {
            navigateToLogin();
        });
        etLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                presenter.setLogin(editable.toString());
                String text = editable.toString().trim();
                if (text.length() > 0 && text.length() < 5) {
                    etLogin.setError("Kamida 5 ta belgi kiriting");
                } else if (text.contains("#") || text.contains("&")) {
                    etLogin.setError("# va & belgilarini ishlatish mumkin emas");
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
                String text = editable.toString().trim();
                if (text.length() > 0 && text.length() < 5) {
                    etPassword.setError("Kamida 5 ta belgi kiriting");
                } else if (text.contains("#") || text.contains("&")) {
                    etPassword.setError("# va & belgilarini ishlatish mumkin emas");
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
        etConPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                presenter.setRepeatPassword(editable.toString());
                String text = editable.toString();
                if (text.length() > 0 && !text.equals(etPassword.getText().toString())) {
                    etConPassword.setError("Parollar bir xil emas");
                } else {
                    etConPassword.setError(null);
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
    public void showConfirmPasswordError(String message) {
        etConPassword.setError(message);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void alreadyExistLogin() {
        new AlertDialog.Builder(this)
                .setTitle("Warning")
                .setMessage("Bu login band")
                .setPositiveButton("Ok", (dialog, which) -> dialog.dismiss())
                .show();
    }

    @Override
    public void showInvalidCharacterError() {
        new AlertDialog.Builder(this)
                .setTitle("Xatolik")
                .setMessage("Login va parolda # yoki & belgilarini ishlatish mumkin emas")
                .setPositiveButton("Ok", (dialog, which) -> dialog.dismiss())
                .show();
    }

    @Override
    public void navigateToMain(int index) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("INDEX", index);
        startActivity(i);
        finish();
    }

    @Override
    public void navigateToLogin() {
        finish();
    }

    @Override
    public void submitButtonState(boolean bool) {
        btnSubmit.setEnabled(bool);
    }
}
