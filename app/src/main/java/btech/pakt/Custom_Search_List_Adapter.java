package btech.pakt;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;



/**
 * Created by Brennan on 9/9/2015.
 */
public class Custom_Search_List_Adapter extends RecyclerView.Adapter<Custom_Search_List_Adapter.ItemsViewHolder> {

    List<Item_Description_Class> itemsList;
    Context con;

    public Custom_Search_List_Adapter(List<Item_Description_Class> itemsList, Context con){
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
       view.IconImage.setImageResource(object.getImage());





        

        view.IconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                YoYo.with(Techniques.Pulse)
                        .duration(700)
                        .playOn(v);
                imagePopup();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public void imagePopup(){



        Dialog settingsDialog = new Dialog(con);
        settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        settingsDialog.setContentView(LayoutInflater.from(con).inflate(R.layout.imageviewer
                , null));
        settingsDialog.show();


        ImageButton nxtImage = (ImageButton) settingsDialog.findViewById(R.id.imageRight);
        ImageButton prevImage = (ImageButton) settingsDialog.findViewById(R.id.imageLeft);

        nxtImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(con, "Next Image", Toast.LENGTH_LONG).show();
            }
        });

        prevImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(con, "prev Image", Toast.LENGTH_LONG).show();
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
