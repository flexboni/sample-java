package boni.sample.java.event;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import boni.sample.java.R;

import java.util.HashMap;
import java.util.Map;

public class MotionEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_event);

        final Map<Integer, TextView> tvMap = new HashMap<>();
        tvMap.put(0, findViewById(R.id.tvOne));
        tvMap.put(1, findViewById(R.id.tvTwo));
        tvMap.put(2, findViewById(R.id.tvThree));
        tvMap.put(3, findViewById(R.id.tvFour));

        ConstraintLayout layout = findViewById(R.id.layoutbg);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                handleTouch(event);
                return true;
            }

            private void handleTouch(MotionEvent motionEvent) {

                int pointerCount = motionEvent.getPointerCount();

                for (int i = 0; i < pointerCount; i++) {
                    int id = motionEvent.getPointerId(i);
                    String actionString = CMotionEvent.getActionString(motionEvent.getActionMasked());
                    String touchStatus = String.format(
                            "Action: %s Index: %d ID: %d X: %d Y: %d", actionString,
                            motionEvent.getActionIndex(), id, (int) motionEvent.getX(i), (int) motionEvent.getY(i));

                    try {
                        tvMap.get(id).setText(touchStatus);
                    } catch (Exception e) {
                    }
                }
            }
        });

    }
}
