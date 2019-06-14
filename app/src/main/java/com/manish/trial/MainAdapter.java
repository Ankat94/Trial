package com.manish.trial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {

    ArrayList<MainData> mainData;

    public MainAdapter(ArrayList<MainData> mainData) {
        this.mainData = mainData;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_row,parent,false);
        return new MainHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, int position) {

        holder.mainText.setText(mainData.get(position).name);
        holder.tag.setText(mainData.get(position).tag);

    }

    @Override
    public int getItemCount() {
        return mainData.size();
    }

    public static class MainHolder extends RecyclerView.ViewHolder {

        TextView mainText;
        TextView tag;

        public MainHolder(View itemView) {
            super(itemView);

            mainText = itemView.findViewById(R.id.main_text);
            tag = itemView.findViewById(R.id.main_tag);
        }
    }
}
