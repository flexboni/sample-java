package com.example.gbkim.gubonny.DateTime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gbkim.gubonny.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DateTimeActivity extends AppCompatActivity {

    @BindView(R.id.tv_activity_date_time_date)
    TextView textDate;

    @BindView(R.id.btn_activity_date_time_today_date)
    Button buttonTodayDate;
    @BindView(R.id.btn_activity_date_time_after_one_day_date)
    Button buttonAfterDate;
    @BindView(R.id.btn_activity_date_time_two_weeks_ago_date)
    Button buttonTwoWeeksAgoDate;

    private String todayDate;
    private SimpleDateFormat m_SimpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);

        ButterKnife.bind(this);

        init();

        UIEvents();


        // 하루 증가한 날짜 가져오기
    }

    private void init() {
        m_SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
    }

    private void UIEvents() {
        buttonTodayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 오늘 날짜 가져오기
                todayDate = m_SimpleDateFormat.format(new Date());

                Log.e("DateTime", "Today Date : " + todayDate);

                textDate.setText(todayDate);
            }
        });

        buttonAfterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (todayDate == null || todayDate.equals("")) {
                    Toast.makeText(DateTimeActivity.this, "오늘날짜 버튼 먼저 클릭 해주세요.", Toast.LENGTH_SHORT).show();

                } else {
                    String afterOneDayDate = String.valueOf(Integer.parseInt(todayDate) + 1);

                    try {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);

                        // 날짜 형식으로 바꾸기
                        Date date = simpleDateFormat.parse(afterOneDayDate);

                        afterOneDayDate = m_SimpleDateFormat.format(date);

                        Log.e("DateTime", "Today Date : " + afterOneDayDate);

                        textDate.setText(afterOneDayDate);

                    } catch (ParseException e) {
                        e.printStackTrace();

                        Toast.makeText(DateTimeActivity.this, "시간 변환 오류", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        buttonTwoWeeksAgoDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (todayDate == null || todayDate.equals("")) {
                    Toast.makeText(DateTimeActivity.this, "오늘날짜 버튼 먼저 클릭 해주세요.", Toast.LENGTH_SHORT).show();

                } else {
                    String twoWeeksAgoDate = String.valueOf(Integer.parseInt(todayDate) + 1);

//                    try {
//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
//
//                        // 날짜 형식으로 바꾸기
//                        Date date = simpleDateFormat.parse(twoWeeksAgoDate);
//
//                        afterOneDayDate = m_SimpleDateFormat.format(date);
//
//                        Log.e("DateTime", "Today Date : " + afterOneDayDate);
//
//                        textDate.setText(afterOneDayDate);
//
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//
//                        Toast.makeText(DateTimeActivity.this, "시간 변환 오류", Toast.LENGTH_SHORT).show();
//                    }
                }
            }
        });
    }


}
