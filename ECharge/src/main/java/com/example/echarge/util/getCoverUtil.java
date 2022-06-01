package com.example.echarge.util;

import java.util.ArrayList;
import java.util.List;

public class getCoverUtil {
    public static String getCover(String urlString){
        if(urlString == null){
            return null;
        }
        String[] urls = urlString.split(";");
        if(urls.length>0){
            return urls[0];
        }
        else{
            return "";
        }
    }
    public static String[] getUrlList(String urlString){
        if(urlString == null){
            return null;
        }
        return urlString.split(";");
    }
}
