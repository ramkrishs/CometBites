package edu.utdallas.cometbites.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.utdallas.cometbites.R;
import edu.utdallas.cometbites.util.CometbitesAPI;
import edu.utdallas.cometbites.util.Constants;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneVerifyActivity extends AppCompatActivity {
    private static final String TAG = "PhoneVerifyActivity";
    private FirebaseAuth fbAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    EditText codeText;

    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verify);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_sign_up_page);
        toolbar.setTitle("Enter Verification Code");
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        nextButton= (Button)findViewById(R.id.nextButton);
        codeText=(EditText)findViewById(R.id.phone_verification_code) ;


        //First Get Reference
        fbAuth = FirebaseAuth.getInstance();


        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                    Log.d(TAG, "Signed in: " + user.getUid());


                } else {
                    // User is signed out
                    Log.d(TAG, "Currently signed out");
                }
            }
        };




        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify();

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // TODO: add the AuthListener
        fbAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        // TODO: Remove the AuthListener
        if (firebaseAuthListener != null) {
            fbAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }

    public void verify() {
        Log.d(TAG, "Verify");

        if (!validate()) {
            onVerificationFailed();
            return;
        }

        nextButton.setEnabled(false);
        final ProgressDialog progressDialog = new ProgressDialog(PhoneVerifyActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Verifying Phone...");
        progressDialog.show();

        String code = codeText.getText().toString();

        // TODO: Implement your own verification logic here.

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String netid=user.getEmail().substring(0,9);

        CometbitesAPI cometbitesAPI= Constants.getCometbitesAPI();
        Call<ResponseBody> call=cometbitesAPI.verifyPhone(netid,code);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(PhoneVerifyActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onVerificationSuccess or onVerificationFailed
                        // depending on success
                        onVerificationSuccess();
                        //onVerificationFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);

    }

    public void onVerificationSuccess() {
        nextButton.setEnabled(true);
        Intent payment = new Intent(this,AddPaymentActivity.class);
        startActivityForResult(payment, RESULT_OK);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onVerificationFailed() {
        Toast.makeText(getBaseContext(), "Incorrect code", Toast.LENGTH_LONG).show();

        nextButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String otp = codeText.getText().toString();

        if (otp.isEmpty() || otp.length() < 3 || otp.length()>5) {
            codeText.setError("should be 4 digit number");
            valid = false;
        } else {
            codeText.setError(null);
        }

        return valid;
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(PhoneVerifyActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }
}
