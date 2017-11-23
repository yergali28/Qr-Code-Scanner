package kz.zhakhanyergali.qrscanner.CustomWidgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by zhakhanyergali on 11.10.17.
 */

public class CustomTextView extends TextView {

    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context, attrs);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);

        Typeface customFont = selectTypeface(context, textStyle);
        setTypeface(customFont);
    }

    private Typeface selectTypeface(Context context, int textStyle) {

        switch (textStyle) {
            case Typeface.BOLD: // medium
                return FontCache.getTypeface("RobotoMedium.ttf", context);

            case Typeface.NORMAL: // regular
                return FontCache.getTypeface("RobotoRegular.ttf", context);

            default: // light
                return FontCache.getTypeface("RobotoLight.ttf", context);
        }
    }

}
