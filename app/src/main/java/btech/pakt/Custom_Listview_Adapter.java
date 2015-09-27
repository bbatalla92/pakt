package btech.pakt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Brennan on 9/9/2015.
 */
public class Custom_Listview_Adapter extends RecyclerView.Adapter<Custom_Listview_Adapter.ItemsViewHolder> {

    List<Item_Description_Class> itemsList;
    Context con;
    int itemOfView;

    public Custom_Listview_Adapter(List<Item_Description_Class> itemsList, Context con, int itemView){
        this.itemsList = itemsList;
        this.con = con;
        this.itemOfView = itemView;
    }


    @Override
    public ItemsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(itemOfView, viewGroup, false);

        return new ItemsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemsViewHolder itemsViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }


    //          Holder Class


    public static class ItemsViewHolder extends RecyclerView.ViewHolder {



        public ItemsViewHolder(View v) {
            super(v);




        }
    }


}
