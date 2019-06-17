package com.manish.trial;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Variable {

    private String type;
    private int[] values;
    private String studyType;
    private String parameterName;
    private int minValue;
    private int maxValue;
    private int defaultValue;

    public Variable(JSONObject object) throws JSONException {

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
