package edu.utdallas.cometbites.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import edu.utdallas.cometbites.R;
import edu.utdallas.cometbites.util.CometbitesAPI;
import edu.utdallas.cometbites.util.Constants;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";

    private FirebaseAuth fbAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private EditText fnameText;
    private EditText lnameText;
    private EditText emailText;
    private EditText phoneText;
    private EditText passwordText;
    private EditText confirmPasswordText;
    Button nextButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fnameText= (EditText) findViewById(R.id.et_signup_firstnameID);
        lnameText= (EditText) findViewById(R.id.et_signup_lastnameID);
        emailText= (EditText) findViewById(R.id.et_signup_emailID);
        phoneText= (EditText) findViewById(R.id.et_signup_phoneID);
        passwordText=(EditText) findViewById(R.id.et_signup_passwordID);
        confirmPasswordText=(EditText) findViewById(R.id.et_signup_confirmPasswordID);
        nextButton= (Button) findViewById(R.id.next);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_sign_up_page);
        toolbar.setTitle("Enter Comet Credentials");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


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
                signup();

            }
        });

    }

    private void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }


        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        nextButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        // TODO: Implement your own signup logic here.
        fbAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignUpActivity.this, "User created", Toast.LENGTH_SHORT)
                                            .show();
                                    new android.os.Handler().postDelayed(
                                            new Runnable() {
                                                public void run() {
                                                    // On complete call either onSignupSuccess or onSignupFailed
                                                    // depending on success
                                                    onSignupSuccess();
                                                    // onSignupFailed();
                                                    progressDialog.dismiss();
                                                }
                                            }, 3000);
                                } else {
                                    Toast.makeText(SignUpActivity.this, "Account creation failed", Toast.LENGTH_SHORT)
                                            .show();
                                    new android.os.Handler().postDelayed(
                                            new Runnable() {
                                                public void run() {
                                                    // On complete call either onSignupSuccess or onSignupFailed
                                                    // depending on success
                                                    //onSignupSuccess();
                                                    onSignupFailed();
                                                    progressDialog.dismiss();
                                                }
                                            }, 3000);
                                }
                            }
                        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, e.toString());
                        if (e instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(SignUpActivity.this, "This email address is already in use.", Toast.LENGTH_SHORT)
                                    .show();


                        }
                        else {
                            Toast.makeText(SignUpActivity.this,e.getLocalizedMessage(), Toast.LENGTH_SHORT)
                                    .show();

                        }
                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        // On complete call either onSignupSuccess or onSignupFailed
                                        // depending on success
                                        //onSignupSuccess();
                                        onSignupFailed();
                                        progressDialog.dismiss();
                                    }
                                }, 3000);
                    }
                });
    }


    public void onSignupSuccess() {
        nextButton.setEnabled(true);
        //TODO add retrofit code for adding new user

        String first_name = fnameText.getText().toString();
        String last_name = lnameText.getText().toString();
        String emailid = emailText.getText().toString();
        String phone = phoneText.getText().toString();
        String password=passwordText.getText().toString();
        String netid=emailid.substring(0,9);


        saveLoginDetails(emailid,password);
        //Retrofit code
        CometbitesAPI cometbitesAPI = Constants.getCometbitesAPI();
        Call<Integer> customerCall=cometbitesAPI.addCustomer(first_name,last_name,netid,emailid,phone);
        customerCall.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });

//code to send rest for code
        CometbitesAPI cometbitesAPI1=Constants.getCometbitesAPI();
        Call<ResponseBody> call=cometbitesAPI1.sendToken(netid);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Exception: "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


        Intent verify = new Intent(this,PhoneVerifyActivity.class);
        startActivityForResult(verify, RESULT_OK);
        setResult(RESULT_OK, verify);
        finish();
    }

    private void saveLoginDetails(String email, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.LOGIN_PREFS, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("email", email);
        editor.putString("password", password);
        editor.apply();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        nextButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String fname = fnameText.getText().toString();
        String lname = lnameText.getText().toString();
        String email = emailText.getText().toString();
        String mobile = phoneText.getText().toString();
        String password = passwordText.getText().toString();
        String reEnterPassword = confirmPasswordText.getText().toString();

        if (fname.isEmpty() || fname.length() < 3) {
            fnameText.setError("at least 3 characters");
            valid = false;
        } else {
            fnameText.setError(null);
        }

        if (lname.isEmpty() || lname.length() < 3) {
            lnameText.setError("at least 3 characters");
            valid = false;
        } else {
            lnameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("enter a valid email address");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=10) {
            phoneText.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            phoneText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            confirmPasswordText.setError("Password Do not match");
            valid = false;
        } else {
            confirmPasswordText.setError(null);
        }

        return valid;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * When the Activity starts and stops, the app needs to connect and
     * disconnect the AuthListener
     */
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

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
