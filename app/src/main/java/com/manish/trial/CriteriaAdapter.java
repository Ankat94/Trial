package com.manish.trial;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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

            Iterator<Map.Entry<String,Variable>> variableMap = criteria.get(position).variableMap.entrySet().iterator();

            String criteriaText = criteria.get(position).text;

            final SpannableStringBuilder builder = new SpannableStringBuilder();
            builder.append(criteriaText);

            while(variableMap.hasNext()){
                Map.Entry entry = variableMap.next();
                final String key = entry.getKey().toString();
                final Variable value = (Variable) entry.getValue();
                criteriaText = builder.toString();
                int index = criteriaText.indexOf(key);
                if (value.type.equals("value")) {
                    String replaceText = "(" + String.valueOf(value.values[0]) + ")";
                    builder.replace(index,index + key.length(),replaceText);
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
                                    int start = changeText.indexOf(item[value.pos]);
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
                    },index, index + replaceText.length(),0);
                    builder.setSpan(new ForegroundColorSpan(Color.BLUE),index, index + replaceText.length(),0);
                }

            }

//            SpannableStringBuilder builder = holder.createSpan(criteria.get(position).text, criteria.get(position));
            holder.name.setMovementMethod(LinkMovementMethod.getInstance());
            holder.name.setText(builder, TextView.BufferType.SPANNABLE);
        }
        else {
            holder.name.setText(criteria.get(position).text);
        }

//        holder.name.setText(criteria.get(position).text);

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

        public SpannableStringBuilder createSpan(String text, Criteria criteria) {
            //int pos = 0;
            int start = 0;
            boolean loop;

            SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
            do {

//                loop = false;
//                int index = text.indexOf("$", start);
//                String[] dollors = text.split("$");
//                if(dollors != null && dollors.length > 0){
//
//                }
//                if (criteria.variables.get(pos).type.equalsIgnoreCase("value") && index != -1) {
////                    stringBuilder.append(text,start,index + 2);
//                    Iterator<Map.Entry<String,Variable>> variableMap = criteria.variableMap.entrySet().iterator();
//
//                    while(variableMap.hasNext()){
//                        Map.Entry entry = variableMap.next();
//                        String key = entry.getKey().toString();
//                        Variable value = (Variable) entry.getValue();
//
//                        String criteriaText = criteria.text;
//
//                        criteriaText.replace(key,String.valueOf(value.values.get(0)));
//
//
//                    }
//
//                    final ArrayList<Integer> values = criteria.variables.get(pos).values;
//                    String replaceString = "(" + values + ")";
//                    stringBuilder.replace(index, index + 2, replaceString);
//                    stringBuilder.setSpan(new ClickableSpan() {
//                        @Override
//                        public void onClick(View view) {
//                            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
//                            String[] items = new String[values.size()];
//                            for (int i=0; i < items.length; i++) {
//                                items[i] = String.valueOf(values.get(0));
//                            }
//                            alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//
//                                }
//                            });
//                            AlertDialog dialog = alertBuilder.create();
//                            dialog.show();
//                        }
//                    },index, index + replaceString.length(), 0);
//                    stringBuilder.setSpan(new ForegroundColorSpan(Color.BLUE),index,index + replaceString.length(),0);
//
//                    start = index + 2;
//                    stringBuilder.append(text,start,text.length());
//                    loop = true;
//                } else if (index == -1) {
//                    stringBuilder.append(text,start,text.length());
//                    loop = false;
//                }


            }while (false);

            return stringBuilder;
        }
    }
}




//    String text = criteria.get(position).text;
//
//
//    int start = 0;
//    int textPos = 0;
//    boolean loop;
//    int endIndex;
//
//    SpannableStringBuilder builder = new SpannableStringBuilder();
//
//            do {
//
//                    int index = text.indexOf("$",start + 1);
//
//                    if (index > start) {
//                    loop = true;
//
//                    builder.append(text.substring(start,index + 2));
//
//                    String variableType = criteria.get(position).variables.get(0).type;
//
//                    switch (variableType) {
//                    case "value" :
//
//final int[] values = criteria.get(position).variables.get(textPos).values;
//        endIndex = index + ("(" + values[0] + ")").length();
//        builder.replace(index,index + 2, "(" + values[0] + ")");
//        builder.setSpan(new ClickableSpan() {
//@Override
//public void onClick(View view) {
//        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
//        String[] items = new String[values.length];
//        for (int i=0; i < items.length; i++) {
//        items[i] = String.valueOf(values[i]);
//        }
//        alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialogInterface, int i) {
//
//        }
//        });
//        AlertDialog dialog = alertBuilder.create();
//        dialog.show();
//        }
//        },index, endIndex,0);
//        builder.setSpan(new ForegroundColorSpan(Color.BLUE),index,endIndex,0);
//        break;
//
//        case "indicator" :
//
//        int deafultValue = criteria.get(position).variables.get(textPos).defaultValue;
//        endIndex = index + ("(" + deafultValue + ")").length();
//        builder.replace(index,index + 2, "(" + deafultValue + ")");
//        builder.setSpan(new ClickableSpan() {
//@Override
//public void onClick(View view) {
//        Toast.makeText(context,"Create Dialog", Toast.LENGTH_SHORT).show();
//        }
//        },index,endIndex,0);
//        builder.setSpan(new ForegroundColorSpan(Color.BLUE),index,endIndex,0);
//        break;
//        }
//
//        holder.name.setMovementMethod(LinkMovementMethod.getInstance());
//        holder.name.setText(builder, TextView.BufferType.SPANNABLE);
//        start = index + 2;
//        }
//        else {
//        loop = false;
//        builder.append(text.substring(start + 2));
//        }
//
//        textPos = textPos + 1;
//
//        } while (loop);