package com.roshan.traversity.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.roshan.traversity.LocationActivity;
import com.roshan.traversity.Models.Location;
import com.roshan.traversity.Models.Routes;
import com.roshan.traversity.R;
import com.roshan.traversity.RouteActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Recylcerview.adapter
 * Recyclerview.viewholder
 */

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder>{

    private Context mCtx;
    private List<Location> locationList;

    public LocationAdapter(Context mCtx, List<Location> locationsList) {
        this.mCtx = mCtx;
        this.locationList = locationsList;
    }


    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(R.layout.route_thumbnail_layout,null);

        LocationViewHolder holder = new LocationViewHolder(view,mCtx,locationList);

        return holder;

    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, int position) {
        Location location = locationList.get(position);

        holder.textView.setText(location.getTitle());
        Glide.with(mCtx).load(location.getThumbnail()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    //get image


    class LocationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView textView;
        List<Location> locationList = new ArrayList<Location>();
        Context ctx;

        public LocationViewHolder(View itemView,Context ctx,List<Location> locations) {

            super(itemView);
            this.locationList = locations;
            itemView.setOnClickListener(this);
            this.ctx = ctx;
            imageView = itemView.findViewById(R.id.route_image);
            textView =  itemView.findViewById(R.id.route_title);

        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            Location location = this.locationList.get(position);

            Intent intent = new Intent(mCtx, LocationActivity.class);
            intent.putExtra("id",location.getId());
            intent.putExtra("user_id",location.getUser_id());
            intent.putExtra("title",location.getTitle());
            intent.putExtra("category",location.getCategory());
            intent.putExtra("thumbnail",location.getThumbnail());
            intent.putExtra("description",location.getDescription());
            this.ctx.startActivity(intent);
        }
    }

}
