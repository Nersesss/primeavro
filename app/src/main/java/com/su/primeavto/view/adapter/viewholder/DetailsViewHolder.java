package com.su.primeavto.view.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.su.primeavto.R;
import com.su.primeavto.model.NameValueModel;

import static com.su.primeavto.util.StringUtil.capitalizeFirstLater;

public class DetailsViewHolder extends RecyclerView.ViewHolder {
    private TextView textValue, textName;

    public DetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        textValue = itemView.findViewById(R.id.text_value);
        textName = itemView.findViewById(R.id.text_name);
    }

    public void initData(NameValueModel nameValueModel){
        textValue.setText(capitalizeFirstLater(nameValueModel.getValue()));
        textName.setText(capitalizeFirstLater(nameValueModel.getName()));
    }
}
