package com.example.gbkim.gubonny.Calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.gbkim.gubonny.Common.Dialog_Calendar;
import com.example.gbkim.gubonny.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalendarViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_calendar)
     void onClick(View view) {
        Dialog_Calendar dialog = new Dialog_Calendar(this, "날짜 선택");
        dialog.show();
    }
}
