package medic.vaidyaka;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Distresscall extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 71;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distresscall);



       Intent call = new Intent(Intent.ACTION_CALL);

        String number = ("tel:9673953101");
        call = new Intent(Intent.ACTION_CALL);
        call.setData(Uri.parse(number));
// Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(Distresscall.this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);

            // MY_PERMISSIONS_REQUEST_CALL_PHONE is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        } else {
            //You already have permission
            try {
                startActivity(call);
            } catch(SecurityException e) {
                e.printStackTrace();
            }
        }
        startActivity(call);
    }
}
