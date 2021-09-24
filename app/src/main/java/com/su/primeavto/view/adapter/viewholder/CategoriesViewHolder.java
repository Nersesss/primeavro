package com.su.primeavto.view.adapter.viewholder;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.su.primeavto.R;
import com.su.primeavto.util.StringUtil;
import com.su.primeavto.view.customview.CategoryButton;

import static com.su.primeavto.util.StringUtil.capitalizeFirstLater;

public class CategoriesViewHolder extends RecyclerView.ViewHolder {

    public CategoryButton button;

    public CategoriesViewHolder(@NonNull View itemView) {
        super(itemView);
        button = itemView.findViewById(R.id.button_categories);
    }

   public void initData(String name){
       button.setText(capitalizeFirstLater(name));
   }
}
