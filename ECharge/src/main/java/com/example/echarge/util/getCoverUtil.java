package com.example.echarge.util;

public class getCoverUtil {
    public static String getCover(String urlString){
        String[] urls = urlString.split(";");
        if(urls.length>0){
            return urls[0];
        }
        else{
            return "";
        }
    }
}
