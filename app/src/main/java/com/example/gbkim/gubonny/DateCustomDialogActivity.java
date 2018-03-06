package com.example.gbkim.gubonny;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.gbkim.gubonny.VO.VO_Scale;

import java.util.ArrayList;
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
    private int btn_position = 0;
    private int changBtnPosition = 0;

    private VO_Scale vo_scale;
    private ArrayList<Integer> default_scale;


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

        getDate();

        setDate();

        event(); // 각종 이벤트
    }

    private void getDate() {
        // DB에서 가져올 값
        int default_bp = 20;
        int default_hp = 50;
        int default_mp = 3;

        default_scale = new ArrayList<>();
        default_scale.add(default_bp);
        default_scale.add(default_hp);
        default_scale.add(default_mp);
    }

    private void setDate() {
        btn_bp.setText(default_scale.get(0));
        btn_hp.setText(default_scale.get(1));
        btn_mp.setText(default_scale.get(2));
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
                btn_position = 0;

                show(btn_bp, btn_position, default_scale.get(btn_position));
            }
        });

        btn_hp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_position = 1;

                show(btn_hp, btn_position, default_scale.get(btn_position));
            }
        });

        btn_mp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_position = 2;

                show(btn_mp, btn_position, default_scale.get(btn_position));
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_bp.setText(btn_bp.getText());
                tv_hp.setText(btn_hp.getText());
                tv_mp.setText(btn_mp.getText());

            }
        });
    }

    private void show(final Button btn, final int btn_position, final int scale) {
        final Dialog dialog = new Dialog(DateCustomDialogActivity.this);

        dialog.setTitle("Set BP");
        dialog.setContentView(R.layout.number_dialog);

        Button btn_set = dialog.findViewById(R.id.btn_set);
        Button btn_cancel = dialog.findViewById(R.id.btn_cancel);

        final NumberPicker np = dialog.findViewById(R.id.numberPicker1);
        np.setMaxValue(100);
        np.setMinValue(0);
        np.setValue(scale);
        np.setWrapSelectorWheel(true);
        np.setOnValueChangedListener(this);

        btn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn.setText(String.valueOf(np.getValue()));
                dialog.dismiss();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

        ImageView iv_left = dialog.findViewById(R.id.iv_left);
        ImageView iv_right = dialog.findViewById(R.id.iv_right);

        if (btn_position == 0) {
            iv_left.setVisibility(View.GONE);
        } else if (btn_position == 2) {
            iv_right.setVisibility(View.GONE);
        }

        changBtnPosition = btn_position;

        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changBtnPosition--;

                if (btn_position == 1) {
                    show(btn_bp, changBtnPosition, default_scale.get(changBtnPosition));
                } else if (btn_position == 2) {
                    show(btn_hp, changBtnPosition, default_scale.get(changBtnPosition));
                }
            }
        });

        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changBtnPosition++;

                if (btn_position == 0) {
                    show(btn_hp, changBtnPosition, default_scale.get(changBtnPosition));
                } else if (btn_position == 1) {
                    show(btn_mp, changBtnPosition, default_scale.get(changBtnPosition));
                }
            }
        });
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {

    }
}
