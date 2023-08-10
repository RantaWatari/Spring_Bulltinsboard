package com.tuyano.springboot;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeGenerate {

        public static String TimeToString(LocalDateTime time){
        	DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分ss秒");
        	String modifiedtime = timeFormat.format(time); 

        return modifiedtime;

        }
        
        public static LocalDateTime now(){
        	LocalDateTime now_time = LocalDateTime.now();

        return now_time;

        }
        
        
        public static String nowToString(){
            LocalDateTime now_time = LocalDateTime.now();
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分ss秒");
            String modifiedtime = timeFormat.format(now_time); 

        return modifiedtime;

        }
}
