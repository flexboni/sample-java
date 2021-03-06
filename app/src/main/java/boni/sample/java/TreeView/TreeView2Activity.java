package boni.sample.java.TreeView;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import boni.sample.java.R;
import boni.sample.java.TreeView.fragment.CustomViewHolderFragment;
import boni.sample.java.TreeView.fragment.FolderStructureFragment;
import boni.sample.java.TreeView.fragment.SelectableTreeFragment;
import boni.sample.java.TreeView.fragment.TwoDScrollingArrowExpandFragment;
import boni.sample.java.TreeView.fragment.TwoDScrollingFragment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class TreeView2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_view2);

        final LinkedHashMap<String, Class<?>> listItems = new LinkedHashMap<>();
        listItems.put("Folder Structure Example", FolderStructureFragment.class);
        listItems.put("Custom Holder Example", CustomViewHolderFragment.class);
        listItems.put("Selectable Nodes", SelectableTreeFragment.class);
        listItems.put("2d scrolling", TwoDScrollingFragment.class);
        listItems.put("Expand with arrow only", TwoDScrollingArrowExpandFragment.class);

        final List<String> list = new ArrayList(listItems.keySet());
        final ListView listview = findViewById(R.id.listview);
        final SimpleArrayAdapter adapter = new SimpleArrayAdapter(this, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener((parent, view, position, id) -> {
            Class<?> clazz = listItems.values().toArray(new Class<?>[]{})[position];

            Intent i = new Intent(TreeView2Activity.this, SingleFragmentActivity.class);
            i.putExtra(SingleFragmentActivity.FRAGMENT_PARAM, clazz);

            TreeView2Activity.this.startActivity(i);
        });
    }

    private class SimpleArrayAdapter extends ArrayAdapter<String> {
        public SimpleArrayAdapter(Context context, List<String> objects) {
            super(context, android.R.layout.simple_list_item_1, objects);

        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    }

}
