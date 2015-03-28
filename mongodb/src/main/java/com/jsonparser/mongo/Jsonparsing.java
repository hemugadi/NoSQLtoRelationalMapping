package com.jsonparser.mongo;

import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Jsonparsing {
	
	

		public static Properties properties = null;

		public static JSONObject jsonObject = null;

		static {
		    properties = new Properties();
		}

		public static void main(String[] args) {
			

		    try {

		        JSONParser jsonParser = new JSONParser();

		        File file = new File("C:/bank/bankdo.json");

		        Object object = jsonParser.parse(new FileReader(file));

		        jsonObject = (JSONObject) object;
                System.out.println("Main table name=database name");
		        parseJson(jsonObject);

		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		}

		public static void getArray(Object object2) throws ParseException {

		    JSONArray jsonArr = (JSONArray) object2;

		    for (int k = 0; k < jsonArr.size(); k++) {

		        if (jsonArr.get(k) instanceof JSONObject) {
		            parseJson((JSONObject) jsonArr.get(k));
		        } else {
		           // System.out.println(jsonArr.get(k));
		        }

		    }
		}

		public static void parseJson(JSONObject jsonObject) throws ParseException {
		    Set<Object> columns = jsonObject.keySet();
		    HashMap hm = new HashMap();
		  
		    Iterator<Object> iterator = columns.iterator();
		    while (iterator.hasNext()) {
		        Object obj = iterator.next();
		        String table;
		        if (jsonObject.get(obj) instanceof JSONArray) { 
		        	
		        	System.out.println("\ncreat table named="+obj.toString());
		        	
		        	
		            getArray(jsonObject.get(obj));
		        } else {
		            if (jsonObject.get(obj) instanceof JSONObject) {
		            	System.out.println("\ncreat table named="+obj.toString());
		            	
		                parseJson((JSONObject) jsonObject.get(obj)); 
		            } else {
		             // System.out.println(obj.toString() + "=" + jsonObject.get(obj));
		              hm.put(obj.toString() , jsonObject.get(obj)); 
		                
		            }
		        }
		    }
		    Set values = hm.entrySet();
		 // Get an iterator
		 Iterator i = values.iterator();
		 System.out.println(" \ncolumns to the table are="+jsonObject.keySet());
		 
		 System.out.println("values to above columns: ");
		 while(i.hasNext()) {
		 Map.Entry me = (Map.Entry)i.next();
		 System.out.print(me.getKey() + ": ");
		 System.out.println(me.getValue());
		 }
		        
		}

}
