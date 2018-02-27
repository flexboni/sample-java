package com.example.gbkim.gubonny;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gbkim.gubonny.Adapter.Adapter_DateCustomDialog;
import com.example.gbkim.gubonny.Adapter.RecyclerViewAdapter;
import com.example.gbkim.gubonny.Listener.RecyclerItemClickListener;

import java.util.ArrayList;

public class DateCustomDialogActivity extends AppCompatActivity {

    private RecyclerView rv_dcd;
    private LinearLayoutManager RecyclerViewLayoutManager;
    private ArrayList<String> title;
    private Button btn_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_custom_dialog);

        btn_date = findViewById(R.id.btn_date);

        rv_dcd = findViewById(R.id.rv_dcd);
        RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_dcd.setLayoutManager(RecyclerViewLayoutManager);

        addItems(); // 카드뷰에 데이터 넣기

        event(); // 각종 이벤트

        Adapter_DateCustomDialog adapter = new Adapter_DateCustomDialog(getApplicationContext(), title);
        rv_dcd.setAdapter(adapter);
    }

    private void event() {
        // RecyclerView 아이템 선택 이벤트
        rv_dcd.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), rv_dcd, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView tv_cardItem = view.findViewById(R.id.tvTitle);
                tv_cardItem.setText("눌렀쯤");
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        // 날짜 다이얼로그
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });
    }

    private void addItems() {
        title = new ArrayList<>();

        title.add("BP");
        title.add("HP");
        title.add("MP");
    }

    private void show() {
        final Dialog dialog = new Dialog(getApplicationContext());

        // custom dialog 넣기
        dialog.setContentView(R.layout.custom_dialog);

        // 제목 넣기
        TextView tvTitle = dialog.findViewById(R.id.tv_custom_dia_title);
        tvTitle.setText("네트워크 연결 오류");

        // 내용 넣기
        TextView tvContent = dialog.findViewById(R.id.tv_custom_dia_content);
        tvContent.setText("네트워크가 연결되지 않았습니다.\n네트워크 연결 후에\n이용해 주시기 바랍니다.");

        dialog.show();

        dialog.findViewById(R.id.btn_custom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
