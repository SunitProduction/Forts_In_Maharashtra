package com.example.nitesh_sunil.fortsinmaharashtra.Login_SignUp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.nitesh_sunil.fortsinmaharashtra.DashBoard_Fragment.Menu_Fragment;
import com.example.nitesh_sunil.fortsinmaharashtra.R;
import com.example.nitesh_sunil.fortsinmaharashtra.UserClass.UserData;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.net.URL;

public class SignUp_Fragment extends Fragment implements View.OnClickListener {
    private ImageView closeSignup;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseFirestore db;
    private AlertDialog alertDialog;
    private final static int RC_SIGN_IN = 01;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        closeSignup = view.findViewById(R.id.close_SignUp);
        Button signup = view.findViewById(R.id.btn_signup);
        LinearLayout LL_signup_Google = view.findViewById(R.id.LL_signup_Google);
        LL_signup_Google.setOnClickListener(this);
        signup.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        //close fragment
        closeSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.LL_signup_Google:
                showAlertDialog();
                signin();


                break;


            case R.id.btn_signup:
                Toast.makeText(getContext(), "signup", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void showAlertDialog() {
        alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Please Wait");
        alertDialog.setMessage("Creating User Account !");
        alertDialog.show();
    }

    private void signin() {
        Intent signinclientintent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signinclientintent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            if (requestCode == RC_SIGN_IN) {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                handleSignInResult(task);
            }
        } else {
            Toast.makeText(getContext(), "Account not found", Toast.LENGTH_SHORT).show();
        }


    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount acc = task.getResult(ApiException.class);
            if (acc != null) {
                AddUserData();
                Toast.makeText(getContext(), "Signed in Successful", Toast.LENGTH_SHORT).show();
                FirebaseGoogleAuth(acc);



            } else {
                Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();

            }


        } catch (ApiException e) {
            Toast.makeText(getContext(), "Signup Failed" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(null);
        }
    }

    private void AddUserData() {




    }

    private void FirebaseGoogleAuth(GoogleSignInAccount acc) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(acc.getIdToken(), null);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                   // Toast.makeText(getContext(), "task Successful", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                    //getActivity().finish();
                } else {
                    Toast.makeText(getContext(), "task Failed", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }

    private void updateUI(FirebaseUser user) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (account != null) {

            String username = account.getDisplayName();
            String userfamilyname = account.getFamilyName();
            String id = account.getId();

            String userGivenName = account.getGivenName();
            String useremail = account.getEmail();
            Uri photo = account.getPhotoUrl();
            String imageurl = photo.toString();

            String uid =FirebaseAuth.getInstance().getCurrentUser().getUid();


            String number =null;
            String location = null;
            String post ="USER";


            DocumentReference productCollection = db.collection("UserInfo").document(uid);
            UserData userinfo = new UserData(
                    username,
                    useremail,
                    location,
                    number,
                    imageurl,
                    post);
            productCollection.set(userinfo);

           // AddUserData();

          //  Toast.makeText(getActivity(), username + "\n" + userGivenName + "\n" + userfamilyname + "\n" + useremail + "\n" +
            //        id + "\n" + photo, Toast.LENGTH_SHORT).show();

            alertDialog.dismiss();

            FragmentManager manager = getFragmentManager();
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);

            getActivity().finish();


        }

    }
}


