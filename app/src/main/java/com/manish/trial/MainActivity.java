package com.manish.trial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.manish.trial.Adapter.MainAdapter;
import com.manish.trial.Models.MainData;
import com.manish.trial.Utils.Data_Callback;
import com.manish.trial.Utils.ManagerData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView mainRecycle;

    ArrayList<MainData> mainData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainRecycle = findViewById(R.id.main_recycle);

        final MainAdapter mainAdapter = new MainAdapter(mainData,this);
        mainRecycle.setAdapter(mainAdapter);
        mainRecycle.setHasFixedSize(true);
        mainRecycle.setLayoutManager(new LinearLayoutManager(this));

        ManagerData.Get_Call(new Data_Callback() {
            @Override
            public void handler(JSONArray jsonArray) throws JSONException {
                Log.d("Data Check", jsonArray.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject parseData = jsonArray.getJSONObject(i);
                    MainData data = new MainData(parseData);
                    mainData.add(data);
                }
                mainAdapter.notifyDataSetChanged();
            }
        });

    }
}
