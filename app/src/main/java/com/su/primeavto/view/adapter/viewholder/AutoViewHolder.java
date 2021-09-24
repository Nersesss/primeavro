package com.su.primeavto.view.adapter.viewholder;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.su.primeavto.R;
import com.su.primeavto.model.AutoModel;
import com.su.primeavto.util.PicassoHelper;

import static com.su.primeavto.model.AutoModel.appendValuta;

public class AutoViewHolder extends RecyclerView.ViewHolder {
    private ImageView imgPhoto;
    private TextView textName, textPrice;

    public AutoViewHolder(@NonNull View itemView) {
        super(itemView);

        imgPhoto = itemView.findViewById(R.id.img_photo);
        textName = itemView.findViewById(R.id.text_name);
        textPrice = itemView.findViewById(R.id.text_price);
    }

    @SuppressLint("SetTextI18n")
    public void initData(AutoModel autoModel){
        PicassoHelper.setImagePhoto(imgPhoto, autoModel.getPicturePath()
                .replace("gorodavto", "primeavto"));
        textName.setText(autoModel.getName());
        String price = appendValuta(autoModel.getOneDayPrice()).getValue() + " " + autoModel.getOneDayPrice().getName();
        textPrice.setText(price);
    }
}
