package com.example.gbkim.gubonny;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class TempActivity extends AppCompatActivity {

    private Button btn1;
    private Button btn2;

    final int DIALOG_DATE = 1;
    final int DIALOG_TIME = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DIALOG_DATE);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DIALOG_TIME);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_DATE :
                DatePickerDialog dpd = new DatePickerDialog(getApplicationContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Toast.makeText(getApplicationContext(), year+"년 "+(month+1)+"월 "+dayOfMonth+"일 을 선택했습니다", Toast.LENGTH_SHORT).show();
                    }
                }, 2000, 1, 1);
                return dpd;
            case DIALOG_TIME:
                TimePickerDialog tpd = new TimePickerDialog(getApplicationContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(getApplicationContext(), hourOfDay + "시 " + minute + "분 을 선택했습니다.", Toast.LENGTH_SHORT).show();
                    }
                }, 4, 19, true);
                return tpd;
        }
        return super.onCreateDialog(id);
    }
}
