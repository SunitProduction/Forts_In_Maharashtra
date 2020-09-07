package com.example.nitesh_sunil.fortsinmaharashtra.Login_SignUp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nitesh_sunil.fortsinmaharashtra.R;
import com.google.android.material.textfield.TextInputLayout;


public class Login_Fragment extends Fragment {
    private Button btn_Login;
    private TextInputLayout inputEmail;
    private TextInputLayout inputPassword;
    private String Email;
    private String Password;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ImageView closeLogin= view.findViewById(R.id.Close_Login);
        closeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        inputEmail=view.findViewById(R.id.inputText_Email);
        inputPassword=view.findViewById(R.id.inputText_password);

        TextView textView_signup =view.findViewById(R.id.TextView_SignUp);
        textView_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp_Fragment sf = new SignUp_Fragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Login_Signup_Container,sf)
                        .addToBackStack("SingUp")
                        .commit();
            }
        });


        btn_Login=view.findViewById(R.id.btn_login);
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Email=inputEmail.getEditText().getText().toString();
                Password=inputPassword.getEditText().getText().toString();
                if (Email.isEmpty()&&Password.isEmpty()){
                    Toast.makeText(getContext(), "Please Enter Email and Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), Email+"\n"+Password, Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }
}
