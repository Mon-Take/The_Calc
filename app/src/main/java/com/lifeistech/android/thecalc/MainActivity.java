package com.lifeistech.android.thecalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class MainActivity extends AppCompatActivity {
    BigDecimal number1 = new BigDecimal(0.0);
    BigDecimal number2 = new BigDecimal(0.0);
    BigDecimal answer = new BigDecimal(0.0);
    int ope;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ope = 0;

        textView = (TextView) findViewById(R.id.textView);

        textView.setText("0");
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
            answer = number2.divide(number1,500,RoundingMode.HALF_UP);
        }
        textView.setText(String.valueOf(answer));
    }



    public void clear(View v){
        ope = 0;
        answer = new BigDecimal(0.0);
        number1 = new BigDecimal(0.0);
        number2 = new BigDecimal(0.0);
        textView.setText("0");
    }
}
