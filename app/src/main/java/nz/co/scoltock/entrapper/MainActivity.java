package nz.co.scoltock.entrapper;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TrapDbHelper mDbHelper = new TrapDbHelper(this.getBaseContext());

        List<TrapContract.Trap> traps = mDbHelper.getAllTraps();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id) {
            case (R.id.action_settings):
                return true;
            case (R.id.action_add_trap):
                Intent intent = new Intent(this, AddTrap.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}
