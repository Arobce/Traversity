package com.roshan.traversity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.roshan.traversity.Models.Location;
import com.roshan.traversity.RecyclerView.LocationAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roshan on 9/14/2017.
 */

public class RouteGeneralFragment extends Fragment {

    TextView description;
    RecyclerView recyclerView;
    LocationAdapter locationAdapter;
    List<Location> locations;
    JSONArray locatonList;


    public static final String TAG = "routeGeneralFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.route_general_fragment,container,false);

        description = view.findViewById(R.id.route_description);
        if(getArguments()!=null) {
            description.setText(getArguments().getString("description"));
        }else {
            description.setText("Error");
        }

        /*
        * Location Fragment
        * */

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        locations = new ArrayList<Location>();


        try {
            locatonList = new JSONArray(getArguments().getString("locationList"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            setLocations(locatonList);
        } catch (JSONException e) {
            e.printStackTrace();
        }


//        locations.add(
//                new Location(
//                        1,2,"Durbar Squares","Cultural","Feel the durbar square","https://upload.wikimedia.org/wikipedia/commons/thumb/f/fb/Durbar_Square_2010.jpg/1200px-Durbar_Square_2010.jpg",""
//                ));
//
//        locations.add(
//                new Location(
//                        1,2,"Angel Falls","Cultural","Feel the durbar square","https://www.thetimes.co.uk/imageserver/image/methode%2Fsundaytimes%2Fprod%2Fweb%2Fbin%2F42e82360-46e0-11e7-9575-cc3570f7abb6.jpg?crop=2250%2C1266%2C0%2C117",""
//                ));



        locationAdapter = new LocationAdapter(getActivity(),locations);

        recyclerView.setAdapter(locationAdapter);


        return view;
    }

    public void setLocations(JSONArray locationsJSON) throws JSONException {

        for (int i=0;i<locationsJSON.length();i++){

            JSONObject location = locationsJSON.getJSONObject(i);

            int id = Integer.parseInt(location.getString("id"));
            int userID = Integer.parseInt(location.getString("user_id"));
            String title = location.getString("title");
            String description = location.getString("description");
            String category = location.getString("category");
            String thumbnail = location.getString("thumbnail");
            String lat_lng = location.getString("lat_lng");


            locations.add(
                    new Location(id, userID, title, category,
                            description, thumbnail, lat_lng)
            );
        }
    }

}
