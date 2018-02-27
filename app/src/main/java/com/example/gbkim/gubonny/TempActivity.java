package com.example.gbkim.gubonny;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class TempActivity extends AppCompatActivity {

    private Button btn1;
    private Button btn2;

    final int DIALOG_DATE = 1;
    final int DIALOG_TIME = 2;

    private String TAG = "PickerActivity";

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

        init();
    }

    private void init() {
        final Calendar cal = Calendar.getInstance();

        Log.e(TAG, cal.get(Calendar.YEAR)+"");
        Log.e(TAG, cal.get(Calendar.MONTH)+1+"");
        Log.e(TAG, cal.get(Calendar.DATE)+"");
        Log.e(TAG, cal.get(Calendar.HOUR_OF_DAY)+"");
        Log.e(TAG, cal.get(Calendar.MINUTE)+"");

        //DATE PICKER DIALOG
      btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(TempActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                        String msg = String.format("%d 년 %d 월 %d 일", year, month+1, date);
                        Toast.makeText(TempActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

                dialog.getDatePicker().setMaxDate(new Date().getTime());    //입력한 날짜 이후로 클릭 안되게 옵션
                dialog.show();

            }
        });

      btn2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

          }
      });



    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_DATE :
                DatePickerDialog dpd = new DatePickerDialog(TempActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Toast.makeText(getApplicationContext(), year+"년 "+(month+1)+"월 "+dayOfMonth+"일 을 선택했습니다", Toast.LENGTH_SHORT).show();
                    }
                }, 2000, 1, 1);
                return dpd;
            case DIALOG_TIME:
                TimePickerDialog tpd = new TimePickerDialog(TempActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
