package com.findcafe;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by Tianying on 7/04/2016.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    public MainActivityTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    @Override
    public void setUp() throws Exception {
        //setUp() is run before a test case is started.
        //This is where the solo object is created.
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        //tearDown() is run after a test case has finished.
        //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
        solo.finishOpenedActivities();
    }

    // Test database
    public void testDatabase() throws Exception {
        DataHandler dataHandlerTest = new DataHandler();
        DatabaseHelper databaseHelperTest = new DatabaseHelper(getActivity());
        try {
            double latitudeTest = -37.7980844;
            double longitudeTest = 144.9589147;
            JSONArray arrayTest = dataHandlerTest.getCafeDataFromServer(latitudeTest, longitudeTest);

            dataHandlerTest.insertCafeInfoIntoDatabase(databaseHelperTest, arrayTest);
            List<Cafe> testList = dataHandlerTest.getCafeList(databaseHelperTest);
            assertEquals(testList.size(), arrayTest.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testUI() throws Exception {
        solo.unlockScreen();
        solo.waitForDialogToClose();
        solo.assertCurrentActivity("Test current activity", MainActivity.class);
        solo.clickOnText("Cafe Map");
        solo.clickOnText("Cafe List");
        solo.clickInList(1, 0);
    }
}
