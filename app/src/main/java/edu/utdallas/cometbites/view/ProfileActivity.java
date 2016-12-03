package edu.utdallas.cometbites.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.List;

import edu.utdallas.cometbites.R;
import edu.utdallas.cometbites.model.Customer;
import edu.utdallas.cometbites.util.CometbitesAPI;
import edu.utdallas.cometbites.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    private TextView userNameTextView;
    private TextView userNetidTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userNameTextView=(TextView)findViewById(R.id.userNameView);
        userNetidTextView=(TextView)findViewById(R.id.userNetidView);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String netid=user.getEmail().substring(0,9);
        CometbitesAPI cometbitesAPI= Constants.getCometbitesAPI();
        Call<List<Customer>> call=cometbitesAPI.getUserDetails(netid);

        call.enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                Customer customer = response.body().get(0);

                String fname = customer.getFirstname();
                String lname = customer.getLastname();
                String email = customer.getEmailid();
                userNameTextView.setText(fname + " " + lname);
                userNetidTextView.setText(email);
            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Error:" + t, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
