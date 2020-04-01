package boni.sample.java.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import boni.sample.java.Common.Dialog_Calendar;
import boni.sample.java.Common.Dialog_Calendar2;
import boni.sample.java.R;

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
