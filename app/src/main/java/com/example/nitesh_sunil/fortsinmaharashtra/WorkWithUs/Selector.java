package com.example.nitesh_sunil.fortsinmaharashtra.WorkWithUs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.nitesh_sunil.fortsinmaharashtra.R;
import com.example.nitesh_sunil.fortsinmaharashtra.UserClass.MasterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Selector extends AppCompatActivity implements View.OnClickListener {

    private Button btn_company_registration, btn_guider, btn_MasterControl;
    private FirebaseUser muser;
    private String UserUID;
    private String check_me;
    private CollectionReference UserInfoCollection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        muser = FirebaseAuth.getInstance().getCurrentUser();
        UserUID = muser.getUid();
        UserInfoCollection = FirebaseFirestore.getInstance().collection("UserInfo");

        btn_company_registration = findViewById(R.id.btn_register_company);
        btn_guider = findViewById(R.id.btn_register_guide);
        btn_MasterControl = findViewById(R.id.btn_Master);

        btn_company_registration.setOnClickListener(this);
        btn_guider.setOnClickListener(this);
        btn_MasterControl.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register_company:
                Toast.makeText(this, "company registration", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_register_guide:
                Toast.makeText(this, "guider registration", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_Master:
                MasterAuthentication();
                break;
        }
    }

    private void MasterAuthentication() {

        if (UserUID != null) {
            DocumentReference DR = UserInfoCollection.document(UserUID);
            DR.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot doc = task.getResult();
                        if (doc != null) {
                            check_me = doc.getString("post");

                            Validate();

                        } else {
                            Toast.makeText(Selector.this, "empty document", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

        } else {
            Toast.makeText(this, "User Account not found", Toast.LENGTH_SHORT).show();

        }
    }

    private void Validate() {
        if (check_me.equals("MASTER")) {
            startActivity(new Intent(Selector.this, MasterActivity.class));

        } else {
            showDialogbox();

        }

    }

    private void showDialogbox() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                //set icon
                .setIcon(R.drawable.ic_error)
                //set title
                .setTitle("Warning")
                //set message
                .setMessage("You Don't have Access of MasterControl,For more Contact Us):")
                //set positive button
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked
                        finish();
                    }
                }).show();
    }

}
