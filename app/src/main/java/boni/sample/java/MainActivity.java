package boni.sample.java;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import boni.sample.java.AsyncTask.ActivityAsyncTask;
import boni.sample.java.Calendar.CalendarViewActivity;
import boni.sample.java.DateTime.DateTimeActivity;
import boni.sample.java.NotificationService.FCMActivity;
import boni.sample.java.NotificationService.NoServerNotifiActivity;
import boni.sample.java.Picker.NumberPickerActivity;
import boni.sample.java.ServiceBind.ServiceBindActivity;
import boni.sample.java.SplashImage.SplashActivity;
import boni.sample.java.TakePicture.ActivityTakePicture;
import boni.sample.java.TreeView.TreeView2Activity;
import boni.sample.java.TreeView.TreeViewActivity;
import boni.sample.java.autocomplete.AutoCompleteActivity;
import boni.sample.java.cardview.CardViewActivity;
import boni.sample.java.changebg.ChangeBgActivity;
import boni.sample.java.datecustomdialog.DateCustomDialogActivity;
import boni.sample.java.dialog.CustomDialogActivity;
import boni.sample.java.dialog.DialogBasicActivity;
import boni.sample.java.dialog.TempActivity;
import boni.sample.java.event.EventBasicActivity;
import boni.sample.java.event.MotionEventActivity;
import boni.sample.java.expandable.ExpandableActivity;
import boni.sample.java.filter.FilterActivity;
import boni.sample.java.fragment.FragmentActivity;
import boni.sample.java.gridview.ActivityGridView;
import boni.sample.java.layout.JavaLayoutActivity;
import boni.sample.java.layout.ScrollViewActivity;
import boni.sample.java.list.ListActivity;
import boni.sample.java.list.ListFragmentActivity;
import boni.sample.java.list.LvItemSelectActivity;
import boni.sample.java.list.MultiplyViewActivity;
import boni.sample.java.login.SaveLoginActivity;
import boni.sample.java.receiver.NetworkReceiverActivity;
import boni.sample.java.receiver.ReceiverActivity;
import boni.sample.java.viewpager.ClickableVpActivity;
import boni.sample.java.viewpager.ViewPagerActivity;

public class MainActivity extends AppCompatActivity {

    Map<String, Class<?>> menuActs = new LinkedHashMap<>();
    private ArrayList<String> arDate;
    private ArrayAdapter<String> arA;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addList();    // list 추가

        setAdapter(); // 화면구성, 어탭터 추가

        itemClick();  // 리스트 선택 이벤트
    }

    private void addList() {
        // list 메뉴 명 / 클래스명
        menuActs.put("ReclycerView", CardViewActivity.class);
        menuActs.put(" - CardView", CardViewActivity.class);
        menuActs.put(" - ChangeBackground", ChangeBgActivity.class);
        menuActs.put(" - Filter", FilterActivity.class);
        menuActs.put("JavaCode UI", JavaLayoutActivity.class);
        menuActs.put("ListView", ListActivity.class);
        menuActs.put(" - LvItemSelect", LvItemSelectActivity.class);
        menuActs.put("Event", EventBasicActivity.class);
        menuActs.put(" - MotionEvent", MotionEventActivity.class);
        menuActs.put("Receiver", ReceiverActivity.class);
        menuActs.put(" - NetworkReceiver", NetworkReceiverActivity.class);
        menuActs.put("Dialog", DialogBasicActivity.class);
        menuActs.put(" - CustomeDialog", CustomDialogActivity.class);
        menuActs.put(" - DateCustomDialog", DateCustomDialogActivity.class);
        menuActs.put("SaveLogin", SaveLoginActivity.class);
        menuActs.put("Fragment", FragmentActivity.class);
        menuActs.put(" - CustomListFragment", ListFragmentActivity.class);
        menuActs.put("ViewPager", ViewPagerActivity.class);
        menuActs.put(" - Clickable", ClickableVpActivity.class);
        menuActs.put("Expandable", ExpandableActivity.class);
        menuActs.put("Multiple View Type", MultiplyViewActivity.class);
        menuActs.put("ScrollView", ScrollViewActivity.class);
        menuActs.put("AutoComplete", AutoCompleteActivity.class);
        menuActs.put("Temp", TempActivity.class);
        menuActs.put("Notification", null);
        menuActs.put(" - No_Server_Notification", NoServerNotifiActivity.class);
        menuActs.put(" - FCMNotification", FCMActivity.class);
        menuActs.put("ServiceBind", ServiceBindActivity.class);
        menuActs.put("GridView", ActivityGridView.class);
        menuActs.put("TakePicture", ActivityTakePicture.class);
        menuActs.put("Picker", NumberPickerActivity.class);
        menuActs.put("Splash Image", SplashActivity.class);
        menuActs.put("Tree View", null);
        menuActs.put(" - Tree View1", TreeViewActivity.class);
        menuActs.put(" - Tree View2", TreeView2Activity.class);
        menuActs.put("AsyncTask", ActivityAsyncTask.class);
        menuActs.put("DateTime", DateTimeActivity.class);
        menuActs.put("Calendar", CalendarViewActivity.class);
    }

    private void setAdapter() {
        arDate = new ArrayList<>();
        for (String key : menuActs.keySet()) arDate.add(key);

        arA = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arDate);

        lv = findViewById(R.id.lvMainMenu);
        lv.setAdapter(arA);
    }

    private void itemClick() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, menuActs.get(((ListView) findViewById(R.id.lvMainMenu)).getItemAtPosition(position)));
                startActivity(intent);
            }
        });
    }
}

