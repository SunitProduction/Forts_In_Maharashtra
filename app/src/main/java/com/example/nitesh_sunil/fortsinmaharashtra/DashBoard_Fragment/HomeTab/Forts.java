package com.example.nitesh_sunil.fortsinmaharashtra.DashBoard_Fragment.HomeTab;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.nitesh_sunil.fortsinmaharashtra.DashBoard_Fragment.HomeTabAdapter.PlaceAdapter;
import com.example.nitesh_sunil.fortsinmaharashtra.ModelClass.Place;
import com.example.nitesh_sunil.fortsinmaharashtra.Place_Package.PlaceActivity;
import com.example.nitesh_sunil.fortsinmaharashtra.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.gson.Gson;


public class Forts extends Fragment  implements PlaceAdapter.OnItemClickListener {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference FortsRef;
    private RecyclerView RV_Forts;
    private  PlaceAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.forts_frag,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RV_Forts= view.findViewById(R.id.RV_Forts);

        FortsRef=db.collection("TouristPlaces/TP/Forts");

        SetRecyclerView();


    }

    private void SetRecyclerView() {
        Query query = FortsRef.orderBy("placeName", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Place> options = new FirestoreRecyclerOptions.Builder<Place>()
                .setQuery(query, Place.class)
                .build();

        adapter= new PlaceAdapter(options);

        RV_Forts.setHasFixedSize(true);

        final RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        RV_Forts.setLayoutManager(manager);
        RV_Forts.setAdapter(adapter);

        //new code added here


        final SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(RV_Forts);

        adapter.setOnItemClickListener(this);



        RV_Forts.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                View view = snapHelper.findSnapView(manager);
                assert view != null;
                int pos = manager.getPosition(view);
                RecyclerView.ViewHolder viewHolder = RV_Forts.findViewHolderForAdapterPosition(pos);
                assert viewHolder != null;



                ConstraintLayout constraintLayout = viewHolder.itemView.findViewById(R.id.layoutConstraint);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    constraintLayout.animate().setDuration(290).scaleX(1).scaleY(1).setInterpolator(new AccelerateInterpolator()).start();
                }else {
                    constraintLayout.animate().setDuration(290).scaleX(0.9f).scaleY(0.9f).setInterpolator(new AccelerateInterpolator()).start();
                }

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }


    @Override
    public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
        Gson gson = new Gson();
        String myJson = gson.toJson(documentSnapshot.toObject(Place.class));
        Intent intent = new Intent(getContext(), PlaceActivity.class);
        intent.putExtra("data", myJson);
        intent.putExtra("ID",documentSnapshot.getId());
        startActivity(intent);
    }
}
