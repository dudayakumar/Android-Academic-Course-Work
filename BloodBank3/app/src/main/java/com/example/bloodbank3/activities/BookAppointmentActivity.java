package com.example.bloodbank3.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.bloodbank3.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {

    private EditText appointmentDate;
    private EditText appointmentTime;
    private DatePickerDialog datePicker;
    private TimePickerDialog timePicker;
    private Button appointmentBookBtn;

    private DatabaseReference db_ref;
    private FirebaseAuth mAuth;
    private FirebaseDatabase fdb;

    String uid,uemail;
    String appttime, apptdate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        mAuth = FirebaseAuth.getInstance();
        fdb = FirebaseDatabase.getInstance();
        db_ref = fdb.getReference("appointments");

        appointmentDate = findViewById(R.id.appointment_date);
        appointmentTime = findViewById(R.id.appointment_time);
        appointmentBookBtn = findViewById(R.id.schedule_appointment);

        appointmentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                datePicker = new DatePickerDialog(BookAppointmentActivity.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        appointmentDate.setText(day + "/" + (month + 1) + "/" + year);
                    }
                },year,month,day);
                datePicker.show();
            }
        });

        appointmentTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);

                timePicker = new TimePickerDialog(BookAppointmentActivity.this,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        appointmentTime.setText(hourOfDay+":"+minute);
                    }
                },hour,minute,true);
                timePicker.show();
            }
        });

        //Fetching currently logged in user
        FirebaseUser cur_user = mAuth.getInstance().getCurrentUser();

        if(cur_user == null)
        {
            //Navigate to login page if no user is logged in
            startActivity(new Intent(BookAppointmentActivity.this, LoginActivity.class));
        } else {
            //Else get the user id and store it in uid
            uid = cur_user.getUid();
            uemail = cur_user.getEmail();
            Log.d("BookAppointment: ", "*****User email: "+uemail);
        }

        appointmentBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fetch user's id from the User data reference in Firebase
                final Query findUid = fdb.getReference("users").child(uid);
                final Query findName = fdb.getReference("users").child("Name");
                final String ApptDate = appointmentDate.getText().toString();
                final String ApptTime = appointmentTime.getText().toString();
                Log.d("BookAppointment","*****User id: "+((DatabaseReference) findUid).getKey());

                if (appointmentDate.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "Please pick a date", Toast.LENGTH_LONG).show();
                }
                else if (appointmentTime.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "Please pick a time", Toast.LENGTH_LONG).show();
                }
                else {
                    //Save the date and time in the Appointment data reference in Firebase
                    db_ref.child(uid).child("Date").setValue(ApptDate);
                    db_ref.child(uid).child("Time").setValue(ApptTime);
                }
            }
        });
    }
}