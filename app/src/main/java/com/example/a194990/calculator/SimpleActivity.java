package com.example.a194990.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.a194990.calculator.R.string.inputError;

public class SimpleActivity extends AppCompatActivity {
    private TextView textView;
    private StringBuilder onScreen = new StringBuilder("");
    private String currentOperator = "";
    private boolean isComaAble = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = (TextView)findViewById(R.id.textView);
        textView.setText(onScreen);
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
    }

    public void onClickNumber(View view){
        Button button = (Button)view;
        onScreen.append(button.getText());
        showOnScreen();
    }

    public void onClickComa(View view){
        if(isComaAble){
            Button button = (Button)view;
            onScreen.append(button.getText());
            showOnScreen();
            setIsComaAble(false);
        }
    }

    public void onClickOperator(View view){
        Button button = (Button)view;
        onScreen.append(button.getText());
        currentOperator = button.getText().toString();
        showOnScreen();
        setIsComaAble(true);
    }


    public void onClickEqual(View view){
        try {
            onScreen = new StringBuilder(new Expression(onScreen.toString()).eval().toString());
        }catch (Exception e){
            Toast.makeText(this, inputError,Toast.LENGTH_SHORT).show();
        }
        showOnScreen();
    }

    public void onClickBackspace(View view){
        if(onScreen.length()>0) {
            String string = onScreen.toString();
            char lastChar = string.charAt(string.length()-1);
            if(lastChar=='.'){
                setIsComaAble(true);
            }else if(string.contains(".")&&(lastChar=='+'||lastChar=='-'||lastChar=='/'||lastChar=='*'||lastChar=='^')){
                setIsComaAble(false);
            }
            onScreen = new StringBuilder(onScreen.substring(0, onScreen.length() - 1));
            showOnScreen();
        }
    }
    public void onClickClear(View view){
        onScreen.delete(0,onScreen.length());
        currentOperator = "";
        showOnScreen();
        setIsComaAble(true);
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
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onScreen = new StringBuilder(savedInstanceState.getString("onScreen"));
        isComaAble = savedInstanceState.getBoolean("isComaAble");
        showOnScreen();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("onScreen", onScreen.toString());
        outState.putBoolean("isComaAble", isComaAble);
        super.onSaveInstanceState(outState);
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
                startActivity(intent);
                break;
            case R.id.About:
                Intent intent2 = new Intent(this, AboutActivity.class);
                startActivity(intent2);
                break;
            case R.id.Exit:
                Intent intentExit = new Intent(getApplicationContext(), SimpleActivity.class);
                intentExit.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentExit.putExtra("EXIT", true);
                startActivity(intentExit);
                break;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.Simple) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setIsComaAble(boolean isComaOnScreen) {
        this.isComaAble = isComaOnScreen;
    }
}
