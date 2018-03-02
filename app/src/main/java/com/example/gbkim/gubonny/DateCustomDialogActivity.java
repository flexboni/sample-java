package com.example.gbkim.gubonny;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class DateCustomDialogActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    private Button btn_date;
    private Button btn_time;
    private Button btn_bp;
    private Button btn_hp;
    private Button btn_mp;
    private Button btn_save;
    private TextView tv_bp;
    private TextView tv_hp;
    private TextView tv_mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_custom_dialog);

        btn_date = findViewById(R.id.btn_date);
        btn_time = findViewById(R.id.btn_time);
        btn_bp = findViewById(R.id.btn_bp);
        btn_hp = findViewById(R.id.btn_hp);
        btn_mp = findViewById(R.id.btn_mp);
        btn_save = findViewById(R.id.btn_save);

        tv_bp = findViewById(R.id.tv_bp);
        tv_hp = findViewById(R.id.tv_hp);
        tv_mp = findViewById(R.id.tv_mp);

        event(); // 각종 이벤트
    }

    private void event() {
        final Calendar cal = Calendar.getInstance();

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dpd = new DatePickerDialog(DateCustomDialogActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        String msg = String.format("%d-%d-%d", year, month + 1, date);
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

        btn_bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(btn_bp);
            }
        });

        btn_hp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(btn_hp);
            }
        });

        btn_mp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(btn_mp);
            }
        });
    }

    private void show(final Button btn) {
        final Dialog dialog = new Dialog(DateCustomDialogActivity.this);

        dialog.setTitle("Set BP");
        dialog.setContentView(R.layout.dialog);

        Button b1 = dialog.findViewById(R.id.button1);
        Button b2 = dialog.findViewById(R.id.button1);

        final NumberPicker np = dialog.findViewById(R.id.numberPicker1);
        np.setMaxValue(100);
        np.setMinValue(0);
        np.setWrapSelectorWheel(true);
        np.setOnValueChangedListener(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn.setText(String.valueOf(np.getValue()));
                dialog.dismiss();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {

    }
}
