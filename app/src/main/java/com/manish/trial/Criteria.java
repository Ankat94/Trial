package com.manish.trial;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Criteria {

    public String type;
    public String text;
    public ArrayList<Variable> variables = new ArrayList<>();

    public Criteria(JSONObject object) throws JSONException {

        this.type = object.getString("type");
        this.text = object.getString("text");
    }
}
