package com.example.nitesh_sunil.fortsinmaharashtra.Login_SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.nitesh_sunil.fortsinmaharashtra.R;

public class Login_SignUp_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        Toast.makeText(this, "Login Sign up Activity", Toast.LENGTH_SHORT).show();

        //initially calling login fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.Login_Signup_Container,new Login_Fragment()).commit();




    }
}
