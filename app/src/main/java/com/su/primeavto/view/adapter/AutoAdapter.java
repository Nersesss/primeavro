package com.su.primeavto.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.su.primeavto.OnItemClickListener;
import com.su.primeavto.R;
import com.su.primeavto.model.AutoModel;
import com.su.primeavto.view.adapter.viewholder.AutoViewHolder;

import java.util.ArrayList;
import java.util.List;

public class AutoAdapter extends RecyclerView.Adapter<AutoViewHolder> {

    private final Context context;
    private List<AutoModel> list;
    private final LayoutInflater inflater;
    private OnItemClickListener<AutoModel> listener;

    public AutoAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public AutoAdapter(Context context ,List<AutoModel> list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<AutoModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_of_cars, parent, false);
        return new AutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AutoViewHolder holder, int position) {
        AutoModel item = getItem(position);
        if (item != null) {
            holder.initData(item);
            holder.itemView.setOnClickListener(v -> {
                if (listener != null){
                    listener.onClick(v, item, position);
                }
            });
        }
    }

    private AutoModel getItem(int position) {
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

    public void setOnItemClickListener(OnItemClickListener<AutoModel> listener){
        this.listener = listener;
    }
}
