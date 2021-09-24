package com.su.primeavto;

import android.view.View;

public interface OnItemClickListener <T>{
    void onClick(View v, T t, int position);
}
