package com.example.consultingapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.consultingapp.Controller.ApiHold;
import com.example.consultingapp.Model.User;
import com.example.consultingapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.LENGTH_LONG;
import static com.example.consultingapp.SERVER.URLs.ROOT_URL;

public class SignInActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText nameChamp;
    private EditText emailChamp;
    private EditText passwordChamp;
    private EditText addressChaamp;
    private EditText regionChamps;
    private String statut;
    private EditText telChamp;
    private Button buttonLogin;
    private Spinner spinner;
    private ApiHold apiHold;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //link attributs with view
        nameChamp = findViewById(R.id.editText_name);
        passwordChamp = findViewById(R.id.editText2);
        emailChamp = findViewById(R.id.editText3);
        telChamp = findViewById(R.id.editText4);
        addressChaamp = findViewById(R.id.editText5);
        regionChamps = findViewById(R.id.editText6);
        buttonLogin = findViewById(R.id.button2);
        //profession
        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.profession, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL )
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiHold = retrofit.create(ApiHold.class);
        spinner.setOnItemSelectedListener(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final  String myEmail = emailChamp.getText().toString().trim().toLowerCase();
                final  String myName = nameChamp.getText().toString();
                final  String myPassword = passwordChamp.getText().toString();
                final  String myTel = telChamp.getText().toString();
                final  String myAdress = addressChaamp.getText().toString();
                final  String myRegion = regionChamps.getText().toString();

                String text = myName+myEmail;
                User user= new User(myName,myEmail,myPassword,myRegion,statut, myTel,myAdress,false);


                Call<User> call = apiHold.signin(user);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {


                        Toast.makeText(getApplicationContext(),
                                response.toString(), LENGTH_LONG).show();
                    }


                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),
                                t.getMessage(), LENGTH_LONG).show();
                    }

                });

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         statut = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void verifierChamps()
    {
    }
}
