package com.example.googlecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button zero, one, two, three, four, five, six, seven, eight, nine, plus, minus, multiply, divide, equal, dot, delete;
    TextView input, output;
    String text, operation;
    Double number1, number2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zero =findViewById(R.id.zero);
        one =findViewById(R.id.one);
        two =findViewById(R.id.two);
        three =findViewById(R.id.three);
        four =findViewById(R.id.four);
        five =findViewById(R.id.five);
        six =findViewById(R.id.six);
        seven =findViewById(R.id.seven);
        eight =findViewById(R.id.eight);
        nine =findViewById(R.id.nine);

        plus =findViewById(R.id.plus);
        minus =findViewById(R.id.minus);
        multiply =findViewById(R.id.multiply);
        divide =findViewById(R.id.divide);
        delete =findViewById(R.id.delete);
        dot =findViewById(R.id.dot);
        equal=findViewById(R.id.equal);

        input=findViewById(R.id.input);
        output=findViewById(R.id.output);

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.getText().toString().equals("0")){
                    text="";
                }
                else{
                    text=input.getText().toString();
                }
                input.setText(text+"0");
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.getText().toString().equals("0")){
                    text="";
                }
                else{
                    text=input.getText().toString();
                }
                input.setText(text+"1");
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.getText().toString().equals("0")){
                    text="";
                }
                else{
                    text=input.getText().toString();
                }
                input.setText(text+"2");
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.getText().toString().equals("0")){
                    text="";
                }
                else{
                    text=input.getText().toString();
                }
                input.setText(text+"3");
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.getText().toString().equals("0")){
                    text="";
                }
                else{
                    text=input.getText().toString();
                }
                input.setText(text+"4");
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.getText().toString().equals("0")){
                    text="";
                }
                else{
                    text=input.getText().toString();
                }
                input.setText(text+"5");
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.getText().toString().equals("0")){
                    text="";
                }
                else{
                    text=input.getText().toString();
                }
                input.setText(text+"6");
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.getText().toString().equals("0")){
                    text="";
                }
                else{
                    text=input.getText().toString();
                }
                input.setText(text+"7");
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.getText().toString().equals("0")){
                    text="";
                }
                else{
                    text=input.getText().toString();
                }
                input.setText(text+"8");
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.getText().toString().equals("0")){
                    text="";
                }
                else{
                    text=input.getText().toString();
                }
                input.setText(text+"9");
            }
        });
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.getText().toString().equals("")){
                    text="0";
                }
                else{
                    text=input.getText().toString();
                }
                input.setText(text+".");
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=input.getText().toString();
                text = text.substring(0, text.length()- 1);
                input.setText(text+"");
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=input.getText().toString();
                number1 = Double.parseDouble(text);
                operation="+";
                input.setText("");
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=input.getText().toString();
                number1 = Double.parseDouble(text);
                operation="-";
                input.setText("");
            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=input.getText().toString();
                number1 = Double.parseDouble(text);
                operation="*";
                input.setText("");
            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=input.getText().toString();
                number1 = Double.parseDouble(text);
                operation="/";
                input.setText("");
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=input.getText().toString();
                number2 = Double.parseDouble(text);
                if (operation.equals("+")) {
                    output.setText(String.valueOf(number1+number2));
                }
                else if(operation.equals("-")){
                    output.setText(String.valueOf(number1-number2));
                }
                else if(operation.equals("*")){
                    output.setText(String.valueOf(number1*number2));
                }
                else if(operation.equals("/")){
                    output.setText(String.valueOf(number1/number2));
                }
            }
        });
    }
}