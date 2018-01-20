package com.roshan.traversity.RecyclerView;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.roshan.traversity.Models.Routes;
import com.roshan.traversity.R;


import java.util.List;

/**
 * Recylcerview.adapter
 * Recyclerview.viewholder
 */

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.RouteViewHolder>{

    private Context mCtx;
    private List<Routes> routesList;

    public RouteAdapter(Context mCtx, List<Routes> routesList) {
        this.mCtx = mCtx;
        this.routesList = routesList;
    }


    @Override
    public RouteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(R.layout.route_thumbnail_layout,null);

        RouteViewHolder holder = new RouteViewHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(RouteViewHolder holder, int position) {
        Routes routes = routesList.get(position);

        holder.textView.setText(routes.getTitle());
        Glide.with(mCtx).load(routes.getThumbnail()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return routesList.size();
    }

    //get image


    class RouteViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public RouteViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.route_image);
            textView =  itemView.findViewById(R.id.route_title);

        }
    }

}
