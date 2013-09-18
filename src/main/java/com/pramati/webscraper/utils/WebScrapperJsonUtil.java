/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pramati.webscraper.utils;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import com.pramati.webscraper.db.model.ExtractedDataDetails;

public class WebScrapperJsonUtil {

    // Instantiates a new JSON UTIL.
    public WebScrapperJsonUtil() {
    }

    /**
     * This method Converts the Object to JSON sting.
     * 
     * @param createJsonOf the create and dispatch doc OBJ
     * @return the JSON string
     */
    public static String objectToJsonSting(final Object 
            createJsonOf) {

        String temp = null;
        if (createJsonOf != null) {
            temp = JSONSerializer.toJSON(createJsonOf).toString();
        }
        return temp;
    }

    /**
     * This method converts JSON sting to object.
     * 
     * @param jsonString the JSON string
     * @return the abstract service request
     */
    public static Object jsonStingToObject(final Object jsonString, 
            Class cls) {

        if (jsonString != null) {
            JSONObject jo = JSONObject.fromObject(jsonString);
            return JSONObject.toBean(jo, cls);
        } else {
            return null;
        }
    }
    
    
   public static JSONObject toJsonString(ExtractedDataDetails searchResult) throws JSONException {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("htmlWebAddress", searchResult.getUrl());
		jsonObj.put("htmlData", searchResult.getHtmlData());
		jsonObj.put("CreatedDate", searchResult.getCreatedDate());
		jsonObj.put("CreatedBy", searchResult.getCreatedby());
		
		return jsonObj;
	}
}
