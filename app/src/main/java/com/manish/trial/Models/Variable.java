package com.manish.trial.Models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Variable {

    public String type;
    public int[] values;
    public String studyType;
    public String parameterName;
    public int minValue;
    public int maxValue;
    public int defaultValue;
    public int pos = 0;

    public Variable(JSONObject object) throws JSONException {

        Log.d("vvv", object.toString());
        this.type = object.getString("type");

        switch (type) {
            case "value" :
                JSONArray valueArray = object.getJSONArray("values");
                this.values = new int[valueArray.length()];
                for (int i = 0; i < valueArray.length(); i++) {
                    int value = valueArray.getInt(i);
                    this.values[i] = value;
                }
                break;

            case "indicator" :
                this.studyType = object.getString("study_type");
                this.parameterName = object.getString("parameter_name");
                this.minValue = object.getInt("min_value");
                this.maxValue = object.getInt("max_value");
                this.defaultValue = object.getInt("default_value");
                break;
        }
    }
}
