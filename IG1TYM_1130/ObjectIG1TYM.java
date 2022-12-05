package IG1TYM;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.json.JSONObject.put;

public class ObjectIG1TYM
{
    public static void main(String[] args)
    {
        JSONParser parser = new JSONParser();

        try
        {
            Object obj = parser.parse(new FileReader("JSONIG1TYM.json"));
            JSONObject jsonObject = (JSONObject)obj;
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}