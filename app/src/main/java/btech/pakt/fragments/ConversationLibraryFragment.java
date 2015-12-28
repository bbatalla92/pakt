package btech.pakt.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import btech.pakt.FireBaseAPI;
import btech.pakt.ConversationClass;
import btech.pakt.ListAdapter_ConversationLibrary;
import btech.pakt.R;
import btech.pakt.SharedPrefs;
import btech.pakt.UserData;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConversationLibraryFragment extends Fragment {

    //Layout Widgets
    Toolbar toolbar;
    ListView conList;
    TextView noMessagesText;

    //Other
    Firebase baseRef;
    private final String TAG = "Conversation Library";
    SharedPrefs sharedPrefs;
    ArrayList<UserData> userArray;
    ArrayList<ConversationClass> conClasses;
    FragmentManager fm;
    ArrayList<String> conKeys;






    public ConversationLibraryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_conversation_library, container, false);

        initialize(v);
        toolbarInit();
        getCon();
        return v;
    }

    private void initialize(View v){

        conList = (ListView) v.findViewById(R.id.conversationList);
        noMessagesText = (TextView) v.findViewById(R.id.noCon);
        baseRef = new Firebase((new FireBaseAPI()).getBase());
        sharedPrefs = new SharedPrefs(getContext());
        userArray = new ArrayList<>();
        conClasses = new ArrayList<>();
        fm = getActivity().getSupportFragmentManager();
        conKeys = (ArrayList) getActivity().getIntent().getSerializableExtra("ConKeys");

    }

    private void toolbarInit(){
        toolbar = (Toolbar) getActivity().findViewById(R.id.my_awesome_toolbar);
        toolbar.setNavigationIcon(null);
        toolbar.setTitle("Messages");
    }

    public void getCon(){

        for(int i = 0; i < conKeys.size(); i++) {

            baseRef.child("chat").child(conKeys.get(i)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    ConversationClass conversation = dataSnapshot.getValue(ConversationClass.class);



                        conClasses.add(conversation);
                    //}
                    if (!conversation.getSender().matches(sharedPrefs.getAuthuid()))
                        getUser(conversation.getSender());
                    else
                        getUser(conversation.getReciever());







                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

        }


    }

    private void getUser(String userKey){

        baseRef.child("users").child(userKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserData user = dataSnapshot.getValue(UserData.class);
                userArray.add(user);
                Log.i(TAG, "userArray: " + userArray.size());
                listInitialize(userArray);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void listInitialize(ArrayList<UserData> list){

        Log.i(TAG, "list of users size: "+list.size());
        if(list.size() > 0) {
            noMessagesText.setVisibility(View.GONE);
            ListAdapter_ConversationLibrary adapter = new ListAdapter_ConversationLibrary(getContext(), R.layout.conversation_list_layout, list);
            conList.setAdapter(adapter);
        }

        conList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "List item: "+ position);

                getActivity().getIntent().putExtra("UserName", userArray.get(position).getDisplayName());
                getActivity().getIntent().putExtra("UserURL", userArray.get(position).getProfileImageURL());
                Log.i(TAG, userArray.get(position).getDisplayName());
                getActivity().getIntent().putExtra("ConKey", conKeys.get(position));

                fm.beginTransaction().replace(R.id.fragmentContainer, (new MessageFragment())).addToBackStack("toSingleMessages").commit();


            }
        });
    }

}
