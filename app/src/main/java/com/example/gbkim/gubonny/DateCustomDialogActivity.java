package com.example.gbkim.gubonny;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.gbkim.gubonny.Adapter.Adapter_DateCustomDialog;
import com.example.gbkim.gubonny.Adapter.RecyclerViewAdapter;
import com.example.gbkim.gubonny.Listener.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateCustomDialogActivity extends AppCompatActivity {

    private RecyclerView rv_dcd;
    private LinearLayoutManager RecyclerViewLayoutManager;
    private ArrayList<String> title;
    private Button btn_date;
    private Button btn_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_custom_dialog);

        btn_date = findViewById(R.id.btn_date);
        btn_time = findViewById(R.id.btn_time);

        rv_dcd = findViewById(R.id.rv_dcd);
        RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_dcd.setLayoutManager(RecyclerViewLayoutManager);

        addItems(); // 카드뷰에 데이터 넣기

        event(); // 각종 이벤트

        Adapter_DateCustomDialog adapter = new Adapter_DateCustomDialog(getApplicationContext(), title);
        rv_dcd.setAdapter(adapter);
    }

    private void addItems() {
        title = new ArrayList<>();

        title.add("BP");
        title.add("HP");
        title.add("MP");
    }

    private void event() {
        final Calendar cal = Calendar.getInstance();

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dpd = new DatePickerDialog(DateCustomDialogActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        String msg = String.format("%d-%d-%d", year, month+1, date);
                        btn_date.setText(msg);
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
                dpd.getDatePicker().setMaxDate(new Date().getTime());    //입력한 날짜 이후로 클릭 안되게 옵션
                dpd.show();
            }
        });

        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog tpd = new TimePickerDialog(DateCustomDialogActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        String msg = String.format("%d:%d", hour, min);
                        btn_time.setText(msg);
                    }
                }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);
                tpd.show();
            }
        });
    }
}
