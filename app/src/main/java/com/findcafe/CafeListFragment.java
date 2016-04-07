package com.findcafe;


import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;



/**
 * A simple {@link Fragment} subclass.
 */
public class CafeListFragment extends Fragment implements LocationListener {

    private DatabaseHelper databaseHelper;
    private DataHandler dataHandler;
    private CafeListAdapter cafeListAdapter;
    public CafeListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cafe_list, container, false);
        final Context context = this.getActivity();
        databaseHelper = new DatabaseHelper(this.getActivity());
        dataHandler = new DataHandler();
        // Get current location
        LocationManager locationManager =
                (LocationManager) this.getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 0, this);
        final ListView cafeListView = (ListView)view.findViewById(R.id.cafe_list_view);
        // Set List Adapter
        cafeListAdapter = new CafeListAdapter(this.getActivity(),
                dataHandler.getCafeList(databaseHelper));
        cafeListView.setAdapter(cafeListAdapter);

        return view;
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static CafeListFragment newInstance() {
        CafeListFragment fragment = new CafeListFragment();

        return fragment;
    }



    @Override
    public void onLocationChanged(Location location) {
        // When location changes, update data in the list
        DatabaseAsyncTask databaseAsyncTask = new DatabaseAsyncTask(databaseHelper,
                location.getLatitude(), location.getLongitude());
        databaseAsyncTask.execute();
        cafeListAdapter.update(dataHandler.getCafeList(databaseHelper));
        cafeListAdapter.notifyDataSetChanged();
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
