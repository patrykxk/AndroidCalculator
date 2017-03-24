package com.example.a194990.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.a194990.calculator.R.string.inputError;

public class AdvancedActivity extends AppCompatActivity {
    private TextView textView;
    private StringBuilder onScreen = new StringBuilder("");
    private String currentOperator = "";
    private Boolean isCommaAble = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced);

        textView = (TextView)findViewById(R.id.textView);
        textView.setText(onScreen);
    }

    public void onClickNumber(View view){
        Button button = (Button)view;
        onScreen.append(button.getText());
        showOnScreen();
    }

    public void onClickComa(View view){
        if(isCommaAble) {
            Button button = (Button) view;
            onScreen.append(button.getText());
            showOnScreen();
            setIsCommaAble(false);
        }
    }

    public void onClickOperator(View view){
        Button button = (Button)view;
        onScreen.append(button.getText());
        currentOperator = button.getText().toString();
        setIsCommaAble(true);
        showOnScreen();
    }
    public void onClickTrigonometryOrLog(View view){
        Button button = (Button)view;
        onScreen.append(button.getText()+"(");
        currentOperator = button.getText().toString();
        showOnScreen();
    }
    public void onClickSqrt(View view){
        Button button = (Button)view;
        onScreen.append("SQRT(");
        currentOperator = button.getText().toString();
        showOnScreen();
    }

    public StringBuilder checkBracketsEqality(StringBuilder stringBuilder){
        String string = stringBuilder.toString();
        int numberOfOpeningBrackets = 0;
        int numberOfClosingBrackets = 0;
        for(int i =0; i<string.length();i++){
            if(string.charAt(i)=='('){
                numberOfOpeningBrackets++;
            }else if(string.charAt(i)==')'){
                numberOfClosingBrackets++;
            }
        }
        int diff = numberOfOpeningBrackets-numberOfClosingBrackets;
        if(diff>0){
            while(diff>0){
                stringBuilder.append(")");
                diff--;
            }
        }
        return stringBuilder;
    }
    public void onClickEqual(View view){
        onScreen = checkBracketsEqality(onScreen);
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
                setIsCommaAble(true);
            }else if(string.contains(".")&&(lastChar=='+'||lastChar=='-'||lastChar=='/'||lastChar=='*')){
                setIsCommaAble(false);
            }
            onScreen = new StringBuilder(onScreen.substring(0, onScreen.length() - 1));
            showOnScreen();
        }
    }
    public void onClickClear(View view){
        onScreen.delete(0,onScreen.length());
        currentOperator = "";
        showOnScreen();
        setIsCommaAble(true);
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
        isCommaAble = savedInstanceState.getBoolean("isCommaAble");
        //showOnScreen();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("onScreen", onScreen.toString());
        outState.putBoolean("isCommaAble", isCommaAble);
        super.onSaveInstanceState(outState);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(item.getItemId()){
            case R.id.Simple:
                Intent intent = new Intent(this, SimpleActivity.class);
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
        if (id == R.id.Advanced) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setIsCommaAble(boolean isCommaAble) {
        this.isCommaAble = isCommaAble;
    }
}
