package btech.pakt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Deque;

/**
 * Created by Brennan on 8/27/2015.
 */
public class CustomInventoryListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList items = new ArrayList<>();

    public CustomInventoryListAdapter(Context con, ArrayList array){
        this.context = con;
        this.items = array;

    }

    @Override
    public int getCount() {


        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;

        if (convertView == null) {

            gridView = new View(context);
            gridView = inflater.inflate(R.layout.custom_inventory_layout, null);
            TextView textView = (TextView) gridView.findViewById(R.id.inventoryItemText);
            ImageView image = (ImageView) gridView.findViewById(R.id.inventoryItemImage);
            Item_Description_Class ob = (Item_Description_Class) items.get(i);
            textView.setText(ob.getTitle());


                image.setImageResource((Integer) ob.getImage().get(0));

        }else {
            gridView = (View) convertView;
        }

        return gridView;
    }
}
