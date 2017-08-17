package adapters;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hagermagdy.androidflickerdemo.R;

import java.util.ArrayList;

import models.animal;

/**
 * Created by Hager.Magdy on 8/16/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    View view1;
    ViewHolder viewHolder1;
  ImageView animal_iv;
    private ArrayList<animal> AnmialList;
    public RecyclerViewAdapter(Context context1, ArrayList<animal> AnmialList){

       this.AnmialList = AnmialList;
        context = context1;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{


public ImageView animal_iv;
        public ViewHolder(View v){

            super(v);

            animal_iv = (ImageView) v.findViewById(R.id.img_android);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view1 = LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false);

        viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

     //   holder.animal_iv.setText(AnmialList.get(position).g);
    }

    @Override
    public int getItemCount(){

        return AnmialList.size();
    }
}
