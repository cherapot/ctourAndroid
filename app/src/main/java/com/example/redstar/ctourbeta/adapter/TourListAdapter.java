package com.example.redstar.ctourbeta.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.redstar.ctourbeta.Constant;
import com.example.redstar.ctourbeta.MainActivity;
import com.example.redstar.ctourbeta.R;
import com.example.redstar.ctourbeta.TourActivity;
import com.example.redstar.ctourbeta.dto.TourDTO;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URL;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Created by RedStar on 04.04.2016.
 */
public class TourListAdapter extends RecyclerView.Adapter<TourListAdapter.TourViewHolder> {

    private List<TourDTO> data;

    public TourListAdapter(List<TourDTO> data) {
        this.data = data;
    }

    @Override
    public TourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tour_item, parent, false);
        return new TourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TourViewHolder holder, int position) {
        TourDTO item = data.get(position);
        holder.title.setText(item.getName());
        if(item.getInfo().length() > 255) {
            holder.info.setText(item.getInfo().substring(0, 255) + "...");
        }else{
            holder.info.setText(item.getInfo());
        }
        holder.id.setText(item.getId().toString());
        holder.date.setText("Дата публикации: " + item.getDate());




        URI uri = URI.create(Constant.URL.IMAGE + item.getImage());
        String urlSpec = Constant.URL.IMAGE + item.getImage();

        URL imageUrl;

        try {
            imageUrl = new URL(urlSpec);
            ImageLoader.getInstance().displayImage(urlSpec, holder.image);
        } catch (Exception e) {
            Log.e("<<LOADIMAGE>>", e.getMessage());
        }





    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class TourViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView title;
        TextView info;
        TextView id;
        TextView date;
        ImageView image;



        public TourViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            title = (TextView) itemView.findViewById(R.id.title);
            info = (TextView) itemView.findViewById(R.id.info);
            image = (ImageView) itemView.findViewById(R.id.imageTour);
            id = (TextView) itemView.findViewById(R.id.idTour);
            date = (TextView) itemView.findViewById(R.id.dateTour);

            cardView.setOnClickListener( new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    // item clicked
                    Intent intent = new Intent(v.getContext(), TourActivity.class);
                    intent.putExtra("id", id.getText());
                    v.getContext().startActivity(intent);
                }
            });


        }

        public void bindDrawble(Drawable drawable){
            image.setImageDrawable(drawable);
        }


    }

    public void setData(List<TourDTO> data) {
        this.data = data;
    }



}
