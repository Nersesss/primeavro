package com.su.primeavto.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.su.primeavto.OnItemClickListener;
import com.su.primeavto.R;
import com.su.primeavto.view.adapter.viewholder.CategoriesViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {

    private Context context;
    private List<String> list;
    private LayoutInflater inflater;
    private OnItemClickListener<String> listener;
    private int clickedPosition;

    public CategoriesAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public CategoriesAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public void setData(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_of_categories, parent, false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        String name = getItem(position);
        holder.initData(name);
        holder.button.setOnClickListener(v -> {
            onClick(v, name, position);
        });

        holder.button.changeState(false);
        if (position == clickedPosition) {
            holder.button.changeState(true);
        }
    }

    private void onClick(View v, String name, int position) {
        if (listener != null) {
            listener.onClick(v, name, position);
            this.clickedPosition = position;
            notifyDataSetChanged();
        }
    }

    private String getItem(int position) {
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

    public void setOnItemClickListener(OnItemClickListener<String> listener) {
        this.listener = listener;
    }

    public void changeSelectedStare(String name, int firstVisiblePosition) {
        onClick(null, name, firstVisiblePosition);
    }
}
