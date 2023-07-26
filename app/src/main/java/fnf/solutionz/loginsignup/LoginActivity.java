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

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;
    TextView txtSignup;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtSignup = findViewById(R.id.txt_signup);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        sharedPreferences = getSharedPreferences("pny", MODE_PRIVATE);

        btnLogin.setOnClickListener(v -> {
            if (validation()) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLogin", true);
                editor.apply();

                Intent intent = new Intent(this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        txtSignup.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private boolean validation() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        String e = sharedPreferences.getString("email", "");
        String p = sharedPreferences.getString("password", "");

        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Email is Required!!");
            return false;
        } else if (TextUtils.isEmpty(password)) {
            etPassword.setError("Passwird is Required!!");
            return false;
        } else if (!(email.equals(e) && password.equals(p))) {
            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}