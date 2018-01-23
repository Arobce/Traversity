package com.roshan.traversity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.roshan.traversity.Models.Location;
import com.roshan.traversity.RecyclerView.LocationAdapter;
import com.roshan.traversity.RecyclerView.RouteAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RouteActivity extends AppCompatActivity {

    Bundle bundle;
    private SectionsPageAdapter sectionsPageAdapter;
    RouteGeneralFragment routeGeneralFragment = new RouteGeneralFragment();
    List<Integer> locationId = new ArrayList<>();
    List<Location> locationList;
    LocationAdapter locationAdapter;
    Context ctx = this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitleTextColor(Color.BLACK);

        toolbar.setTitle(getIntent().getStringExtra("title"));

        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final Boolean[] addedToFavorites = {false};
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!addedToFavorites[0]) {
                    Snackbar.make(view, "Added to favorites", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_fab_active));
                    addedToFavorites[0] = true;
                } else {

                    Snackbar.make(view, "Removed from favorites", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_fab_inactive));
                    addedToFavorites[0] = false;

                }
            }
        });

        bundle = new Bundle();




        locationId = new ArrayList<Integer>();
        locationList = new ArrayList<Location>();
        getLocationID();
        getLocationData();



        setRouteImage();

    }

    private void setUpViewPager(ViewPager viewPager){

        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        routeGeneralFragment.setArguments(bundle);
        adapter.addFragment(routeGeneralFragment,"Overview");
        adapter.addFragment(new RouteMapFragment(), "Maps");
        viewPager.setAdapter(adapter);

    }

    public void setRouteImage(){

        // Set Image
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);

        String image = getIntent().getStringExtra("thumbnail");

        Glide.with(this)
                .asBitmap()
                .load(image)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

                        Drawable d = new BitmapDrawable(getResources(), resource);
                        collapsingToolbarLayout.setBackground(d);
                    }

                });


    }



    public void getLocationID(){

        String url = "http://192.168.100.4/routelocation.php";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0; i<response.length(); i++){

                    try {

                        JSONObject route = response.getJSONObject(i);

                        int id = Integer.parseInt(route.getString("routeID"));
                        //int route_id = Integer.parseInt(getIntent().getStringExtra("id"));

                        if(id==1){
                            locationId.add(Integer.parseInt(route.getString("location_id")));
                        }else {
                            Toast.makeText(RouteActivity.this, "ERRRRR", Toast.LENGTH_SHORT).show();
                        }


                    } catch (JSONException e) {

                        e.printStackTrace();

                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        Singleton.getInstance(getApplicationContext()).addToRequestQueue(request);


    }

    public void getLocationData(){

        String url = "http://192.168.100.4/location.php";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0; i<response.length(); i++){

                    try {

                        JSONObject location = response.getJSONObject(i);

                        for(int k=0;k<locationId.size();k++) {

                            if (Integer.parseInt(location.getString("id")) == locationId.get(k)) {

                                int id = Integer.parseInt(location.getString("id"));
                                int userID = Integer.parseInt(location.getString("user_id"));
                                String title = location.getString("title");
                                String description = location.getString("description");
                                String category = location.getString("category");
                                String thumbnail = location.getString("thumbnail");
                                String lat_lng = location.getString("lat_lng");


                                locationList.add(
                                        new Location(id, userID, title, category,
                                                description, thumbnail, lat_lng)
                                );


                            }
                        }


                    } catch (JSONException e) {

                        e.printStackTrace();

                    }

                }

                // Pass Data to fragment
                Gson gson = new Gson();
                String json = gson.toJson(locationList);

                bundle.putString("description",getIntent().getStringExtra("description"));
                bundle.putString("locationList",json);

                //===========tabbed layout=============
                ViewPager viewPager = (ViewPager) findViewById(R.id.container);

                //call method
                setUpViewPager(viewPager);


                //set view pager to tab
                TabLayout tableLayout = (TabLayout) findViewById(R.id.tabbar);
                tableLayout.setupWithViewPager(viewPager);

                //========================================


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        Singleton.getInstance(getApplicationContext()).addToRequestQueue(request);

        //Toast.makeText(this, locationId.toString(), Toast.LENGTH_SHORT).show();

    }

}
