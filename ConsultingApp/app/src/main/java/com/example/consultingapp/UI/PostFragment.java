package com.example.consultingapp.UI;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.consultingapp.Controller.ApiHold;
import com.example.consultingapp.Controller.SessionManager;
import com.example.consultingapp.MainActivity;
import com.example.consultingapp.Model.User;
import com.example.consultingapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.LENGTH_LONG;
import static com.example.consultingapp.SERVER.URLs.ROOT_URL;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ApiHold apiHold;
    private String token;

    private TextView createAnn ;
    private LinearLayout coLay;
    private LinearLayout siLay;
    private LinearLayout abLay;


    public PostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PostFragment newInstance(String param1, String param2) {
        PostFragment fragment = new PostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);




        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_post, container, false);


        Button connect = view.findViewById(R.id.button3);
        Button sinscrire = view.findViewById(R.id.button);
        Button abonne = view.findViewById(R.id.button4);

        abonne.setVisibility(view.INVISIBLE);
         createAnn = (TextView) view.findViewById(R.id.Creer_annonce);
        createAnn.setVisibility(view.INVISIBLE);

        coLay =view.findViewById(R.id.connect_lay);
        siLay =view.findViewById(R.id.sins_lay);
        abLay  =view.findViewById(R.id.abonnez_lay);
        checkInfo();

        sinscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(),SignInActivity.class);
                startActivity(i);
            }
        });
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(),LogInActivity.class);
                startActivity(i);
            }
        });
        abonne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSubscription();

            }
        });





        //si l'utilisateur est déjà connecté on lance Main activity
        if((SessionManager.getInstance(getActivity()).isLoggedIn())){


            connect.setVisibility(view.INVISIBLE);
            coLay.setVisibility(view.GONE);
            sinscrire.setVisibility(view.INVISIBLE);
            siLay.setVisibility(view.GONE);
            if((SessionManager.getInstance(getActivity()).isSubscribed()))
            { abonne.setVisibility(view.INVISIBLE);
                abLay.setVisibility(view.GONE);
                creerAnnonce();}
            else {
                abonne.setVisibility(view.VISIBLE);
                abLay.setVisibility(view.VISIBLE);
            }



        }
        //si non on lance LoginActivity
        else {
            if(SessionManager.getInstance(getActivity()).getToken() != null){
                //mettre à jour token
                token = SessionManager.getInstance(getActivity()).getToken().getToken();
            }
        }



        return view;



    }
    private void creerAnnonce(){
        createAnn.setVisibility(View.VISIBLE);

    }
    private void sendSubscription()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL )
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiHold = retrofit.create(ApiHold.class);
        User utilisateur = new User();
        final User userToSend = new User();
        userToSend.setSubscriber(true);
        Call<User> call = apiHold.updateSubscription(SessionManager.getInstance(getActivity())
                .getUser(utilisateur).getId(),userToSend);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {

                    Toast.makeText(getActivity(),
                            "Code: " +"wrong password or email " , LENGTH_LONG).show();
                    return;
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
    private void checkInfo()
    { Retrofit retrofit1 = new Retrofit.Builder()
            .baseUrl(ROOT_URL )
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        apiHold = retrofit1.create(ApiHold.class);
        User utilisateur1 = new User();
        Call<User> call1 = apiHold.getUserInfo(SessionManager.getInstance(getActivity()).getUser(utilisateur1).getId());
        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {

                    Toast.makeText(getActivity(),
                            "Error " , LENGTH_LONG).show();
                    return;
                }
                SessionManager.getInstance(getActivity()).updateInfo(response.body());


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }


}
