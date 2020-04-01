package com.example.gbkim.gubonny.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gbkim.gubonny.Common.Dialog_Calendar;
import com.example.gbkim.gubonny.Common.Dialog_Calendar2;
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

    @OnClick(R.id.btn_calendar_type_01)
     void onClickCalendarType01() {
        Dialog_Calendar dialog = new Dialog_Calendar(this, "날짜 선택");
        dialog.show();
    }

    @OnClick(R.id.btn_calendar_type_02)
    void onClickCalendarType02() {
        Dialog_Calendar2 dialog = new Dialog_Calendar2(this, "날짜 선택");
        dialog.show();
    }
}
