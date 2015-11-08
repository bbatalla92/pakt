package btech.pakt.fragments;



import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;


import com.melnykov.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.entities.Profile;
import com.sromku.simple.fb.listeners.OnProfileListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import btech.pakt.CustomInventoryListAdapter;
import btech.pakt.FlipAnimation;
import btech.pakt.Item_Description_Class;
import btech.pakt.MainActivity;
import btech.pakt.R;
import btech.pakt.UserData;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile_Fragment extends Fragment {

    GridView myInventory;

    ArrayList<Item_Description_Class> items = new ArrayList<>();
    FragmentManager fm;
    Toolbar toolbar;
    final static String TAG = "PROFILE FRAG";

    // Drop down menu
    FloatingActionButton fab;
    LinearLayout dropDownLayout;

    // Globalize Fragment Layout for view flip
    View v;

    FrameLayout headerContainer;

    //User Info
    UserData user;
    TextView userName;
    TextView userNameCard;
    Picasso p;
    ImageView headerImage;
    ImageView profileImage;
    ImageView profileImageCard;


    SimpleFacebook mSimpleFacebook;

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

        headerContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });



        Profile.Properties properties = new Profile.Properties.Builder()
                .add(Profile.Properties.FIRST_NAME)
                .add(Profile.Properties.COVER)
                .add(Profile.Properties.PICTURE)
                .build();





        OnProfileListener onProfileListener = new OnProfileListener() {
            @Override
            public void onComplete(Profile profile) {

                userName.setText(profile.getFirstName());
                userNameCard.setText(profile.getFirstName());

                p.load(profile.getPicture()).into(profileImage);
                p.load(profile.getPicture()).into(profileImageCard);
                p.load(profile.getCover().toString()).into(headerImage);
            }

        };

        mSimpleFacebook.getProfile(properties, onProfileListener );


        return v;
    }


    public void initialize(View v){
        toolbar = (Toolbar) getActivity().findViewById(R.id.my_awesome_toolbar);
        toolbar.setNavigationIcon(null);
        toolbar.setTitle("Home");

        //User Data
        profileImage = (ImageView) v.findViewById(R.id.profileImage);
        profileImageCard = (ImageView) v.findViewById(R.id.profileImageCard);
        userName = (TextView) v.findViewById(R.id.userName);
        userNameCard = (TextView) v.findViewById(R.id.userNameCard);
        headerImage = (ImageView) v.findViewById(R.id.headerImage);


        myInventory = (GridView) v.findViewById(R.id.myInventoryView);
        myInventory.setAdapter(new CustomInventoryListAdapter(getActivity(), items));




        myInventory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                getActivity().getIntent().putExtra("ITEM", items.get(i));

                fm.beginTransaction().replace(R.id.fragmentContainer, new Item_Profile_Fragment()).addToBackStack("").commit();


            }
        });

        mSimpleFacebook = SimpleFacebook.getInstance();
        p = new Picasso.Builder(getActivity()).build();
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

    //pull image from web
    public Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            Log.i(TAG, e.getLocalizedMessage());
            return null;
        }
    }








}



