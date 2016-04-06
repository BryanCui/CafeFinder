package com.findcafe;


import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CafeListFragment extends Fragment implements LocationListener {

    private List<Cafe> cafeList;
    private JSONArray cafesArray;
    private double latitude;
    private double longitude;
    private DatabaseHelper databaseHelper;
    private DataHandler dataHandler;

    public CafeListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cafe_list, container, false);
        databaseHelper = new DatabaseHelper(this.getActivity());
        dataHandler = new DataHandler();
        ListView cafeListView = (ListView)view.findViewById(R.id.cafe_list_view);
        cafeListView.setAdapter(new CafeListAdapter(this.getActivity(), dataHandler.getCafeList(databaseHelper)));
        return view;
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static CafeListFragment newInstance() {
        CafeListFragment fragment = new CafeListFragment();
//        Bundle args = new Bundle();
//        args.putString("cafeArray", cafesArray.toString());
//        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
