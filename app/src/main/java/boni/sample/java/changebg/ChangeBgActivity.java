package boni.sample.java.changebg;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import boni.sample.java.Adapter.Adapter_Change_Bg;
import boni.sample.java.Listener.Listener_Change_Bg;
import boni.sample.java.R;

import java.util.ArrayList;

public class ChangeBgActivity extends AppCompatActivity implements Listener_Change_Bg {

    private RecyclerView recyclerview;
    private Adapter_Change_Bg adapter;
    private ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_bg);

        recyclerview = findViewById(R.id.rv_change_Bg);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);

        items = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            items.add("TextView_" + i);
        }

        adapter = new Adapter_Change_Bg(items);
        recyclerview.setAdapter(adapter);

        adapter.setClickListener(this);
    }

    @Override
    public void itemClicked(View view, int position) {
        Toast.makeText(getApplicationContext(), items.get(position), Toast.LENGTH_SHORT).show();
    }
}
