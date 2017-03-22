package com.example.a194990.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private StringBuilder onScreen = new StringBuilder("");
    private String currentOperator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = (TextView)findViewById(R.id.textView);
        textView.setText(onScreen);
    }

    public void onClickNumber(View view){
        Button button = (Button)view;
        onScreen.append(button.getText());
        showOnScreen();
    }

    public void onClickComa(View view){
        if(!onScreen.toString().contains(".")){
            Button button = (Button)view;
            onScreen.append(button.getText());
            showOnScreen();
        }
    }

    public void onClickOperator(View view){
        Button button = (Button)view;
        onScreen.append(button.getText());
        currentOperator = button.getText().toString();
        showOnScreen();
    }


    public void onClickEqual(View view){
        Button button = (Button)view;
        onScreen = new StringBuilder(new Expression(onScreen.toString()).eval().toString());
        showOnScreen();
    }

    public void onClickBackspace(View view){
        if(onScreen.length()>0) {
            onScreen = new StringBuilder(onScreen.substring(0, onScreen.length() - 1));
            showOnScreen();
        }
    }
    public void onClickClear(View view){
        onScreen.delete(0,onScreen.length());
        currentOperator = "";
        showOnScreen();
    }

    public void showOnScreen(){
        textView.setText(onScreen);
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
                Intent intent = new Intent(this, AdvancedActivity.class);
                this.startActivity(intent);
                break;
            case R.id.About:
                Snackbar mySnackbar = Snackbar.make(findViewById(R.id.Simple), "S", 3);
                mySnackbar.show();
                break;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.Simple) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
