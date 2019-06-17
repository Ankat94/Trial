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

        if (this.type.equalsIgnoreCase("variable")) {

            int start = 0;
            boolean loop;
            do {
                int index = this.text.indexOf("$",start + 1);
                if (index > start) {
                    start = index;

                    String s = this.text.substring(start, start + 2);
                    JSONObject variableObject = object.getJSONObject("variable");
                    JSONObject data = variableObject.getJSONObject(s);
                    Variable variable = new Variable(data);
                    variables.add(variable);
                    loop = true;
                }
                else {
                    loop = false;
                }
            }while (loop);

        }
    }
}
