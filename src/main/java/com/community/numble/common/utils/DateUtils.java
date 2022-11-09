package com.community.numble.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public DateUtils(){
        throw new RuntimeException("생성자 금지");
    }

    public static String format(LocalDateTime dateTime, String format){
        return dateTime.format(DateTimeFormatter.ofPattern(format));
    }

}
