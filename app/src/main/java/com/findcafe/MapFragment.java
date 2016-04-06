package com.findcafe;


import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {

    private GoogleMap map;
    private DataHandler dataHandler;
    private DatabaseHelper databaseHelper;

    public MapFragment() {
        // Required empty public constructor
    }
    /**
     * Returns a new instance of this fragment
     */
    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        map = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
        map.setMyLocationEnabled(true);
        dataHandler = new DataHandler();
        databaseHelper = new DatabaseHelper(this.getActivity());
        List<Cafe> cafeList = dataHandler.getCafeList(databaseHelper);

        LocationManager locationManager = (LocationManager)this.getActivity().getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();

        // Get Current Location
        Location myLocation = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);

        // Get latitude of the current location
        double latitude = myLocation.getLatitude();

        // Get longitude of the current location
        double longitude = myLocation.getLongitude();

        // Create a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);

        // Zoom in the Google Map
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myLocation.getLatitude(), myLocation.getLongitude()), 15));


        for(int i = 0; i < cafeList.size(); i++) {
            LatLng markerLatLng = new LatLng(cafeList.get(i).getCafeLatitude(), cafeList.get(i).getCafeLongitude());
            //Create MarkerOptions
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(markerLatLng);

            Marker marker = map.addMarker(markerOptions);//.title(artworkTitle).snippet(shortDescription));
            marker.setTitle(cafeList.get(i).getCafeName());
        }
        return view;
    }

}
