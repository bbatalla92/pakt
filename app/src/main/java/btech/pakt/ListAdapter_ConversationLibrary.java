package btech.pakt;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Brennan on 12/27/2015.
 */
public class ListAdapter_ConversationLibrary extends ArrayAdapter<UserData>{

    Picasso pic;
    final private String TAG = "ListAdapterCon";


    public ListAdapter_ConversationLibrary(Context context, int textViewResourceId) {
        super(context, textViewResourceId);

        pic = new Picasso.Builder(context).build();

    }

    public ListAdapter_ConversationLibrary(Context context, int resource, List<UserData> items) {
        super(context, resource, items);

        pic = new Picasso.Builder(context).build();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.conversation_list_layout, null);
        }

        UserData p = getItem(position);

        if(p != null){

            ImageView image = (ImageView) v.findViewById(R.id.profileImagee);
            TextView name = (TextView) v.findViewById(R.id.displayName);
            TextView date = (TextView) v.findViewById(R.id.lastMessageDate);


            pic.load(p.getProfileImageURL()).fit().into(image);
            //Log.i(TAG ,p.getProfileImageURL());
            name.setText(p.getDisplayName());
            date.setText("2 days");


        }



        return v;








    }





}
