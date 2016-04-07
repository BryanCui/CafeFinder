package com.findcafe;

import android.app.Application;
import android.test.ApplicationTestCase;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.*;

import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    private DataHandler dataHandlerTest;
    private DatabaseHelper databaseHelperTest;
    public ApplicationTest() {
        super(Application.class);
        dataHandlerTest = new DataHandler();
    }

    @Test
    public void testGetDataFromServer() {
        double latitudeTest = -37.7980844;
        double longitudeTest = 144.9589147;
        JSONArray arrayTest = dataHandlerTest.getCafeDataFromServer(latitudeTest, longitudeTest);
        assertTrue(arrayTest.length() > 0);
    }

    @Test
    public void testCafeInfoHandle() {
        try {
            double latitudeTest = -37.7980844;
            double longitudeTest = 144.9589147;
            JSONArray arrayTest = dataHandlerTest.getCafeDataFromServer(latitudeTest, longitudeTest);

            Cafe cafe = dataHandlerTest.cafeInfoHandle(arrayTest.getJSONObject(0));
            assertTrue(cafe != null);
            assertTrue(cafe.getCafeId() != null);
            assertTrue(cafe.getCafeName() != null);
            assertTrue(cafe.getCafeAddress() != null);
            assertTrue(cafe.getCafeLatitude() != 0.0);
            assertTrue(cafe.getCafeLongitude() != 0.0);
            assertTrue(cafe.getCafeDistance() != 0);
        } catch (JSONException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

}