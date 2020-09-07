package com.example.nitesh_sunil.fortsinmaharashtra.UserClass.MasterFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.nitesh_sunil.fortsinmaharashtra.R;

public class Master_Data_Category_TouristPlace extends Fragment {

    private Button btn_Next_Frag;
    private FragmentManager manager;
    private EditText Master_PlaceName, Master_PlaceAddress,Master_PlaceDescription;
    public String PlaceName, PlaceAddress,PlaceDescription;
    Bundle args;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.master_data_category_touristplace, container, false);

       Master_PlaceName =view.findViewById(R.id.Master_PlaceName);
       Master_PlaceAddress=view.findViewById(R.id.Master_PlaceAddress);
       Master_PlaceDescription=view.findViewById(R.id.Master_PlaceDescription);

        btn_Next_Frag=view.findViewById(R.id.btn_Next_Frag);
        btn_Next_Frag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "+"+Master_PlaceName.getText().toString(), Toast.LENGTH_SHORT).show();
                CallNextFrag();
            }
        });


        return view;
    }

    private void CallNextFrag() {
        PlaceName=Master_PlaceName.getText().toString();
        PlaceAddress=Master_PlaceAddress.getText().toString();
        PlaceDescription=Master_PlaceDescription.getText().toString();


       args = new Bundle();
       args.putString("PlaceName", PlaceName);
       args.putString("PlaceAddress", PlaceAddress);
       args.putString("PlaceDescription", PlaceDescription);
       Master_Data_Category_TouristPlace2 selectedFragment= new Master_Data_Category_TouristPlace2();
       selectedFragment.setArguments(args);
       manager=getActivity().getSupportFragmentManager();
       manager.beginTransaction()
               .replace(R.id.Master_Frag_Container,selectedFragment,"Master_Data_Category_TouristPlace2")
               .addToBackStack("Master_Data_Category_TouristPlace")
               .commit();
    }



}
