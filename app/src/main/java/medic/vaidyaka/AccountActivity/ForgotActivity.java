package medic.vaidyaka.AccountActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import medic.vaidyaka.R;

public class ForgotActivity extends AppCompatActivity {

    private EditText foremail;
    private ProgressDialog progressDialog;
    private FirebaseAuth resauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        progressDialog = new ProgressDialog(this);
        resauth= FirebaseAuth.getInstance();
        foremail = findViewById(R.id.resetEmail);

    }


    public void forgotpass(View view) {
        String email = foremail.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Please wait ...");
        progressDialog.show();

        resauth.sendPasswordResetEmail(email)

                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgotActivity.this,LoginActivity.class));
                            finish();
                        } else {
                            Toast.makeText(ForgotActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }


                    }

                });
    }

    public void goback(View view) {
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }
}
