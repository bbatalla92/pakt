package btech.pakt;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Brennan on 9/9/2015.
 */
public class ListAdapter_ItemSearch extends RecyclerView.Adapter<ListAdapter_ItemSearch.ItemsViewHolder> {

    List<Item_Description_Class> itemsList;
    Context con;
    int count = 0;

    public ListAdapter_ItemSearch(List<Item_Description_Class> itemsList, Context con){
        this.itemsList = itemsList;
        this.con = con;
    }


    @Override
    public ItemsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.custom_list_item_1, viewGroup, false);

        return new ItemsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ItemsViewHolder view, final int i) {

        final Item_Description_Class object = itemsList.get(i);

        view.Title.setText(object.getTitle());
       view.IconImage.setImageResource((Integer) object.getImage().get(0));





        

        view.IconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                YoYo.with(Techniques.Pulse)
                        .duration(700)
                        .playOn(v);
                imagePopup(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public void imagePopup(int i){

        Item_Description_Class object = itemsList.get(i);
        final ArrayList imageList = object.getImage();
        final int numImages = imageList.size()-1;


        Dialog settingsDialog = new Dialog(con);
        settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View v = LayoutInflater.from(con).inflate(R.layout.imageviewer
                , null);
        settingsDialog.setContentView(v);
        settingsDialog.show();


        ImageButton nxtImage = (ImageButton) settingsDialog.findViewById(R.id.imageRight);
        ImageButton prevImage = (ImageButton) settingsDialog.findViewById(R.id.imageLeft);

        final ImageView image = (ImageView) settingsDialog.findViewById(R.id.iconSearchImage);
        image.setImageResource((Integer) imageList.get(0));


        image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(count > numImages)
                    count = 0;
                image.setImageResource((Integer) imageList.get(count++));
                Log.i("ADAPTER", ""+count);
                return false;
            }
        });

    }

















    //          Holder Class


    public static class ItemsViewHolder extends RecyclerView.ViewHolder {

        ImageView IconImage;
        TextView Title;

        public ItemsViewHolder(View v) {
            super(v);

            IconImage = (ImageView) v.findViewById(R.id.iconImage);
            Title = (TextView) v.findViewById(R.id.title);



        }
    }


}
