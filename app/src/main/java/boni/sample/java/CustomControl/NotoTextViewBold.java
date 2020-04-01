package boni.sample.java.CustomControl;

import android.content.Context;
import android.graphics.Typeface;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

public class NotoTextViewBold extends AppCompatTextView {

    public NotoTextViewBold(Context context) {
        super(context);
        setType(context);
    }

    public NotoTextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        setType(context);
    }

    public NotoTextViewBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setType(context);
    }

    private void setType(Context context) {
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "NotoSansKR-Bold-Hestia.otf"));
    }
}
