package com.example.gbkim.gubonny.Common;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.example.gbkim.gubonny.R;
import com.example.gbkim.gubonny.util.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Dialog_Calendar extends Dialog {
    private Context m_Context;

    @BindView(R.id.iv_dialog_date_time_picker_close)
    ImageView imageTopCloseButton;

    @BindView(R.id.tv_dialog_date_time_picker_today_button)
    TextView imageTodayButton;
    @BindView(R.id.tv_dialog_date_time_picker_title)
    TextView textTopTitle;
    @BindView(R.id.tv_dialog_date_time_picker_date_start_date)
    TextView textStartDate;
    @BindView(R.id.tv_dialog_date_time_picker_date_end_date)
    TextView textEndDate;

    @BindView(R.id.v_dialog_date_time_picker_date_start_indicator)
    View viewStartDateIndicator;
    @BindView(R.id.v_dialog_date_time_picker_date_end_indicator)
    View viewEndDateIndicator;

    @BindView(R.id.cv_dialog_date_time_picker)
    CalendarView calendarView;

    @BindView(R.id.btn_dialog_date_time_picker_ok)
    Button buttonBottomOk;

    private OnClickListener m_OnClickListener;

    private String sTitle;
    private String getTime;
    private boolean bFirstSelect = true;

    public interface OnClickListener {
        void OnOkClickListener(String previousDate, String forwardDate);

        void OnOkClickListener(String todayDate);
    }

    public void setOnClickListener(OnClickListener listener) {
        m_OnClickListener = listener;
    }

    public Dialog_Calendar(@NonNull Context context, String title) {
        super(context);

        this.m_Context = context;
        this.sTitle = title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_date);

        ButterKnife.bind(this);

        init();

        UIEvents();
    }

    private void init() {
        // 다이얼로그 외부 화면 흐리게 표현
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        try {
            setCurrentTime();

        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    private Date getCurrentDate() {
        // Step1. 현재 시간 가져오기.
        long now = System.currentTimeMillis();
        // Step2. Date 생성하기
        return new Date(now);
    }

    @NonNull
    private String getConvertDate(Date date) {
        // Step3. 가져오고 싶은 형식으로 가져오기
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        // 출처: https://liveonthekeyboard.tistory.com/entry/안드로이드-현재-시간-현재-날짜-구하기-SimpleDateFormat [키위남]
        getTime = sdf.format(date);

        return getTime;
    }

    private void UIEvents() {
        calendarView.setOnDayClickListener(eventDay -> {
            Calendar a = eventDay.getCalendar();

            // "yyy-MM-dd" 형식으로 변경
            String selectDate = String.format(Locale.KOREA, "%1$tY-%1$tm-%1$td", a);

            // 시작 일
            if (bFirstSelect) {
                textStartDate.setText(selectDate);

                bFirstSelect = false;

                // 종료 일
            } else {
                String sPrevious = textStartDate.getText().toString();
                boolean bChange = Utils.DateCompare(sPrevious, selectDate);

                if (bChange) {
                    textEndDate.setText(selectDate);

                } else {
                    textStartDate.setText(selectDate);
                    textEndDate.setText(sPrevious);
                }

                bFirstSelect = true;
            }
        });
    }

    // 상단 닫기 버튼 클릭, 하단 버튼 클릭
    @OnClick({R.id.iv_dialog_date_time_picker_close, R.id.btn_dialog_date_time_picker_ok})
    void onClickButton(View view) {
        switch (view.getId()) {
            // 하단 버튼 클릭
            case R.id.btn_dialog_date_time_picker_ok:
                if (m_OnClickListener != null) {
                    String sPrevious = textStartDate.getText().toString();
                    String sForward = textEndDate.getText().toString();

                    if (sPrevious.equals(sForward)) {
                        m_OnClickListener.OnOkClickListener(sForward);

                    } else {
                        m_OnClickListener.OnOkClickListener(sPrevious, sForward);
                    }
                }

                break;
        }

        dismiss();
    }

    // 오늘 버튼 클릭
    @OnClick(R.id.tv_dialog_date_time_picker_today_button)
    void clickTodayButton() {
        try {
            setCurrentTime();

        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }
    }

    private void setCurrentTime() throws OutOfDateRangeException {
        Date date = getCurrentDate();

        calendarView.setDate(date);

        String getTime = getConvertDate(date);

        textStartDate.setText(getTime);
        textEndDate.setText(getTime);


        List<Calendar> calendars = new ArrayList<>();
        calendarView.setSelectedDates(calendars);
    }
}
