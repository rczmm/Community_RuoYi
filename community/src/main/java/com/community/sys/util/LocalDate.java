package com.community.sys.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LocalDate {

    String dateStr;

    public LocalDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dateStr = sdf.format(new Date());
    }

}
