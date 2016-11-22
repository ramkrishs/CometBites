package edu.utdallas.cometbites.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.google.firebase.FirebaseApp;

import edu.utdallas.cometbites.R;

public class LandingActivity extends Activity {
    final private int DELAY = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(LandingActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, DELAY );

    }
}
