package com.example.a194990.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by Patryk on 2017-03-23.
 */

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
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
        switch(item.getItemId()){
            case R.id.Advanced:
                Toast.makeText(this, "ADVANCED!!",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, AdvancedActivity.class);
                startActivity(intent);
                break;
            case R.id.Simple:
                Toast.makeText(this, "SIMPLE_ACC!!",
                        Toast.LENGTH_LONG).show();
                Intent intent3 = new Intent(this, SimpleActivity.class);
                startActivity(intent3);
                break;
            case R.id.Exit:
                System.exit(0);
                break;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.About) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
