package vetro.uz.authentication.screens.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import vetro.uz.authentication.R;
import vetro.uz.authentication.screens.login.LoginActivity;

public class MainActivity extends AppCompatActivity implements MainContract.View{
    private MainContract.Presenter presenter;
    private ImageView btnEdit;
    private ImageView btnLogout;
    private TextView txtFirstName;
    private TextView txtLastName;
    private TextView txtPassword;
    private Button btnDelete;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this, getIntent().getIntExtra("INDEX",-1));
        findView();
        setClickListener();
        presenter.onScreenLoaded();
    }

    private void findView() {
        btnEdit = findViewById(R.id.btn_edit);
        btnLogout = findViewById(R.id.btn_logout);
        btnDelete = findViewById(R.id.btn_delete_account);
        txtFirstName = findViewById(R.id.tv_first_name);
        txtLastName = findViewById(R.id.tv_last_name);
        txtPassword = findViewById(R.id.tv_password);
    }
    private void setClickListener() {
        btnEdit.setOnClickListener(view -> {
            showEditProfileDialog(txtFirstName.getText().toString(),
                    txtLastName.getText().toString());
        });
        btnLogout.setOnClickListener(view -> {
            presenter.onLogoutClicked();
        });
        btnDelete.setOnClickListener(view -> {
            presenter.onDeleteAccountClicked();
        });
    }
    @Override
    public void showEditProfileDialog(String firstName, String lastName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_edit_profile, null);
        EditText etFirstName = view.findViewById(R.id.et_first_name);
        EditText etLastName = view.findViewById(R.id.et_last_name);
        Button btnSave = view.findViewById(R.id.btn_save);

        etFirstName.setText(firstName);
        etLastName.setText(lastName);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        btnSave.setOnClickListener(view1 -> {
            String first = etFirstName.getText().toString().trim();
            String last = etLastName.getText().toString().trim();
            presenter.onSaveProfileClicked(first, last);
            dialog.dismiss();
        });
        dialog.show();
    }

    @Override
    public void showLogoutConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_logout, null);
        builder.setView(view);

        AlertDialog dialog = builder.create();

        TextView btnYes = view.findViewById(R.id.btn_yes);
        TextView btnNo = view.findViewById(R.id.btn_no);

        btnYes.setOnClickListener(v -> {
            presenter.confirmLogout();
            dialog.dismiss();
        });

        btnNo.setOnClickListener(v -> {
            dialog.dismiss();
        });

        dialog.show();
    }

    @Override
    public void showDeleteConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_delete_account, null);
        builder.setView(view);

        AlertDialog dialog = builder.create();

        TextView btnYes = view.findViewById(R.id.btn_yes);
        TextView btnNo = view.findViewById(R.id.btn_no);

        btnYes.setOnClickListener(v -> {
            presenter.confirmDeleteAccount();
            dialog.dismiss();
        });

        btnNo.setOnClickListener(v -> {
            dialog.dismiss();
        });

        dialog.show();
    }

    @Override
    public void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_success, null);
        builder.setView(view);

        AlertDialog dialog = builder.create();

        TextView btnOkay = view.findViewById(R.id.btn_okay);
        btnOkay.setOnClickListener(v -> {
            dialog.dismiss();
        });

        dialog.show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showUserInfo(String firstName, String lastName, String password) {
        txtFirstName.setText(firstName);
        txtLastName.setText(lastName);
        txtPassword.setText(password);
    }
    @Override
    public void navigateToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
