package com.api.search.norman.utils;

import android.content.Context;
import android.util.JsonReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by m.normansyah on 02/10/2015.
 * this class help building the object
 */
public class FactoryHelper {
    private static JsonReader mJsonReader;

    protected static Reader getJsonExample(Context mContext) throws IOException{
//        return new InputStreamReader(mContext.getAssets().open("data.json"),"UTF-8");
        return new InputStreamReader(mContext.getAssets().open("data_05_10_2015.json"),"UTF-8");
    }

    protected static JsonReader onJsonReaderCreate(Reader reader){
        if(mJsonReader == null)
            mJsonReader = new JsonReader(reader);
        return  mJsonReader;
    }

    public static boolean onJsonReaderClose(){
        if(mJsonReader == null)
            return false;

        mJsonReader = null;
        return true;
    }
}
