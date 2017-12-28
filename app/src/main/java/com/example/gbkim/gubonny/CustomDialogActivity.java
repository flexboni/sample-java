package com.example.gbkim.gubonny;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustomDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);

        Button button = findViewById(R.id.btn_custom_dia_main);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
    }

    private void show() {

        final Dialog dialog = new Dialog(CustomDialogActivity.this);

        // custom dialog 넣기
        dialog.setContentView(R.layout.custom_dialog);

        // 제목 넣기
        TextView tvTitle = dialog.findViewById(R.id.tv_custom_dia_title);
        tvTitle.setText("네트워크 연결 오류");

        // 내용 넣기
        TextView tvContent = dialog.findViewById(R.id.tv_custom_dia_content);
        tvContent.setText("네트워크가 연결되지 않았습니다.\n네트워크 연결 후에\n이용해 주시기 바랍니다.");

//        // 백버튼, 외부 터치 시 취소 X
//        dialog.setCancelable(false);
//        dialog.setCanceledOnTouchOutside(false);

        dialog.show();

        dialog.findViewById(R.id.btn_custom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}