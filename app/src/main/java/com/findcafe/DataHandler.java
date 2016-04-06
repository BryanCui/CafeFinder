package com.findcafe;

import android.database.Cursor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 * Created by Tianying on 4/04/2016.
 */
public class DataHandler {

    private List<Cafe> cafeList;

    public DataHandler() {

    }

    /**
     *
     * @param latitude latitude of current location
     * @param longitude longitude of current location
     * @return JSONArray including the result of the request
     */
    public JSONArray getCafeDataFromServer(double latitude, double longitude) {
        try {
            String cafeRequest =
                    "https://api.foursquare.com/v2/venues/search?client_id=ACAO2JPKM1MXHQJCK45IIFKRFR2ZVL0QASMCBCG5NPJQWF2G" +
                            "&client_secret=YZCKUYJ1WHUV2QICBXUBEILZI1DMPUIDP5SHV043O04FKBHL" +
                            "&v=20160315" +
                            "&radius=1000&ll=" + latitude + "," + longitude +
                            "&query=coffee";

            Client client = ClientBuilder.newBuilder().build();
            Response requestCafeResponse = client.target(cafeRequest)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get();

            String cafes = requestCafeResponse.readEntity(String.class);
            JSONObject cafesJson = new JSONObject(cafes);
            JSONArray cafesArray = cafesJson.getJSONObject("response").getJSONArray("venues");

            return cafesArray;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Get cafe JSON error!!!!!!!!!");
            return null;
        }
    }

    /**
     *
     * @param cafesJson JSONObject containing cafes information
     * @return Cafe class instance
     */
    public Cafe cafeInfoHandle(JSONObject cafesJson) {
        try {
            String cafeId = cafesJson.getString("id");
            String cafeName = cafesJson.getString("name");
            String cafeAddress = cafesJson.getJSONObject("location").getString("formattedAddress");
            String cafePhone = "";
            if(cafesJson.getJSONObject("contact").has("phone")) {
                cafePhone = cafesJson.getJSONObject("contact").getString("phone");
            }
            JSONObject cafeLocation = cafesJson.getJSONObject("location");
            double latitude = cafeLocation.getDouble("lat");
            double longitude = cafeLocation.getDouble("lng");
            int distance = cafeLocation.getInt("distance");
            Cafe cafe = new Cafe(cafeId, cafeName,cafeAddress,latitude,longitude,cafePhone,distance);
            return cafe;

        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("cafe Info Handle error!!!!!!!!!");
            return null;
        }
    }

    /**
     *
     * @param databaseHelper database operation helper
     * @param cafesArray JSONArray containing cafes information
     */
    public void insertCafeInfoIntoDatabase(DatabaseHelper databaseHelper, JSONArray cafesArray) {
        try {
            for (int i = 0; i < cafesArray.length(); i++) {
                databaseHelper.insertDataIntoDatabase(cafeInfoHandle(cafesArray.getJSONObject(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Insert cafe List error!!!!!!!!!");
        }
    }

    /**
     *
     * @param databaseHelper database operation helper
     * @return list of Cafe instance
     */
    public List<Cafe> getCafeList(DatabaseHelper databaseHelper) {
        Cursor cafeCursor = databaseHelper.selectDataFromDatabase();
        List<Cafe> cafeList = new ArrayList<Cafe>();
        while(cafeCursor.moveToNext()) {
            String cafeId = cafeCursor.getString(0);
            String cafeName = cafeCursor.getString(1);
            String cafeAddress = cafeCursor.getString(2);
            double cafeLatitude = cafeCursor.getDouble(3);
            double cafeLongitude = cafeCursor.getDouble(4);
            int cafeDistance = cafeCursor.getInt(5);
            String cafePhone = cafeCursor.getString(6);

            Cafe cafe = new Cafe(cafeId,cafeName,cafeAddress,cafeLatitude,cafeLongitude, cafePhone, cafeDistance);
            cafeList.add(cafe);
        }
        cafeCursor.close();
        return cafeList;
    }
}
