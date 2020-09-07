package com.example.nitesh_sunil.fortsinmaharashtra.DashBoard_Fragment.HomeTabAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nitesh_sunil.fortsinmaharashtra.ModelClass.Place;
import com.example.nitesh_sunil.fortsinmaharashtra.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class PlaceAdapter extends FirestoreRecyclerAdapter <Place, PlaceAdapter.PlaceViewHolder>{
    private final static int FADE_DURATION = 1000;
    private Context context;
    private OnItemClickListener listener;


    public PlaceAdapter(@NonNull FirestoreRecyclerOptions<Place> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PlaceViewHolder placeViewHolder, int i, @NonNull Place place) {
        placeViewHolder.PlaceName.setText(place.getPlaceName());
        placeViewHolder.Address.setText(place.getPlaceAddress());
        ArrayList<String> Urls =place.getImageUrls();
        placeViewHolder.PlaceName.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        placeViewHolder.Address.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation_delay));

            Picasso.get().load(Urls.get(0)).fit().into(placeViewHolder.MainImage);
            Picasso.get().load(Urls.get(1)).fit().into(placeViewHolder.Middle);
            Picasso.get().load(Urls.get(2)).fit().into(placeViewHolder.Bottom);
            Picasso.get().load(Urls.get(3)).fit().into(placeViewHolder.TopLeft);

            placeViewHolder.TopLeft.startAnimation(AnimationUtils.loadAnimation(context,R.anim.down_from_top));
            placeViewHolder.Middle.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation_opp));
            placeViewHolder.Bottom.startAnimation(AnimationUtils.loadAnimation(context,R.anim.up_from_bottom));

            setScaleAnimation(placeViewHolder.MainImage);


    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_layout,parent,false);
        return new PlaceViewHolder(view);
    }

    public class PlaceViewHolder  extends RecyclerView.ViewHolder {
        TextView PlaceName;
        TextView Address;
        ImageView MainImage,TopLeft, Bottom, Middle;
        ConstraintLayout constraintLayout;

        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            PlaceName =itemView.findViewById(R.id.TV_PlaceName);
            MainImage=itemView.findViewById(R.id.IV_MainImage);
            TopLeft=itemView.findViewById(R.id.IV_TopLeft);
            Bottom=itemView.findViewById(R.id.IV_Bottom);
            Middle=itemView.findViewById(R.id.Middle);
            Address=itemView.findViewById(R.id.TV_PlaceAddress);
            constraintLayout=itemView.findViewById(R.id.layoutConstraint);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener!=null){
                        listener.onItemClick(getSnapshots().getSnapshot(position),position);
                    }
                }
            });

        }
    }
    private void setScaleAnimation(ImageView imageView) {
        ScaleAnimation anim = new ScaleAnimation(0.8f, 1.0f, 0.8f, 1.0f, Animation.RELATIVE_TO_SELF, 0.6f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        imageView.startAnimation(anim);
    }

    public interface  OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }




}
