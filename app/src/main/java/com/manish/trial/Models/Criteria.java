package com.manish.trial.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Criteria {

    public String type;
    public String text;
    public ArrayList<Variable> variables = new ArrayList<>();
    public Map<String, Variable> variableMap = new HashMap<String,Variable>();

    public Criteria(JSONObject object) throws JSONException {

        this.type = object.getString("type");
        this.text = object.getString("text");

        if (this.type.equalsIgnoreCase("variable")) {

            JSONObject variableObject = object.getJSONObject("variable");
            Iterator<String> key = variableObject.keys();

            while (key.hasNext()) {
                String value = key.next();
                variableMap.put(value, new Variable(variableObject.getJSONObject(value)));
            }


            //variables.add(new Variable(object.getJSONObject("variable")));

            /*
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
            }while (loop);*/

        }
    }
}
