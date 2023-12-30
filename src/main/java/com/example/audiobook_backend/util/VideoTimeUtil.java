package com.example.audiobook_backend.util;

import java.util.Date;

/**
 * @author molimark<br />
 * @date: 2023/1/19 23:38<br/>
 * @description: <br/>
 */
public class VideoTimeUtil {
    /**
     * 毫秒时间
     * Long类型时间转换成视频时长
     */
    public static String format(Long time) {
        if (time == null) {
            return null;
        } else {
            Date date = new Date(time);
            long hour = time / (60 * 60 * 1000);
            long minute = (time - hour * 60 * 60 * 1000) / (60 * 1000);
            long second = (time - hour * 60 * 60 * 1000 - minute * 60 * 1000) / 1000;
            return (hour == 0 ? "00" : (hour > 10 ? hour : ("0" + hour))) + ":" + (minute == 0 ? "00" : (minute > 10 ? minute : ("0" + minute))) + ":" + (second == 0 ? "00" : (second > 10 ? second : ("0" + second)));
        }
    }

    /**
     * 时间为秒
     * Long类型时间转换成视频时长
     */
    public static String formatTime(Long time) {
        if (time == null) {
            return null;
        } else {
            Date date = new Date(time);
            long hour = time / (60 * 60);
            long minute = (time - hour * 60 * 60) / 60;
            long second = time - hour * 60 * 60 - minute * 60;
            return (hour == 0 ? "00" : (hour > 10 ? hour : ("0" + hour))) + ":" + (minute == 0 ? "00" : (minute > 10 ? minute : ("0" + minute))) + ":" + (second == 0 ? "00" : (second > 10 ? second : ("0" + second)));
        }
    }
}