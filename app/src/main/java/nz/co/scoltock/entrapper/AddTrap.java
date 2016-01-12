package nz.co.scoltock.entrapper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;


public class AddTrap extends ActionBarActivity {


    private Button addTrap = null;
    private EditText trapName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trap);

        addTrap = (Button) findViewById(R.id.storeTrap);
        trapName = (EditText) findViewById(R.id.trap_name);

         /* Use the LocationManager class to obtain GPS locations */
        LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locListener = new MyLocationListener();
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);


        addTrap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveTrap(v);
            }});
    }

    private void saveTrap(View v) {
        TrapDbHelper mDbHelper = new TrapDbHelper(v.getContext());

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

    // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(TrapContract.Trap.COLUMN_NAME_CODE, trapName.getText().toString());

    // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                TrapContract.Trap.TABLE_NAME,
                TrapContract.Trap.COLUMN_NAME_CODE,
                values);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_trap, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            EditText tv = (EditText) findViewById(R.id.location);

            double lat = location.getLatitude();
            double lon = location.getLongitude();

            String Text = "My current location is: " +
                    "\nLatitude = " + lat +
                    "\nLongitude = " + lon;

            // Display location
            tv.setText(Text);

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
}
