package btech.pakt.fragments;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;

import btech.pakt.CustomArrayAdapter;
import btech.pakt.Item_Description_Class;
import btech.pakt.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {

    GridView myInventory;
    ImageView profileImage;
    ArrayList<Item_Description_Class> items = new ArrayList<>();
    FragmentManager fm;
    Item_Profile_Fragment itemProfile;



    public Profile() {
        // Required empty public constructor
        items.add(new Item_Description_Class("Item1", "This is item1", 22, 100, R.mipmap.ic_launcher));
        items.add(new Item_Description_Class("Item2", "This is item2", 22, 100, R.mipmap.ic_person_grey600_24dp));
        items.add(new Item_Description_Class("Item3", "This is item3", 22, 100, R.drawable.material_drawer_badge));
        items.add(new Item_Description_Class("Item4", "This is item4", 22, 100, R.drawable.material_drawer_circle_mask));
        items.add(new Item_Description_Class("Item5", "This is item5", 22, 100, R.mipmap.ic_launcher));
        items.add(new Item_Description_Class("Item6", "This is item6", 22, 100, R.mipmap.ic_person_grey600_24dp));
        items.add(new Item_Description_Class("Item7", "This is item7", 22, 100, R.mipmap.ic_launcher));

        //fm = getActivity().getFragmentManager();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        profileImage = (ImageView) v.findViewById(R.id.profileImage);

        myInventory = (GridView) v.findViewById(R.id.myInventoryView);
        myInventory.setAdapter(new CustomArrayAdapter(getActivity(), items));

        myInventory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //@TODO - Create item fragment

                /*Toast.makeText(getActivity(),
                        ((TextView) view.findViewById(R.id.inventoryItemText)).getText(),
                        Toast.LENGTH_LONG)
                        .show();*/
                getActivity().getIntent().putExtra("ITEM", items.get(i));

                fm = getActivity().getFragmentManager();
                 itemProfile = new Item_Profile_Fragment();
                fm.beginTransaction().replace(R.id.fragmentContainer, itemProfile).addToBackStack("").commit();



            }
        });

        return v;
    }


}
