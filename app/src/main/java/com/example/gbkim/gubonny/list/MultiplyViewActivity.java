package com.example.gbkim.gubonny.list;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gbkim.gubonny.R;
import com.example.gbkim.gubonny.VO.VO_Model;

import java.util.ArrayList;

public class MultiplyViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiply_view);

        ArrayList<VO_Model> list = new ArrayList<>();
        list.add(new VO_Model((VO_Model.TEXT_TYPE), "Hello. This is the Text-only View Type. Nice to meet you", 0));
        list.add(new VO_Model((VO_Model.IMAGE_TYPE), "Hi. I display a cool image too besides the omnipresent TextView.", R.drawable.img_icon_favor_on));
        list.add(new VO_Model((VO_Model.AUDIO_TYPE), "Hey. Pressing the FAB button will playback an audio file on loop.", R.drawable.img_icon_air));
        list.add(new VO_Model((VO_Model.IMAGE_TYPE), "Hi again. Another cool image here. Which one is better?", R.drawable.img_icon_splash));

        Adapter_MultiViewType adapter_multiViewType = new Adapter_MultiViewType(list, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        RecyclerView recyclerView = findViewById(R.id.rv_multiply_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter_multiViewType);

    }
}
