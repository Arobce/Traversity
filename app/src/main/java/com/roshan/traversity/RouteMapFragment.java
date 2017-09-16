package com.roshan.traversity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Roshan on 9/14/2017.
 */

public class RouteMapFragment extends Fragment implements OnMapReadyCallback {


    public static final String TAG = "routeMapsFragment";
    GoogleMap mgoogleMap;
    MapView mapView;
    View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.route_maps_fragment,container,false);
        return mView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView= (MapView) mView.findViewById(R.id.map);

        if(mapView!=null){

            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());

        mgoogleMap=googleMap;
        mgoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        LatLng everest = new LatLng(27.9878493, 86.9162713);

        mgoogleMap.addMarker(new MarkerOptions().position(everest).title("Liberty").snippet("Lets go"));
        mgoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(everest,10));

    }
}
