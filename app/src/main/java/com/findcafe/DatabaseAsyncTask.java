package com.findcafe;

import android.os.AsyncTask;

import org.json.JSONArray;

/**
 * Created by Tianying on 5/04/2016.
 */
public class DatabaseAsyncTask extends AsyncTask<Integer, Integer, String> {
    private double latitude;
    private double longitude;
    private DatabaseHelper databaseHelper;
    private DataHandler dataHandler;
    public DatabaseAsyncTask(DatabaseHelper databaseHelper, double latitude, double longitude) {
        this.databaseHelper = databaseHelper;
        this.latitude = latitude;
        this.longitude = longitude;
        dataHandler = new DataHandler();
    }
    @Override
    protected String doInBackground(Integer... params) {
        databaseHelper.clearTable();
        JSONArray cafesArray = dataHandler.getCafeDataFromServer(latitude, longitude);
        dataHandler.insertCafeInfoIntoDatabase(databaseHelper, cafesArray);
        System.out.println("FinishedFinishedFinishedFinishedFinishedFinishedFinishedFinishedFinishedFinished");
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }

}
