package com.manish.trial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

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

        CriteriaAdapter criteriaAdapter = new CriteriaAdapter(mainData.criterias,this);
        criteriaRecycele.setHasFixedSize(true);
        criteriaRecycele.setLayoutManager(new LinearLayoutManager(this));
        criteriaRecycele.setAdapter(criteriaAdapter);
    }
}
