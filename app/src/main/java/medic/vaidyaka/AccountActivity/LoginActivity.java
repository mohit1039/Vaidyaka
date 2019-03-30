package medic.vaidyaka.AccountActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;

import java.util.Objects;

import medic.vaidyaka.Dashboard.Dashboard;
import medic.vaidyaka.R;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    FirebaseAuth auth;
    String semail,spass;
    ProgressDialog progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseApp.initializeApp(this);
        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.loginemail);
        password = findViewById(R.id.loginpassword);

        progressBar = new ProgressDialog(this);

    }

    public void register(View view) {
        startActivity(new Intent(this,SignUpActivity.class));

    }

    public void login(View view) {
        semail = email.getText().toString();
        spass = password.getText().toString();

        progressBar.setMessage("Please wait..");
        progressBar.show();

        if (!semail.isEmpty() && !spass.isEmpty()){
            try{
                auth.signInWithEmailAndPassword(semail,spass)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information

                                    progressBar.dismiss();
                                    createtoast("Logged in");

                                    startActivity(new Intent(LoginActivity.this, Dashboard.class));
                                    finish();
                                    email.setText("");
                                    password.setText("");


                                } else {
                                    // If sign in fails, display a message to the user.
                                    progressBar.dismiss();
                                    try{

                                        throw Objects.requireNonNull(task.getException());

                                    }
                                    catch (FirebaseAuthInvalidCredentialsException e)
                                    {


                                        createtoast("Authentication Failed");


                                    }
                                    catch (FirebaseNoSignedInUserException e){
                                        createtoast("Please Sign In");
                                    }
                                    catch (Exception e)
                                    {

                                       createtoast(""+e);
                                    }
                                }

                                // ...
                            }
                        });
            }
            catch (Exception e) {

createtoast(""+e);
            }

        }

    }

    private void createtoast(String s) {
        Toast.makeText(this,""+s,Toast.LENGTH_LONG).show();
    }

}
