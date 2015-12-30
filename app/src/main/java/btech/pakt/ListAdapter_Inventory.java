package btech.pakt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
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
public class ListAdapter_Inventory extends BaseAdapter {

    private Context context;
    private ArrayList items = new ArrayList<>();

    public ListAdapter_Inventory(Context con, ArrayList array){
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


            byte[] im = Base64.decode(ob.getImages().get(0), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(im, 0, im.length);

            if (bitmap != null)
                image.setImageBitmap(bitmap );
            else
                image.setImageResource(R.mipmap.ic_launcher);

        }else {
            gridView = (View) convertView;
        }

        return gridView;
    }
}
