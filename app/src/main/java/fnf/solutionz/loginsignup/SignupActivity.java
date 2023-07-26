package fnf.solutionz.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText etName, etEmail, etPassword, etCPassword;
    Button btnSignup;
    TextView txtLogin;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etCPassword = findViewById(R.id.et_c_password);
        txtLogin = findViewById(R.id.txt_login);
        btnSignup = findViewById(R.id.btn_signup);

        sharedPreferences = getSharedPreferences("pny", MODE_PRIVATE);

        btnSignup.setOnClickListener(v -> {
            if (validation()) {
                signup();
            }
        });

        txtLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private boolean validation() {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String c_password = etCPassword.getText().toString();

        if (TextUtils.isEmpty(name)) {
            etName.setError("Name is Required!!");
            return false;
        } else if (TextUtils.isEmpty(email)) {
            etEmail.setError("Email is Required!!");
            return false;
        } else if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password is Required!!");
            return false;
        } else if (TextUtils.isEmpty(c_password)) {
            etCPassword.setError("Confirm Password is Required!!");
            return false;
        } else if (password.length() < 8) {
            etPassword.setError("Password must be 8 characters long!!");
            return false;
        } else if (!password.equals(c_password)) {
            etCPassword.setError("Password & Confirm Password does not match!!");
            return false;
        } else {
            return true;
        }
    }

    private void signup() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", etName.getText().toString());
        editor.putString("email", etEmail.getText().toString());
        editor.putString("password", etPassword.getText().toString());
        editor.apply();

        Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}