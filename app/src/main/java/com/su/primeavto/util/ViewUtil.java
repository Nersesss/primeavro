package com.su.primeavto.util;

import android.content.Context;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.recyclerview.widget.RecyclerView;

import com.su.primeavto.R;

public class ViewUtil {


    public static void runLayoutAnimation(final RecyclerView recyclerView, boolean show) {
        if (show) {
            final Context context = recyclerView.getContext();
            final LayoutAnimationController controller =
                    AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

            recyclerView.setLayoutAnimation(controller);
            if (recyclerView.getAdapter() != null)
                recyclerView.getAdapter().notifyDataSetChanged();
            recyclerView.scheduleLayoutAnimation();
        }
    }

}
