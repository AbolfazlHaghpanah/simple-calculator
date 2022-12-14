package com.haghpanah.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button ac,backspace,percent,division,multiply,minus,plus,equal;
    Button[] numbers = new Button[10];
    TextView operatorContainer,numberContainer1,numberContainer2,numberContainer3;
    int numberOne ,numberTwo =0 , iForNumber =0,operatorCase=0,finalNumber=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        setupOnClickListener();

    }


    private int numberRevers(int num){
        int reverse = 0;
        while(num != 0)
        {
            int remainder = num % 10;
            reverse = reverse * 10 + remainder;
            num = num/10;
        }
        return reverse;
    }

    private void setupOnClickListener(){
        for (int i= 0;i<numbers.length;i++){
            numbers[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button btn = (Button) view;
                    if (numberTwo==0)
                    {
                        numberContainer2.setText(""+btn.getText());
                    }else {
                        numberContainer2.setText(""+numberContainer2.getText()+btn.getText());
                    }
                    numberTwo +=(Integer.parseInt(""+ btn.getText()))*Math.pow(10,iForNumber++);

                }
            });
        }

        View.OnClickListener operatorsOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button btn = (Button) view;
                if (numberOne!=0&&numberTwo!=0){
                    equal();
                    numberContainer3.setText(""+finalNumber);
                    numberTwo=0;
                    numberOne = finalNumber;
                }else if(finalNumber==0){
                    numberTwo = numberRevers(numberTwo);
                    numberOne = numberTwo;
                    numberTwo = 0;
                    numberContainer1.setText(""+numberOne);
                    numberContainer2.setText("0");
                }else {
                    numberOne = finalNumber;
                    numberTwo = 0;
                    numberContainer1.setText(""+numberOne);
                    numberContainer2.setText("0");
                    numberContainer3.setText("0");
                }





                switch (btn.getId()){
                    case R.id.multiply:
                        operatorCase=3;
                        operatorContainer.setText("*");
                        break;
                    case R.id.Division:
                        operatorCase=4;
                        operatorContainer.setText("/");

                        break;
                    case R.id.minus:
                        operatorCase=2;
                        operatorContainer.setText("-");

                        break;
                    case R.id.plus:
                        operatorCase=1;
                        operatorContainer.setText("+");

                        break;
                    case R.id.percent:
                        operatorCase=5;
                        operatorContainer.setText("%");
                        break;
                    default:operatorCase=0;
                    break;


                }
                operatorContainer.setVisibility(View.VISIBLE);
            }
        };

        equal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                equal();
                numberContainer3.setText(""+finalNumber);
            }
        });

        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restore();
            }
        });

        multiply.setOnClickListener(operatorsOnClick);
        percent.setOnClickListener(operatorsOnClick);
        division.setOnClickListener(operatorsOnClick);
        plus.setOnClickListener(operatorsOnClick);
        minus.setOnClickListener(operatorsOnClick);
    }

    private void equal(){
        numberTwo = numberRevers(numberTwo);

        switch (operatorCase){
            case 1:
                finalNumber=numberOne+numberTwo;
                break;
            case 2:
                finalNumber=numberOne-numberTwo;
                break;
            case 3:
                finalNumber=numberOne*numberTwo;
                break;
            case 4:
                finalNumber=numberOne/numberTwo;
                break;

        }

        numberContainer3.setVisibility(View.VISIBLE);
    }

    private void restore(){
        numberOne = 0;
        numberTwo =0 ;
        iForNumber =0;
        operatorCase=0;
        finalNumber=0;
        numberContainer1.setText("0");
        numberContainer2.setText("0");
        numberContainer3.setText("0");
        operatorContainer.setVisibility(View.INVISIBLE);
        numberContainer3.setVisibility(View.GONE);
    }
    private void setupView(){
        numberContainer3 = findViewById(R.id.numberContainer3);
        numberContainer2 = findViewById(R.id.numberContainer2);
        numberContainer1 = findViewById(R.id.numberContainer1);
        operatorContainer = findViewById(R.id.operator);
        backspace = findViewById(R.id.Backspace);
        ac = findViewById(R.id.AC);
        percent =findViewById(R.id.percent);
        multiply = findViewById(R.id.multiply);
        division = findViewById(R.id.Division);
        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        equal = findViewById(R.id.equal);
        numbers[0]=findViewById(R.id.number0);
        numbers[1]=findViewById(R.id.number1);
        numbers[2]=findViewById(R.id.number2);
        numbers[3]=findViewById(R.id.number3);
        numbers[4]=findViewById(R.id.number4);
        numbers[5]=findViewById(R.id.number5);
        numbers[6]=findViewById(R.id.number6);
        numbers[7]=findViewById(R.id.number7);
        numbers[8]=findViewById(R.id.number8);
        numbers[9]=findViewById(R.id.number9);
    }


}