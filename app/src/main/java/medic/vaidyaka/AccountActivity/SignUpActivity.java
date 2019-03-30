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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import java.util.Objects;

import medic.vaidyaka.R;

public class SignUpActivity extends AppCompatActivity {
    FirebaseAuth auth;
    EditText email,password,conpassword;
    String semail,spassword,sconpassword;
    ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        progressBar = new ProgressDialog(this);

        auth = FirebaseAuth.getInstance();



        email = findViewById(R.id.regemail);
        password = findViewById(R.id.regpassword);
        conpassword = findViewById(R.id.regconpassword);

    }

    public void login(View view) {
    }

    public void register(View view) {


        spassword = password.getText().toString();
        sconpassword = conpassword.getText().toString();
        semail = email.getText().toString();
        progressBar.setMessage("Please Wait..");
        progressBar.show();

        if ( !sconpassword.isEmpty() && !semail.isEmpty() && !spassword.isEmpty())
        {
            if (spassword.equals(sconpassword)){
                auth.createUserWithEmailAndPassword(semail, spassword)
                        .addOnCompleteListener(
                                new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (!task.isSuccessful()) {
                                            progressBar.dismiss();
                                            try {
                                                throw Objects.requireNonNull(task.getException());
                                            }
                                            // if user enters wrong email.
                                            catch (FirebaseAuthWeakPasswordException weakPassword) {
                                                createtoast("Weak Password");


                                            }
                                            // if user enters wrong password.
                                            catch (FirebaseAuthInvalidCredentialsException malformedEmail) {
                                                createtoast("Malformed Email");


                                            } catch (FirebaseAuthUserCollisionException existEmail) {
                                                createtoast("Email already exists");


                                            } catch (Exception e) {
                                                createtoast("registeration failed");
                                            }
                                        } else if (task.isSuccessful()) {

                                            progressBar.dismiss();
                                            createtoast("Registration is done successfully, Please verify  your email id");
                                            finish();
                                            startActivity(new Intent(SignUpActivity.this, AddProfileActivity.class));


                                        }
                                    }
                                }
                        );
            }else{
                progressBar.dismiss();
                createtoast("Password did not matched");
            }

        }
        else{
           progressBar.dismiss();
            createtoast("Please enter the complete details");
        }
    }

    private void createtoast(String s) {
        Toast.makeText(this,""+s,Toast.LENGTH_LONG).show();
    }

    public void goback(View view) {
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }
}
