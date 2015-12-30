package btech.pakt.fragments;



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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;


import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.melnykov.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.sromku.simple.fb.SimpleFacebook;

import java.util.ArrayList;

import btech.pakt.ListAdapter_Inventory;
import btech.pakt.FireBaseAPI;
import btech.pakt.FlipAnimation;
import btech.pakt.Item_Description_Class;
import btech.pakt.R;
import btech.pakt.SharedPrefs;
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

    // Globalize Fragment Layout for view flip
    View v;

    FrameLayout headerContainer;

    // layout widgets
    TextView userName;
    TextView userNameCard;
    TextView userBio;
    Picasso p;
    ImageView headerImage;
    ImageView profileImage;
    ImageView profileImageCard;
    ImageButton editCard;

    ArrayList testImages;

    // Shared Preferences
    SharedPrefs sharedPrefs;

    //user Data
    UserData user;
    Firebase userRef;
    String newBio;

    SimpleFacebook mSimpleFacebook;
    FloatingActionButton fab;

    public Profile_Fragment() {
        // Required empty public constructor





    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_profile, container, false);



        initialize(v);
        firebase();







        return v;
    }


    public void initialize(View v){
        sharedPrefs = new SharedPrefs(getActivity());
        toolbar = (Toolbar) getActivity().findViewById(R.id.my_awesome_toolbar);
        toolbar.setNavigationIcon(null);
        toolbar.setTitle("Home");
        fab = (FloatingActionButton) v.findViewById(R.id.addProdButton);

        fm = getActivity().getSupportFragmentManager();

        headerContainer = (FrameLayout) v.findViewById(R.id.headerContainer);

        headerContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });

        //Layout widget initialize
        profileImage = (ImageView) v.findViewById(R.id.profileImage);
        profileImageCard = (ImageView) v.findViewById(R.id.profileImageCard);
        userName = (TextView) v.findViewById(R.id.userName);
        userNameCard = (TextView) v.findViewById(R.id.userNameCard);
        headerImage = (ImageView) v.findViewById(R.id.headerImage);
        userBio = (TextView) v.findViewById(R.id.userBio);
        editCard = (ImageButton) v.findViewById(R.id.editBackCard);

        myInventory = (GridView) v.findViewById(R.id.myInventoryView);
        myInventory.setAdapter(new ListAdapter_Inventory(getActivity(), items));




        myInventory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                getActivity().getIntent().putExtra("ITEM", items.get(i));

                fm.beginTransaction().replace(R.id.fragmentContainer, new Item_Profile_Fragment()).addToBackStack("").commit();


            }
        });


        mSimpleFacebook = SimpleFacebook.getInstance();
        p = new Picasso.Builder(getActivity()).build();







        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.fragmentContainer, new Item_Profile_Edit_Fragment()).addToBackStack("").commit();

            }
        });
        }

    private void profileSetup(){

        userName.setText(sharedPrefs.getFirstName());
        userNameCard.setText(sharedPrefs.getFirstName());
        userBio.setText(user.bio);
        p.load(sharedPrefs.getProPic()).into(profileImage);
        p.load(sharedPrefs.getProPic()).fit().into(profileImageCard);
        p.load(sharedPrefs.getCoverURL()).into(headerImage);

        if(user.getCoverImageURL().equals(sharedPrefs.getCoverURL()))
        editCard.setVisibility(View.VISIBLE);
        editCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backCardDialog();
            }
        });
    }

    public void firebase(){
        Log.i(TAG, "Firebase Setup");
        userRef = new Firebase((new FireBaseAPI()).getUsersURL()+"/"+sharedPrefs.getAuthuid());



        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(UserData.class);

                if(getActivity() != null)
                getActivity().getIntent().putExtra("ConKeys", user.getConversationKeys());

                profileSetup();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.i(TAG, "Firebase Error: " + firebaseError.getMessage());
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

        private void backCardDialog() {
            new MaterialDialog.Builder(getContext())
                    .title("Back of Card Content")
                    .positiveText("Save")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {

                        }
                    })
                    .inputRangeRes(10, 100, R.color.red_500)
                    .input("Tell everyone about yourself!", "", new MaterialDialog.InputCallback() {
                        @Override
                        public void onInput(MaterialDialog dialog, CharSequence input) {
                            Log.i(TAG, "material diolg input");
                            //newBio = input.toString();
                            (new FireBaseAPI()).updateSingleChild(
                                    userRef,
                                    "bio",
                                    input.toString());
                        }
                    }).show();
        }


}



