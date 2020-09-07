package com.example.nitesh_sunil.fortsinmaharashtra.UserClass.MasterFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.example.nitesh_sunil.fortsinmaharashtra.R;

import java.util.Objects;

public class Data_Category_Selector extends Fragment {
    private Spinner masterSpiner;
    private FragmentManager manager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_category_selector, container, false);


        masterSpiner=view.findViewById(R.id.MasterSpiner);

        assert container != null;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(container.getContext(),
                android.R.layout.simple_spinner_item, getResources()
                .getStringArray(R.array.MasterOption));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        masterSpiner.setAdapter(adapter);
        masterSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                        setFrag( position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

                Toast.makeText(getActivity(), "Please Select Something", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void setFrag(int position) {
        String []option =getResources().getStringArray(R.array.MasterOption).clone();
        manager= Objects.requireNonNull(getActivity()).getSupportFragmentManager();

        switch (position){
            case 6:
                Toast.makeText(getActivity(),option[6], Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(getActivity(),option[5], Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(getActivity(),option[4], Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(getActivity(),option[3], Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getActivity(),option[2], Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Master_Data_Category_TouristPlace selectedFragment= new Master_Data_Category_TouristPlace();
                manager.beginTransaction()
                        .replace(R.id.Master_Frag_Container,selectedFragment,"Master_Data_Category_TouristPlace")
                        .addToBackStack("Data_Category_Selector")
                        .commit();
                //when user press back btn
               // FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
               // manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            case 0:
                break;
               // Toast.makeText(getActivity(),option[0], Toast.LENGTH_SHORT).show();
               // break;
        }

    }
}
