package btech.pakt.fragments;



import android.app.Activity;

import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import btech.pakt.R;
import btech.pakt.fragments.history_fragments.Rented_History_Fragment;
import btech.pakt.fragments.history_fragments.Rented_Out_History_Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class History_Fragment extends Fragment {
    Toolbar toolbar;
    Button his1;
    Button his2;

    Rented_History_Fragment hisFrag1;

    private FragmentActivity myContext;
    private FragmentTabHost mTabHost;



    public History_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_history, container, false);

        initToolbar();

//        his1 = (Button) v.findViewById(R.id.button);
//        his2 = (Button) v.findViewById(R.id.button3);
          hisFrag1 = new Rented_History_Fragment();

        mTabHost = (FragmentTabHost) v.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("Rented").setIndicator("Rented"), Rented_History_Fragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("Rented Out").setIndicator("Rented Out"),
                Rented_Out_History_Fragment.class, null);



        return v;
    }

    public void initToolbar(){

        toolbar = (Toolbar) getActivity().findViewById(R.id.my_awesome_toolbar);
        toolbar.setTitle("History");
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getFragmentManager().popBackStack();

            }
        });

    }


//    @Override
//    public void onAttach(Activity activity) {
//        myContext=(FragmentActivity) activity;
//        super.onAttach(activity);
//    }
}
