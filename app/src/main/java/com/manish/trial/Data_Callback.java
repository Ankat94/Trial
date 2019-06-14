package com.manish.trial;

import org.json.JSONArray;
import org.json.JSONException;

public interface Data_Callback {

    void handler(JSONArray jsonArray) throws JSONException;
}
