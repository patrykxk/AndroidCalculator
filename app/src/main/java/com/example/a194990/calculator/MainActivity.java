package com.example.a194990.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private String onScreen = "";
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
        onScreen += button.getText();
        showOnScreen();
    }

    public void onClickOperator(View view){
        Button button = (Button)view;
        onScreen += button.getText();
        currentOperator = button.getText().toString();
        showOnScreen();
    }
    public void onClickEqual(View view){
        Button button = (Button)view;
        String[] numbers = onScreen.split(Pattern.quote(currentOperator));
        if(numbers.length<2) return;
        onScreen = String.valueOf(calculate(numbers[0],numbers[1], currentOperator));

        showOnScreen();
    }

    public void onClickBackspace(View view){


        onScreen = onScreen.substring(0, onScreen.length()-1);
        showOnScreen();
    }
    public void onClickClear(View view){
        onScreen = "";
        currentOperator = "";
        showOnScreen();
    }

    public void showOnScreen(){
        textView.setText(onScreen);
    }

    private double calculate(String firstNumber, String secondNumber, String operator){
        switch (operator){
            case "+": return Double.valueOf(firstNumber) + Double.valueOf(secondNumber);
            case "-": return Double.valueOf(firstNumber) - Double.valueOf(secondNumber);
            case "*": return Double.valueOf(firstNumber) * Double.valueOf(secondNumber);
            case "/": return Double.valueOf(firstNumber) / Double.valueOf(secondNumber);
            default: return -1;
        }
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
        if (id == R.id.simple) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
