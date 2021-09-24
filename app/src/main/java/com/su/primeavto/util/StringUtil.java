package com.su.primeavto.util;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.su.primeavto.R;

public class StringUtil {

    public static String capitalizeFirstLater(String text) {
        if (text != null && !text.trim().isEmpty()) {
            return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
        }
        return "";
    }

    public static void makeSpannableString(String startText, String endText, CharSequence text, int color) {
        Spannable s = (Spannable) text;
        int start = startText.length();
        int end = start + endText.length();
        s.setSpan(new ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    public static void spannableStringWithFont(
            Context context, String startText,
            String endText, CharSequence text, int colorRecID, int titleFontResID) {
        if (startText == null) {
            startText = "";
        }
        if (endText == null) {
            endText = "";
        }
        Typeface font2 = setFont(context, titleFontResID);
        Typeface font = setFont(context, R.font.roboto);
        Spannable s = (Spannable) text;
        int start = startText.length();
        int end = start + endText.length();
        s.setSpan(new CustomTypefaceSpan("", font), 0, start, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        s.setSpan(new CustomTypefaceSpan("", font2), start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        s.setSpan(new ForegroundColorSpan(
                ContextCompat.getColor(context, colorRecID)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

    }

    public static Typeface setFont(Context context, int fontResID) {
        Typeface typeface;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            typeface = context.getResources().getFont(fontResID);
        } else {
            typeface = ResourcesCompat.getFont(context, fontResID);
        }
        return typeface;
    }


    public static String uppercaseFirst(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }


    public static void setSpannableText(Context context, TextView textView, String first, String second) {
        String text = first + second;
        textView.setText(text, TextView.BufferType.SPANNABLE);
        spannableStringWithFont(context, first, second, textView.getText(),
                R.color.appRed, R.font.roboto_bold);
    }

}
