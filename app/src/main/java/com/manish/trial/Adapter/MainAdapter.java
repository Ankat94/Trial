package com.manish.trial.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.manish.trial.CriteriaActivity;
import com.manish.trial.Models.MainData;
import com.manish.trial.R;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {

    ArrayList<MainData> mainData;
    Context context;

    public MainAdapter(ArrayList<MainData> mainData, Context context) {
        this.mainData = mainData;
        this.context = context;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_row,parent,false);
        return new MainHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, final int position) {

        holder.mainText.setText(mainData.get(position).name);
        holder.tag.setText(mainData.get(position).tag);

        switch (mainData.get(position).color) {
            case "green":
                holder.tag.setTextColor(ContextCompat.getColor(context,R.color.green));
                break;

            case "red":
                holder.tag.setTextColor(ContextCompat.getColor(context,R.color.red));
                break;
        }

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CriteriaActivity.mainData = mainData.get(position);
                Intent criteriaIntent = new Intent(context,CriteriaActivity.class);
                context.startActivity(criteriaIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mainData.size();
    }

    public static class MainHolder extends RecyclerView.ViewHolder {

        TextView mainText;
        TextView tag;
        ConstraintLayout mainLayout;

        public MainHolder(View itemView) {
            super(itemView);

            mainText = itemView.findViewById(R.id.main_text);
            tag = itemView.findViewById(R.id.main_tag);
            mainLayout = itemView.findViewById(R.id.main_layout);
        }
    }
}
