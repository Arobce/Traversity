package com.roshan.traversity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Roshan on 9/14/2017.
 */

public class RouteMapFragment extends Fragment {

    public static final String TAG = "routeMapsFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.route_maps_fragment,container,false);
        return view;
    }
}
