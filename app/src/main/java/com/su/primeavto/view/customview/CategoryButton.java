package com.su.primeavto.view.customview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.su.primeavto.R;

public class CategoryButton extends AppCompatTextView {
    public CategoryButton(@NonNull Context context) {
        super(context);
    }

    public CategoryButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CategoryButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void changeState(boolean selected) {
        setBackground(ContextCompat.getDrawable(
                getContext(), selected ? R.drawable.app_color_bg : R.drawable.white_bg_left_radius));
        setTextColor(ContextCompat.getColor(getContext(), selected ? R.color.white : R.color.appColor));
    }
}
