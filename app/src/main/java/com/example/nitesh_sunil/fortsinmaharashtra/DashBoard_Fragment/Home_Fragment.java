package com.example.nitesh_sunil.fortsinmaharashtra.DashBoard_Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.nitesh_sunil.fortsinmaharashtra.DashBoard_Fragment.HomeTab.Adventure;
import com.example.nitesh_sunil.fortsinmaharashtra.DashBoard_Fragment.HomeTab.ForYou_frag;
import com.example.nitesh_sunil.fortsinmaharashtra.DashBoard_Fragment.HomeTab.Forts;
import com.example.nitesh_sunil.fortsinmaharashtra.DashBoard_Fragment.HomeTab.Nature;
import com.example.nitesh_sunil.fortsinmaharashtra.DashBoard_Fragment.HomeTab.Trekking;
import com.example.nitesh_sunil.fortsinmaharashtra.DashBoard_Fragment.HomeTab.ViewPagerAdapter;
import com.example.nitesh_sunil.fortsinmaharashtra.R;
import com.example.nitesh_sunil.fortsinmaharashtra.UserClass.MasterFragments.Tags_Forts_Model;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Home_Fragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private ForYou_frag forYouFrag;
    private Forts forts_frag;
    private Adventure adventure_frag;
    private Nature nature_frag;
    private Trekking trekking_frag;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout=view.findViewById(R.id.TabLayout);
        viewPager=view.findViewById(R.id.ViewPager);

        forYouFrag = new ForYou_frag();
        forts_frag = new Forts();
        adventure_frag= new Adventure();
        nature_frag = new Nature();
        trekking_frag = new Trekking();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(),0);
        adapter.addFragment(forYouFrag,"For You");
        adapter.addFragment(forts_frag,"Forts");
        adapter.addFragment(adventure_frag,"Adventure");
        adapter.addFragment(nature_frag,"Nature");
        adapter.addFragment(trekking_frag, "Trekking");

        viewPager.setAdapter(adapter);

    }
}
