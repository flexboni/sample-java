package boni.sample.java.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import boni.sample.java.R;

/**
 * Created by gbkim on 2018-01-02.
 */

public class CustomListFragment extends ListFragment{

    String countries[] = {"One", "Two", "Three", "Four", "Five", "Six "};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_listview, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, countries);
        setListAdapter(adapter);

    }
}
