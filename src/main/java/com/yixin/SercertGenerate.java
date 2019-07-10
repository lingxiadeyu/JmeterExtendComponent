package com.yixin;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/*
测试类
 */
public class SercertGenerate {

    public String generate(String[] args){
        String key = args[0];
        String sercert = args[1];
        String method = args[2];
        String path = args[3];

        String gmtdate = getGMTDate();
        String signstr = "date: "+gmtdate+"\n"+"key: "+key+"\n"+"sercert: "+sercert+"\n"+"method: "+method+"\n"+"path: "+path+" HTTP/1.1";
        return signstr;
    }

    public String getGMTDate(){
        DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(Calendar.getInstance().getTime());
    }
}
