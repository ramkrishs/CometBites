package edu.utdallas.cometbites.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.google.firebase.FirebaseApp;

import edu.utdallas.cometbites.R;

public class LandingActivity extends Activity {
    final private int DELAY = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        final ProgressBar pb = (ProgressBar) findViewById(R.id.landingLoad);
        pb.setVisibility(ProgressBar.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pb.setVisibility(ProgressBar.INVISIBLE);
                Intent intent=new Intent(LandingActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, DELAY );

    }
}
