package com.su.primeavto.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.su.primeavto.OnItemClickListener;
import com.su.primeavto.R;
import com.su.primeavto.model.NameValueModel;
import com.su.primeavto.view.adapter.viewholder.DetailsViewHolder;

import java.util.ArrayList;
import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsViewHolder> {

    private final Context context;
    private List<NameValueModel> list;
    private final LayoutInflater inflater;
    private OnItemClickListener<NameValueModel> listener;

    public DetailsAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public DetailsAdapter(Context context, List<NameValueModel> list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<NameValueModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_details, parent, false);
        return new DetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder holder, int position) {
        NameValueModel item = getItem(position);
        if (item != null) {
            holder.initData(item);
            holder.itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onClick(v, item, position);
                }
            });
        }
    }

    private NameValueModel getItem(int position) {
        if (list != null && position >= 0 && list.size() > position) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            list = new ArrayList<>();
        }
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener<NameValueModel> listener) {
        this.listener = listener;
    }
}
