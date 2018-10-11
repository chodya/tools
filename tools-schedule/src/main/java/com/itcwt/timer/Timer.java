package com.itcwt.timer;

import com.itcwt.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Timer {

    private ScheduledExecutorService schedule = new ScheduledThreadPoolExecutor(1);

    private Logger logger = LoggerFactory.getLogger(Timer.class);

    /**
     * 计算距离下一个 time 的毫秒数
     * @param time 时间[HH:mm:ss]
     * @return 毫秒
     */
    private long getFixedTimestamp(String time){
        Date currentDate = new Date();
        // 当前时间戳
        long currentTimestamp = currentDate.getTime();
        // 当前日期，字符串
        String currentDateStr = TimeUtil.parseDateToString(currentDate, TimeUtil.PATTERN_yyyy_MM_dd);
        // 下一个time对应时间戳
        long nextTimestamp = TimeUtil.parseStringToDate(currentDateStr + " " + time, TimeUtil.PATTERN_yyyy_MM_dd_HHmmss).getTime();

        long diffTimestamp = nextTimestamp - currentTimestamp;

        return diffTimestamp >=0 ? diffTimestamp : nextTimestamp + TimeUtil.DAY_MILLISECONDS - currentTimestamp;
    }

}
