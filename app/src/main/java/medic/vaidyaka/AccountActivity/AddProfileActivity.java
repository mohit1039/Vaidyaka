package medic.vaidyaka.AccountActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.Objects;

import medic.vaidyaka.Dashboard.Dashboard;
import medic.vaidyaka.Model.Register;
import medic.vaidyaka.R;

public class AddProfileActivity extends AppCompatActivity {

CountryCodePicker ccp,ccp1;
EditText contactno,doccontactno,name,docname;
String personno,docno,Docname,personname;

DatabaseReference fdb;
FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);

        contactno = findViewById(R.id.Contactno);
        doccontactno = findViewById(R.id.doctorcontacno);
        name = findViewById(R.id.Name);
        docname = findViewById(R.id.Doctorname);

        auth = FirebaseAuth.getInstance();


        fdb = FirebaseDatabase.getInstance().getReference("Register");

        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        ccp1 = (CountryCodePicker) findViewById(R.id.docccp);
        ccp.registerCarrierNumberEditText(contactno);
        ccp1.registerCarrierNumberEditText(doccontactno);
    }

    public void submit(View view) {
        personno = ccp.getFullNumberWithPlus();
        docno = ccp1.getFullNumberWithPlus();
        Docname = docname.getText().toString();
        personname = name.getText().toString();
        Register register = new Register(personname,personno,Docname,docno,"200");
        fdb.child(Objects.requireNonNull(auth.getUid())).setValue(register);
        startActivity(new Intent(this, Dashboard.class));
        finish();
    }

    public void goback(View view) {
        startActivity(new Intent(this,Register.class));
        finish();
    }
}
