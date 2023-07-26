package fnf.solutionz.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView txtName, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        txtName = findViewById(R.id.txt_name);
        txtEmail = findViewById(R.id.txt_email);

        sharedPreferences = getSharedPreferences("pny", MODE_PRIVATE);

        txtName.setText("Hello, " + sharedPreferences.getString("name", ""));
        txtEmail.setText(sharedPreferences.getString("email", ""));
    }
}