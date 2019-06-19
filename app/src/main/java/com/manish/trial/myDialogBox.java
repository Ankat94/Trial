package com.manish.trial;

import android.content.DialogInterface;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class myDialogBox implements DialogInterface.OnClickListener {

    private String key;
    private String[] item;
    private SpannableStringBuilder builder;
    CriteriaAdapter.CriteriaHolder holder;
    Variable value;

    public myDialogBox(String key, String[] values, SpannableStringBuilder builder, CriteriaAdapter.CriteriaHolder holder) {
        this.key = key;
        this.item = values;
        this.builder = builder;
        this.holder = holder;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        {
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
    }
}
