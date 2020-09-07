
package com.example.nitesh_sunil.fortsinmaharashtra.UserClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;


import com.example.nitesh_sunil.fortsinmaharashtra.R;
import com.example.nitesh_sunil.fortsinmaharashtra.UserClass.MasterFragments.Data_Category_Selector;

public class MasterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_acivity);


        getSupportFragmentManager().beginTransaction().replace(R.id.Master_Frag_Container,new Data_Category_Selector(),"Data_Category_Selector").commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Fragment fragment =  getSupportFragmentManager().findFragmentByTag("Master_Data_Category_TouristPlace");
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, intent);
        }
    }

}
