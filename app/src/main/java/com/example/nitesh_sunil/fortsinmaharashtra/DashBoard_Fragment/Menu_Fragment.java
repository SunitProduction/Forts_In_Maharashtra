package com.example.nitesh_sunil.fortsinmaharashtra.DashBoard_Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nitesh_sunil.fortsinmaharashtra.Login_SignUp.Login_SignUp_Activity;
import com.example.nitesh_sunil.fortsinmaharashtra.R;
import com.example.nitesh_sunil.fortsinmaharashtra.WorkWithUs.Selector;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Menu_Fragment extends Fragment implements View.OnClickListener {

    FirebaseUser user;
    LinearLayout LL_workwithus,Profile_Linear,LL_login_signup;
    Button btn_login_signup;
    CircleImageView userprofileImage;
    TextView UserName,UserEmail,UserMobileNumber;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        user=FirebaseAuth.getInstance().getCurrentUser();

        btn_login_signup=view.findViewById(R.id.btn_login_signup_menu);

        LL_workwithus=view.findViewById(R.id.LL_workwithus);
        LL_workwithus.setVisibility(View.INVISIBLE);
        Profile_Linear=view.findViewById(R.id.linearLayout);
        Profile_Linear.setVisibility(View.INVISIBLE);
        LL_login_signup=view.findViewById(R.id.LL_login_signup);
        LL_workwithus.setOnClickListener(this);

        userprofileImage = view.findViewById(R.id.UserImage);
        UserName =view.findViewById(R.id.UserName);
        UserEmail = view.findViewById(R.id.UserEmail);
        UserMobileNumber=view.findViewById(R.id.UserMobileNumber);
        ImageView edit =view.findViewById(R.id.btn_Edit);
        edit.setOnClickListener(this);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Logged Out", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                Profile_Linear.setVisibility(View.INVISIBLE);
                LL_login_signup.setVisibility(View.VISIBLE);

            }
        });

        if (user==null){
            LL_login_signup.setVisibility(View.VISIBLE);



        }else {
            LoadProfile();
            Profile_Linear.setVisibility(View.VISIBLE);
            LL_login_signup.setVisibility(View.INVISIBLE);
            LL_workwithus.setVisibility(View.VISIBLE);
        }

        btn_login_signup.setOnClickListener(this);



        return view;
    }

    private void LoadProfile() {
        String Name = user.getDisplayName();
        Uri imageurl= user.getPhotoUrl();
        String Email = user.getEmail();
        String Number =user.getPhoneNumber();

        Picasso.get().load(imageurl).into(userprofileImage);
        Picasso.get().load(imageurl)
                .placeholder(R.drawable.ic_home_black)
                .error(R.drawable.ic_error)
                // .resizeDimen(R.dimen.list_detail_image_size,R.dimen.list_detail_image_size)
                //.centerInside()
                .into(userprofileImage);
        UserName.setText(Name);
        UserEmail.setText(Email);
        Toast.makeText(getContext(), ""+Number, Toast.LENGTH_SHORT).show();
        UserMobileNumber.setText(Number);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.LL_workwithus:
                startActivity(new Intent(getActivity(), Selector.class));
                break;
            case R.id.btn_Edit:
                Toast.makeText(getContext(), "edit clicked", Toast.LENGTH_SHORT).show();
            case R.id.btn_login_signup_menu:
                //check
                startActivity(new Intent(getActivity(), Login_SignUp_Activity.class));
        }


    }
}
