package btech.pakt.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.squareup.picasso.Picasso;

import btech.pakt.Item_Description_Class;
import btech.pakt.R;
import btech.pakt.SharedPrefs;

/**
 * A simple {@link Fragment} subclass.
 */
public class Item_Profile_Fragment extends Fragment{


    TextView itemTitle;
    TextView itemDescription;
    TextView itemPPD;
    TextView itemSD;
    ImageSwitcher itemImages;
    ImageView profileImage;
    Picasso p;
    SharedPrefs sharedPrefs;

    int imageIds[]={R.mipmap.ic_person_grey600_24dp,R.drawable.cityskyline,R.mipmap.ic_launcher, R.drawable.desert};
    int currentImage = 0;

    Item_Description_Class item;

    //navbar
    Toolbar toolbar;

    public Item_Profile_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_item__profile, container, false);

        item = (Item_Description_Class) getActivity().getIntent().getSerializableExtra("ITEM");

        initialize(v);

        itemImages = (ImageSwitcher) v.findViewById(R.id.imageSwitch);
        itemImages.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                // TODO Auto-generated method stub

                // Create a new ImageView set it's properties
                ImageView imageView = new ImageView(getActivity().getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                imageView.setImageResource(imageIds[currentImage]);
                return imageView;
            }
        });
            // Declaring animations
        Animation in = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_out_right);


        itemImages.setInAnimation(in);
        itemImages.setOutAnimation(out);

        itemImages.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int numImages = imageIds.length-1;
                currentImage++;
                if(currentImage > numImages )
                    currentImage =0;

                itemImages.setImageResource(imageIds[currentImage]);
                return false;
            }
        });


        initToolbar();

            return v;
        }

    public void initialize(View v){
        sharedPrefs = new SharedPrefs(getContext());
        itemTitle = (TextView) v.findViewById(R.id.title);
        itemDescription = (TextView) v.findViewById(R.id.itemDescription);
        itemPPD = (TextView) v.findViewById(R.id.itemRate);
        itemSD = (TextView) v.findViewById(R.id.itemSD);
        profileImage = (ImageView) v.findViewById(R.id.profileImage);

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Go to profile", Toast.LENGTH_LONG).show();
            }
        });

        p = new Picasso.Builder(getActivity()).build();;
        p.load(sharedPrefs.getProPic()).into(profileImage);

        itemTitle.setText(item.getTitle());
        itemDescription.setText(item.getDescription());
        itemPPD.setText(item.getRentPrice() + " "+item.getRentRate());
        itemSD.setText("Safety Deposit: $" + item.getDeposit());

    }

    public void initToolbar(){
        toolbar = (Toolbar) getActivity().findViewById(R.id.my_awesome_toolbar);
        toolbar.setTitle("Item Profile");
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("BLAH", "BLAH");
                getActivity().getSupportFragmentManager().popBackStack();

            }
        });
    }

}
