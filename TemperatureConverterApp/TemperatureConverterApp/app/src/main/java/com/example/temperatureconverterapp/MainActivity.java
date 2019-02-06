/**
* Author: Dhivya Udaya Kumar
* Program: Temperature Conversion Application
* Description: The application converts temperature between Celsius and Fahrenheit and changes background color
*              based on temperature
*/
package com.example.temperatureconverterapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText tempText;
    ImageView iv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tempText = findViewById(R.id.temp);
        iv = findViewById(R.id.imageView);
        iv.setVisibility(View.INVISIBLE);
    }

    /*
     * This method is called when user clicks on Convert button
     */
    public void onClick(View view){
        switch (view.getId()){
            case R.id.calc:
                RadioButton celsiusBtn = findViewById(R.id.toCelsius);
                RadioButton fahrenheitBtn = findViewById(R.id.toFahrenheit);
                if(tempText.getText().length() == 0){
                    Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_LONG).show();
                    return;
                }
                float inputValue = Float.parseFloat(tempText.getText().toString());
                if(celsiusBtn.isChecked()){
                    tempText.setText(String.format("%.2f", ConverterUtil.convertFahrenheitToCelsius(inputValue)));
                    celsiusBtn.setChecked(false);
                    fahrenheitBtn.setChecked(true);
                }
                else {
                    tempText.setText(String.format("%.2f", ConverterUtil.convertCelsiusToFahrenheit(inputValue)));
                    celsiusBtn.setChecked(true);
                    fahrenheitBtn.setChecked(false);
                }
                inputValue = Float.parseFloat(tempText.getText().toString());
                view = findViewById(R.id.activity_main);
                iv = findViewById(R.id.imageView);

                if((inputValue < 14 && (!fahrenheitBtn.isChecked())) || (inputValue < -10 && (!celsiusBtn.isChecked()))) {
                    view.setBackgroundColor(Color.parseColor("#B4CDCD"));
                    iv.setVisibility(View.VISIBLE);
                    ((ImageView) iv.findViewById(R.id.imageView)).setImageResource(0);
                    iv.setImageResource(R.drawable.extreme_cold_2);
                }
                else if((inputValue >= 14 && inputValue < 50 && (!fahrenheitBtn.isChecked())) || (inputValue >= -10 && inputValue < 10 && (!celsiusBtn.isChecked()))) {
                    view.setBackgroundColor(Color.parseColor("#87CEFF"));
                    iv.setVisibility(View.VISIBLE);
                    ((ImageView) iv.findViewById(R.id.imageView)).setImageResource(0);
                    iv.setImageResource(R.drawable.cool_1);
                }
                else if ((inputValue >= 50 && inputValue <= 95 && (!fahrenheitBtn.isChecked())) || (inputValue >= 10 && inputValue <= 35 && (!celsiusBtn.isChecked()))) {
                    view.setBackgroundColor(Color.parseColor("#FFE900"));
                    iv.setVisibility(View.VISIBLE);
                    ((ImageView) iv.findViewById(R.id.imageView)).setImageResource(0);
                    iv.setImageResource(R.drawable.warmth);
                }
                else if ((inputValue > 95 && (!fahrenheitBtn.isChecked())) || (inputValue > 35 && (!celsiusBtn.isChecked()))) {
                    view.setBackgroundColor(Color.parseColor("#BA0909"));
                    iv.setVisibility(View.VISIBLE);
                    ((ImageView) iv.findViewById(R.id.imageView)).setImageResource(0);
                    iv.setImageResource(R.drawable.extreme_hot);
                }
                break;
        }
    }
}
