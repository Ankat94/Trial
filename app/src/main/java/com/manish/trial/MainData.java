package com.manish.trial;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainData {

    public int id;
    public String name;
    public String tag;
    public String color;
    public ArrayList<Criteria> criterias = new ArrayList<>();

    public MainData(JSONObject object) throws JSONException {

        this.id = object.getInt("id");
        this.name = object.getString("name");
        this.tag = object.getString("tag");
        this.color = object.getString("color");

    }
}
