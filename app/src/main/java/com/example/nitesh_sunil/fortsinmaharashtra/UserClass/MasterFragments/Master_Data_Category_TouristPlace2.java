package com.example.nitesh_sunil.fortsinmaharashtra.UserClass.MasterFragments;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nitesh_sunil.fortsinmaharashtra.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class Master_Data_Category_TouristPlace2 extends Fragment {

    private ListView LL_Season, LL_Tags;
    private ImageButton btn_ShowGallery;
    private static final int REQ_CODE = 02;
    private ArrayList<String> mArrayUri = new ArrayList<String>();
    private TextView imgCount;
    private String Tags,Season,PlaceName,PlaceAddress,PlaceDescription  ;
    private StorageReference mStorage;
    private FirebaseFirestore mFirestore;
    private CollectionReference TouristPlace;
    private String DownloadUri;
    private ProgressBar mProgress;
    private List<String> ListImageUrls;

    private TextView SeasonSelected;
    private TextView TagsSelected;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.master_data_category_touristplace2, container, false);

        Button AddData = view.findViewById(R.id.btn_AddData);
        mProgress =view.findViewById(R.id.Progress_ImageUpload);
        mProgress.setVisibility(View.INVISIBLE);


        ListImageUrls =new ArrayList<>();

        LL_Season = view.findViewById(R.id.LV_Season);
        LL_Tags = view.findViewById(R.id.LV_Tags);
        btn_ShowGallery = view.findViewById(R.id.btn_showGallery);
        imgCount=view.findViewById(R.id.img_count);
        SeasonSelected = view.findViewById(R.id.LV_Season_Selected);
        TagsSelected=view.findViewById(R.id.LV_Tags_Selected);

        Bundle args = this.getArguments();
        if (args != null) {
            PlaceName = args.getString("PlaceName");
            PlaceAddress = args.getString("PlaceAddress");
            PlaceDescription = args.getString("PlaceDescription");

            Toast.makeText(getActivity(), PlaceName + "+" + PlaceAddress + "" + PlaceDescription, Toast.LENGTH_SHORT).show();

        } else {
            Log.d("Err", "Bundle is Null");
        }


        mStorage =FirebaseStorage.getInstance().getReference("ImageData");
        mFirestore=FirebaseFirestore.getInstance();

        TouristPlace =mFirestore.collection("Forts");




        LL_Season.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_multiple_choice, Month));
        LL_Season.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        LL_Tags.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_multiple_choice, TagsArray));
        LL_Tags.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        LL_Tags.setItemChecked(0, true);

        LL_Season.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Season = "";
                int cntChoice = LL_Season.getCount();
                SparseBooleanArray sparseBooleanArray = LL_Season.getCheckedItemPositions();
                for (int i = 0; i < cntChoice; i++) {
                    if (sparseBooleanArray.get(i)) {
                        Season += LL_Season.getItemAtPosition(i).toString() + ",";
                    }
                }
               // Toast.makeText(getContext(), selected, Toast.LENGTH_LONG).show();
                SeasonSelected.setText(Season);
                Log.i("SA",Season);
            }
        });

        LL_Tags.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tags = "";
                int cntChoice = LL_Tags.getCount();
                SparseBooleanArray sparseBooleanArray = LL_Tags.getCheckedItemPositions();
                for (int i = 0; i < cntChoice; i++) {
                    if (sparseBooleanArray.get(i)) {
                        Tags += LL_Tags.getItemAtPosition(i).toString() + ",";
                    }
                }
               // Toast.makeText(getContext(), Tags, Toast.LENGTH_LONG).show();
                TagsSelected.setText(Tags);

            }
        });

        btn_ShowGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowGalleryImages();
            }
        });

        AddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UploadData();
            }
        });





        return view;
    }

    private void ShowGalleryImages() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), REQ_CODE);
    }


    private static String[] Month = new String[]{
            "Jan", "Feb", "March", "Apl", "May", "Jun", "Jul",
            "Aust", "Sep", "Octo", "Nov", "Dec"
    };


    private static String[] TagsArray = new String[]{
            "All", "Fort", "Adventure", "Nature", "Honeymoon", "Friend tour", "Family tour"
    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQ_CODE && resultCode==RESULT_OK) {
            if (data != null) {
                if(data.getClipData() != null){

                    int totalItemsSelected = data.getClipData().getItemCount();

                    for(int i = 0; i < totalItemsSelected; i++){

                        Uri fileUri = data.getClipData().getItemAt(i).getUri();

                        String fileName = getFileName(fileUri);
                        //ArrayList<String> mArrayUri = new ArrayList<String>();
                        mArrayUri.add(fileName);

                        final StorageReference fileToUpload = mStorage.child("Tags").child("Forts").child(PlaceName.toUpperCase()).child(fileName);

                        //final int finalI = i;
                        fileToUpload.putFile(fileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                fileToUpload.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        DownloadUri= uri.toString();
                                        //List<String> ListImageUrls=ListImageUrls =new ArrayList<>();
                                        ListImageUrls.add(DownloadUri);
                                        Log.d("URI",DownloadUri);

                                    }
                                });
                               // UploadData();

                            }

                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), "Something went Wrong", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                                mProgress.setVisibility(View.VISIBLE);
                                double progress=(100.0 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                                mProgress.setProgress((int)progress);

                            }
                        });

                    }

                    //Toast.makeText(MainActivity.this, "Selected Multiple Files", Toast.LENGTH_SHORT).show();

                } else if (data.getData() != null){

                    Toast.makeText(getActivity(), "Selected Single File", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    private void UploadData() {
        Tags_Forts_Model TFM = new Tags_Forts_Model(
                PlaceName,
                PlaceAddress,
                PlaceDescription.trim(),
                SeasonSelected.getText().toString().trim(),
                TagsSelected.getText().toString().trim(),
                ListImageUrls
        );

        //CollectionReference TouristPlace =mFirestore.collection("Forts");
        TouristPlace.add(TFM).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getActivity(),"Data Added to firestore",Toast.LENGTH_SHORT).show();
                mProgress.setProgress(0);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(),""+e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                mProgress.setProgress(0);
            }
        });

    }

    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }




}
