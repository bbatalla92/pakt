package btech.pakt.fragments;


import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import btech.pakt.FireBaseAPI;
import btech.pakt.ConversationClass;
import btech.pakt.R;
import btech.pakt.SharedPrefs;
import btech.pakt.SingleMessage;
import btech.pakt.UserData;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment implements View.OnClickListener {

    // Layout widgets
    EditText textEdit;
    Button sendButton;
    ListView messageList;

    //Other
    final static private String TAG = "Message Layout Fragment";
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    Firebase ref;
    String name;
    ArrayList<SingleMessage> messages;
    Picasso pic;

    //Delete Variables
    // @TODO: 12/25/2015 -- delete the ex authID
    String exAuth = "facebook:10208332059928582";
    String exName = "Justin";

    // Firebase listAdapter
    private Firebase mFirebaseRef;
    FirebaseListAdapter<SingleMessage> mListAdapter;





    public MessageFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_messages, container, false);

        initialize(v);




        return v;
    }

    private void initialize(View v){
        textEdit = (EditText) v.findViewById(R.id.textMessage);
        sendButton = (Button) v.findViewById(R.id.send_button);
        sendButton.setOnClickListener(this);
        messageList = (ListView) v.findViewById(R.id.messageList);
        name = getActivity().getIntent().getStringExtra("UserName");
        final String imageURL = getActivity().getIntent().getStringExtra("UserURL");
        String conKey = getActivity().getIntent().getStringExtra("ConKey");
        ref = new Firebase((new FireBaseAPI()).getBase()+"chat/"+conKey+"/messages");
        pic = new Picasso.Builder(getContext()).build();

        mListAdapter = new FirebaseListAdapter<SingleMessage>(getActivity()
                , SingleMessage.class,R.layout.custom_messaging_layout,
                ref) {
            @Override
            protected void populateView(View view, SingleMessage model) {
               TextView nameDate = (TextView) view.findViewById(R.id.nameDate);
               TextView singleMessage = (TextView) view.findViewById(R.id.messageCon);
                LinearLayout layout= (LinearLayout) view.findViewById(R.id.messageContainer);
                ImageView otherImage = (ImageView) view.findViewById(R.id.otherPImage);
                ImageView myImage = (ImageView) view.findViewById(R.id.myPImage);
                CardView card = (CardView) view.findViewById(R.id.cardViewMessage);




                if (model.sender.matches((new SharedPrefs(getContext())).getAuthuid())) {
                    nameDate.setText("Me");
                    //nameDate.setGravity(Gravity.RIGHT);
                    layout.setGravity(Gravity.RIGHT);
                    nameDate.setGravity(Gravity.RIGHT);
                    singleMessage.setText(model.getMesage());
                    singleMessage.setTextColor(getResources().getColor(R.color.blue_400));
                    pic.load((new SharedPrefs(getContext())).getProPic()).fit().into(myImage);
                    myImage.setVisibility(View.VISIBLE);
                    otherImage.setVisibility(View.GONE);
                    card.setCardBackgroundColor(getResources().getColor(R.color.grey_200));
                } else {
                    nameDate.setText(name);
                    singleMessage.setText(model.getMesage());
                    singleMessage.setTextColor(getResources().getColor(R.color.green_400));
                    pic.load(imageURL).fit().into(otherImage);

                }
            }
        };
        messageList.setAdapter(mListAdapter);    //setListAdapter();


        //UserData user = (new FireBaseAPI()).getUserData(exAuth);



    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.send_button:
                sendButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Map<String,Object> values = new HashMap<>();
                        values.put("sender", (new SharedPrefs(getActivity())).getAuthuid());
                        values.put("message", textEdit.getText().toString());
                        values.put("mTimeStamp", dateFormat.format(date));

                        ref.push().setValue(values);
                        textEdit.setText("");

                    }
                });
                break;
        }
    }

}
