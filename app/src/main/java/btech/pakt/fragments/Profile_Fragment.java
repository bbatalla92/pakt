package btech.pakt.fragments;



import android.support.v4.app.FragmentManager;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

import btech.pakt.CustomInventoryListAdapter;
import btech.pakt.FlipAnimation;
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
    Toolbar toolbar;

    // Drop down menu
    FloatingActionButton fab;
    LinearLayout dropDownLayout;

    // Globalize Fragment Layout for view flip
    View v;

    FrameLayout headerContainer;


    public Profile_Fragment() {
        // Required empty public constructor
        items.add(new Item_Description_Class("Item1", "This is item1", 22, 100, R.mipmap.ic_launcher));
        items.add(new Item_Description_Class("Item2", "This is item2", 22, 100, R.mipmap.ic_person_grey600_24dp));
        items.add(new Item_Description_Class("Item3", "This is item3", 22, 100, R.drawable.material_drawer_badge));
        items.add(new Item_Description_Class("Item4", "This is item4", 22, 100, R.drawable.material_drawer_circle_mask));
        items.add(new Item_Description_Class("Item5", "This is item5", 22, 100, R.mipmap.ic_launcher));
        items.add(new Item_Description_Class("Item6", "This is item6", 22, 100, R.mipmap.ic_person_grey600_24dp));
        items.add(new Item_Description_Class("Item7", "This is item7", 22, 100, R.mipmap.ic_launcher));


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_profile, container, false);

        initialize(v);

        fm = getActivity().getSupportFragmentManager();//getActivity().getFragmentManager();

        headerContainer = (FrameLayout) v.findViewById(R.id.headerContainer);

/*        headerContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });*/

        headerContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                flipCard();
                return false;
            }
        });



        return v;
    }

    public void initialize(View v){
        toolbar = (Toolbar) getActivity().findViewById(R.id.my_awesome_toolbar);
        toolbar.setNavigationIcon(null);
        toolbar.setTitle("Home");

        profileImage = (ImageView) v.findViewById(R.id.profileImage);

        myInventory = (GridView) v.findViewById(R.id.myInventoryView);
        myInventory.setAdapter(new CustomInventoryListAdapter(getActivity(), items));




        myInventory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                getActivity().getIntent().putExtra("ITEM", items.get(i));

                fm.beginTransaction().replace(R.id.fragmentContainer, new Item_Profile_Fragment()).addToBackStack("").commit();


            }
        });
    }


        public void flipCard(){
            View rootLayout = v.findViewById(R.id.headerContainer);
            View cardFace = v.findViewById(R.id.main_activity_card_face);
            View cardBack = v.findViewById(R.id.main_activity_card_back);

            FlipAnimation flipAnimation = new FlipAnimation(cardFace, cardBack);

            if (cardFace.getVisibility() == View.GONE)
            {
                flipAnimation.reverse();
            }
            rootLayout.startAnimation(flipAnimation);
        }










}



