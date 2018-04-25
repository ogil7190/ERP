package com.mycampusdock.erp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Student item, View view, int pos);
    }

    private final List<Student> items;
    private Context context;
    private final OnItemClickListener listener;

    public StudentsAdapter(Context context, List<Student> items, OnItemClickListener listener) {
        this.items = items;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(context, items.get(position), position, listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, roll;
        private LinearLayout container;

        public ViewHolder(View view) {
            super(view);
            container = view.findViewById(R.id.container);
            name = view.findViewById(R.id.name);
            roll = view.findViewById(R.id.roll);
        }

        public void bind(final Context context, final Student item, final int pos, final OnItemClickListener listener) {
            name.setText(item.getName());
            roll.setText(item.getRoll());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.setMarked(!item.isMarked());
                    if(item.isMarked()){
                        container.setAlpha(0.5f);
                    }
                    else
                        container.setAlpha(1f);
                }
            });
        }
    }
}