package com.manish.trial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.manish.trial.Adapter.CriteriaAdapter;
import com.manish.trial.Models.MainData;

public class CriteriaActivity extends AppCompatActivity {

    public static MainData mainData;

    RecyclerView criteriaRecycele;
    TextView title,tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criteria);

        criteriaRecycele = findViewById(R.id.criteria_recycle);
        title = findViewById(R.id.criteria_main_title);
        tag = findViewById(R.id.criteria_main_tag);

        title.setText(mainData.name);
        tag.setText(mainData.tag);

        switch (mainData.color) {
            case "green":
                tag.setTextColor(ContextCompat.getColor(this,R.color.green));
                break;

            case "red":
                tag.setTextColor(ContextCompat.getColor(this,R.color.red));
                break;
        }

        CriteriaAdapter criteriaAdapter = new CriteriaAdapter(mainData.criterias,this);
        criteriaRecycele.setHasFixedSize(true);
        criteriaRecycele.setLayoutManager(new LinearLayoutManager(this));
        criteriaRecycele.setAdapter(criteriaAdapter);
    }
}
