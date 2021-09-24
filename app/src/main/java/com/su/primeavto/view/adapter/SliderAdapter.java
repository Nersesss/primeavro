package com.su.primeavto.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;
import com.su.primeavto.R;
import com.su.primeavto.util.PicassoHelper;

import java.util.List;

public class SliderAdapter  extends SliderViewAdapter<SliderAdapter.SliderAdapterVH>  {

    private Context context;
    private List<String> list;

    public SliderAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {

        PicassoHelper.setImagePhoto(viewHolder.imageViewBackground, list.get(position));
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return list.size();
    }

    static class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}
