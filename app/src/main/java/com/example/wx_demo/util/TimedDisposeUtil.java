package com.example.wx_demo.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimedDisposeUtil {


    @SuppressLint("SimpleDateFormat")
    public static String timestampToFormatTime(String timeStamp){
        SimpleDateFormat sdf;//这个是你要转成后的时间的格式
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd = sdf.format(new Date(Long.parseLong(timeStamp)));   // 时间戳转换成时间
        return sd;
    }

    public static Calendar timeToSpecified(String time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar= Calendar.getInstance();
        try {
            calendar.setTime(formatter.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    public static String dateTimeStamp(String date_str,String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


}
