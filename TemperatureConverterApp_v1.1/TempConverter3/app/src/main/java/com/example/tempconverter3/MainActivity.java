/**
 * Author: Dhivya Udaya Kumar
 * Program: Temperature Conversion Application
 * Description: The application converts temperature between Celsius and Fahrenheit and displays forecast for next 5 days
 */

package com.example.tempconverter3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //seekbar, textview object
    SeekBar seekBar;
    TextView textView;
    TextView textView2;
    Button btn;
    //member variables for SeekBar
    int discrete = 0;
    int start = 50;
    int start_position = 50; //progress tracker
    int temp = 0;
    //objects for ViewStub
    ViewStub stub;
    CheckBox checkBox;
    //listview object
    ListView lv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declare viewstub object
        stub = findViewById(R.id.viewStub1);
        @SuppressWarnings("unused")
        View inflated = stub.inflate();
        stub.setVisibility(View.INVISIBLE);

        //ViewStub logic
        checkBox = findViewById(R.id.checkBox1);

        //handle checkbox click event
        checkBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (isChecked) {
                    //remove objs from parent view to allow for child view objs
                    checkBox.setVisibility(View.GONE);
                    seekBar.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                    stub.setVisibility(View.VISIBLE);
                    btn.setVisibility(View.VISIBLE);
                }
            }
        });

        //seekbar logic

        textView = findViewById(R.id.textview);
        textView2 = findViewById(R.id.textview2);
        textView.setText("     Celsius at 0 degrees");
        textView2.setText("     Fahrenheit at 32 degrees");
        //set default view
        seekBar = findViewById(R.id.seekbar);
        seekBar.setProgress(start_position);

        //create event handler for SeekBar
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar,
                                          int progress, boolean fromUser) {
                // To convert progress passed as discrete (Fahrenheit) value
                temp = progress - start;
                discrete = (int) Math.round((((temp * 9.0) /
                        5.0) + 32)); //convert C to F temp
                textView.setText("     Celsius at " + temp + " degrees");

                textView2.setText("    Fahrenheit at " + discrete + " degrees");
            }
        });

        //Listview logic

        //Temperatures
        String[] wkTemps = new String[]{"High 27°C / Low 19°C", "High 26°C / Low 22°C", "High 32°C / Low 22°C", "High 23°C / Low 15°C", "High 19°C / Low 15°C"};

        //Images
        int wkImg[] = new int[]{
                R.drawable.weather_coudy_icon,
                R.drawable.weather_hail_icon,
                R.drawable.weather_partly_sunny_icon,
                R.drawable.weather_snow_icon,
                R.drawable.weather_sunny_icon};

        //Days
        String[] wkDays = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

        lv = findViewById(R.id.listView);
        List<HashMap<String,String>> wkList = new ArrayList<HashMap<String,String>>();

        for(int i=0; i<5; i++){
            HashMap<String,String> hMap = new HashMap<String, String>();
            hMap.put("wkDays", wkDays[i]);
            hMap.put("wkTemps", wkTemps[i]);
            hMap.put("wkImg", Integer.toString(wkImg[i]));
            wkList.add(hMap);
        }

        String[] from = {"wkImg", "wkDays", "wkTemps"};
        int[] to = {R.id.wkImg, R.id.wkDays, R.id.wkTemp};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), wkList, R.layout.stub_component, from, to);
        lv.setAdapter(simpleAdapter);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkBox.setVisibility(View.VISIBLE);
                seekBar.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                stub.setVisibility(View.INVISIBLE);
                checkBox.setChecked(false);
                btn.setVisibility(View.INVISIBLE);
            }
        });

    }//end onCreate method
}