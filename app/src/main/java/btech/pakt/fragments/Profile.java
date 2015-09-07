package btech.pakt.fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import btech.pakt.CustomArrayAdapter;
import btech.pakt.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {

    GridView myInventory;
    ImageView profileImage;
    ArrayList<String> item = new ArrayList<>();


    static final String[] letters = new String[] {
            "A", "B", "C", "D", "E",
            "F", "G", "H"};

    public Profile() {
        // Required empty public constructor
        Collections.addAll(item, letters);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        profileImage = (ImageView) v.findViewById(R.id.profileImage);

        myInventory = (GridView) v.findViewById(R.id.myInventoryView);
        myInventory.setAdapter(new CustomArrayAdapter(getActivity(), item));

        myInventory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Bitmap yourBitmap = BitmapFactory.decodeFile(String.valueOf(R.mipmap.ic_launcher));
//               // Bitmap resized = Bitmap.createScaledBitmap(yourBitmap,(int)(yourBitmap.getWidth()*0.8), (int)(yourBitmap.getHeight()*0.8), true);
//               // profileImage.setImageBitmap(yourBitmap);
//                profileImage.setImageResource(R.mipmap.ic_launcher);
                Toast.makeText(getActivity(),
                        ((TextView) view.findViewById(R.id.inventoryItemText)).getText(),
                        Toast.LENGTH_LONG)
                        .show();

            }
        });

        return v;
    }


}
