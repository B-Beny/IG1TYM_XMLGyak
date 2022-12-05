package IG1TYM;

import java.text.ParseException;
import java.util.Iterator;
import java.util.Map;
import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONParseIG1TYM {

	public static void main(String[] args) throws ParseException, FileNotFoundException, IOException, org.json.simple.parser.ParseException {
        
        Object obj = new JSONParser().parse(new FileReader("vizsgakJSONIG1TYM.json"));
        JSONObject jObject = (JSONObject) obj;
        try {
            FileWriter myWriter = new FileWriter("JSONWRITER.txt");
            JSONArray jArray = (JSONArray) jObject.get("vizsgak");
            System.out.println(ja);
		    Iterator itr = jArray.iterator();
	          
		    while (itr.hasNext()) 
	        {
		    	Iterator<Map.Entry> itr1 = ((Map) itr.next()).entrySet().iterator();
	            while (itr1.hasNext()) {
	                Map.Entry pair = itr1.next();
	                myWriter.write(pair.getKey() + " : " + pair.getValue());
	                myWriter.write("\n");
	            }
	        }
		    myWriter.close();
        } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}

	}
}