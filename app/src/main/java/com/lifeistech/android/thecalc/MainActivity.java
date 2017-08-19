package com.lifeistech.android.thecalc;

import android.app.Dialog;
import android.app.SharedElementCallback;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.math.BigDecimal;
import java.math.RoundingMode;


public class MainActivity extends AppCompatActivity {
    BigDecimal number1 = new BigDecimal(0.0);
    BigDecimal number2 = new BigDecimal(0.0);
    BigDecimal answer = new BigDecimal(0.0);
    int ope;
    TextView textView;
    TextView binarytext;
    TextView hextext;

    int decimalnumber;
    SharedPreferences preferences;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ope = 0;

        textView = (TextView) findViewById(R.id.textView);
        binarytext = (TextView) findViewById(R.id.binarytext);
        hextext = (TextView) findViewById(R.id.hextext);
        textView.setText("0");
        hextext.setText("0");
        binarytext.setText("0");
        preferences = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        decimalnumber = preferences.getInt("decimalnumber",100);

        Log.d("decimalnumber",decimalnumber + "");
    }

    public void click(View v) {

        String tag = (String) v.getTag();

        switch (tag) {
            case "0":
                number1 = number1.multiply(new BigDecimal(10.0));
                break;
            case "1":
                number1 = number1.multiply(new BigDecimal(10.0)).add(new BigDecimal(1.0));
                break;
            case "2":
                number1 = number1.multiply(new BigDecimal(10.0)).add(new BigDecimal(2.0));
                break;
            case "3":
                number1 = number1.multiply(new BigDecimal(10.0)).add(new BigDecimal(3.0));
                break;
            case "4":
                number1 = number1.multiply(new BigDecimal(10.0)).add(new BigDecimal(4.0));
                break;
            case "5":
                number1 = number1.multiply(new BigDecimal(10.0)).add(new BigDecimal(5.0));
                break;
            case "6":
                number1 = number1.multiply(new BigDecimal(10.0)).add(new BigDecimal(6.0));
                break;
            case "7":
                number1 = number1.multiply(new BigDecimal(10.0)).add(new BigDecimal(7.0));
                break;
            case "8":
                number1 = number1.multiply(new BigDecimal(10.0)).add(new BigDecimal(8.0));
                break;
            case "9":
                number1 = number1.multiply(new BigDecimal(10.0)).add(new BigDecimal(9.0));
                break;
            default:
                break;
        }
        textView.setText(String.valueOf(number1));
        binarytext.setText(Integer.toBinaryString(Integer.parseInt(number1.toString())));
        hextext.setText(Integer.toHexString(Integer.parseInt(number1.toString())));
    }
    public void plus(View v){
        number2 = number1;
        ope = 1;
        number1 = new BigDecimal(0.0);
    }

    public void minus(View v){
        number2 = number1;
        ope = 2;
        number1 = new BigDecimal(0.0);
    }

    public void multiply(View v){
        number2 = number1;
        ope = 3;
        number1 = new BigDecimal(0.0);
    }
    public void divide(View v){
        number2 = number1;
        ope = 4;
        number1 = new BigDecimal(0.0);
    }

    public void equal(View v){
        if(ope == 1){
            answer = number1.add(number2);
        }else if(ope == 2){
            answer = number2.subtract(number1);
        }else if (ope == 3){
            answer = number1.multiply(number2);
        }else if (ope == 4){
            answer = number2.divide(number1,decimalnumber,RoundingMode.HALF_UP);
        }
        textView.setText(String.valueOf(answer));
        binarytext.setText(Integer.toBinaryString(Integer.parseInt(answer.setScale(0, BigDecimal.ROUND_HALF_UP).toString())));
        hextext.setText(Integer.toHexString(Integer.parseInt(answer.setScale(0, BigDecimal.ROUND_HALF_UP).toString())));
    }


    public void clear(View v){
        ope = 0;
        answer = new BigDecimal(0.0);
        number1 = new BigDecimal(0.0);
        number2 = new BigDecimal(0.0);
        textView.setText("0");
        binarytext.setText("0");
        hextext.setText("0");
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.settings:
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                View view = inflater.inflate(R.layout.dialogue,null);
                final EditText editText =(EditText)view.findViewById(R.id.editText);
                editText.setText(decimalnumber + "");
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("小数点の桁数")
//                        .setIcon(R.drawable.icon)
                        .setView(view)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, editText.getText().toString(), Toast.LENGTH_LONG).show();
                                decimalnumber = Integer.parseInt( editText.getText().toString());
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putInt("decimalnumber", decimalnumber);
                                editor.apply();
                            }
                        })
                        .setNegativeButton("cancel",null)
                        .show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return true;
    }
}
