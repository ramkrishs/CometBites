package edu.utdallas.cometbites.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
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

public class AddPaymentActivity extends AppCompatActivity {

    private static final String TAG = "AddPaymentActivity";
    private AutoCompleteTextView cardNameView;
    private AutoCompleteTextView cardNumberView;
    private EditText cvvView;
    private EditText expMonthView;
    private EditText expYearView;
    private Button finishButton;
    private String cardname;
    private String cardno;
    private String cvv;
    private String expMonth;
    private String expYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_sign_up_page);
        toolbar.setTitle("Enter Card Credentials");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        cardNameView = (AutoCompleteTextView) findViewById(R.id.cardname);
        cardNumberView = (AutoCompleteTextView) findViewById(R.id.cardno);
        cvvView = (EditText) findViewById(R.id.cvv);
        expMonthView = (EditText) findViewById(R.id.expMonth);
        expYearView = (EditText) findViewById(R.id.expYear);

        finishButton = (Button) findViewById(R.id.finish);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addPayment();
            }
        });
    }

    public void addPayment() {
        Log.d(TAG, "Add Payment");

        if (!validate()) {
            onAddPaymentFailed();
            return;
        }

        finishButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(AddPaymentActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Adding Payment...");
        progressDialog.show();

        String expDate = expMonth + "/" + expYear;

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();
        String netid = email.substring(0, 9);

        //Retrofit code
        CometbitesAPI cometbitesAPI = Constants.getCometbitesAPI();
        Call<ResponseBody> call = cometbitesAPI.addPaymentMethod(netid, cardname, cardno, cvv, expDate);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                onAddPaymentSuccess();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });


    }

    public void onAddPaymentSuccess() {
        finishButton.setEnabled(true);
        setResult(RESULT_OK, null);
        Toast.makeText(getApplicationContext(), "Card added successfully!!", Toast.LENGTH_SHORT).show();
        Bundle bundle= this.getIntent().getExtras();
        Intent i = new Intent(AddPaymentActivity.this, BrowseFoodJointsActivity.class);;
        startActivity(i);
        finish();
        if(bundle !=null){
          String parent =  bundle.getString("parent");

        }
    }

    public void onAddPaymentFailed() {
        Toast.makeText(getBaseContext(), "Adding card failed", Toast.LENGTH_LONG).show();

        finishButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        cardname = cardNameView.getText().toString();
        cardno = cardNumberView.getText().toString();
        cvv = cvvView.getText().toString();
        expMonth = expMonthView.getText().toString();
        expYear = expYearView.getText().toString();


        if (cardno.isEmpty() || cardno.length() > 19 || cardno.length() < 10) {
            cardNameView.setError("should be between 10 to 19 digit number");
            valid = false;
        } else {
            cardNameView.setError(null);
        }

        if (cvv.isEmpty() || cvv.length() != 3) {
            cvvView.setError("should be 3 characters");
            valid = false;
        } else {
            cvvView.setError(null);
        }

        if (expMonth.isEmpty() || expMonth.length() != 2) {
            expMonthView.setError("should be 2 characters");
            valid = false;
        } else {
            expMonthView.setError(null);
        }

        if (expYear.isEmpty() || expYear.length() != 4) {
            expYearView.setError("should be 4 characters");
            valid = false;
        } else {
            expYearView.setError(null);
        }

        return valid;
    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
