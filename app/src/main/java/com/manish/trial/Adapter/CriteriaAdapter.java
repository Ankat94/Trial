package com.manish.trial.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.manish.trial.Models.Criteria;
import com.manish.trial.Models.Variable;
import com.manish.trial.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class CriteriaAdapter extends RecyclerView.Adapter<CriteriaAdapter.CriteriaHolder> {

    ArrayList<Criteria> criteria;
    @SuppressLint("StaticFieldLeak")
    static Context context;

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
    public void onBindViewHolder(@NonNull final CriteriaHolder holder, final int position) {

        if (criteria.get(position).type.equalsIgnoreCase("variable")) {

            Iterator<Map.Entry<String, Variable>> variableMap = criteria.get(position).variableMap.entrySet().iterator();

            String criteriaText = criteria.get(position).text;

            final SpannableStringBuilder builder = new SpannableStringBuilder();
            builder.append(criteriaText);

            while(variableMap.hasNext()){
                Map.Entry entry = variableMap.next();
                final String key = entry.getKey().toString();
                final Variable value = (Variable) entry.getValue();
                criteriaText = builder.toString();
                int index = criteriaText.indexOf(key);

                switch (value.type) {
                    case "value" :

                        String replace1 = "(" + (value.values[0]) + ")";
                        builder.replace(index,index + key.length(),replace1);
                        builder.setSpan(new ClickableSpan() {
                            @Override
                            public void onClick(View view) {
                                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                                final String[] item = new String[value.values.length];
                                for (int i =0 ; i < value.values.length; i ++) {
                                    item[i] = String.valueOf(value.values[i]);
                                }
                                dialogBuilder.setItems(item, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        String changeText = builder.toString();
                                        int start = changeText.indexOf("(" + item[value.pos] + ")");
                                        start = start + 1;
                                        int end = item[value.pos].length();
                                        value.pos = i;
                                        if (start == -1) {
                                            return;
                                        }
                                        builder.replace(start,start + end,item[i]);
                                        holder.name.setMovementMethod(LinkMovementMethod.getInstance());
                                        holder.name.setText(builder, TextView.BufferType.SPANNABLE);
                                    }
                                });
                                dialogBuilder.create().show();
                            }
                        },index, index + replace1.length(),0);
                        builder.setSpan(new ForegroundColorSpan(Color.BLUE),index, index + replace1.length(),0);

                        break;

                    case "indicator" :

                        String replace2 = "(" + (value.defaultValue) + ")";
                        builder.replace(index,index + key.length(),replace2);
                        builder.setSpan(new ClickableSpan() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onClick(View view) {
                                final AlertDialog.Builder indicatorDialog = new AlertDialog.Builder(context);
                                View layoutView = LayoutInflater.from(context).inflate(R.layout.indiactor_edittext,null);
                                final EditText valueEdit = layoutView.findViewById(R.id.indicator_edit);
                                indicatorDialog.setView(layoutView);
                                AlertDialog dialog = indicatorDialog.create();
                                dialog.setTitle("Enter Value");
                                dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OKAY", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        int indiactorValue = Integer.parseInt(valueEdit.getText().toString());
                                        if (indiactorValue > value.minValue && indiactorValue < value.maxValue) {

                                            String changeText = builder.toString();
                                            int start = changeText.indexOf(String.valueOf(value.defaultValue));
                                            int end = String.valueOf(value.defaultValue).length();
                                            value.defaultValue = indiactorValue;
                                            builder.replace(start,start + end,String.valueOf(value.defaultValue));

                                            holder.name.setMovementMethod(LinkMovementMethod.getInstance());
                                            holder.name.setText(builder, TextView.BufferType.SPANNABLE);
                                        }
                                        else {
                                            Toast.makeText(context,"Please Enter Value Min " + value.minValue + " and Max " + value.maxValue,Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                dialog.show();
                            }
                        },index, index + replace2.length(),0);
                        builder.setSpan(new ForegroundColorSpan(Color.BLUE),index, index + replace2.length(),0);

                        break;
                }
            }

            holder.name.setMovementMethod(LinkMovementMethod.getInstance());
            holder.name.setText(builder, TextView.BufferType.SPANNABLE);
        }
        else {
            holder.name.setText(criteria.get(position).text);
        }


        if ((position + 1) == criteria.size()){
            holder.tag.setVisibility(View.INVISIBLE);
        }
        else {
            holder.tag.setVisibility(View.VISIBLE);
        }
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