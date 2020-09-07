package com.example.nitesh_sunil.fortsinmaharashtra.Place_Package;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nitesh_sunil.fortsinmaharashtra.ModelClass.Package;
import com.example.nitesh_sunil.fortsinmaharashtra.ModelClass.Place;
import com.example.nitesh_sunil.fortsinmaharashtra.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class PlaceActivity extends AppCompatActivity {
    private String ID;
    private Toolbar placeToolbar;
    private ImageView test;
    private Place ob ;
    private RecyclerView RV_Package;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference PackageRef;
    private PackageAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        InitializeView();


        Gson gson = new Gson();
        ID= getIntent().getStringExtra("ID");
        ob=gson.fromJson(getIntent().getStringExtra("data"), Place.class);
        SetToolbar();


        //setting image in toolbar
        Picasso.get().load(ob.getImageUrls().get(1)).fit().into(test);

        //     /TouristPlaces/TP/Forts/2RCGkc0sg9XLNYgnUUR5/packages/jgfq5BCHqdNlvW6HvI8M

        //CollectionReference main = db.collection("/TouristPlaces/TP/Forts");
        if(ID!=null){
            PackageRef=db.collection("TouristPlaces/TP/Forts/"+ID+"/packages");
        }

        Query query = PackageRef.orderBy("packageName", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Package> options = new FirestoreRecyclerOptions.Builder<Package>()
                .setQuery(query, Package.class)
                .build();

        adapter=new PackageAdapter(options);
        SetRecyclerView();


    }

    private void SetRecyclerView() {

            RV_Package.setHasFixedSize(true);

            final RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            RV_Package.setLayoutManager(manager);
            RV_Package.setAdapter(adapter);
            




    }

    private void SetToolbar() {
        placeToolbar.setTitle(ob.getPlaceName());
        placeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaceActivity.super.onBackPressed();
            }
        });
    }

    private void InitializeView() {
        placeToolbar=findViewById(R.id.PlaceActivityToolbar);
        test=findViewById(R.id.test);
        RV_Package=findViewById(R.id.RecyclerViewPackages);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();


    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
