package btech.pakt.fragments;



import android.os.Bundle;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

import btech.pakt.CustomInventoryListAdapter;
import btech.pakt.Item_Description_Class;
import btech.pakt.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile_Fragment extends Fragment {

    GridView myInventory;
    ImageView profileImage;
    ArrayList<Item_Description_Class> items = new ArrayList<>();
    FragmentManager fm;
    Item_Profile_Fragment itemProfile;
    Toolbar toolbar;

    // Drop down menu
    FloatingActionButton fab;
    LinearLayout dropDownLayout;



    public Profile_Fragment() {
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

        toolbar = (Toolbar) getActivity().findViewById(R.id.my_awesome_toolbar);
        toolbar.setNavigationIcon(null);
        toolbar.setTitle("Home");

        profileImage = (ImageView) v.findViewById(R.id.profileImage);

        myInventory = (GridView) v.findViewById(R.id.myInventoryView);
        myInventory.setAdapter(new CustomInventoryListAdapter(getActivity(), items));

        dropDown(v);


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

    private void dropDown(View v){
        fab = (FloatingActionButton) v.findViewById(R.id.fab);

        dropDownLayout = (LinearLayout) v.findViewById(R.id.dropDownBio);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dropDownLayout.getVisibility() == View.VISIBLE){
                   // YoYo.with(Techniques.FadeOutUp).delay(500).playOn(dropDownLayout);
                    dropDownLayout.setVisibility(View.GONE);


                    fab.setImageResource(R.drawable.ic_play_arrow_black_24dp);

                }else{
                    dropDownLayout.setVisibility(View.VISIBLE);
                   // YoYo.with(Techniques.FadeIn).delay(500).playOn(dropDownLayout);
                    fab.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
                }
            }
        });


    }


}
