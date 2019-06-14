package com.manish.trial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CriteriaAdapter extends RecyclerView.Adapter<CriteriaAdapter.CriteriaHolder> {

    ArrayList<Criteria> criteria;
    Context context;

    public CriteriaAdapter(ArrayList<Criteria> criteria, Context context) {
        this.criteria = criteria;
        this.context = context;
    }

    @NonNull
    @Override
    public CriteriaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.criteria_row,parent,false);
        return new CriteriaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CriteriaHolder holder, int position) {

        holder.name.setText(criteria.get(position).text);

    }

    @Override
    public int getItemCount() {
        return criteria.size();
    }

    public static class CriteriaHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView tag;

        public CriteriaHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.criteria_main);
            tag = itemView.findViewById(R.id.criteria_tag);
        }
    }
}
