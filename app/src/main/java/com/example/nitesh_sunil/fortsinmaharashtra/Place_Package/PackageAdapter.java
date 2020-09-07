package com.example.nitesh_sunil.fortsinmaharashtra.Place_Package;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nitesh_sunil.fortsinmaharashtra.ModelClass.Package;
import com.example.nitesh_sunil.fortsinmaharashtra.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

public class PackageAdapter extends FirestoreRecyclerAdapter <Package , PackageAdapter.PackageViewHolder>{




    public PackageAdapter(@NonNull FirestoreRecyclerOptions<Package> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PackageViewHolder packageViewHolder, int i, @NonNull Package aPackage) {
        packageViewHolder.TV_PackageName.setText(aPackage.getPackageName());
        packageViewHolder.TV_packagePrice.setText(String.valueOf(aPackage.getPackagePrice()));
        Picasso.get().load(aPackage.getImages().get(0)).fit().into(packageViewHolder.packageImage);


    }

    @NonNull
    @Override
    public PackageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.package_single_layout,parent,false);
        return new PackageViewHolder(view);
    }

    public class PackageViewHolder  extends RecyclerView.ViewHolder {
        TextView TV_PackageName,TV_packagePrice,CitiesName;
        TextView TV_Flight,TV_Hotel,TV_Breakfast,TV_Transfer;
        ImageView packageImage;
        Button btn_package;

        public PackageViewHolder(@NonNull View itemView) {
            super(itemView);
            TV_PackageName=itemView.findViewById(R.id.TV_PackageName);
            TV_packagePrice=itemView.findViewById(R.id.TV_packagePrice);
            CitiesName=itemView.findViewById(R.id.CitiesName);
            btn_package=itemView.findViewById(R.id.btn_package);
            packageImage=itemView.findViewById(R.id.packageImage);

            //----------//
            TV_Flight=itemView.findViewById(R.id.TV_Flight);
            TV_Hotel=itemView.findViewById(R.id.TV_Hotel);
            TV_Breakfast =itemView.findViewById(R.id.TV_Breakfast);
            TV_Transfer=itemView.findViewById(R.id.TV_Transfer);





        }
    }


}
